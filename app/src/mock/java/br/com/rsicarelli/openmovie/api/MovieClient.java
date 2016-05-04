package br.com.rsicarelli.openmovie.api;

import android.os.AsyncTask;
import android.support.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.util.List;

import br.com.rsicarelli.openmovie.R;
import br.com.rsicarelli.openmovie.data.Movie;
import br.com.rsicarelli.openmovie.data.MovieResponse;
import br.com.rsicarelli.openmovie.global.OpenMovieApplication;

public class MovieClient implements MovieServiceApi {

    @Override
    public void findMoviesByQuery(String query, @NonNull final SearchMoviesCallback callback) {
        AsyncTask<Void, Void, List<Movie>> asyncTask = new AsyncTask<Void, Void, List<Movie>>() {
            @Override
            protected List<Movie> doInBackground(Void... params) {
                InputStream is = OpenMovieApplication.getOpenMovieApplication()
                        .getResources()
                        .openRawResource(R.raw.movie_result);
                BufferedReader reader = new BufferedReader(new InputStreamReader(is));
                Gson gson = new GsonBuilder()
                        .excludeFieldsWithModifiers(Modifier.FINAL, Modifier.TRANSIENT, Modifier.STATIC)
                        .serializeNulls()
                        .create();
                Type listType = new TypeToken<List<Movie>>() {
                }.getType();
                return gson.fromJson(reader, listType);
            }

            @Override
            protected void onPostExecute(List<Movie> movieList) {
                callback.onMovieFounded(movieList);
            }
        };
        asyncTask.execute();
    }

    @Override
    public void findMovieById(String id, @NonNull final MovieCallback callback) {
        AsyncTask<Void, Void, MovieResponse> asyncTask = new AsyncTask<Void, Void, MovieResponse>() {
            @Override
            protected MovieResponse doInBackground(Void... params) {
                InputStream is = OpenMovieApplication.getOpenMovieApplication()
                        .getResources()
                        .openRawResource(R.raw.movie_detail);
                BufferedReader reader = new BufferedReader(new InputStreamReader(is));
                Gson gson = new GsonBuilder()
                        .excludeFieldsWithModifiers(Modifier.FINAL, Modifier.TRANSIENT, Modifier.STATIC)
                        .serializeNulls()
                        .create();
                return gson.fromJson(reader, MovieResponse.class);
            }

            @Override
            protected void onPostExecute(MovieResponse movieResponse) {
                callback.onMovieFounded(movieResponse);
            }
        };
        asyncTask.execute();
    }
}
