package br.com.rsicarelli.openmovie.api;

public interface MovieServiceApi {
    void findMoviesByQuery(String query, SearchMoviesCallback callback);
}
