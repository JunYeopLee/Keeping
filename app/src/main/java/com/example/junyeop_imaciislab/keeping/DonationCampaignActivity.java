package com.example.junyeop_imaciislab.keeping;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

public class DonationCampaignActivity extends Activity {
    private ImageButton certifiChoiceButton;
    private String selectedBloodID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donation_campaign);
        int position = getIntent().getExtras().getInt("position");
        certifiChoiceButton = (ImageButton)findViewById(R.id.btn_certifi_choice_cam);
        certifiChoiceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DonationCampaignActivity.this, CertifiChoiceActivity.class);
                intent.putExtra("activityFrom","DonationCampaignActivity");
                startActivityForResult(intent, 1234);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode,int resultCode,Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        if(requestCode==1234) {
            selectedBloodID = intent.getExtras().getString("bloodID");
            ((EditText)findViewById(R.id.edit_bloodid_cam)).setText(selectedBloodID);
            Log.d("BLDID", selectedBloodID);
        }
    }
}
