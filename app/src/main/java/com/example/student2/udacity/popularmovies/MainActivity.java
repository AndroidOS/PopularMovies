package com.example.student2.udacity.popularmovies;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;

public class MainActivity extends AppCompatActivity {


    private RadioButton radioMovieButtonR;
    private RadioButton radioMovieButtonP;
    private RadioButton radioFavourites;
    private Button btnDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        setTitle("Popular Movies  - " + Model.downloadMode);


        radioMovieButtonR = findViewById(R.id.menu_sort_by_highest_rated);


        radioMovieButtonP = findViewById(R.id.menu_sort_by_most_popular);

        radioFavourites = findViewById(R.id.menu_favourites);


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = getIntent();
                finish();
                startActivity(intent);
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
            }
        });

        //Test for Internet. Show refresh button if no connection
        if (Connectivity.isNetworkAvailable(MainActivity.this)) {
            fab.setVisibility(View.GONE);
        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);


        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();


        if (id == R.id.action_settings) {
            return true;
        }

        if (id == R.id.menu_sort_by_highest_rated) {

            menuHelper("rated", id);
            return true;
        }

        if (id == R.id.menu_sort_by_most_popular) {

            menuHelper("popular", id);
            return true;
        }

        if (id == R.id.menu_favourites) {

            menuHelper("favourites", id);
            return true;
        }


        return super.onOptionsItemSelected(item);
    }

    void menuHelper(String sortMethod, int id) {


        if ("favourites" == sortMethod) {

            Model.dataLoaded = true;
            Model.downloadMode = "favourites";


            Intent intent = new Intent(this, MainActivity.class);

            startActivity(intent);
        } else if

                (Model.downloadMode != sortMethod) {
            Model.dataLoaded = false;
            Model.downloadMode = sortMethod;
            Intent intent = new Intent(this, MainActivity.class);

            startActivity(intent);
        }


    }

}
