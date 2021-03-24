package com.example.myapplicationtestmm.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIclient {

    //Defining base url
    public static String base_url = "https://picsum.photos/list";

    //retrofit
    public static Retrofit getClient(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(base_url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit;
    }

    public static APIinterface apIinterface(){
        return getClient().create(APIinterface.class);
    }
}
