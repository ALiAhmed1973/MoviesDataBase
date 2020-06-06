package com.aliprojects.moviesdatabase.utils;

import com.aliprojects.moviesdatabase.model.ResponseResult;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MovieClient {

    public final static String API_KEY_PARAM = "api_key";
    private static final String BASE_MOVIES_URL = "https://api.themoviedb.org/";
    private static final String API_KEY_VALUE = "630161aff460e2b14689983c03e19a6f";
    private static MovieClient INSTANCE;
    private MovieApi movieApi;

    public MovieClient() {

        Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_MOVIES_URL).addConverterFactory(GsonConverterFactory.create())
                .build();
        movieApi = retrofit.create(MovieApi.class);

    }

    public static MovieClient getINSTANCE() {
        if (INSTANCE == null) {
            INSTANCE = new MovieClient();
        }
        return INSTANCE;
    }


    public Call<ResponseResult> getMovies() {
        return movieApi.getMovies(API_KEY_VALUE);
    }
}
