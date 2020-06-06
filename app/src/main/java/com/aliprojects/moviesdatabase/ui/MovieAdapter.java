package com.aliprojects.moviesdatabase.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.recyclerview.widget.RecyclerView;

import com.aliprojects.moviesdatabase.R;
import com.aliprojects.moviesdatabase.model.Movie;
import com.aliprojects.moviesdatabase.utils.MovieClient;
import com.bumptech.glide.Glide;

import java.net.URL;
import java.util.List;

public class MovieAdapter extends

        RecyclerView.Adapter<MovieAdapter.ViewHolder> {

    private static final String TAG = MovieAdapter.class.getSimpleName();

    private Context context;

    private List<Movie> movieList;

    private OnMovieClickListener onMovieClickListener;


    public MovieAdapter(Context context,
                        OnMovieClickListener onMovieClickListener) {

        this.context = context;


        this.onMovieClickListener = onMovieClickListener;

    }

    @Override

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        Context context = parent.getContext();

        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(R.layout.movie_list_item, parent, false);

        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;

    }

    @Override

    public void onBindViewHolder(ViewHolder holder, int position) {
        Movie item = movieList.get(position);

        if (item.getPosterPath() != null && !item.getPosterPath().isEmpty()) {

            URL url = MovieClient.buildImageUrl(item.getPosterPath());
            Glide.with(context).load(url.toString()).error(R.drawable.ic_launcher_background).into(holder.imageViewMovie);


        }
        holder.bind(item, onMovieClickListener);

    }

    @Override

    public int getItemCount() {
        if (movieList == null) {
            return 0;
        }
        return movieList.size();

    }

    public void setMovieList(List<Movie> movieList) {
        this.movieList = movieList;
        notifyDataSetChanged();
    }

    public interface OnMovieClickListener {

        void onItemClick(Movie movie);

    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageViewMovie;

        public ViewHolder(View itemView) {
            super(itemView);
            imageViewMovie = itemView.findViewById(R.id.movie_img);

        }

        public void bind(final Movie movie,

                         final OnMovieClickListener listener) {

            itemView.setOnClickListener(new View.OnClickListener() {

                @Override

                public void onClick(View v) {

                    listener.onItemClick(movie);

                }

            });

        }

    }

}