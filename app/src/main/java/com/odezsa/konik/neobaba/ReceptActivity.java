package com.odezsa.konik.neobaba;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;

import android.widget.ListView;
import android.widget.Toast;



    ListView listView;
    DatabaseHelper myDbHelper;
    String[] recNames = new String[2];

    int count;

    int chosen[];

    Integer[] imageId = new Integer[2];




    //Cursor cursor =  db.rawQuery("select * from products where prodName like '" + searchView.getQuery() + "%';" , null);


        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recept);
        Bundle extras = getIntent().getExtras();
        chosen= extras.getIntArray("chosen");
        count = extras.getInt("count");

        myDbHelper = new DatabaseHelper(getApplicationContext());




        imageId[0] = R.drawable.popara;
        imageId[1] = R.drawable.omlet;

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

        DatabaseHelper dbConnector = new DatabaseHelper(
                getApplicationContext());
            }

            }

            if(test && !cursor.isAfterLast()) {
            }

        }



            }

    }


}
