package br.com.rsicarelli.openmovie.api;

import br.com.rsicarelli.openmovie.data.SearchResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by rodrigosicarelli on 1/18/16.
 */
public interface MovieService {

    @GET("/")
    Call<SearchResponse> loadMovies(
            @Query("s") String query,
            @Query("type") String type,
            @Query("r") String returnType
    );

}
