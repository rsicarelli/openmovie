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

    private final MovieClient client;
    private final HomeContract.View homeView;

    public HomePresenter(MovieClient client, HomeContract.View homeView) {
        this.client = checkNotNull(client, "Movie client cannot be null");
        this.homeView = checkNotNull(homeView, "You must pass the HomeContract.View as parameter");
    }

    @Override
    public void doSearch(String query) {
        homeView.setProgressIndicator(true);
        client.findMoviesByQuery(query, this);
    }

    @Override
    public void onMovieFounded(List<Movie> movieList) {
        homeView.setProgressIndicator(false);
        homeView.showResults(movieList);
    }

    @Override
    public void onMovieNotFound(String errorMessage) {
        homeView.setProgressIndicator(false);
        homeView.showError(errorMessage);
    }
}
