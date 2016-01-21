
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
    private String poster;

    public String getPoster() {
        if (poster.contains("SX300")) {
            poster = poster.replace("SX300", "SX500");
        }
        return poster;
    }

}
