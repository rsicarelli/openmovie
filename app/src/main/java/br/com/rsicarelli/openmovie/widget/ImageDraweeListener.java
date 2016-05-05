package br.com.rsicarelli.openmovie.widget;

import android.graphics.drawable.Animatable;

import com.facebook.drawee.controller.BaseControllerListener;

import br.com.rsicarelli.openmovie.util.EspressoIdlingResource;

/**
 * Created by rodrigosicarelli on 5/5/16.
 */
public class ImageDraweeListener extends BaseControllerListener {

    @Override
    public void onSubmit(String id, Object callerContext) {
        EspressoIdlingResource.increment();
    }

    @Override
    public void onFinalImageSet(String id, Object imageInfo, Animatable animatable) {
        EspressoIdlingResource.decrement();
    }

    @Override
    public void onFailure(String id, Throwable throwable) {
        EspressoIdlingResource.decrement();
    }
}
