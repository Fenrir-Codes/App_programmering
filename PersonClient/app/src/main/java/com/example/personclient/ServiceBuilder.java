package com.example.personclient;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceBuilder {


    //hjem: 192.168.0.193
    //skole:  http://(connect to E308 net):8080/PersonWebApi/api/
    //password E308: 98806829
    private static final String URL = "http://192.168.0.227:8080/PersonWebApi/api/";
    private static Retrofit.Builder builder =
            new Retrofit.Builder().baseUrl(URL)
                    .addConverterFactory(GsonConverterFactory.create());

    private static Retrofit retrofit = builder.build();

    public static <S> S buildService(Class<S> serviceType) {
        return retrofit.create(serviceType);
    }
}
