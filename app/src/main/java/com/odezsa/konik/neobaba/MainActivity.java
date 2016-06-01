package com.odezsa.konik.neobaba;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
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
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;

import java.io.IOException;
import java.sql.SQLException;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public static int counter = 0;
    private final int  NUM_COL= 3;
    private final int NUM_ROW=3;
    public Menu testMenu;
    public static Food food[] = new Food[6];

    DatabaseHelper myDbHelper;



    public static boolean checkFood(int i){
        if(food[i].isChecked) return true;
        else return false;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        NavigationView nvView = (NavigationView) findViewById(R.id.nav_view);
        testMenu = nvView.getMenu();


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

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView =
                (SearchView) findViewById(R.id.search);
        //searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));

        SQLiteDatabase db = myDbHelper.getWritableDatabase();
        Cursor productCursor = myDbHelper.getWordMatches(searchManager.QUERY, null);
        ProductAdapter productAdapter = new ProductAdapter(this, productCursor);
        searchView.setSuggestionsAdapter(productAdapter);

        //0-hlqb 1-domat 2-sirene 3-kashkaval 4-qica 5-mlqko
        /*food[0] = new Food("Хляб", (ImageView) findViewById(R.id.helb));
        food[1] = new Food("Домат", (ImageView)findViewById(R.id.tomato));
        food[2] = new Food("Сирене", (ImageView)findViewById(R.id.sir));
        food[3] = new Food("Кашкавал", (ImageView)findViewById(R.id.kash));
        food[4] = new Food("Яица", (ImageView)findViewById(R.id.qco));
        food[5] = new Food("Мляко", (ImageView)findViewById(R.id.mill));*/


        //0-hlqb 1-domat 2-sirene 3-kashkaval 4-qica 5-mlqko



        ImageView but = (ImageView) findViewById(R.id.but);
        but.getLayoutParams().width = but.getLayoutParams().height;


        int imgSize = (int) (displaymetrics.widthPixels * 0.35);

       /* for(int i=0; i<food.length; i++ ){
            food[i].getImg().getLayoutParams().height = imgSize;
            food[i].getImg().getLayoutParams().width = imgSize;
        }
        imgSize = (int) (displaymetrics.widthPixels * 0.2);
        but.getLayoutParams().width = imgSize;
        but.getLayoutParams().height =(int) (imgSize * 0.9);*/


    }

    private void handleIntent(Intent intent) {

        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            Cursor c = myDbHelper.getWordMatches(query, null);
            //process Cursor and display results
        }

        public void startSearch(View view){
            Intent i = new Intent(MainActivity.this, ReceptActivity.class);
            startActivity(i);
        }

      /*  public void markProduct(View view){
            if(testMenu.findItem(R.id.mill) == null) {
                testMenu.add(0, R.id.mill, counter, "Мляко");
                food[5].setIsChecked(true);
                food[5].getImg().setBackgroundColor(getResources().getColor(R.color.checked));
            }
            else{
                food[5].setIsChecked(true);
                testMenu.removeItem(R.id.mill);
                food[5].getImg().setBackgroundColor(0);
            }

        }*/


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
       /* MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        */


        return true;
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

    private class SuggestionsAdapter extends CursorAdapter {

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
    }
}
