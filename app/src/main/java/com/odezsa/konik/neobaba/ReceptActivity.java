package com.odezsa.konik.neobaba;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;


public class ReceptActivity extends AppCompatActivity {

    ListView listView;
    DatabaseHelper myDbHelper;
    private String[] recNames = new String[20];
    private int count;


    //Cursor cursor =  db.rawQuery("select * from products where prodName like '" + searchView.getQuery() + "%';" , null);

    Integer[] imageId = {
            R.drawable.popara,
            R.drawable.omlet
    };

    private boolean isIn(int value, int[] array){
        for(int i=0; i<array.length; i++){
            if(value == array[i]) return true;
        }
        return false;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recept);
        count = 0;
        Bundle extras = getIntent().getExtras();
        myDbHelper = new DatabaseHelper(getApplicationContext());

        int chosen[] = extras.getIntArray("chosen");

        SQLiteDatabase db = myDbHelper.getWritableDatabase();
        int recId = 0;
        Cursor cursor =  db.rawQuery("select _id from combo where productId = " + chosen[0] , null);
        cursor.moveToFirst();

        for(int i=0; i<chosen.length;i++){
            boolean test = true;
            while(!cursor.isAfterLast()){
                recId = cursor.getInt(cursor.getColumnIndex("_id"));
            }
            cursor = db.rawQuery("select productId from combo where _id = " + recId , null);
            cursor.moveToFirst();
            while(!cursor.isAfterLast()){
                if(!isIn(cursor.getInt(cursor.getColumnIndex("productId")), chosen)) test = false;
            }
            cursor =  db.rawQuery("select recName from recipes where _id = " + recId, null);

            if(test && !cursor.isAfterLast()) recNames[count] = cursor.getString(cursor.getColumnIndex("recName"));

        }

        cursor.close();


        CustomListview adapter = new
                CustomListview(ReceptActivity.this, recNames, imageId);
        listView=(ListView)findViewById(R.id.list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Toast.makeText(ReceptActivity.this, "You Clicked at " + recNames[position], Toast.LENGTH_SHORT).show();

            }
        });


        }


        @Override
        public boolean onCreateOptionsMenu (Menu menu){
            // Inflate the menu; this adds items to the action bar if it is present.
            getMenuInflater().inflate(R.menu.menu_recept, menu);
            return true;
        }

        @Override
        public boolean onOptionsItemSelected (MenuItem item){
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
        }
