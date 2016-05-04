package br.com.rsicarelli.openmovie.movie;

import javax.inject.Inject;

import br.com.rsicarelli.openmovie.api.MovieCallback;
import br.com.rsicarelli.openmovie.api.MovieClient;
import br.com.rsicarelli.openmovie.data.MovieResponse;
import br.com.rsicarelli.openmovie.global.OpenMovieApplication;

/**
 * Created by rodrigosicarelli on 1/18/16.
 */
public class MoviePresenter implements MovieContract.UserInteractions, MovieCallback {

    @Inject
    MovieClient movieClient;

    private MovieContract.View movieView;

    public MoviePresenter() {
        OpenMovieApplication.getOpenMovieApplication().getComponent().inject(this);
    }

    public void setMovieView(MovieContract.View movieView) {
        this.movieView = movieView;
    }

    @Override
    public void doSearch(String query) {
        movieView.setProgressIndicator(true);
        movieClient.findMovieById(query, this);
    }


    @Override
    public void onMovieFounded(MovieResponse movie) {
        movieView.setProgressIndicator(false);
        movieView.showResults(movie);
    }

    @Override
    public void onMovieNotFound(String errorMessage) {
        movieView.setProgressIndicator(false);
        movieView.showError(errorMessage);
    }
}
