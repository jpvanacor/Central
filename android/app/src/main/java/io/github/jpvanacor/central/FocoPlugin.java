package io.github.jpvanacor.central;

import android.Manifest;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.content.ContextCompat;
import com.getcapacitor.JSObject;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;
import com.getcapacitor.annotation.CapacitorPlugin;

/**
 * Aviso fixo do Central enquanto uma sessão de tempo está rodando.
 * Pomodoro: contagem regressiva até o fim da fase, e o aviso some sozinho quando ela acaba.
 * Cronômetro: contagem progressiva desde o início.
 * Tocar no aviso abre o app na aba Tempo (central://tempo).
 */
@CapacitorPlugin(name = "Foco")
public class FocoPlugin extends Plugin {

    static final String CANAL = "sessao_v1";
    static final int ID = 900003;

    private void criarCanal(Context ctx) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) return;
        NotificationManager nm = (NotificationManager) ctx.getSystemService(Context.NOTIFICATION_SERVICE);
        if (nm == null || nm.getNotificationChannel(CANAL) != null) return;
        NotificationChannel ch = new NotificationChannel(CANAL, "Sessão em andamento", NotificationManager.IMPORTANCE_LOW);
        ch.setDescription("Aviso fixo com o tempo do Pomodoro ou do cronômetro");
        ch.setShowBadge(false);
        ch.setSound(null, null);
        ch.enableVibration(false);
        nm.createNotificationChannel(ch);
    }

    private boolean podeAvisar(Context ctx) {
        if (!NotificationManagerCompat.from(ctx).areNotificationsEnabled()) return false;
        return Build.VERSION.SDK_INT < 33 ||
            ContextCompat.checkSelfPermission(ctx, Manifest.permission.POST_NOTIFICATIONS) == PackageManager.PERMISSION_GRANTED;
    }

    @PluginMethod
    public void mostrar(PluginCall call) {
        Context ctx = getContext();
        JSObject d = call.getData();
        if (!podeAvisar(ctx)) {
            call.resolve();
            return;
        }
        criarCanal(ctx);

        Intent abrir = new Intent(ctx, MainActivity.class);
        abrir.setAction(Intent.ACTION_VIEW);
        abrir.setData(Uri.parse("central://tempo"));
        abrir.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent toque = PendingIntent.getActivity(ctx, ID, abrir, PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE);

        Notification.Builder b = Build.VERSION.SDK_INT >= Build.VERSION_CODES.O
            ? new Notification.Builder(ctx, CANAL)
            : new Notification.Builder(ctx);
        b.setSmallIcon(R.drawable.ic_stat_central)
            .setContentTitle(d.optString("titulo", "Central"))
            .setContentText(d.optString("texto", ""))
            .setContentIntent(toque)
            .setOngoing(true)
            .setOnlyAlertOnce(true)
            .setLocalOnly(true)
            .setCategory("stopwatch")
            .setVisibility(Notification.VISIBILITY_PUBLIC);
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
            b.setPriority(Notification.PRIORITY_LOW);
        }
        try {
            b.setColor(Color.parseColor(d.optString("cor", "#0F62B4")));
        } catch (IllegalArgumentException e) {
            b.setColor(Color.parseColor("#0F62B4"));
        }

        long agora = System.currentTimeMillis();
        long fim = d.optLong("fim", 0);
        long inicio = d.optLong("inicio", 0);
        if (fim > agora) {
            b.setWhen(fim).setShowWhen(true).setUsesChronometer(true).setChronometerCountDown(true);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                b.setTimeoutAfter(fim - agora + 1500);
            }
        } else if (inicio > 0) {
            b.setWhen(inicio).setShowWhen(true).setUsesChronometer(true);
        } else {
            b.setShowWhen(false);
        }

        try {
            NotificationManagerCompat.from(ctx).notify(ID, b.build());
        } catch (SecurityException e) {
            // sem permissão de notificação: o app segue sem o aviso fixo
        }
        call.resolve();
    }

    @PluginMethod
    public void esconder(PluginCall call) {
        NotificationManagerCompat.from(getContext()).cancel(ID);
        call.resolve();
    }
}
