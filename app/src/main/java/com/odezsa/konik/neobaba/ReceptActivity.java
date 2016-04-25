package com.odezsa.konik.neobaba;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.app.Activity;
import android.os.Bundle;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;


import java.util.List;


public class ReceptActivity extends AppCompatActivity {

    private ListView lv;
    public static int receptId;

    public static int getReceptId() {
        return receptId;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recept);

        RelativeLayout rl = (RelativeLayout) findViewById(R.id.rl);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);

        Button bt[] = new Button[2];




        for(int j=0; j<bt.length; j++){
            bt[j] = new Button(this);
            bt[j].setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            bt[j].setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
            //bt[j].setLayoutParams(params);
            bt[j].setId(j);


        }

        bt[0].setText(R.string.poparaName);
        bt[1].setText(R.string.omletName);


        bt[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (MainActivity.checkFood(2) && MainActivity.checkFood(0)) {
                    receptId = 0;
                    Intent i = new Intent(ReceptActivity.this, ReceptaActivity.class);
                    startActivity(i);

                }
            }
        });





        bt[1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (MainActivity.checkFood(4) && MainActivity.checkFood(5)) {
                    receptId = 1;
                    Intent i = new Intent(ReceptActivity.this, ReceptaActivity.class);
                    startActivity(i);

                }
            }
        });

        //0-hlqb 1-domat 2-sirene 3-kashkaval 4-qica 5-mlqko
        if(MainActivity.checkFood(2) && MainActivity.checkFood(0)){
            System.out.println(bt[0].getId());
            rl.addView(bt[0], new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT));
            params.setMargins(0, 200, 0, 0);

        }

        if(MainActivity.checkFood(4) && MainActivity.checkFood(5)) {
            System.out.println(bt[1].getId());
            rl.addView(bt[1], params);

        }



       /* 
            <Button
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:text="@string/current_recept"
        android:id="@+id/button"
        android:textAlignment="center"
        android:layout_alignParentTop="true"
        android:background="@color/colorPrimaryDark"
        android:onClick="clickMe"
        android:layout_alignParentStart="true"
        android:layout_alignParentEnd="true" />

       lv = (ListView) findViewById(R.id.recepti);


        lv.setOnItemClickListener(new AdapterView.OnItemClickListener(){


            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                receptId = position;
                Intent i = new Intent(ReceptActivity.this, ReceptaActivity.class);
                startActivity(i);
            }
        });*/


    }



    public void clickMe(View view){
        if(MainActivity.checkFood(2) && MainActivity.checkFood(0)){
            receptId = 0;
            Intent i = new Intent(ReceptActivity.this, ReceptaActivity.class);
            startActivity(i);

        }
        //0-hlqb 1-domat 2-sirene 3-kashkaval 4-qica 5-mlqko
        if(MainActivity.checkFood(4) && MainActivity.checkFood(5)){
            receptId = 1;
            Intent i = new Intent(ReceptActivity.this, ReceptaActivity.class);
            startActivity(i);

        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_recept, menu);
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
}
