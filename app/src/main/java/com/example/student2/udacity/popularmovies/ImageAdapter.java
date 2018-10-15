package com.example.student2.udacity.popularmovies;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by user on 14/08/2016 Custom Adapter for GridView.
 */


public class ImageAdapter extends ArrayAdapter<Movie> {
    private static final String LOG_TAG = ImageAdapter.class.getSimpleName();
    String URL;
    List<Movie> adapterMovies;
    //List<Movie> adapterMovies;
    private Context mContext;


    /**
     * This is our own custom constructor (it doesn't mirror a superclass constructor).
     * The context is used to inflate the layout file, and the List is the data we want
     * to populate into the lists
     *
     * @param mContext1     The current context. Used to inflate the layout file.
     * @param adapterMovies A List of AndroidFlavor objects to display in a list
     */
    public ImageAdapter(Activity mContext1, List<Movie> adapterMovies) {
        // Here, we initialize the ArrayAdapter's internal storage for the context and the list.
        // the second argument is used when the ArrayAdapter is populating a single TextView.
        // Because this is a custom adapter for two TextViews and an ImageView, the adapter is not
        // going to use this second argument, so it can be any value. Here, we used 0.
        super(mContext1, 0, adapterMovies);
        this.mContext = mContext1;
        this.adapterMovies = adapterMovies;

    }

    /**
     * Provides a view for an AdapterView (ListView, GridView, etc.)
     *
     * @param position    The AdapterView position that is requesting a view
     * @param convertView The recycled view to populate.
     *                    (search online for "android view recycling" to learn more)
     * @param parent      The parent ViewGroup that is used for inflation.
     * @return The View for the position in the AdapterView.
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Gets the AndroidFlavor object from the ArrayAdapter at the appropriate position
        Movie movie = getItem(position);

        // Adapters recycle views to AdapterViews.
        // If this is a new View object we're getting, then inflate the layout.
        // If not, this view already has the layout inflated from a previous call to getView,
        // and we modify the View widgets as usual.
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.image_grid_item, parent, false);
        }

        ImageView iconView = convertView.findViewById(R.id.grid_item_imageview);

//            for (int i=0; i< Model.movies.size(); i++){
//                String a = Model.movies.get(i).thumbnailURL;
//                URL = a;
//
//            }

        for (int i = 0; i < adapterMovies.size(); i++) {
            String a = adapterMovies.get(i).thumbnailURL;
            URL = a;

        }


        //String URL = Model.movies.get(position).thumbnailURL;
        String URL = adapterMovies.get(position).thumbnailURL;


        Picasso.get().setLoggingEnabled(false);
        Picasso.get()
                .load(URL)
                .fit()
                .placeholder(R.drawable.vader)
                .into(iconView);

        return convertView;
    }
}


