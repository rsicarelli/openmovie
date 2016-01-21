package br.com.rsicarelli.openmovie.api;

import br.com.rsicarelli.openmovie.data.MovieResponse;

/**
 * Created by rodrigosicarelli on 1/19/16.
 */
public interface MovieCallback {
    void onMovieFounded(MovieResponse movie);
    void onMovieNotFound(String errorMessage);
}
