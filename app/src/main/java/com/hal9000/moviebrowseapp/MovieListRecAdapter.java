package com.hal9000.moviebrowseapp;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.List;

public class MovieListRecAdapter extends RecyclerView.Adapter<MovieListRecAdapter.MovieViewHolder>{

    private List<Movie> mDataset;
    private ClickListener clickListener;    // defining onClick here directly wouldn't make much difference since the whole class already is customized to fit a specific lists' needs
                                            // dis fancy tho

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class MovieViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView titleTV, categoryTV;
        private ImageView imageIV;
        private ClickListener clickListener;

        public MovieViewHolder(View v, ClickListener clickListener) {
            super(v);
            titleTV = v.findViewById(R.id.movie_title);
            categoryTV = v.findViewById(R.id.category);
            imageIV = v.findViewById(R.id.movie_list_image);

            // Handle on click events
            this.clickListener = clickListener; // bind listeners; alternatively set clickListener in MovieListRecAdapter as static (not sure about it causing a memory leak though)
            v.setOnClickListener(this);
        }

        @Override
        public void onClick(View v){
            clickListener.onItemClick(getAdapterPosition(), v); // call onClick implementation of clickListener (which, here, is an activity)
        }

    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public MovieListRecAdapter(List<Movie> myDataset) {
        mDataset = myDataset;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup parent,
                                              int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.movie_list_row, parent, false);
        //...
        MovieViewHolder vh = new MovieViewHolder(v, clickListener);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(MovieViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.titleTV.setText(mDataset.get(position).getTitle());
        holder.categoryTV.setText(mDataset.get(position).getCategory());
        holder.imageIV.setImageResource(mDataset.get(position).getMovieIconDrawableID());
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public void removeItem(int position) {
        mDataset.remove(position);
        notifyItemRemoved(position);
    }

    // Allow onClick implementation in activity or fragment that uses this adapter (for versatility)
    public void setOnItemClickListener(ClickListener clickListener) {
        this.clickListener = clickListener;
    }

    interface ClickListener{
        void onItemClick(int position, View v);
    }
}
