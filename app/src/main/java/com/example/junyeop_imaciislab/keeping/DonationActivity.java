package com.example.junyeop_imaciislab.keeping;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.example.junyeop_imaciislab.keeping.util.CustomGallery;

public class DonationActivity extends Activity {
    private CustomGallery mCustomGallery;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
/*
        Log.d("MSG","onCreate");
        setContentView(R.layout.activity_donation);
        mCustomGallery = (CustomGallery)findViewById(R.id.gallery_custom);
        mCustomGallery.setAdapter(new CustomGalleryImageAdapter(this,3));
*/
    }

    @Override
    protected void onStop() {
        Log.d("MSG","onStop");
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Log.d("MSG","onDestory");
        super.onDestroy();
    }
}
