package com.hal9000.moviebrowseapp;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.widget.Toast;


public class MovDetailsViewPagerAdapter extends FragmentPagerAdapter {  // responsible for sliding between fragments in MovieDetailsActivity

    private static int NUM_ITEMS = 2;
    private Movie movie;

    public MovDetailsViewPagerAdapter(FragmentManager fragmentManager, Movie movie) {

        super(fragmentManager);
        this.movie = movie;
    }

    // Returns total number of pages.
    @Override
    public int getCount() {
        return NUM_ITEMS;
    }

    // Returns the fragment to display for a particular page.
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return ActorListFragment.newInstance(movie); // pages are cached so fragments within range will not be destroyed on swipe
            case 1:
                return PicGalleryFragment.newInstance(movie);
            default:
                return null;
        }
    }

    // Returns the page title for the top indicator
    @Override
    public CharSequence getPageTitle(int position) {
        return "Tab " + position;
    }
}
