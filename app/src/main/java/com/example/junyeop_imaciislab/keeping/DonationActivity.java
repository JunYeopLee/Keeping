package com.example.junyeop_imaciislab.keeping;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.junyeop_imaciislab.keeping.adapter.CustomGalleryImageAdapter;
import com.example.junyeop_imaciislab.keeping.util.CustomGallery;

import butterknife.ButterKnife;

public class DonationActivity extends Activity {
    private CustomGallery mCustomGallery;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.inject(this);
        Log.d("MSG", "onCreate");
        setContentView(R.layout.activity_donation);
        mCustomGallery = (CustomGallery)findViewById(R.id.gallery_custom);
        mCustomGallery.setAdapter(new CustomGalleryImageAdapter(getApplicationContext(), 3));
        mCustomGallery.setSelection(1);

        findViewById((R.id.btn_donation_to_personal)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DonationActivity.this, DonationPersonalActivity.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.btn_donation_to_institution).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DonationActivity.this,DonationInstitutionActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onStop() {
        Log.d("MSG","onStop");
        super.onStop();
        System.gc();
    }

    @Override
    protected void onDestroy() {
        Log.d("MSG","onDestory");
        super.onDestroy();
        System.gc();
    }
}
