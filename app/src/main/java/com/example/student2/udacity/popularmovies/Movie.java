package com.example.student2.udacity.popularmovies;

import java.util.List;

/**
 * Created by user on 14/08/2016.
 */
public class Movie {

    public String[] trailers = {};
    public List<String> reviews;
    String originalTitle;
    String thumbnailURL;
    String plot;
    Double userRating;
    String releaseDate;
    int movieId;


    public Movie(String vTitle, String vURL, String vPlot, Double vRating, String vReleaseDate, int vMovieId) {
        this.originalTitle = vTitle;
        this.thumbnailURL = vURL;
        this.plot = vPlot;
        this.userRating = vRating;
        this.releaseDate = vReleaseDate;
        this.movieId = vMovieId;
    }


}
