package com.github.megatronking.netbare.sample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.github.megatronking.netbare.NetBare;
import com.github.megatronking.netbare.NetBareConfig;
import com.github.megatronking.netbare.http.HttpInterceptor;
import com.github.megatronking.netbare.http.HttpInterceptorFactory;
import com.github.megatronking.netbare.ssl.JKS;

import java.io.IOException;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private final int REQUEST_CODE_PREPARE = 1;
    private Button start_btn;
    private ListView summary_lv;
    private NetBare mNetBare;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mNetBare = NetBare.get();
        start_btn = findViewById(R.id.start_btn);
        summary_lv = findViewById(R.id.summary_lv);
        start_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mNetBare.isActive()){
                    mNetBare.stop();
                } else{

                }
            }
        });
    }

    private void prepareNetBare(){
        if(!JKS.isInstalled(this, App.JKS_ALIAS)){
            try {
                JKS.install(this, App.JKS_ALIAS, App.JKS_ALIAS);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return;
        }
        Intent intent = NetBare.get().prepare();
        if(intent != null){
            startActivityForResult(intent, REQUEST_CODE_PREPARE);
            return;
        }
        mNetBare.start(NetBareConfig.defaultHttpConfig(App.getInstance().getJKS(), interceptorFactories()));
    }
    private List<HttpInterceptorFactory> interceptorFactories(){
        HttpInterceptorFactory interceptor =HttpURLPrint.createFactory();
        List<HttpInterceptorFactory> list = null;
        list.add(interceptor);
        return list;
    }
}
