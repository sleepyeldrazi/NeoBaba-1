package com.odezsa.konik.neobaba;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;

import android.widget.ListView;

import android.widget.Toast;



public class ReceptActivity extends AppCompatActivity{

    ListView listView;
    DatabaseHelper myDbHelper;
    ReceptSample[] recipes;

    int count;

    int chosen[];

    //Cursor cursor =  db.rawQuery("select * from products where prodName like '" + searchView.getQuery() + "%';" , null);



    public String[] getNames(ReceptSample[] rs){
        String[] arr = new String[rs.length];
        for(int i=0; i<rs.length; i++){
            arr[i] = rs[i].getName();
        }
        return arr;
    }

    public Integer[] getImgs(ReceptSample[] rs){
        Integer[] arr = new Integer[rs.length];
        for(int i=0; i<rs.length; i++){
            arr[i] = rs[i].getImg();
        }
        return arr;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recept);
        Bundle extras = getIntent().getExtras();
        chosen= extras.getIntArray("chosen");
        count = extras.getInt("count");

        myDbHelper = new DatabaseHelper(getApplicationContext());

        LoadResults lr = new LoadResults();
        lr.execute();





        }


        @Override
        public boolean onCreateOptionsMenu (Menu menu){
            // Inflate the menu; this adds items to the action bar if it is present.
            //getMenuInflater().inflate(R.menu.menu_recept, menu);
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

    private class LoadResults extends AsyncTask<Void, Object, ReceptSample[]> {
        DatabaseHelper dbConnector = new DatabaseHelper(
                getApplicationContext());

        public LoadResults(){}
        private boolean isIn(int value, int[] array){
            for(int i=0; i<array.length; i++){
                if(value == array[i]) return true;
            }
            return false;
        }

        @Override
        protected ReceptSample[] doInBackground(Void... params) {

            SQLiteDatabase db = myDbHelper.getWritableDatabase();
            String[] names = new String[10];
            int recId = 0;
            int numOfFound = 0;
            int j=0;
            Cursor cursor = db.rawQuery("select _id from combo where productId = " + chosen[j], null);

            while(cursor!=null && j<chosen.length) {

                cursor.moveToFirst();

                for (int i = 0; i < count; i++) {
                    boolean test = true;
                    while (cursor.moveToNext()) {
                        recId = cursor.getInt(cursor.getColumnIndex("_id"));
                    }

                    cursor = db.rawQuery("select productId from combo where _id = " + recId, null);
                    cursor.moveToFirst();
                    while (cursor.moveToNext()) {
                        if (!isIn(cursor.getInt(cursor.getColumnIndex("productId")), chosen))
                            test = false;
                    }
                    cursor = db.rawQuery("select recName from recipes where _id = " + recId, null);

                    if (test && !cursor.isAfterLast()) {
                        names[numOfFound] = cursor.getString(cursor.getColumnIndex("recName"));
                        numOfFound++;
                    }

                }
                cursor = db.rawQuery("select _id from combo where productId = " + chosen[j], null);
                j++;
            }
            ReceptSample[] rs = new ReceptSample[numOfFound];
            for(int i=0; i<numOfFound; i++){
                rs[i]= new ReceptSample(names[i]) {
                    @Override
                    public void setName(String name) {
                        super.setName(name);
                    }
                };
                Cursor c = db.rawQuery("select * from recipes where recName = " + names[i], null);
                rs[i].setId(c.getInt(c.getColumnIndex("_id")));
                rs[i].setDescription(c.getString(c.getColumnIndex("recDescr")));
                switch(rs[i].getName()) {
                    case "Омлет":
                        rs[i].setImg(R.drawable.omlet);
                        break;
                    case "Попара":
                        rs[i].setImg(R.drawable.popara);
                }

            }



            return  rs;
        }

        @Override
        protected void onPostExecute(ReceptSample[] result) {
            CustomListview adapter = new
                    CustomListview(ReceptActivity.this, getNames(result), getImgs(result));
            listView=(ListView)findViewById(R.id.list);
            listView.setAdapter(adapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> parent, View view,
                                        int position, long id) {
                    Toast.makeText(ReceptActivity.this, "You Clicked at " + getNames(recipes)[position], Toast.LENGTH_SHORT).show();

                }
            });
                dbConnector.close();

        }
    }


}


