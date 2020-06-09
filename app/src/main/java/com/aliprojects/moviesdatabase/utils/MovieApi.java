package com.aliprojects.moviesdatabase.utils;

import com.aliprojects.moviesdatabase.model.ResponseResult;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MovieApi {

    @GET("3/discover/movie")
    public Call<ResponseResult> getMovies(@Query(MovieClient.API_KEY_PARAM) String key);

    @GET("3/movie/popular")
    public Call<ResponseResult> getMostPopularMovies(@Query(MovieClient.API_KEY_PARAM) String key);

    @GET("3/movie/top_rated")
    public Call<ResponseResult> getTopRatedMovies(@Query(MovieClient.API_KEY_PARAM) String key);
}
