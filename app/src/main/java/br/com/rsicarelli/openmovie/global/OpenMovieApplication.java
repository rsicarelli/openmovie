package br.com.rsicarelli.openmovie.global;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;

import br.com.rsicarelli.openmovie.di.ClientModule;
import br.com.rsicarelli.openmovie.di.DaggerMainComponent;
import br.com.rsicarelli.openmovie.di.MainComponent;
import br.com.rsicarelli.openmovie.di.MainModule;

/**
 * Created by rodrigosicarelli on 1/18/16.
 */
public class OpenMovieApplication extends Application {

    MainComponent component;
    private static OpenMovieApplication openMovieApplication;

    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);
        openMovieApplication = this;
        if (component == null) {
            component = DaggerMainComponent.builder()
                    .mainModule(new MainModule())
                    .clientModule(new ClientModule())
                    .build();
        }

    }

    public static OpenMovieApplication getOpenMovieApplication() {
        return openMovieApplication;
    }

    public MainComponent getComponent() {
        return component;
    }

    public void setComponent(MainComponent component) {
        this.component = component;
    }
}
