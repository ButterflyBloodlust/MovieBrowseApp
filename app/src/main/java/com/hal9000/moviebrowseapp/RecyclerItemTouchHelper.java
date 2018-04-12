package com.hal9000.moviebrowseapp;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

// This class only serves the purpose of detecting swipe activity on element
// onSwipe has to be implemented in the class implementing RecyclerItemTouchHelperListener which allows taking additional args in onSwiped
public class RecyclerItemTouchHelper extends ItemTouchHelper.SimpleCallback{
    RecyclerItemTouchHelperListener listener;

    RecyclerItemTouchHelper(int dragDirs, int swipeDirs, RecyclerItemTouchHelperListener listener){
        super(dragDirs, swipeDirs);
        this.listener = listener;
    }

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        return false;
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction){
        listener.onSwiped( viewHolder, direction, viewHolder.getAdapterPosition());
    }

    public interface RecyclerItemTouchHelperListener{
        void onSwiped(RecyclerView.ViewHolder viewHolder, int direction, int position);
    }
}
