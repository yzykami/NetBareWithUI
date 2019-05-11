package com.github.megatronking.netbare.sample;

import android.app.Application;
import android.content.Context;

import com.github.megatronking.netbare.NetBare;
import com.github.megatronking.netbare.NetBareUtils;
import com.github.megatronking.netbare.ssl.JKS;

import me.weishu.reflection.Reflection;

public class App extends Application {
    public static String JKS_ALIAS = "NetBareSample";
    private static App sInstance;
    private JKS mJKS;

    public static App getInstance(){
        return sInstance;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
        mJKS = new JKS(this, JKS_ALIAS, JKS_ALIAS.toCharArray(), JKS_ALIAS, JKS_ALIAS,
                JKS_ALIAS, JKS_ALIAS, JKS_ALIAS);
        NetBare.get().attachApplication(this, BuildConfig.DEBUG);
    }
    public JKS getJKS(){
        return mJKS;
    }
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        if (NetBareUtils.isAndroidQ()) {
            Reflection.unseal(base);
        }
    }
}
