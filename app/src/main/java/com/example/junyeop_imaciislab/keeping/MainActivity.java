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

import com.example.junyeop_imaciislab.keeping.util.Constant;

public class MainActivity extends TabActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Constant.setUserId(getIntent().getExtras().getString("userId"));

        TabHost tabHost = getTabHost();
        ImageView imageView = new ImageView(getApplicationContext());
        imageView.setImageResource(R.drawable.btn_tab1);
        Intent intent = new Intent(getApplicationContext(), HomeActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("name", getIntent().getExtras().getString("name"));
        intent.putExtra("bloodType",getIntent().getExtras().getString("bloodType"));
        intent.putExtra("userId",getIntent().getExtras().getString("userId"));
        tabHost.addTab(tabHost.newTabSpec("Tab1")
                .setIndicator(imageView)
                .setContent(intent));

        imageView = new ImageView(getApplicationContext());
        imageView.setImageResource(R.drawable.btn_tab2);
        tabHost.addTab(tabHost.newTabSpec("Tab2")
                .setIndicator(imageView)
                .setContent(new Intent(getApplicationContext(), InventoryActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)));

        imageView = new ImageView(getApplicationContext());
        imageView.setImageResource(R.drawable.btn_tab3);
        tabHost.addTab(tabHost.newTabSpec("Tab3")
                .setIndicator(imageView)
                .setContent(new Intent(getApplicationContext(), DonationActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)));


        imageView = new ImageView(getApplicationContext());
        imageView.setImageResource(R.drawable.btn_tab4);
        tabHost.addTab(tabHost.newTabSpec("Tab4")
                .setIndicator(imageView)
                .setContent(new Intent(getApplicationContext(), BenefitActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)));

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
                Log.d("TAB",tabId);
                System.gc();
            }
        });

        tabHost.setCurrentTab(0);
        }
    }
