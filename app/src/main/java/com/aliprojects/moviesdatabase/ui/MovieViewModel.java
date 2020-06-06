package com.aliprojects.moviesdatabase.ui;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.aliprojects.moviesdatabase.model.Movie;
import com.aliprojects.moviesdatabase.model.ResponseResult;
import com.aliprojects.moviesdatabase.utils.MovieClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieViewModel extends ViewModel {
    MutableLiveData<List<Movie>> moviesMutableLiveData = new MutableLiveData<>();

    public void getMovies() {
        MovieClient.getINSTANCE().getMovies().enqueue(new Callback<ResponseResult>() {
            @Override
            public void onResponse(Call<ResponseResult> call, Response<ResponseResult> response) {
                moviesMutableLiveData.setValue(response.body().getMovies());
                Log.d("ssssssssssss", response.body().getMovies().get(0).getOriginalTitle());
            }

            @Override
            public void onFailure(Call<ResponseResult> call, Throwable t) {

            }
        });
    }
}
