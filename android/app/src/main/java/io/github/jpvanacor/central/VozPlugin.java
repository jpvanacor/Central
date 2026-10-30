package io.github.jpvanacor.central;

import android.Manifest;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import androidx.activity.result.ActivityResult;
import com.getcapacitor.JSArray;
import com.getcapacitor.JSObject;
import com.getcapacitor.PermissionState;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;
import com.getcapacitor.annotation.ActivityCallback;
import com.getcapacitor.annotation.CapacitorPlugin;
import com.getcapacitor.annotation.Permission;
import com.getcapacitor.annotation.PermissionCallback;
import java.util.ArrayList;
import java.util.List;

/**
 * Anotar falando, com o reconhecimento de voz do próprio Android.
 *
 * ouvir({idioma}): na primeira vez pede o microfone; depois escuta dentro do app e manda pelo evento "voz"
 * o andamento ({tipo: pronto | falando | parcial | nivel | processando}, com texto ou nível de 0 a 1).
 * Resolve com {texto, alternativas} quando a pessoa para de falar.
 * Sem o microfone do app (negado, ou a janela de permissão fechada), usa a janela de voz do Google, que tem o microfone dela;
 * também quando o reconhecimento dentro do app não tem português, está ocupado ou não tem acesso ao microfone.
 * Falhas (code): nada, rede, permissao, microfone, idioma, ocupado, indisponivel, cancelado, erro.
 * parar(): termina agora com o que já ouviu. cancelar(): desiste sem anotar.
 */
@CapacitorPlugin(name = "Voz", permissions = { @Permission(alias = "microfone", strings = { Manifest.permission.RECORD_AUDIO }) })
public class VozPlugin extends Plugin {

    private final Handler principal = new Handler(Looper.getMainLooper());
    private SpeechRecognizer reconhecedor;
    private PluginCall chamada;
    private String parcial = "";
    private long ultimoNivel = 0;
    private int repeticao = 0;

    @PluginMethod
    public void disponivel(PluginCall call) {
        JSObject r = new JSObject();
        r.put("disponivel", SpeechRecognizer.isRecognitionAvailable(getContext()) || temJanela());
        r.put("permissao", getPermissionState("microfone").toString());
        call.resolve(r);
    }

    @PluginMethod
    public void ouvir(PluginCall call) {
        if (getPermissionState("microfone") == PermissionState.GRANTED) {
            comecar(call, 0);
        } else {
            // pede sempre: negado de vez, o Android responde na hora e segue para a janela do Google
            requestPermissionForAlias("microfone", call, "depoisDaPermissao");
        }
    }

    @PermissionCallback
    private void depoisDaPermissao(PluginCall call) {
        if (getPermissionState("microfone") == PermissionState.GRANTED) {
            comecar(call, 0);
        } else {
            janela(call);
        }
    }

    @PluginMethod
    public void parar(PluginCall call) {
        principal.post(() -> {
            if (reconhecedor != null) {
                try {
                    reconhecedor.stopListening();
                } catch (Exception e) {
                    terminar(parcial);
                }
            }
        });
        call.resolve();
    }

    @PluginMethod
    public void cancelar(PluginCall call) {
        principal.post(() -> desistir("cancelado"));
        call.resolve();
    }

    @Override
    protected void handleOnPause() {
        // o app saiu da frente no meio da escuta: desiste (a janela do Google não passa por aqui)
        principal.post(() -> {
            if (reconhecedor != null || chamada != null) desistir("cancelado");
        });
    }

    @Override
    protected void handleOnDestroy() {
        principal.post(() -> desistir("cancelado"));
    }

    private Intent intencao(PluginCall call) {
        String idioma = call.getString("idioma", "pt-BR");
        Intent i = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        i.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        i.putExtra(RecognizerIntent.EXTRA_LANGUAGE, idioma);
        i.putExtra(RecognizerIntent.EXTRA_LANGUAGE_PREFERENCE, idioma);
        i.putExtra(RecognizerIntent.EXTRA_PARTIAL_RESULTS, true);
        i.putExtra(RecognizerIntent.EXTRA_MAX_RESULTS, 3);
        i.putExtra(RecognizerIntent.EXTRA_CALLING_PACKAGE, getContext().getPackageName());
        // pausa curta no meio da frase não encerra (o reconhecedor pode ignorar)
        i.putExtra(RecognizerIntent.EXTRA_SPEECH_INPUT_COMPLETE_SILENCE_LENGTH_MILLIS, 1500L);
        i.putExtra(RecognizerIntent.EXTRA_SPEECH_INPUT_POSSIBLY_COMPLETE_SILENCE_LENGTH_MILLIS, 1500L);
        return i;
    }

    private void comecar(PluginCall call, int vez) {
        principal.post(() -> {
            if (vez == 0) desistir("cancelado");
            else limpar();
            if (!SpeechRecognizer.isRecognitionAvailable(getContext())) {
                if (temJanela()) {
                    janela(call);
                } else {
                    call.reject("Reconhecimento de voz indisponível", "indisponivel");
                }
                return;
            }
            chamada = call;
            parcial = "";
            repeticao = vez;
            try {
                reconhecedor = SpeechRecognizer.createSpeechRecognizer(getActivity());
                reconhecedor.setRecognitionListener(new Ouvinte());
                reconhecedor.startListening(intencao(call));
            } catch (Exception e) {
                limpar();
                chamada = null;
                call.reject("Não deu para ouvir", "erro");
            }
        });
    }

    /* o reconhecimento dentro do app não deu: a janela de voz do Google, com a mesma chamada */
    private void paraJanela() {
        PluginCall c = chamada;
        chamada = null;
        limpar();
        if (c == null) return;
        if (temJanela()) {
            janela(c);
        } else {
            JSObject d = new JSObject();
            d.put("parcial", parcial);
            c.reject("Reconhecimento de voz indisponível", "indisponivel", d);
        }
    }

    /* tenta de novo uma vez (o serviço de voz às vezes ainda está fechando a escuta anterior) */
    private boolean repetir() {
        if (repeticao >= 1 || !parcial.trim().isEmpty() || chamada == null) return false;
        final PluginCall c = chamada;
        final int prox = repeticao + 1;
        limpar();
        // a chamada continua pendente: se cancelarem na espera, ela é recusada e não volta a ouvir
        principal.postDelayed(() -> {
            if (chamada == c) comecar(c, prox);
        }, 350);
        return true;
    }

    /* sem o microfone do app: a janela de voz do Google */
    private void janela(PluginCall call) {
        Intent i = intencao(call);
        i.putExtra(RecognizerIntent.EXTRA_PROMPT, "O que anotar?");
        try {
            startActivityForResult(call, i, "voltaDaJanela");
        } catch (ActivityNotFoundException e) {
            bridge.releaseCall(call);
            call.reject("Reconhecimento de voz indisponível", "indisponivel");
        }
    }

    @ActivityCallback
    private void voltaDaJanela(PluginCall call, ActivityResult res) {
        if (call == null) return;
        Intent d = res.getData();
        ArrayList<String> xs = d != null ? d.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS) : null;
        if (res.getResultCode() == Activity.RESULT_OK && xs != null && !xs.isEmpty() && !xs.get(0).trim().isEmpty()) {
            call.resolve(resultado(xs.get(0), xs));
        } else if (res.getResultCode() == Activity.RESULT_OK) {
            call.reject("Não ouvi nada", "nada");
        } else {
            call.reject("Cancelado", "cancelado");
        }
    }

    private boolean temJanela() {
        try {
            List<?> xs = getContext().getPackageManager().queryIntentActivities(new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH), 0);
            return xs != null && !xs.isEmpty();
        } catch (Exception e) {
            return false;
        }
    }

    private JSObject resultado(String texto, List<String> xs) {
        JSObject r = new JSObject();
        r.put("texto", texto.trim());
        JSArray alt = new JSArray();
        if (xs != null) for (String x : xs) alt.put(x);
        r.put("alternativas", alt);
        return r;
    }

    private void evento(String tipo, String texto, Double nivel) {
        JSObject e = new JSObject();
        e.put("tipo", tipo);
        if (texto != null) e.put("texto", texto);
        if (nivel != null) e.put("nivel", nivel);
        notifyListeners("voz", e);
    }

    /* fim com texto (ou com o que já tinha ouvido) */
    private void terminar(String texto) {
        PluginCall c = chamada;
        chamada = null;
        limpar();
        if (c == null) return;
        if (texto != null && !texto.trim().isEmpty()) {
            List<String> xs = new ArrayList<>();
            xs.add(texto);
            c.resolve(resultado(texto, xs));
        } else {
            c.reject("Não ouvi nada", "nada");
        }
    }

    private void falhar(String msg, String code) {
        PluginCall c = chamada;
        chamada = null;
        limpar();
        if (c == null) return;
        JSObject d = new JSObject();
        d.put("parcial", parcial);
        c.reject(msg, code, d);
    }

    private void desistir(String code) {
        if (reconhecedor != null) {
            try {
                reconhecedor.cancel();
            } catch (Exception e) {
                // já tinha parado
            }
        }
        falhar("Cancelado", code);
    }

    private void limpar() {
        if (reconhecedor != null) {
            try {
                reconhecedor.destroy();
            } catch (Exception e) {
                // nada a fazer
            }
            reconhecedor = null;
        }
    }

    private static String primeiro(Bundle b) {
        if (b == null) return "";
        ArrayList<String> xs = b.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
        return xs != null && !xs.isEmpty() && xs.get(0) != null ? xs.get(0) : "";
    }

    private class Ouvinte implements RecognitionListener {

        @Override
        public void onReadyForSpeech(Bundle params) {
            evento("pronto", null, null);
        }

        @Override
        public void onBeginningOfSpeech() {
            evento("falando", null, null);
        }

        @Override
        public void onRmsChanged(float rmsdB) {
            long agora = System.currentTimeMillis();
            if (agora - ultimoNivel < 90) return;
            ultimoNivel = agora;
            double v = Math.max(0, Math.min(1, (rmsdB + 2) / 12.0));
            evento("nivel", null, v);
        }

        @Override
        public void onBufferReceived(byte[] buffer) {}

        @Override
        public void onEndOfSpeech() {
            evento("processando", null, null);
        }

        @Override
        public void onError(int erro) {
            switch (erro) {
                case SpeechRecognizer.ERROR_NO_MATCH:
                case SpeechRecognizer.ERROR_SPEECH_TIMEOUT:
                    terminar(parcial);
                    break;
                case SpeechRecognizer.ERROR_NETWORK:
                case SpeechRecognizer.ERROR_NETWORK_TIMEOUT:
                case SpeechRecognizer.ERROR_SERVER:
                case SpeechRecognizer.ERROR_SERVER_DISCONNECTED:
                case SpeechRecognizer.ERROR_TOO_MANY_REQUESTS:
                    falhar("Sem conexão para entender a voz", "rede");
                    break;
                case SpeechRecognizer.ERROR_INSUFFICIENT_PERMISSIONS:
                    // o app tem o microfone, mas o serviço de voz não: a janela do Google pede o dela
                    if (getPermissionState("microfone") == PermissionState.GRANTED) paraJanela();
                    else falhar("Sem acesso ao microfone", "permissao");
                    break;
                case SpeechRecognizer.ERROR_AUDIO:
                    falhar("O microfone não respondeu", "microfone");
                    break;
                case SpeechRecognizer.ERROR_LANGUAGE_NOT_SUPPORTED:
                case SpeechRecognizer.ERROR_LANGUAGE_UNAVAILABLE:
                    paraJanela();
                    break;
                case SpeechRecognizer.ERROR_RECOGNIZER_BUSY:
                    if (!repetir()) paraJanela();
                    break;
                case SpeechRecognizer.ERROR_CLIENT:
                    if (!repetir()) falhar("Não deu para ouvir", "erro");
                    break;
                default:
                    falhar("Não deu para ouvir", "erro");
            }
        }

        @Override
        public void onResults(Bundle results) {
            String t = primeiro(results);
            terminar(t.trim().isEmpty() ? parcial : t);
        }

        @Override
        public void onPartialResults(Bundle partialResults) {
            String t = primeiro(partialResults);
            if (t.trim().isEmpty() || t.equals(parcial)) return;
            parcial = t;
            evento("parcial", t, null);
        }

        @Override
        public void onEvent(int eventType, Bundle params) {}
    }
}
