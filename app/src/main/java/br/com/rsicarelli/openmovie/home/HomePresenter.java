package br.com.rsicarelli.openmovie.home;

import java.util.List;

import javax.inject.Inject;

import br.com.rsicarelli.openmovie.api.MovieClient;
import br.com.rsicarelli.openmovie.api.SearchMoviesCallback;
import br.com.rsicarelli.openmovie.data.Movie;
import br.com.rsicarelli.openmovie.global.OpenMovieApplication;

/**
 * Created by rodrigosicarelli on 1/18/16.
 */
public class HomePresenter implements HomeContract.UserInteractions, SearchMoviesCallback {

    @Inject
    MovieClient client;

    private HomeContract.View homeView;

    public HomePresenter() {
        OpenMovieApplication.getOpenMovieApplication().getComponent().inject(this);
    }

    public void setHomeView(HomeContract.View homeView) {
        this.homeView = homeView;
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
