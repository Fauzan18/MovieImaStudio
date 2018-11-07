package com.mobile.fauzanibnusarky.movieimastudio.Network;

import com.mobile.fauzanibnusarky.movieimastudio.BuildConfig;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ConfigRetrofit {
    public static Retrofit retrofit = null;

    public static Retrofit getClient(){
        if (retrofit == null){
        return new Retrofit.Builder()
                .baseUrl(BuildConfig.BASEURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

    }

    return retrofit;

    }
}
