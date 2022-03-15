package com.example.testbri.API;


import android.content.Context;

import com.example.testbri.API.Request.RequestDaftar;
import com.example.testbri.API.Respon.ApiBasic;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.POST;

public class REST_Controller {
    public static String BASE_URL ="https://hookb.in/";
    private static Retrofit retrofit = null;
    private static Context context;
    public static Retrofit getClient() {



        OkHttpClient client = new OkHttpClient.Builder().build();
        GsonBuilder gb = new GsonBuilder();
        gb.registerTypeAdapter(String.class, new StringConverter());
        gb.serializeNulls();

        Gson gson = gb.create();


        retrofit = new Retrofit.Builder()
                .baseUrl(REST_Controller.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))

                .client(getClient(context))
                .build();



        return retrofit;
    }

    public static OkHttpClient getClient(Context context) {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
//        new CustomTrust(builder);
        return builder
                .connectTimeout(10, TimeUnit.MINUTES)
                .readTimeout(10, TimeUnit.MINUTES)
                .writeTimeout(0, TimeUnit.SECONDS)
                .addInterceptor(getLoggingInterceptor())
//                .connectionSpecs(Arrays.asList(ConnectionSpec.MODERN_TLS, ConnectionSpec.CLEARTEXT, ConnectionSpec.COMPATIBLE_TLS))
//                .addInterceptor(getInceptorRequestHeader(context))
                .build();
    }

    public static HttpLoggingInterceptor getLoggingInterceptor() {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(getInterceptorLevel());

        return httpLoggingInterceptor;
    }

    public static HttpLoggingInterceptor.Level getInterceptorLevel() {
        if (BuildConfig.DEBUG) return HttpLoggingInterceptor.Level.BODY;
        else return HttpLoggingInterceptor.Level.NONE;
    }

}
