package com.example.student2.udacity.popularmovies;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * A placeholder fragment containing a simple view.
 */
public class ActivityReviewsFragment extends Fragment {

    int rPosition;
    Movie myMovie;
    TextView movieReview;
    int reviewIndex = 0;
    int reviewCount = 0;

    ActivityReviews myReview;
    FloatingActionButton fab;


    public ActivityReviewsFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        //fab = (FloatingActionButton) getView().findViewById(R.id.fab);

//        if(fab == null){
//            Log.v("FAB", "is Null");
//        }


        //View view = inflater.inflate(R.layout.fragment_detail_, container, false);
        View view = inflater.inflate(R.layout.fragment_activity_reviews, container, false);

        movieReview = view.findViewById(R.id.movieReview);

        //movieReview.setText("SetText");
        final Intent intent = getActivity().getIntent();

        if (intent != null && intent.hasExtra(Intent.EXTRA_TEXT)) {
            String indexStr = intent.getStringExtra(Intent.EXTRA_TEXT);

            rPosition = Integer.parseInt(indexStr);
        }

//        myMovie = Model.movies.get(rPosition);
//
//        //Log.v("Reviews"," " + position);
//
//        String movieID = Integer.toString(myMovie.movieId);
//
//        FetchReviewsTask reviewsTask = new FetchReviewsTask(movieID,rPosition,this);
//        reviewsTask.execute();

        //movieReview.setText(Model.movies.get(position).reviews.get(0));

//        FetchMovieTaskP2 movieTask = new FetchMovieTaskP2("271110", 0,this , "reviews");
//        movieTask.execute();

        return view;
    }

    public void helper() {

        String review = "Sorry No reviews available";

        if (Model.movies.get(rPosition).reviews.size() != 0) {

            review = Model.movies.get(rPosition).reviews.get(0);
            reviewCount = (Model.movies.get(rPosition).reviews.size()) - 1;
            Model.showReviewFAB = true;


        }

        Log.v("Reviews number1", " " + Model.movies.get(rPosition).reviews.size());

        //fab.hide();

        if (reviewCount == 1) {
            //fab.hide();
            Log.v("Reviews number", "Hide FAB ");
        }

        getActivity().setTitle("Movie Reviews  " + "(" + (reviewIndex + 1) + " of " + (reviewCount + 1) + ")");
        //myReview.fabHelper();

        Log.v("Reviews number1", " " + Model.movies.get(rPosition).reviews.size());

        //review = review.replaceAll("\r\n", "\\n");

        //Log.v("Reviews",Model.movies.get(position).reviews.get(0));
        movieReview.setText(review);

        if (reviewIndex < reviewCount) {
            reviewIndex++;
        }
    }

    public void nextReview() {

//        getActivity().setTitle("Movie Reviews  " + "(" + (reviewIndex+1) + " of " + (reviewCount+1) + ")");

        if (reviewIndex < reviewCount) {
            reviewIndex++;
            String review = Model.movies.get(rPosition).reviews.get(reviewIndex);


        } else {
            reviewIndex = 0;

        }

        getActivity().setTitle("Movie Reviews  " + "(" + (reviewIndex + 1) + " of " + (reviewCount + 1) + ")");

        String review = Model.movies.get(rPosition).reviews.get(reviewIndex);
        movieReview.setText(review);
    }

}
