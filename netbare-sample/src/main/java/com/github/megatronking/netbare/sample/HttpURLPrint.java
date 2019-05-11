package com.github.megatronking.netbare.sample;

import android.support.annotation.NonNull;
import android.util.Log;

import com.github.megatronking.netbare.http.HttpIndexedInterceptor;
import com.github.megatronking.netbare.http.HttpInterceptor;
import com.github.megatronking.netbare.http.HttpInterceptorFactory;
import com.github.megatronking.netbare.http.HttpRequestChain;
import com.github.megatronking.netbare.http.HttpResponseChain;

import java.io.IOException;
import java.nio.ByteBuffer;

public class HttpURLPrint extends HttpIndexedInterceptor {
    private final String TAG = "URL";
    public static HttpInterceptorFactory createFactory(){
        return new HttpInterceptorFactory() {
            @NonNull
            @Override
            public HttpInterceptor create() {
                return new HttpURLPrint();
            }
        };
    }

    @Override
    protected void intercept(@NonNull HttpRequestChain chain, @NonNull ByteBuffer buffer, int index) throws IOException {
        if(index == 0){
            Log.i(TAG, "Request: "+chain.request().url());
        }
        chain.process(buffer);
    }

    @Override
    protected void intercept(@NonNull HttpResponseChain chain, @NonNull ByteBuffer buffer, int index) throws IOException {
        chain.process(buffer);
    }
}
