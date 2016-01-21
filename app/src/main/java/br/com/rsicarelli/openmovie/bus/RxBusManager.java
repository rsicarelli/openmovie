package br.com.rsicarelli.openmovie.bus;

/**
 * Created by rodrigosicarelli on 1/20/16.
 */
public class RxBusManager {
    private static RxBus sInstance;

    public static RxBus getInstance() {
        if (sInstance == null) {
            sInstance = new RxBus();
        }
        return sInstance;
    }
}