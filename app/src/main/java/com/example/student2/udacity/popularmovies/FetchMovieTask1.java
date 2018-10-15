package com.example.student2.udacity.popularmovies;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by user on 29/05/2016 based on the Udacity Code.
 */
public class FetchMovieTask1 extends AsyncTask<Void, Void, Void> {

    private final String LOG_TAG = FetchMovieTask1.class.getSimpleName();
    ProgressBar myProgressBar;
    private Context context;


    //save the context recievied via constructor in a local variable
    private MainActivityFragment myFragment;


    public FetchMovieTask1(Context context, ProgressBar myProgressBar, MainActivityFragment myFragment) {
        this.context = context;
        this.myProgressBar = myProgressBar;
        this.myFragment = myFragment;
    }

    @Override
    protected Void doInBackground(Void... params) {
        // These two need to be declared outside the try/catch
        // so that they can be closed in the finally block.
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;

        // Will contain the raw JSON response as a string.
        String forecastJsonStr = null;
        String baseUrl = null;

        try {
            //Log.v(LOG_TAG, "Networking");
            // Construct the URL for the OpenWeatherMap query
            // Possible parameters are avaiable at OWM's forecast API page, at
            // http://openweathermap.org/API#forecast

            if (Model.downloadMode != "popular") {
                //baseUrl = "https://api.themoviedb.org/3/discover/movie?sort_by=vote_average.desc?";
                //https://api.themoviedb.org/3/movie/550?api_key=###
                baseUrl = "http://api.themoviedb.org/3/movie/popular?api_key=5dd283e7dba19cb8b36ae74ee2e62103";
            } else {
                //baseUrl = "https://api.themoviedb.org/3/discover/movie?sort_by=popularity.desc?";
                baseUrl = "http://api.themoviedb.org/3/movie/top_rated?api_key=5dd283e7dba19cb8b36ae74ee2e62103";
            }


            //baseUrl = "https://api.themoviedb.org/3/discover/movie?sort_by=popularity.desc?";
            String apiKey = "&api_key=" + BuildConfig.THE_MOVIE_DB_API_KEY;
            URL url = new URL(baseUrl.concat(apiKey));

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

            MovieParser myParser = new MovieParser();
            try {
                String myString = myParser.getMovieTitles(forecastJsonStr);
            } catch (JSONException e) {
                e.printStackTrace();
            }


            //Log.v(LOG_TAG,forecastJsonStr);
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

        myProgressBar.setVisibility(View.GONE);
        Model.picURL.clear();


        for (int i = 0; i < Model.movies.size(); i++) {
            Model.picURL.add(Model.movies.get(i).thumbnailURL);

        }


        myFragment.helper();


    }


}
