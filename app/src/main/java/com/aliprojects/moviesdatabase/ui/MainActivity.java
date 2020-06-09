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

import java.util.List;

public class MainActivity extends AppCompatActivity implements MovieAdapter.OnMovieClickListener {

    private static final String TAG = MainActivity.class.getSimpleName();
    public static final String MOVIE_BUNDLE_KEY = "com.aliprojects.moviesdatabase.ui.movie_details";
    private static final String RECYCLERVIEW_STATE_BUNDLE_EXTRA = "com.aliprojects.moviesdatabase.ui.recyclerview_state";
    MovieAdapter movieAdapter;
    MovieViewModel movieViewModel;
    private ActivityMainBinding mainBinding;
    private List<Movie> movies;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = mainBinding.getRoot();
        setContentView(view);


        movieViewModel = new ViewModelProvider(this).get(MovieViewModel.class);
        if (savedInstanceState == null) {
            movieViewModel.getMoviesDataResponse();
        } else {
            //  restoreRecyclerviewScrollPosition();
        }
        movieAdapter = new MovieAdapter(this, this);
        movieAdapter.setStateRestorationPolicy(RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2, RecyclerView.VERTICAL, false);
        mainBinding.movieRv.setLayoutManager(gridLayoutManager);
        mainBinding.movieRv.setAdapter(movieAdapter);

        movieViewModel.getMovies().observe(this, movies -> {
            movieAdapter.setMovieList(movies);
        });


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
            case R.id.action_most_popular:
                movieViewModel.getMostPopularResponse();
                return true;
            case R.id.action_top_rated:
                movieViewModel.getTopRatedResponse();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(RECYCLERVIEW_STATE_BUNDLE_EXTRA, mainBinding.movieRv.getLayoutManager().onSaveInstanceState());
    }


//    @Override
//    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
//        super.onRestoreInstanceState(savedInstanceState);
//        mainBinding.movieRv.getLayoutManager().onRestoreInstanceState(savedInstanceState.getParcelable(RECYCLERVIEW_STATE_BUNDLE_EXTRA));
//    }
////
//    private void restoreRecyclerviewScrollPosition()
//    {
//        if(layoutManagerState!=null) {
//            mainBinding.movieRv.getLayoutManager().onRestoreInstanceState(layoutManagerState);
//            layoutManagerState=null;
//        }
//    }
}
