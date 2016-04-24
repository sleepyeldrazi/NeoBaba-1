package com.odezsa.konik.neobaba;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.TextView;

import org.w3c.dom.Text;

public class ReceptaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recepta);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
        ImageView img = (ImageView) findViewById(R.id.hranaImg);
        TextView name = (TextView) findViewById(R.id.hranaName);
        TextView deck = (TextView) findViewById(R.id.hranaDisc);
        TextView deck2 = (TextView) findViewById(R.id.hranaDisc2);
        TabHost host = (TabHost)findViewById(R.id.tabHost);

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



        if(ReceptActivity.getReceptId() == 1){
            img.setImageResource(R.drawable.omlet);
            name.setText(R.string.omletName);
            deck.setText(R.string.omlet);
            deck2.setText(R.string.omlet2);
        }

        else{
            img.setImageResource(R.drawable.popara);
            name.setText(R.string.poparaName);
            deck.setText(R.string.popara);
            deck2.setText(R.string.popara2);
        }


        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

}
