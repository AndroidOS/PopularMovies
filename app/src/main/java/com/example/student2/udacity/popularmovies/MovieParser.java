package com.example.student2.udacity.popularmovies;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 2/06/2016. JSON Parsing for the TMDB
 */
public class MovieParser {


    List<String> list2 = new ArrayList<String>();
    List<Movie> myMovies = new ArrayList<Movie>();

    public String getMovieTitles(String movieTitlesJson) throws JSONException {

        JSONObject movies = new JSONObject(movieTitlesJson);

        JSONArray moviesTitle = movies.getJSONArray("results");


        list2.clear();

        for (int myindex = 0; myindex < moviesTitle.length(); myindex++) {

            JSONObject titleInfo = moviesTitle.getJSONObject(myindex);

            String movieTitle = titleInfo.getString("original_title");
            String movieDate = titleInfo.getString("release_date");
            String movieDesc = titleInfo.getString("overview");
            Double moviePop = titleInfo.getDouble("vote_average");
            int movieId = titleInfo.getInt("id");
            String movieURL = Model.baseURL + Model.picSizeURL + titleInfo.getString("poster_path");
            list2.add(movieURL);


            Movie myMovie = new Movie(movieTitle, movieURL, movieDesc, moviePop, movieDate, movieId);
            myMovies.add(myMovie);

        }

        Model.picURL = list2;


        Model.movies.clear();
        Model.movies = myMovies;

        return "";


    }


}
