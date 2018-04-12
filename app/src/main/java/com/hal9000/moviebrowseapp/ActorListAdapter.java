package com.hal9000.moviebrowseapp;

import android.os.Bundle;
import android.content.Context;
import android.view.*;
import android.widget.*;
import java.util.*;

public class ActorListAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<MovieActor> data;
    private LayoutInflater inflater = null;

    public ActorListAdapter(Context context, ArrayList<MovieActor> data) {
        super();
        this.data = data;
        this.context = context;
        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public int getCount() {
        return data.size();
    }

    public Object getItem(int position) {
        return position;
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(final int position, View listItemView, ViewGroup parent) {    // listItemView = row

        if(listItemView==null)
            listItemView = inflater.inflate(R.layout.fragment_list_row, null);

        /*
        final ImageButton deleteImageView = listItemView.findViewById(R.id.delete_item_button);
        deleteImageView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                data.remove(position);
                notifyDataSetChanged();
            }
        });
        */

        TextView tv_name_surname = listItemView.findViewById(R.id.name_surname);
        TextView tv_age = listItemView.findViewById(R.id.age);
        ImageView iv_thumb_image = listItemView.findViewById(R.id.list_image);

        MovieActor movieActor = data.get(position);

        // Setting all values in listview
        tv_name_surname.setText(movieActor.getFullName());
        tv_age.setText("AGE: " + movieActor.getAge());
        return listItemView;
    }
}
