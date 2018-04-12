package com.hal9000.moviebrowseapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;

import java.util.*;

public class MainActivity extends AppCompatActivity implements RecyclerItemTouchHelper.RecyclerItemTouchHelperListener { //, MovieListRecAdapter.ClickListener

    private List<Movie> movieList = new LinkedList<>();
    private RecyclerView mRecyclerView;
    private MovieListRecAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = findViewById(R.id.main_activity__recycler_view);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        addListElemDivider();
        specifyAdapter();
        addSlideDeleteSupport();

        // add hard coded data for app testing
        processHardCodedMovieData();
        mAdapter.notifyDataSetChanged();
    }

    private void specifyAdapter() {
        mAdapter = new MovieListRecAdapter(movieList);
        mRecyclerView.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new MovieListRecAdapter.ClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                MovieDetailsActivity.start(MainActivity.this, movieList.get(position));
            }
        });
    }

    private void addSlideDeleteSupport() {
        // add ItemTouchHelper to adapter in order to detect swipes on list items
        ItemTouchHelper.SimpleCallback itemTouchHelperCallback = new RecyclerItemTouchHelper(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT, this);
        new ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(mRecyclerView);
    }

    private void addListElemDivider() {
        // add divider to RecycleView
        DividerItemDecoration listDivider = new DividerItemDecoration(this, LinearLayoutManager.VERTICAL);
        listDivider.setDrawable(this.getResources().getDrawable(R.drawable.movie_list_divider_line, null));
        mRecyclerView.addItemDecoration(listDivider);
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction, int position){
        mAdapter.removeItem(position);
    }

    private ArrayList<MovieActor> getHardCodedActorsList(){
        ArrayList<MovieActor> movieActorsList = new ArrayList<>();
        Calendar tempCal = Calendar.getInstance();
        movieActorsList.add(null);                          // leave pos 0 empty -> only first actor on the list will be unique per movie
        tempCal.set(1964, 11, 2);
        MovieActor movieActor = new MovieActor("Keanu", "Reeves", tempCal);
        movieActorsList.add(movieActor);
        tempCal.set(1980, 12, 19);
        movieActor = new MovieActor("Jake", "Gyllenhaal", tempCal);
        movieActorsList.add(movieActor);
        tempCal.set(1975, 6, 4);
        movieActor = new MovieActor("Angelina", "Jolie", tempCal);
        movieActorsList.add(movieActor);
        tempCal.set(1976, 7, 19);
        movieActor = new MovieActor("Benedict", "Cumberbatch", tempCal);
        movieActorsList.add(movieActor);
        tempCal.set(1981, 4, 28);
        movieActor = new MovieActor("Jessica", "Alba", tempCal);
        movieActorsList.add(movieActor);
        tempCal.set(1967, 7, 26);
        movieActor = new MovieActor("Jason", "Statham", tempCal);
        movieActorsList.add(movieActor);
        return movieActorsList;
    }

    private ArrayList<Integer> getHardCodedPicIDsList(){
        ArrayList<Integer> movieGalleryPicIDs = new ArrayList<>();
        movieGalleryPicIDs.add(R.drawable.ic_local_movies_black_50dp);     // leave pos 0 with placeholder -> only first actor on the list will be unique per movie
        movieGalleryPicIDs.add(R.drawable.drive_icon);
        movieGalleryPicIDs.add(R.drawable.enemy_icon);
        movieGalleryPicIDs.add(R.drawable.interstellar_icon);
        movieGalleryPicIDs.add(R.drawable.some_like_it_hot_icon);
        movieGalleryPicIDs.add(R.drawable.the_bourne_identity_icon);
        return movieGalleryPicIDs;
    }

    @SuppressWarnings("unchecked")  // for casting clone to ArrayList
    private void processHardCodedMovieData(){
        ArrayList<Integer> movieGalleryPicIDs = getHardCodedPicIDsList();
        ArrayList<MovieActor> movieActorsListDefault = getHardCodedActorsList();
        Calendar tempCal = Calendar.getInstance();

        ArrayList<MovieActor> tempActorList = (ArrayList<MovieActor>) movieActorsListDefault.clone();    // shallow copy
        tempCal.set(1969, 11, 4);
        tempActorList.set(0, new MovieActor("Matthew", "McConaughey", tempCal));
        ArrayList<Integer> tempPicIDsList = (ArrayList<Integer>) movieGalleryPicIDs.clone();
        tempPicIDsList.set(0, R.drawable.black_hole_icon);
        movieList.add(new Movie("Interstellar", "Sci-fi", R.drawable.interstellar_poster, tempActorList, R.drawable.interstellar_icon, tempPicIDsList));

        tempActorList = (ArrayList<MovieActor>) movieActorsListDefault.clone();
        tempCal.set(1987, 4, 4);
        tempActorList.set(0, new MovieActor("Sarah", "Gadon", tempCal));
        tempPicIDsList = (ArrayList<Integer>) movieGalleryPicIDs.clone();
        tempPicIDsList.set(0, R.drawable.spider_icon);
        movieList.add(new Movie("Enemy", "Psychological Thriller", R.drawable.enemy_ver8_xlg_poster, tempActorList, R.drawable.enemy_icon, tempPicIDsList));

        tempActorList = (ArrayList<MovieActor>) movieActorsListDefault.clone();
        tempCal.set(1980, 11, 12);
        tempActorList.set(0, new MovieActor("Ryan", "Gosling", tempCal));
        tempPicIDsList = (ArrayList<Integer>) movieGalleryPicIDs.clone();
        tempPicIDsList.set(0, R.drawable.car_icon);
        movieList.add(new Movie("Drive", "Crime Thriller", R.drawable.drive_poster, tempActorList, R.drawable.drive_icon, tempPicIDsList));

        tempActorList = (ArrayList<MovieActor>) movieActorsListDefault.clone();
        tempCal.set(1926, 6, 1);
        tempActorList.set(0, new MovieActor("Marilyn", "Monroe", tempCal));
        tempPicIDsList = (ArrayList<Integer>) movieGalleryPicIDs.clone();
        tempPicIDsList.set(0, R.drawable.lady_icon);
        movieList.add(new Movie("Some Like It Hot", "Comedy", R.drawable.some_like_it_hot_poster, tempActorList, R.drawable.some_like_it_hot_icon, tempPicIDsList));

        tempActorList = (ArrayList<MovieActor>) movieActorsListDefault.clone();
        tempCal.set(1970, 10, 8);
        tempActorList.set(0, new MovieActor("Matt", "Damon", tempCal));
        tempPicIDsList = (ArrayList<Integer>) movieGalleryPicIDs.clone();
        tempPicIDsList.set(0, R.drawable.target_icon);
        movieList.add(new Movie("The Bourne Identity", "Action Thriller", R.drawable.the_bourne_identity_poster, tempActorList, R.drawable.the_bourne_identity_icon, tempPicIDsList));
    }
}
