package com.mobile.fauzanibnusarky.movieimastudio.Network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ConfigRetrofit {
    public static Retrofit retrofit = null;

    public static Retrofit getClient(){
        if (retrofit == null){
        return new Retrofit.Builder()
                .baseUrl(CONSTANT.BASEURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

    }

    return retrofit;

    }
}
