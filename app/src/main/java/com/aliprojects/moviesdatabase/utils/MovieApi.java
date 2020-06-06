package com.aliprojects.moviesdatabase.utils;

import com.aliprojects.moviesdatabase.model.ResponseResult;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MovieApi {

    @GET("3/discover/movie")
    public Call<ResponseResult> getMovies(@Query("api_key") String key);
}
