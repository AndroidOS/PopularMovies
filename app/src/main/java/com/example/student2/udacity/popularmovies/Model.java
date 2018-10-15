package com.example.student2.udacity.popularmovies;

import java.util.List;

/**
 * Created by user on 2/06/2016.
 */
public class Model {
    static public boolean dataLoaded = false;

    static public String baseURL = "http://image.tmdb.org/t/p/";

    static public String picSizeURL = "w342";

    static public List<String> picURL;

    static public String downloadMode = "popular";

    static public int position = 99;

    static public boolean showReviewFAB = false;


    static public List<Movie> movies;
    static public List<Movie> favouriteMovies;
}
