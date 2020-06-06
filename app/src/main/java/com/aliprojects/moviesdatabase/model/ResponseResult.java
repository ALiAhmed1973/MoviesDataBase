package com.aliprojects.moviesdatabase.model;

import java.util.List;

public class ResponseResult {
    private List<Movie> results;

    public List<Movie> getMovies() {
        return results;
    }

    public void setMovies(List<Movie> movies) {
        this.results = movies;
    }
}
