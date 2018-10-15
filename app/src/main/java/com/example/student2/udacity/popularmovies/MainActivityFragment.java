package com.example.student2.udacity.popularmovies;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {


    GridView gridView;
    ProgressBar myProgressBar;
    ImageAdapter myAdapter;

    String movieURL = "http://image.tmdb.org/t/p/w185//inVq3FRqcYIRl2la8iZikYYxFNR.jpg";

    List<Movie> myMovies1 = new ArrayList<Movie>();


    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);


        //Initiate favourite movie list
        if (Model.favouriteMovies == null) {
            List<Movie> myList = new ArrayList<Movie>();
            Model.favouriteMovies = myList;
        }

        for (int i = 0; i < Model.favouriteMovies.size(); i++) {

            Log.v("Favourites ", Model.favouriteMovies.get(i).originalTitle);

        }


        if (Model.movies == null) {

            //add dummy data
            Model.dataLoaded = false;

            for (int i = 0; i < 20; i++) {

                //Movie myMovie = new Movie("Star Wars", movieURL, "good", 5.0, "12/12/2016");
                //myMovies1.add(myMovie);
            }
            Model.movies = myMovies1;
        }


        Model.position = 99;

        gridView = view.findViewById(R.id.grid_view);

        myProgressBar = view.findViewById(R.id.progress_bar);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                String mPosition = String.valueOf(position);

                Intent intent = new Intent(getActivity(), Detail_Activity.class)
                        .putExtra(Intent.EXTRA_TEXT, mPosition);
                startActivity(intent);
            }
        });


        if (!Model.dataLoaded) {
            Model.dataLoaded = true;
            Model.movies.clear();


            if (Connectivity.isNetworkAvailable(getActivity())) {


                myProgressBar.setVisibility(View.VISIBLE);
                FetchMovieTask1 movieTask = new FetchMovieTask1(getActivity(), myProgressBar, this);

                movieTask.execute();


            } else {

                Toast.makeText(getActivity(), "Use Refresh button to test for Connectivity", Toast.LENGTH_SHORT).show();

            }

        }

        if (Model.downloadMode != "favourites") {


            myAdapter = new ImageAdapter(getActivity(), Model.movies);
        } else {
            myAdapter = new ImageAdapter(getActivity(), Model.favouriteMovies);
        }

        gridView = view.findViewById(R.id.grid_view);
        gridView.setAdapter(myAdapter);

        myAdapter.notifyDataSetChanged();
        return view;
    }

    public void helper() {
        myAdapter.notifyDataSetChanged();


        Intent intent = new Intent(getActivity(), MainActivity.class);

        startActivity(intent);


    }


}
