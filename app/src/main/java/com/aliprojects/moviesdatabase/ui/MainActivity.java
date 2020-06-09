package com.aliprojects.moviesdatabase.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.aliprojects.moviesdatabase.MovieDetailsActivity;
import com.aliprojects.moviesdatabase.R;
import com.aliprojects.moviesdatabase.databinding.ActivityMainBinding;
import com.aliprojects.moviesdatabase.model.Movie;

public class MainActivity extends AppCompatActivity implements MovieAdapter.OnMovieClickListener {

    private static final String TAG = MainActivity.class.getSimpleName();
    public static final String MOVIE_BUNDLE_KEY = "com.aliprojects.moviesdatabase.ui.movie_details";
    MovieAdapter movieAdapter;
    MovieViewModel movieViewModel;
    private ActivityMainBinding mainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = mainBinding.getRoot();
        setContentView(view);


        movieViewModel = new ViewModelProvider(this).get(MovieViewModel.class);
        movieViewModel.getMovies().observe(this, movies -> movieAdapter.setMovieList(movies));


        movieAdapter = new MovieAdapter(this, this);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2, RecyclerView.VERTICAL, false);
        mainBinding.movieRv.setLayoutManager(gridLayoutManager);
        mainBinding.movieRv.setAdapter(movieAdapter);


    }

    @Override
    public void onItemClick(Movie movie) {
        Intent intent = new Intent(this, MovieDetailsActivity.class);
        Bundle bundle = new Bundle();
        bundle.putParcelable(MOVIE_BUNDLE_KEY, movie);
        intent.putExtra(MOVIE_BUNDLE_KEY, bundle);
        startActivity(intent);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.string.most_popular:
                return true;
            case R.string.top_rated_movies:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }
}
