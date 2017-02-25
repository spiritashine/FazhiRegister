package com.hujie.fazhiregister.net;

import android.util.Log;

import com.alibaba.fastjson.JSONObject;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by hujie on 2017/2/22.
 */

public class NetUtil {
    private static NetUtil instance;
    private final OkHttpClient client;
    private final Retrofit retrofit;
    private IApi iApi;

    public static NetUtil getInstance(){
        if (instance==null){
            synchronized (NetUtil.class){
                if (instance==null){
                    instance = new NetUtil();
                }
            }
        }
        return instance;
    }

    public NetUtil(){
        client = getClient();
        retrofit = getRetrofit();
    }

    private OkHttpClient getClient(){
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                Log.i("=============", message);
            }
        });
        interceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);
        return new OkHttpClient.Builder().
                addInterceptor(interceptor).
                connectTimeout(20, TimeUnit.SECONDS).
                readTimeout(20,TimeUnit.SECONDS).
                writeTimeout(20,TimeUnit.SECONDS).
                build();
    }

    private Retrofit getRetrofit(){
        return new Retrofit.Builder().
                addCallAdapterFactory(RxJavaCallAdapterFactory.create()).
                addConverterFactory(GsonConverterFactory.create()).
                baseUrl(NetConfig.BASE_URL).
                client(client).
                build();
    }

    public IApi getIApi(){
        if (iApi==null){
            iApi = retrofit.create(IApi.class);
        }
        return iApi;
    }

    public static RequestBody getCodeBody(HashMap<String,Object> map){
        JSONObject params = NetConfig.getBaseParams();
        params.putAll(map);
        params.put("sign",NetConfig.getSign(params));
        return RequestBody.create(NetConfig.TYPEJSON,params.toString());
    }

}
