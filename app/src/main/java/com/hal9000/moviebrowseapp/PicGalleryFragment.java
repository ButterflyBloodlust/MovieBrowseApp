package com.hal9000.moviebrowseapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import java.util.ArrayList;


public class PicGalleryFragment extends Fragment {

    GridView gridView;
    PicGalleryAdapter adapter;
    private final static String MOVIE_KEY = "movie";
    private ArrayList<Integer> imageIDs = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Movie movie = (Movie) getArguments().getSerializable(MOVIE_KEY);
        imageIDs = movie.getMovieGalleryPicIDList();
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pic_gallery, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        gridView = getView().findViewById(R.id.pic_gallery_gridview);
        // Getting adapter and passing data reference
        adapter = new PicGalleryAdapter(getActivity(), imageIDs);
        gridView.setAdapter(adapter);
    }

    public static PicGalleryFragment newInstance(Movie movie) {
        PicGalleryFragment fragment = new PicGalleryFragment();

        Bundle bundle = new Bundle();
        bundle.putSerializable(MOVIE_KEY, movie);
        fragment.setArguments(bundle);

        return fragment;
    }
}
