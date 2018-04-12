package com.hal9000.moviebrowseapp;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Calendar;  // LocalDate is better but works on >=8.0

public class ActorListFragment extends ListFragment {

    ListView listView;
    ActorListAdapter adapter;
    private final static String MOVIE_KEY = "movie";
    ArrayList<MovieActor> listData = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Movie movie = (Movie) getArguments().getSerializable(MOVIE_KEY);
        listData = movie.getActorsList();
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        listView = getView().findViewById(R.id.main_list_view);
        // Getting adapter and passing data reference
        adapter = new ActorListAdapter(getActivity(), listData);
        listView.setAdapter(adapter);
    }

    public static ActorListFragment newInstance(Movie movie) {
        ActorListFragment fragment = new ActorListFragment();

        Bundle bundle = new Bundle();
        bundle.putSerializable(MOVIE_KEY, movie);
        fragment.setArguments(bundle);

        return fragment;
    }
}
