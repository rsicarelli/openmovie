
package br.com.rsicarelli.openmovie.data;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class SearchResponse {

    @SerializedName("Search")
    public List<Movie> result = new ArrayList<>();

}
