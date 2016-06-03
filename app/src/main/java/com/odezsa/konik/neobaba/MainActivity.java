package com.odezsa.konik.neobaba;

import android.app.SearchManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.MenuItemCompat;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.support.v7.widget.SearchView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.sql.SQLException;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, SearchView.OnQueryTextListener {

    public static int counter = 0;
    private final int  NUM_COL= 3;
    public SearchView searchView;
    private final int NUM_ROW=3;
    int[] chosen = new int[50];
    int location;
    int holder;
    public Menu testMenu;
    public ImageButton[] food = new ImageButton[8];
    public int[] icons = new int[8];


    DatabaseHelper myDbHelper;

    public int getStuff(String name){
        myDbHelper = new DatabaseHelper(this);
        SQLiteDatabase db = myDbHelper.getWritableDatabase();
        int loc=0;
        Cursor cursor =  db.rawQuery("select * from products where prodName like '" + name.toLowerCase() + "%';" , null);
        if (cursor != null)
        {
            if (cursor.moveToFirst())
            {
                int id = cursor.getInt(cursor.getColumnIndex("_id"));
                loc = id;
            }
            cursor.close();
        }
        return loc;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        NavigationView nvView = (NavigationView) findViewById(R.id.nav_view);
        testMenu = nvView.getMenu();
        location = counter;
        counter = 0;


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Избери продуктите си и натисни синия бутон.", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();



        ImageView but = (ImageView) findViewById(R.id.but);
        but.getLayoutParams().width = but.getLayoutParams().height;



        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        DisplayMetrics displaymetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);


        myDbHelper = new DatabaseHelper(this);

        try {
            myDbHelper.createDataBase();
        } catch (IOException ioe) {
            throw new Error("Unable to create database");
        }
        try {
            myDbHelper.openDataBase();
        }catch(SQLException sqle){
            try {
                throw sqle;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        //0-hlqb 1-domat 2-sirene 3-kashkaval 4-qica 5-mlqko

        icons[0]= 124; // kashkaval
        icons[1]= 78; // domat
        icons[2]= 307;//sirene
        icons[3]= 421;//qica
        icons[4]= 212;//milk
        icons[5]= 154;//cucum
        icons[6]= 380;//hlqb
        icons[7]= 360;//teleshko

        SQLiteDatabase db = myDbHelper.getWritableDatabase();

        int imgSize = (int) (displaymetrics.widthPixels * 0.35);


        for(int i=0; i<8; i++ ) {
            food[i] = new ImageButton(this);
        }


        food[0] = (ImageButton) findViewById(R.id.helb);
        food[1] = (ImageButton)findViewById(R.id.tomato);
        food[2] = (ImageButton)findViewById(R.id.sir);
        food[3] = (ImageButton)findViewById(R.id.kash);
        food[4] = (ImageButton)findViewById(R.id.qco);
        food[5] = (ImageButton)findViewById(R.id.mill);
       for(int i=0; i<6; i++ ) {
           food[i].getLayoutParams().height = imgSize;
           food[i].getLayoutParams().width = imgSize;
       }

        imgSize = (int) (displaymetrics.widthPixels * 0.3);
        but.getLayoutParams().width = imgSize;
        but.getLayoutParams().height =(int) (imgSize * 0.9);

    }

    //OnClick methods for the icons
    public void PressMeMilk(View view){
        holder = getStuff("Мляко");
        if(testMenu.findItem(holder) == null) {
            testMenu.add(0, holder, counter, "Мляко");
            chosen[counter] = holder;
            counter++;
            Toast.makeText(getApplicationContext(), "Добавихте Мляко", Toast.LENGTH_LONG).show();
        }
        else{
            testMenu.removeItem(holder);
        }

    }
    public void PressMeHleb(View view){
        holder = getStuff("Хляб");
        if(testMenu.findItem(holder) == null) {
            testMenu.add(0, holder, counter, "Хляб");
            chosen[counter] = holder;
            counter++;
            Toast.makeText(getApplicationContext(), "Добавихте Хляб", Toast.LENGTH_LONG).show();
        }
        else {
            testMenu.removeItem(holder);

        }
    }
    public void PressMeTomato(View view){
        holder = getStuff("Домат");
        if(testMenu.findItem(holder) == null) {
            testMenu.add(0, holder, counter, "Домат");
            counter++;
            chosen[counter] = holder;
            Toast.makeText(getApplicationContext(), "Добавихте Домат", Toast.LENGTH_LONG).show();
        }
        else {
            testMenu.removeItem(holder);

        }
    }

    public void PressMeQco(View view){
        holder = getStuff("Яйце");
        if(testMenu.findItem(holder) == null) {
            testMenu.add(0, holder, counter, "Яйце");
            chosen[counter] = holder;
            counter++;
            Toast.makeText(getApplicationContext(), "Добавихте Яйца", Toast.LENGTH_LONG).show();
        }
        else {
            testMenu.removeItem(holder);
        }
    }
    public void PressMeSir(View view){
        holder = getStuff("Сирене");
        if(testMenu.findItem(holder) == null) {
            testMenu.add(0, holder, counter, "Сирене");
            chosen[counter] = holder;
            counter++;
            Toast.makeText(getApplicationContext(), "Добавихте Сирене", Toast.LENGTH_LONG).show();
        }
        else {

            testMenu.removeItem(holder);

        }
    }
    public void PressMeKash(View view){
        holder = getStuff("Кашкавал");
        if(testMenu.findItem(holder) == null) {
            testMenu.add(0, holder, counter, "Кашкавал");
            chosen[counter] = holder;
            counter++;
            Toast.makeText(getApplicationContext(), "Добавихте Кашкавал" +
                    "", Toast.LENGTH_LONG).show();
        }
        else {

            testMenu.removeItem(holder);

        }
    }


        public void startSearch(View view){
            Intent i = new Intent(MainActivity.this, ReceptActivity.class);
            i.putExtra("chosen", chosen);
            i.putExtra("count", counter);
            myDbHelper.close();
            startActivity(i);
        }



    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);

        MenuItem searchItem = menu.findItem(R.id.search);
        searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        searchView.setOnQueryTextListener(this);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(
                new ComponentName(this, MainActivity.class)));
        searchView.setIconifiedByDefault(false);

        return true;
    }

    //TODO:lowercase input



    @Override
    public boolean onQueryTextSubmit(String query) {
        SQLiteDatabase db = myDbHelper.getWritableDatabase();


        Cursor cursor =  db.rawQuery("select * from products where prodName like '" + searchView.getQuery() + "%';" , null);
        if (cursor != null)
        {
            if (cursor.moveToFirst())
            {
                int id = cursor.getInt(cursor.getColumnIndex("_id"));
                location = id;
            }
            cursor.close();
        }
        if(testMenu.findItem(location) == null) {
            testMenu.add(0, location, counter, searchView.getQuery());
            chosen[counter] = location;
            counter++;
            Toast.makeText(getApplicationContext(),"Добавихте " + searchView.getQuery(), Toast.LENGTH_LONG).show();
        } else {
            testMenu.removeItem(location);
        }

        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        SQLiteDatabase db = myDbHelper.getWritableDatabase();
        Cursor c = db.rawQuery("select * from products where prodName like '" + searchView.getQuery() + "%';" , null);
        while(c.moveToNext())
        {
            // your calculation goes here
        }
        return false;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

  /*  private class SuggestionsAdapter extends CursorAdapter {

        public SuggestionsAdapter(Context context, Cursor c) {
            super(context, c, 0);
        }

        @Override
        public View newView(Context context, Cursor cursor, ViewGroup parent) {
            LayoutInflater inflater = LayoutInflater.from(context);
            View v = inflater.inflate(android.R.layout.simple_list_item_1, parent, false);
            return v;
        }

        @Override
        public void bindView(View view, Context context, Cursor cursor) {
            TextView tv = (TextView) view;
            final int textIndex = cursor.getColumnIndex(SearchManager.SUGGEST_COLUMN_TEXT_1);
            tv.setText(cursor.getString(textIndex));
        }
    }*/
}
