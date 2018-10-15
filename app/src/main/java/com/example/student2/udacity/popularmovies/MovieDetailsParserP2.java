package com.example.student2.udacity.popularmovies;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by user on 5/09/2016.
 */
public class MovieDetailsParserP2 {

    public String[] getMovieTrailers(String movieTrailersJson) throws JSONException {

        JSONObject movies = new JSONObject(movieTrailersJson);

        JSONArray moviesTitle = movies.getJSONArray("results");


        //String[] youtube = {};

        ArrayList<String> youtube = new ArrayList<String>();

        for (int myindex = 0; myindex < moviesTitle.length(); myindex++) {

            JSONObject titleInfo = moviesTitle.getJSONObject(myindex);

            String movieTrailer = titleInfo.getString("key");

            String youtubeURL = "https://www.youtube.com/watch?v=" + movieTrailer;

            //Log.v("Trailer JSON",youtubeURL );


            youtube.add(youtubeURL);

            //String movieURL = Model.baseURL + Model.picSizeURL + titleInfo.getString("poster_path");


            //Movie myMovie = new Movie(movieTitle, movieURL, movieDesc, moviePop, movieDate, movieId);
            //return youtube;

        }

//        String[] stockArr = new String[stockList.size()];
//        stockArr = stockList.toArray(stockArr);

        String[] stringURL = new String[youtube.size()];
        stringURL = youtube.toArray(stringURL);


//        for (int i =0; i< stringURL.length; i++){
//
//            Log.v("StringURL = ", stringURL[i]);
//
//        }


        return stringURL;


    }
}
