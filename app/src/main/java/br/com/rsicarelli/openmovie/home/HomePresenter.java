package br.com.rsicarelli.openmovie.home;

import java.util.List;

import br.com.rsicarelli.openmovie.api.MovieClient;
import br.com.rsicarelli.openmovie.api.SearchMoviesCallback;
import br.com.rsicarelli.openmovie.data.Movie;

import static com.facebook.common.internal.Preconditions.checkNotNull;

/**
 * Created by rodrigosicarelli on 1/18/16.
 */
public class HomePresenter implements HomeContract.UserInteractions, SearchMoviesCallback {

    private final MovieClient mClient;
    private final HomeContract.View mHomeView;

    public HomePresenter(MovieClient mClient, HomeContract.View mHomeView) {
        this.mClient = checkNotNull(mClient, "Movie client cannot be null");
        this.mHomeView = checkNotNull(mHomeView, "You must pass the HomeContract.View as parameter");
    }

    @Override
    public void doSearch(String query) {
        mHomeView.setProgressIndicator(true);
        mClient.findMoviesByQuery(query, this);
    }

    @Override
    public void onMovieFounded(List<Movie> movieList) {
        mHomeView.setProgressIndicator(false);
        mHomeView.showResults(movieList);
    }

    @Override
    public void onMovieNotFound(String errorMessage) {
        mHomeView.setProgressIndicator(false);
        mHomeView.showError(errorMessage);
    }
}
