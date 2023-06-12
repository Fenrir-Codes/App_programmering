package com.example.personclient;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceBuilder {


    //192.168.0.193
    //"http://localhost:8080/PersonWebApi/api/"
    private static final String URL = "http://192.168.0.193:8080/PersonWebApi/api/";
    private static Retrofit.Builder builder =
            new Retrofit.Builder().baseUrl(URL)
                    .addConverterFactory(GsonConverterFactory.create());

    private static Retrofit retrofit = builder.build();

    public static <S> S buildService(Class<S> serviceType) {
        return retrofit.create(serviceType);
    }
}
