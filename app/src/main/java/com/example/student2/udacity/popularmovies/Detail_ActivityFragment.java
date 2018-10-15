package com.example.student2.udacity.popularmovies;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * A placeholder fragment containing a simple view.
 */
public class Detail_ActivityFragment extends Fragment {

    String movieURL;
    private ArrayAdapter<String> mTrailerAdapter;
    //ImageView imageview;
    private ImageView imageView;
    private Movie myMovie;
    private TextView movieTitle;
    private TextView movieDesc;
    private TextView movieRate;
    private TextView movieDate;
    private List<String> myTrailers;
    private ListView trailerListView;
    private List<String> myTrailers1;
    private int position;
    private Button review_button;
    private Button favourite_button;
    private TextView reviewText;
    //private  int newPosition = 99;


    public Detail_ActivityFragment() {

    }

    @Override
    public void onStart() {
        super.onStart();

//        if (Model.position != 99){
//            position = Model.position;
//        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        View view = inflater.inflate(R.layout.fragment_detail_, container, false);

        //Correct the movie choice on back button

        if (Model.position != 99) {
            position = Model.position;
        }


        imageView = view.findViewById(R.id.imageview1);
        movieTitle = view.findViewById(R.id.movieTitle);
        movieDesc = view.findViewById(R.id.movieDesc);
        movieRate = view.findViewById(R.id.movieRate);
        movieDate = view.findViewById(R.id.movieDate);
        trailerListView = view.findViewById(R.id.listview_trailers);

        favourite_button = view.findViewById(R.id.favourite_button);
        reviewText = view.findViewById(R.id.reviewDesc);

        setListViewHeightBasedOnChildren(trailerListView);


        final Intent intent = getActivity().getIntent();
        if (intent != null && intent.hasExtra(Intent.EXTRA_TEXT)) {
            String indexStr = intent.getStringExtra(Intent.EXTRA_TEXT);

            position = Integer.parseInt(indexStr);
            Model.position = position;
        }

//        Log.v("Position ", " " + position);
//        Log.v("NewPosition ", " " + Model.position);

        String[] trailerStrings = Model.movies.get(position).trailers;

        myTrailers = new ArrayList<String>(Arrays.asList(trailerStrings));


        myTrailers1 = new ArrayList<String>();

        final String mPosition = String.valueOf(position);

//            review_button.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Intent intent = new Intent(getActivity(), ActivityReviews.class)
//                    .putExtra(Intent.EXTRA_TEXT,mPosition);
//                    startActivity(intent);
//                }
//            });

        favourite_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addToFavourites();
            }
        });

        if (checkIfMovieIsAlreadyFavourite()) {
            favourite_button.setEnabled(false);
        }


        myMovie = Model.movies.get(position);
        String movieID = Integer.toString(myMovie.movieId);
        //Log.v("Here","Now");
        FetchMovieTaskP2 movieTask = new FetchMovieTaskP2(movieID, position, this);
        movieTask.execute();

        getMovieReviews();

        myTrailers1.clear();
        for (int i = 0; i < myMovie.trailers.length; i++) {

            //Log.v("MovieURL = ", myMovie.trailers[i]);
            String myTitle = "Trailer " + (i + 1);
            myTrailers1.add(myTitle);

        }

        mTrailerAdapter =
                new ArrayAdapter<String>(
                        getActivity(), // The current context (this activity)
                        R.layout.text_view, // The name of the layout ID.
                        R.id.list_item_trailer_textview, // The ID of the textview to populate.
                        myTrailers1);

        trailerListView.setAdapter(mTrailerAdapter);


        trailerListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(myMovie.trailers[position]));
                startActivity(intent);
            }
        });


        movieTitle.setText(myMovie.originalTitle);
        movieDesc.setText(myMovie.plot);
        movieRate.setText(myMovie.userRating.toString() + "/10");
        movieDate.setText(myMovie.releaseDate);

        Picasso.get()
                .load(myMovie.thumbnailURL)

                .into(imageView);


        return view;
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


    }

    public void helper() {

        for (int i = 0; i < myMovie.trailers.length; i++) {

            //Log.v("MovieURL = ", myMovie.trailers[i]);
            String myTitle = "Trailer " + (i + 1);
            myTrailers1.add(myTitle);

        }


        mTrailerAdapter.notifyDataSetChanged();
        //Log.v("myTrailers size = ", " " + myTrailers.size());

//        Intent intent = new Intent(getActivity(), Detail_Activity.class);
//
//        startActivity(intent);


    }

    public void addToFavourites() {

        favourite_button.setEnabled(false);

        Log.v("addToFavourites = ", Model.movies.get(position).originalTitle);

        if (!checkIfMovieIsAlreadyFavourite()) {

            Model.favouriteMovies.add(Model.movies.get(position));
        }


        Log.v("addToFavourites = ", " " + position);
//        if (Model.favouriteMovies != null) {
//            Model.favouriteMovies.add(myMovie);
//        } else {
//            List<Movie> myList = new ArrayList<Movie>();
//            myList.add(myMovie);
//            Model.favouriteMovies = myList;
////            Log.v("myFavourite size = ", " " + Model.favouriteMovies.size());
//        }

        Log.v("myFavourite size = ", " " + Model.favouriteMovies.size());

    }

    public boolean checkIfMovieIsAlreadyFavourite() {

        boolean test = false;
        int testID;

        int testMovieID = Model.movies.get(position).movieId;

        for (int i = 0; i < Model.favouriteMovies.size(); i++) {

            testID = Model.favouriteMovies.get(i).movieId;

            if (testID == testMovieID) {
                test = true;
            }


        }

        return test;

    }

    public void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            return;
        }

        int totalHeight = listView.getPaddingTop() + listView.getPaddingBottom();
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);
            if (listItem instanceof ViewGroup)
                listItem.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
    }

    public void getMovieReviews() {
        //myMovie = Model.movies.get(rPosition);

        //Log.v("Reviews"," " + position);

        String movieID = Integer.toString(myMovie.movieId);

        FetchReviewsTask reviewsTask = new FetchReviewsTask(movieID, position, this);
        reviewsTask.execute();
    }

    public void helperReview() {

        Log.v("Review 0 ", " " + myMovie.reviews.size());

        String reviewSuperString = "";

        for (int i = 0; i < myMovie.reviews.size(); i++) {

            reviewSuperString = reviewSuperString + myMovie.reviews.get(i) + "\r\n\r\n";

        }
        reviewText.setText(reviewSuperString);

    }

}

