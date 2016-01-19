package br.com.rsicarelli.openmovie.api;

import java.util.List;

import br.com.rsicarelli.openmovie.data.Movie;

/**
 * Created by rodrigosicarelli on 1/19/16.
 */
public interface SearchMoviesCallback {
    void onMovieFounded(List<Movie> movieList);
    void onMovieNotFounded(String errorMessage);
}
