package com.example.junyeop_imaciislab.keeping;

import android.app.TabActivity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TabWidget;

public class MainActivity extends TabActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TabHost tabHost = getTabHost();

        ImageView imageView = new ImageView(this);
        imageView.setImageResource(R.drawable.btn_tab1);
        tabHost.addTab(tabHost.newTabSpec("Tab1")
                .setIndicator(imageView)
                .setContent(new Intent(this, HomeActivity.class)));

        imageView = new ImageView(this);
        imageView.setImageResource(R.drawable.btn_tab2);
        tabHost.addTab(tabHost.newTabSpec("Tab2")
                .setIndicator(imageView)
                .setContent(new Intent(this, InventoryActivity.class)));

        imageView = new ImageView(this);
        imageView.setImageResource(R.drawable.btn_tab3);
        tabHost.addTab(tabHost.newTabSpec("Tab3")
                .setIndicator(imageView)
                .setContent(new Intent(this, DonationActivity.class)));


        imageView = new ImageView(this);
        imageView.setImageResource(R.drawable.btn_tab4);
        tabHost.addTab(tabHost.newTabSpec("Tab4")
                .setIndicator(imageView)
                .setContent(new Intent(this, BenefitActivity.class)));

        tabHost.setBackgroundColor(Color.TRANSPARENT);
        tabHost.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                Log.d("FOCUS", v.toString() + " " + hasFocus);

            }
        });

        final TabWidget tabWidget = getTabWidget();

        tabWidget.getChildAt(0).setBackground(null);
        tabWidget.getChildAt(0).setBackground(getResources().getDrawable(R.drawable.home_tab1));
        tabWidget.getChildAt(0).invalidate();

        tabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                for( int i = 0 ; i < tabWidget.getTabCount() ; i++ ) {
                    if(tabId.compareTo("Tab"+String.valueOf(i+1))==0) {
                        Log.d("TAB MATCH", tabId);
                        if(i==0) tabWidget.getChildAt(i).setBackgroundResource(R.drawable.btn_tab1);
                        else if(i==1) tabWidget.getChildAt(i).setBackgroundResource(R.drawable.btn_tab2);
                        else if(i==2) tabWidget.getChildAt(i).setBackgroundResource(R.drawable.btn_tab3);
                        else if(i==3) tabWidget.getChildAt(i).setBackgroundResource(R.drawable.btn_tab4);
                    } else {
                        Log.d("TAB UNMATCH", tabId);
                        if(i==0) tabWidget.getChildAt(i).setBackgroundResource(R.drawable.home_tab1_touch);
                        else if(i==1) tabWidget.getChildAt(i).setBackgroundResource(R.drawable.home_tab2_touch);
                        else if(i==2) tabWidget.getChildAt(i).setBackgroundResource(R.drawable.home_tab3_touch);
                        else if(i==3) tabWidget.getChildAt(i).setBackgroundResource(R.drawable.home_tab4_touch);
                    }
                    tabWidget.getChildAt(i).invalidate();
                }
            }
        });
        tabHost.setCurrentTab(0);
    }
}
