package com.aliprojects.moviesdatabase;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.aliprojects.moviesdatabase.databinding.ActivityMovieDetailsBinding;
import com.aliprojects.moviesdatabase.model.Movie;
import com.aliprojects.moviesdatabase.ui.MainActivity;
import com.aliprojects.moviesdatabase.utils.MovieClient;
import com.bumptech.glide.Glide;

import java.net.URL;
import java.util.Objects;

public class MovieDetailsActivity extends AppCompatActivity {

    ActivityMovieDetailsBinding movieDetailsBinding;
    Movie movie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        movieDetailsBinding = ActivityMovieDetailsBinding.inflate(getLayoutInflater());
        View view = movieDetailsBinding.getRoot();
        setContentView(view);
        Intent intent = getIntent();
        if (intent != null) {
            Bundle bundle = intent.getBundleExtra(MainActivity.MOVIE_BUNDLE_KEY);
            movie = Objects.requireNonNull(bundle).getParcelable(MainActivity.MOVIE_BUNDLE_KEY);
        }
        movieDetailsBinding.titleTv.setText(movie.getOriginalTitle());
        movieDetailsBinding.overviewTv.setText(movie.getMovieOverview());
        movieDetailsBinding.ratingTv.setText(String.valueOf(movie.getMovieRating()));
        movieDetailsBinding.releasedateTv.setText(movie.getReleaseDate());

        URL url = MovieClient.buildImageUrl(movie.getPosterPath());
        Glide.with(this).load(url.toString()).error(R.drawable.ic_launcher_background).into(movieDetailsBinding.movieDetailsImg);
    }
}