package io.github.jpvanacor.central;

import android.os.Bundle;
import com.getcapacitor.BridgeActivity;

public class MainActivity extends BridgeActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        // plugin do próprio app: aviso fixo com o tempo da sessão
        registerPlugin(FocoPlugin.class);
        super.onCreate(savedInstanceState);
    }
}
