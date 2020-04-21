package com.shabaka.shabakautils;

import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiService {
    private static ApiService INSTANCE;
    private IApiInterface apiInterface;

    public static final String DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ssZZZZZ";



    private ApiService(IApiInterface apiInterface, String baseUrl) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(new OkHttpClient())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create((new GsonBuilder())
                        .setDateFormat(DATE_FORMAT).create()))
                .build();

        apiInterface = retrofit.create(IApiInterface.class);
    }

    public static ApiService getInstance(IApiInterface apiInterface, String baseUrl) {
        if(INSTANCE == null) INSTANCE = new ApiService(apiInterface, baseUrl);
        return INSTANCE;
    }
        public IApiInterface getApiInterface() {
        return apiInterface;
    }
}
