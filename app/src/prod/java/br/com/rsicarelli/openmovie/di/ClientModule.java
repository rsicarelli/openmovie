package br.com.rsicarelli.openmovie.di;

import br.com.rsicarelli.openmovie.api.MovieClient;
import dagger.Module;
import dagger.Provides;

/**
 * Created by rodrigosicarelli on 5/4/16.
 */
@Module
public class ClientModule {
    @Provides
    public MovieClient provideMovieClient() {
        return new MovieClient();
    }
}
