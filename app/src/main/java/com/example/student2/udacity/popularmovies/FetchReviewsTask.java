package com.example.student2.udacity.popularmovies;


import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

/**
 * Created by user on 10/09/2016.
 */

public class FetchReviewsTask extends AsyncTask<Void, Void, Void> {

    private final String LOG_TAG = FetchMovieTaskP2.class.getSimpleName();

    String movieId;
    String[] youtube = {};
    int moviePos;
    private Detail_ActivityFragment myFragment;
    //private Fragment myFragment;
    //private String mode;
    private String baseUrl;


    public FetchReviewsTask(String movieId, int moviePos, Detail_ActivityFragment fragment) {
        this.movieId = movieId;
        this.moviePos = moviePos;
        myFragment = fragment;
        //this.mode = mode;


        //Log.v(LOG_TAG, "Trailer JSON String: " + movieId);

    }


    @Override
    protected Void doInBackground(Void... params) {
        // These two need to be declared outside the try/catch
        // so that they can be closed in the finally block.
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;


        String forecastJsonStr = null;

        try {


            baseUrl = "http://api.themoviedb.org/3/movie/" + movieId + "/reviews?api_key=5dd283e7dba19cb8b36ae74ee2e62103";


            //String apiKey = "&APPID=" + BuildConfig.OPEN_WEATHER_MAP_API_KEY;
            URL url = new URL(baseUrl);

            // Log.v(LOG_TAG, baseUrl);

            // Create the request to OpenWeatherMap, and open the connection
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            // Read the input stream into a String
            InputStream inputStream = urlConnection.getInputStream();
            StringBuffer buffer = new StringBuffer();
            if (inputStream == null) {
                // Nothing to do.
                return null;
            }
            reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            while ((line = reader.readLine()) != null) {
                // Since it's JSON, adding a newline isn't necessary (it won't affect parsing)
                // But it does make debugging a *lot* easier if you print out the completed
                // buffer for debugging.
                buffer.append(line + "\n");
            }

            if (buffer.length() == 0) {
                // Stream was empty.  No point in parsing.
                return null;
            }
            forecastJsonStr = buffer.toString();
            //Log.v("Trailers", "Forecast JSON String: " + forecastJsonStr);


            MovieReviewsParser myParser = new MovieReviewsParser();
            try {
                List reviews = myParser.getMovieReviews(forecastJsonStr);

                //Model.movies.get(moviePos).trailers = youtube;
                Model.movies.get(moviePos).reviews = reviews;


            } catch (JSONException e) {
                e.printStackTrace();
            }


        } catch (IOException e) {
            Log.e(LOG_TAG, "Error ", e);
            // If the code didn't successfully get the weather data, there's no point in attemping
            // to parse it.
            return null;
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (final IOException e) {
                    Log.e(LOG_TAG, "Error closing stream", e);
                }
            }
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void result) {


        Log.v("onPostExecute", "firing");
        myFragment.helperReview();


    }

}

