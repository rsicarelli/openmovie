package br.com.rsicarelli.openmovie.api;

import android.support.annotation.NonNull;

import java.util.List;

import br.com.rsicarelli.openmovie.data.Movie;
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
            public void onResponse(Response<SearchResponse> response) {
                List<Movie> result = response.body().result;
                if (result != null) {
                    callback.onMovieFounded(result);
                } else {
                    callback.onMovieNotFounded("There is no movies with this name :(");
                }
            }

            @Override
            public void onFailure(Throwable t) {
                callback.onMovieNotFounded("There is no movies with this name :(");
            }
        });
    }

    private class SearchValues {
        public static final String TYPE = "movie";
        public static final String RETURN_TYPE = "JSON";
    }
}
