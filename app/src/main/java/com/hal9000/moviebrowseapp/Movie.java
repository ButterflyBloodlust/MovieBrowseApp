package com.hal9000.moviebrowseapp;

import java.io.Serializable;
import java.util.ArrayList;

public class Movie implements Serializable{
    private String title, category;
    private int moviePosterDrawableID = 0, movieIconDrawableID = 0;
    private ArrayList<MovieActor> movieActorsList = new ArrayList<>();
    private ArrayList<Integer> movieGalleryPicIDList = new ArrayList<>();

    public Movie(String title, String category, int moviePosterDrawableID, ArrayList<MovieActor> movieActorsList, int movieIconDrawableID, ArrayList<Integer> movieGalleryPicIDList) {
        this.title = title;
        this.category = category;
        this.moviePosterDrawableID = moviePosterDrawableID;
        this.movieActorsList = movieActorsList;
        this.movieIconDrawableID = movieIconDrawableID;
        this.movieGalleryPicIDList = movieGalleryPicIDList;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String name) {
        this.title = name;
    }

    public String getCategory() {
        return category;
    }

    public int getMoviePosterDrawableID() { return moviePosterDrawableID; }

    public ArrayList<MovieActor> getActorsList() { return movieActorsList; }

    public int getMovieIconDrawableID() { return movieIconDrawableID; }

    public ArrayList<Integer> getMovieGalleryPicIDList() { return movieGalleryPicIDList; }
}