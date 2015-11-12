package com.example.junyeop_imaciislab.keeping;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class BenefitDetailActivity extends Activity {
    private ImageButton tab1Button;
    private ImageButton tab2Button;
    private ImageButton useCardButton;
    private ImageView tab1Bar;
    private ImageView tab2Bar;
    private LinearLayout tab1Content;
    private ImageView tab2Content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_benefit_detail);
        tab1Button = (ImageButton) findViewById(R.id.btn_tab1_tab);
        tab2Button = (ImageButton) findViewById(R.id.btn_tab2_tab);
        tab1Bar = (ImageView) findViewById(R.id.btn_tab1_bar);
        tab2Bar = (ImageView) findViewById(R.id.btn_tab2_bar);
        tab1Content = (LinearLayout) findViewById(R.id.btn_tab1_content);
        tab2Content = (ImageView) findViewById(R.id.btn_tab2_content);
        tab1Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tab1Button.setImageResource(R.drawable.benefit_content_event1_detail_tab1_text_touch);
                tab2Button.setImageResource(R.drawable.benefit_content_event1_detail_tab2_text);
                tab1Bar.setVisibility(View.VISIBLE);
                tab2Bar.setVisibility(View.INVISIBLE);
                tab1Content.setVisibility(View.VISIBLE);
                tab2Content.setVisibility(View.INVISIBLE);
            }
        });
        tab2Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tab1Button.setImageResource(R.drawable.benefit_content_event1_detail_tab1_text);
                tab2Button.setImageResource(R.drawable.benefit_content_event1_detail_tab2_text_touch);
                tab2Bar.setVisibility(View.VISIBLE);
                tab1Bar.setVisibility(View.INVISIBLE);
                tab2Content.setVisibility(View.VISIBLE);
                tab1Content.setVisibility(View.INVISIBLE);
            }
        });

        useCardButton = (ImageButton)findViewById(R.id.btn_detail_use_card);
        useCardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BenefitDetailActivity.this,CertifiChoiceActivity.class);
                startActivity(intent);
            }
        });
    }
}
