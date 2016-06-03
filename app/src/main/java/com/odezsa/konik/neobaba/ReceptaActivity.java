package com.odezsa.konik.neobaba;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class ReceptaActivity extends AppCompatActivity {
    int chosen[];
    int id;
    DatabaseHelper myDbHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recepta);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

        Bundle extras = getIntent().getExtras();
        chosen= extras.getIntArray("chosen");
        id = extras.getInt("id");

        myDbHelper = new DatabaseHelper(getApplicationContext());


        ImageView img = (ImageView) findViewById(R.id.hranaImg);
        TextView name = (TextView) findViewById(R.id.hranaName);
        TextView deck = (TextView) findViewById(R.id.hranaDisc);
        TextView deck2 = (TextView) findViewById(R.id.hranaDisc2);
        TabHost host = (TabHost)findViewById(R.id.tabHost);

        SQLiteDatabase db = myDbHelper.getWritableDatabase();

        switch(id){
            case 2:
                img.setImageResource(R.drawable.omlet);
                name.setText("Омлет");
                deck.setText(R.string.omlet);
                Cursor c = db.rawQuery("select recDescr from recipes where _id =" + id, null);
                c.moveToFirst();
                deck2.setText(c.getString(c.getColumnIndex("recDescr")));
                c.close();
                break;
            case 1:
                img.setImageResource(R.drawable.popara);
                name.setText("Попара");
                deck.setText(R.string.popara);
                Cursor cur = db.rawQuery("select recDescr from recipes where _id =" + id, null);
                cur.moveToFirst();
                deck2.setText(cur.getString(cur.getColumnIndex("recDescr")));
                cur.close();

        }



        host.setup();
        host.getTabWidget().setShowDividers(TabWidget.SHOW_DIVIDER_MIDDLE);
        host.getTabWidget().setDividerDrawable(R.drawable.div);

        //Tab 1
        TabHost.TabSpec spec = host.newTabSpec("Продукти");
        spec.setContent(R.id.tab1);
        spec.setIndicator("Продукти");
        host.addTab(spec);

        //Tab 2
        spec = host.newTabSpec("Начин на приготвяне");
        spec.setContent(R.id.tab2);
        spec.setIndicator("Начин на приготвяне");
        host.addTab(spec);




        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

}
