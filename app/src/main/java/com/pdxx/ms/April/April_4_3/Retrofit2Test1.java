package com.pdxx.ms.April.April_4_3;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Administrator on 2018/4/3.
 */

public class Retrofit2Test1 {
    public static JSZPService buildRetrofit() {
        OkHttpClient mOkHttpClient=new OkHttpClient.Builder()
                .connectTimeout(20, TimeUnit.SECONDS)//设置连接超时时间
                .readTimeout(20, TimeUnit.SECONDS)//设置读取超时时间
                .writeTimeout(20, TimeUnit.SECONDS)//设置写入超时时间
                .build();
        //创造retrofit对象
        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://jsapp.ezhupei.com:65486/pdapp/res/jswjw/")
                .addConverterFactory(GsonConverterFactory.create(new Gson()))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        //用retrofit创造代理对象
        return retrofit.create(JSZPService.class);

    }

    public static void main(String arg[]) {
        buildRetrofit();
    }
}
