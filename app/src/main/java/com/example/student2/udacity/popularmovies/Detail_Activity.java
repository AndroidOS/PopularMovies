package com.example.student2.udacity.popularmovies;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class Detail_Activity extends AppCompatActivity {

    Detail_ActivityFragment myFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        myFragment = (Detail_ActivityFragment) getSupportFragmentManager().findFragmentById(R.id.fragment2);

        final FloatingActionButton fab = findViewById(R.id.fab);

        fab.hide();
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
                //Toast.makeText(Detail_Activity.this,"Reserved for Submission P2, Will be the favourite button",Toast.LENGTH_SHORT).show();
                myFragment.addToFavourites();
                fab.setImageResource(R.drawable.vader);


            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

}
