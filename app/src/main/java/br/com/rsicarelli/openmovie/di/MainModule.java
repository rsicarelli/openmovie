package br.com.rsicarelli.openmovie.di;

import br.com.rsicarelli.openmovie.home.HomePresenter;
import br.com.rsicarelli.openmovie.movie.MoviePresenter;
import dagger.Module;
import dagger.Provides;

/**
 * Created by rodrigosicarelli on 5/4/16.
 */
@Module
public class MainModule {
    @Provides
    public HomePresenter provideHomePresenter() {
        return new HomePresenter();
    }

    @Provides
    public MoviePresenter provideMoviePresenter(){
        return new MoviePresenter();
    }
}
