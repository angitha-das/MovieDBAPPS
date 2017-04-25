package com.example.angithadas.moviedbapps.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.angithadas.moviedbapps.fragment.MovieListFragment;


public class PagerAdapter extends FragmentPagerAdapter {

    public PagerAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
    }

    // Returns total number of pages
    @Override
    public int getCount() {
        int NUM_ITEMS;
        NUM_ITEMS = 4;
        return NUM_ITEMS;
    }

    // Returns the fragment to display for that page
    @Override
    public Fragment getItem(int position) {
        return MovieListFragment.newInstance(position, getCategory(position));
    }

    // Returns the page title for the top indicator
    @Override
    public String getPageTitle(int position) {
        String[] category ={"Popular","Top Rated","Upcoming","Get Latest"};
        switch (position) {

            case 0:
                return category[0];
            case 1:
                return category[1];
            case 2:
                return category[2];
            case 3:
                return category[3];
            default:
                return null;
        }
    }

    private String getCategory(int position) {
        String[] category ={"popular","top_rated","upcoming","now_playing"};
        switch (position) {
            case 0:
                return category[0];
            case 1:
                return category[1];
            case 2:
                return category[2];
            case 3:
                return category[3];
            default:
                return null;
        }
    }
}