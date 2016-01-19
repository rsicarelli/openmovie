
package br.com.rsicarelli.openmovie.data;

import com.google.gson.annotations.SerializedName;

public class Movie {
    @SerializedName("Title")
    public String title;

    @SerializedName("Year")
    public String year;

    @SerializedName("imdbID")
    public String id;

    @SerializedName("Poster")
    public String poster;
}
