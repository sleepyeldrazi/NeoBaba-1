package com.odezsa.konik.neobaba;

import android.content.Context;
import android.database.Cursor;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;

/**
 * Created by konik on 6/1/2016.
 */
public class ProductAdapter extends CursorAdapter {

    public ProductAdapter(Context context, Cursor cursor){
        super(context, cursor);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return null;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {

    }

}
