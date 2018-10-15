package com.example.student2.udacity.popularmovies;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class ActivityReviews extends AppCompatActivity {

    FloatingActionButton fab;
    ActivityReviewsFragment myFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_reviews);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        myFragment = (ActivityReviewsFragment) getSupportFragmentManager().findFragmentById(R.id.fragment1);

        fab = findViewById(R.id.fab);
//        if (!Model.showReviewFAB) {
//
//            fab.hide();
//        }

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();


                myFragment.nextReview();

            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void fabHelper() {
//        if (!Model.showReviewFAB) {
//
        fab.hide();
//        }
    }

}
