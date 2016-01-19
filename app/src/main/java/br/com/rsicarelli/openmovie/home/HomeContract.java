package br.com.rsicarelli.openmovie.home;

import java.util.List;

import br.com.rsicarelli.openmovie.data.Movie;

/**
 * Created by rodrigosicarelli on 1/18/16.
 */
public interface HomeContract {

    interface View {
        void showResults(List<Movie> result);

        void setProgressIndicator(boolean active);

        void showError(String errorMessage);
    }

    interface UserInteractions {
        void doSearch(String query);
    }
}
