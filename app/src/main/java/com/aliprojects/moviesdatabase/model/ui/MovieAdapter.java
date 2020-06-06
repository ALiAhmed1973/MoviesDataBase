package com.aliprojects.moviesdatabase.model.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.aliprojects.moviesdatabase.R;
import com.aliprojects.moviesdatabase.model.Movie;

import java.util.List;

public class MovieAdapter extends

        RecyclerView.Adapter<MovieAdapter.ViewHolder> {

    private static final String TAG = MovieAdapter.class.getSimpleName();

    private Context context;

    private List<Movie> movieList;

    private OnItemClickListener onItemClickListener;

    public MovieAdapter(Context context, List<Movie> list,

                        OnItemClickListener onItemClickListener) {

        this.context = context;

        this.movieList = list;

        this.onItemClickListener = onItemClickListener;

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

        holder.bind(item, onItemClickListener);

    }

    @Override

    public int getItemCount() {
        if (movieList == null) {
            return 0;
        }
        return movieList.size();

    }

    public interface OnItemClickListener {

        void onItemClick(int position);

    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(View itemView) {

            super(itemView);

        }

        public void bind(final Movie model,

                         final OnItemClickListener listener) {

            itemView.setOnClickListener(new View.OnClickListener() {

                @Override

                public void onClick(View v) {

                    listener.onItemClick(getLayoutPosition());

                }

            });

        }

    }

}