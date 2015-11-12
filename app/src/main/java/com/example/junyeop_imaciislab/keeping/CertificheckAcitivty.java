package com.example.junyeop_imaciislab.keeping;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class CertificheckAcitivty extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_certificheck_acitivty);
        ((TextView)findViewById(R.id.txt_bloodid)).setText(getIntent().getExtras().getString("bloodID"));
    }
}
