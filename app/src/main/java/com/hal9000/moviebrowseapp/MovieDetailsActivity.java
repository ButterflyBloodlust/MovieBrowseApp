package com.hal9000.moviebrowseapp;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class MovieDetailsActivity extends AppCompatActivity {   // AppCompatActivity is a subclass of FragmentActivity

    private static final String KEY_MOVIE = "movie";
    FragmentPagerAdapter adapterViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        Movie movie = (Movie) getIntent().getSerializableExtra(KEY_MOVIE);
        initViews(movie);
        initCategoryBg(movie);

        ViewPager viewPager = findViewById(R.id.pic_gallery_viewpager);
        adapterViewPager = new MovDetailsViewPagerAdapter(getSupportFragmentManager(), movie);
        viewPager.setAdapter(adapterViewPager);
    }

    private void initCategoryBg(Movie movie) {
        String movieCategory = movie.getCategory();
        int bgResId = 0;
        if (movieCategory.contains("Comedy")){
            bgResId = R.drawable.comedy_bg_grad;
        }
        else if (movieCategory.contains("Thriller")){
            bgResId = R.drawable.thriller_bg_grad;
        }
        else if (movieCategory.contains("Sci-fi")){
            bgResId = R.drawable.scif_fi_bg_grad;
        }
        findViewById(R.id.movie_details_title_category_bg).setBackgroundResource(bgResId);
    }

    private void initViews(Movie movie) {
        ((TextView)findViewById(R.id.movie_details_title)).setText(movie.getTitle());
        ((TextView)findViewById(R.id.movie_details_category)).setText(movie.getCategory());
        ((ImageView)findViewById(R.id.movie_details_image)).setImageResource(movie.getMoviePosterDrawableID() == 0 ? R.drawable.ic_local_movies_black_50dp : movie.getMoviePosterDrawableID());
    }

    // starter - to control passing and reading Extra in the same class
    public static void start(Context context, Movie movie) {
        Intent starter = new Intent(context, MovieDetailsActivity.class);
        starter.putExtra(KEY_MOVIE, movie);
        context.startActivity(starter);
    }
}
