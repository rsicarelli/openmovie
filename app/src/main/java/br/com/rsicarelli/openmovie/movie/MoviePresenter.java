package br.com.rsicarelli.openmovie.movie;

import br.com.rsicarelli.openmovie.api.MovieCallback;
import br.com.rsicarelli.openmovie.api.MovieClient;
import br.com.rsicarelli.openmovie.data.MovieResponse;

import static com.facebook.common.internal.Preconditions.checkNotNull;

/**
 * Created by rodrigosicarelli on 1/18/16.
 */
public class MoviePresenter implements MovieContract.UserInteractions, MovieCallback {

    private final MovieClient mClient;
    private final MovieContract.View mMovieView;

    public MoviePresenter(MovieClient mClient, MovieContract.View mMovieView) {
        this.mClient = checkNotNull(mClient, "Movie client cannot be null");
        this.mMovieView = checkNotNull(mMovieView, "You must pass the MovieContract.View as parameter");
    }

    @Override
    public void doSearch(String query) {
        mMovieView.setProgressIndicator(true);
        mClient.findMovieById(query, this);
    }


    @Override
    public void onMovieFounded(MovieResponse movie) {
        mMovieView.setProgressIndicator(false);
        mMovieView.showResults(movie);
    }

    @Override
    public void onMovieNotFound(String errorMessage) {
        mMovieView.setProgressIndicator(false);
        mMovieView.showError(errorMessage);
    }
}
