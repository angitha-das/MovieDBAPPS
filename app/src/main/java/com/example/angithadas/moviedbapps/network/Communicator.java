package com.example.angithadas.moviedbapps.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

//Created by angitha.das on 07-11-2016.

public class Communicator {

    private static final String SERVER_URL = "https://api.themoviedb.org/3/movie/";
    private static Retrofit retrofit = null;

    public static Retrofit getClient () {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(SERVER_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
