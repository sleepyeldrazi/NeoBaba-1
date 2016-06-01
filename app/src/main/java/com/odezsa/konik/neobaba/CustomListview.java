package com.odezsa.konik.neobaba;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by konik on 5/29/2016.
 */
public class CustomListview extends ArrayAdapter<String> {
    private final Activity context;
    private final String[] receptName;
    private final Integer[] imageId;
    public CustomListview(Activity context, String[] receptName, Integer[] imageId){
        super(context, R.layout.list_single, receptName);
        this.context = context;
        this.receptName = receptName;
        this.imageId = imageId;
    }
    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView= inflater.inflate(R.layout.list_single, null, true);
        TextView txtTitle = (TextView) rowView.findViewById(R.id.txt);

        ImageView imageView = (ImageView) rowView.findViewById(R.id.img);
        txtTitle.setText(receptName[position]);

        imageView.setImageResource(imageId[position]);
        return rowView;
    }

}
