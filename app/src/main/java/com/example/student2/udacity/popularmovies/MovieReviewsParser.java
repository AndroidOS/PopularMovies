package com.example.student2.udacity.popularmovies;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 10/09/2016.
 */
public class MovieReviewsParser {


    //String[] reviews;

    public List<String> getMovieReviews(String movieReviewsJson) throws JSONException {

        JSONObject movies = new JSONObject(movieReviewsJson);

        JSONArray moviesReviews = movies.getJSONArray("results");


//        //String[] youtube = {};
//
        ArrayList<String> reviews = new ArrayList<String>();
//
        for (int myindex = 0; myindex < moviesReviews.length(); myindex++) {
//
            JSONObject titleInfo = moviesReviews.getJSONObject(myindex);
//
            String author = titleInfo.getString("author");
            String content = titleInfo.getString("content");

//
//            String youtubeURL = "https://www.youtube.com/watch?v=" + movieTrailer;
//
            //Log.v("Trailer JSON",content);
//
            String review = author + " " + "\r\n" + content;

            review = review.replaceAll("\\\\r", "");
            review = review.replaceAll("\\\\n", "");

            //String review = author + "\r\n" + content;

            reviews.add(review);
            //Log.v("Trailer JSON",review);


        }

        //Log.v("Trailer JSON"," " + reviews.size());
//
////        String[] stockArr = new String[stockList.size()];
////        stockArr = stockList.toArray(stockArr);
//
//        String[] stringURL = new String[youtube.size()];
//        stringURL = youtube.toArray(stringURL);
//
//
////        for (int i =0; i< stringURL.length; i++){
////
////            Log.v("StringURL = ", stringURL[i]);
////
////        }
//
//
        return reviews;
//
//
//
//


    }
}
