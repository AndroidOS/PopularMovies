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

/**
 * Created by user on 5/09/2016.
 */

public class FetchMovieTaskP2 extends AsyncTask<Void, Void, Void> {

    private final String LOG_TAG = FetchMovieTaskP2.class.getSimpleName();

    String movieId;
    String[] youtube = {};
    int moviePos;
    private Detail_ActivityFragment myFragment;
    //private Fragment myFragment;
    //private String mode;
    private String baseUrl;


    public FetchMovieTaskP2(String movieId, int moviePos, Detail_ActivityFragment fragment) {
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

        // If there's no movie ID, there's nothing to look up.  Verify size of params.
//        if (params.length == 0) {
//            return null;
//        }


        // Will contain the raw JSON response as a string.
        String forecastJsonStr = null;

        try {
            // Construct the URL for the TMDB query
            // Possible parameters are avaiable at OWM's forecast API page, at
            // http://openweathermap.org/API#forecast

            //http://api.themoviedb.org/3/movie/271110/videos?api_key=5dd283e7dba19cb8b36ae74ee2e62103


            baseUrl = "http://api.themoviedb.org/3/movie/" + movieId + "/videos?api_key=5dd283e7dba19cb8b36ae74ee2e62103";


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


            MovieDetailsParserP2 myParser = new MovieDetailsParserP2();
            try {
                youtube = myParser.getMovieTrailers(forecastJsonStr);

                Model.movies.get(moviePos).trailers = youtube;
                //Log.v("Trailers", youtube[0] + " " + youtube.length);

//                for (int i =0; i< Model.movies.get(moviePos).trailers.length; i++){
//
//                    Log.v("MovieURL = ", Model.movies.get(moviePos).trailers[i]);
//
//                }


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


        //Model.picURL.clear();

        //Log.v("onPostExecute", "firing");
        myFragment.helper();

        //Log.v("onPostExecute", "firing");
        //myFragment.helper();

        //myFragment.startActivity();
        //myFragment.helper();

        //Intent intent = new Intent(, Detail_Activity.class);

//        Intent intent = new Intent(myFragment.getActivity(), Detail_Activity.class);
//
//
//
//        //startActivity(intent);
//
//        myFragment.startActivity(intent);


    }

}
