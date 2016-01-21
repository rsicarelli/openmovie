package br.com.rsicarelli.openmovie.movie;

import br.com.rsicarelli.openmovie.data.MovieResponse;

/**
 * Created by rodrigosicarelli on 1/18/16.
 */
public interface MovieContract {

    interface View {
        void showResults(MovieResponse movie);

        void setProgressIndicator(boolean active);

        void showError(String errorMessage);
    }

    interface UserInteractions {
        void doSearch(String query);
    }

}
