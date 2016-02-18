package br.com.rsicarelli.openmovie.api;

import android.support.annotation.NonNull;

import java.util.List;

import br.com.rsicarelli.openmovie.data.Movie;
import br.com.rsicarelli.openmovie.data.MovieResponse;
import br.com.rsicarelli.openmovie.data.SearchResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieClient implements MovieServiceApi {

    @Override
    public void findMoviesByQuery(String query, @NonNull final SearchMoviesCallback callback) {
        MovieService movieService = RetrofitManager.getRetrofitInstance().create(MovieService.class);

        Call<SearchResponse> searchResponseCall = movieService.loadMovies(query, SearchValues.TYPE, SearchValues.RETURN_TYPE);
        searchResponseCall.enqueue(new Callback<SearchResponse>() {
            @Override
            public void onResponse(Call<SearchResponse> call, Response<SearchResponse> response) {
                List<Movie> result = response.body().result;
                if (result != null) {
                    callback.onMovieFounded(result);
                } else {
                    callback.onMovieNotFound("There is no movies with this name :(");
                }
            }

            @Override
            public void onFailure(Call<SearchResponse> call, Throwable t) {
                callback.onMovieNotFound("There is no movies with this name :(");
            }
        });
    }

    @Override
    public void findMovieById(String id, @NonNull final MovieCallback callback) {
        MovieService movieService = RetrofitManager.getRetrofitInstance().create(MovieService.class);

        Call<MovieResponse> movieCall = movieService.getMovie(id, SearchValues.PLOT_TYPE, SearchValues.RETURN_TYPE);
        movieCall.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                MovieResponse result = response.body();
                if (result != null) {
                    callback.onMovieFounded(result);
                } else {
                    callback.onMovieNotFound("There is no movie with this id :(");
                }
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {
                callback.onMovieNotFound("There is no movie with this id :(");
            }
        });
    }


    private class SearchValues {
        public static final String TYPE = "movie";
        public static final String RETURN_TYPE = "JSON";
        public static final String PLOT_TYPE = "full";
    }
}
