package br.com.rsicarelli.openmovie.global;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * Created by rodrigosicarelli on 1/18/16.
 */
public class OpenMovieApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);
    }
}
