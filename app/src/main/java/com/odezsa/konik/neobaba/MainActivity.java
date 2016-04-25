package com.odezsa.konik.neobaba;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.DisplayMetrics;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public static int counter = 0;
    public MenuItem items[];
    public Menu testMenu;
    public static boolean food[] = new boolean[6];
    public ImageView foodImg[] = new ImageView[6];

    public static boolean checkFood(int i){
        return food[i];
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        for(int i=0; i<6; i++){
            food[i]=false;
        }

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
                Snackbar.make(view, "This will be useful soon enough.", Snackbar.LENGTH_LONG)
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




        //0-hlqb 1-domat 2-sirene 3-kashkaval 4-qica 5-mlqko

        foodImg[0] = (ImageView) findViewById(R.id.helb);
        foodImg[1] = (ImageView) findViewById(R.id.tomato);
        foodImg[2] = (ImageView) findViewById(R.id.sir);
        foodImg[3] = (ImageView) findViewById(R.id.kash);
        foodImg[4] = (ImageView) findViewById(R.id.qco);
        foodImg[5] = (ImageView) findViewById(R.id.mill);


        int imgSize = (int) (displaymetrics.widthPixels * 0.4);

        for(int i=0; i<foodImg.length; i++ ){
            foodImg[i].getLayoutParams().height = imgSize;
            foodImg[i].getLayoutParams().width = imgSize;
        }


        System.out.println("Menu ID: " + R.id.chosenMenu);

    }

        public void startSearch(View view){
            Intent i = new Intent(MainActivity.this, ReceptActivity.class);
            startActivity(i);
        }

        public void PressMeMilk(View view){
            if(testMenu.findItem(R.id.mill) == null) {
                testMenu.add(0, R.id.mill, counter, "Мляко");
                food[5] = true;
                foodImg[5].setBackgroundColor(getResources().getColor(R.color.checked));
            }
            else{
                food[5] = false;
                testMenu.removeItem(R.id.mill);
                foodImg[5].setBackgroundColor(0);
            }

        }
    public void PressMeHleb(View view){
        if(testMenu.findItem(R.id.helb) == null){
            food[0] = true;
            foodImg[0].setBackgroundColor(getResources().getColor(R.color.checked));
            testMenu.add(0, R.id.helb, counter, "Хляб");}
        else {
            testMenu.removeItem(R.id.helb);
            foodImg[0].setBackgroundColor(0);
            food[0]=false;
        }
    }
    public void PressMeTomato(View view){

        if(testMenu.findItem(R.id.tomato) == null) {
            foodImg[1].setBackgroundColor(getResources().getColor(R.color.checked));
            testMenu.add(0, R.id.tomato, counter, "Домати");
            food[1] = true;
        }
        else {
            testMenu.removeItem(R.id.tomato);
            foodImg[1].setBackgroundColor(0);
            food[1]=false;
        }
    }

    //0-hlqb 1-domat 2-sirene 3-kashkaval 4-qica 5-mlqko
    public void PressMeQco(View view){

        if(testMenu.findItem(R.id.qco) == null) {
            foodImg[4].setBackgroundColor(getResources().getColor(R.color.checked));
            testMenu.add(0, R.id.qco, counter, "Яица");
            food[4] = true;
        }
        else {
            foodImg[4].setBackgroundColor(0);
            food[4] = false;
            testMenu.removeItem(R.id.qco);
        }
    }
    public void PressMeSir(View view){
        if(testMenu.findItem(R.id.sir) == null) {
            foodImg[2].setBackgroundColor(getResources().getColor(R.color.checked));
            food[2] = true;

            testMenu.add(0, R.id.sir, counter, "Сирене");
        }
        else {
            food[2] = false;
            testMenu.removeItem(R.id.sir);
            foodImg[2].setBackgroundColor(0);
        }
    }
    public void PressMeKash(View view){
        if(testMenu.findItem(R.id.kash) == null) {
            foodImg[3].setBackgroundColor(getResources().getColor(R.color.checked));
            testMenu.add(0, R.id.kash, counter, "Кашкавал");
            food[3] = true;
        }
        else {
            food[3] = false;
            testMenu.removeItem(R.id.kash);
            foodImg[3].setBackgroundColor(0);
        }
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.activity_main_drawer, menu);


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
}
