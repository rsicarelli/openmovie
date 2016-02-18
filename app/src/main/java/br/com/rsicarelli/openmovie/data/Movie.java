
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
        if (poster.contains(PosterSize.MEDIUM_SIZE)) {
            poster = poster.replace(PosterSize.MEDIUM_SIZE, PosterSize.BIG_SIZE);
        }
        return poster;
    }

    private static class PosterSize {
        private static final String MEDIUM_SIZE = "SX300";
        private static final String BIG_SIZE = "SX500";
    }

}
