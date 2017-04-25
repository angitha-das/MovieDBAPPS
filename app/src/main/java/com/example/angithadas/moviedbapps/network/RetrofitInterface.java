package com.example.angithadas.moviedbapps.network;


import com.example.angithadas.moviedbapps.model.ServerResponse;

import retrofit2.Call;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;


public interface RetrofitInterface {


    //This method is used for "GET"
    @GET("{category}?language=en-US&page=1")
    Call<ServerResponse> getMovieList(@Path("category") String category,@Query("api_key") String API_KEY);

}
