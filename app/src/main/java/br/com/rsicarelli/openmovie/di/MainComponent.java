package br.com.rsicarelli.openmovie.di;

import javax.inject.Singleton;

import br.com.rsicarelli.openmovie.home.HomeFragment;
import br.com.rsicarelli.openmovie.home.HomePresenter;
import br.com.rsicarelli.openmovie.movie.MovieFragment;
import br.com.rsicarelli.openmovie.movie.MoviePresenter;
import dagger.Component;

/**
 * Created by rodrigosicarelli on 5/4/16.
 */
@Singleton
@Component(modules = {MainModule.class, ClientModule.class})
public interface MainComponent {
    void inject(HomeFragment homeFragment);

    void inject(HomePresenter homePresenter);

    void inject(MoviePresenter moviePresenter);

    void inject(MovieFragment movieFragment);
}
