package br.com.rsicarelli.openmovie.api;

import com.google.gson.Gson;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by rodrigosicarelli on 1/18/16.
 */
public class RetrofitManager {
    private static final String BASE_URL = "http://www.omdbapi.com";
    private static Retrofit sInstance;

    private RetrofitManager() {
    }

    public static Retrofit getRetrofitInstance() {
        if (sInstance == null) {
            sInstance = init();
        }
        return sInstance;
    }

    private static Retrofit init() {

        return new Retrofit.Builder()
                .client(new OkHttpClient())
                .addConverterFactory(GsonConverterFactory.create(new Gson()))
                .baseUrl(BASE_URL)
                .build();
    }
}
