package com.example.junyeop_imaciislab.keeping;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class BenefitActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_benefit);
        ImageView DetailClickImageView = (ImageView)findViewById(R.id.img_detail_click);
        DetailClickImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BenefitActivity.this,BenefitDetailActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onPause()
    {
        super.onPause();
        System.gc();
    }


    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        System.gc();
    }
}
