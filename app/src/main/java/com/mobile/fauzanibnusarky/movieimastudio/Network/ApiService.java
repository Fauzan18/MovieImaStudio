package com.mobile.fauzanibnusarky.movieimastudio.Network;

import com.mobile.fauzanibnusarky.movieimastudio.Pojo.ResponseSearch;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {
    @GET("search/movie")
    Call<ResponseSearch> searchMovie(@Query("api_key") String apikey,
                                     @Query("language") String language,
                                     @Query("query")String query);


}
