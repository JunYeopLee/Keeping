package com.example.junyeop_imaciislab.keeping;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

public class SplashActivity  extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        overridePendingTransition(R.anim.abc_fade_in, R.anim.abc_fade_out);
        initialize();
    }


    private void initialize()
    {
        Handler handler =    new Handler()
        {
            @Override
            public void handleMessage(Message msg)
            {
                Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.abc_fade_in, R.anim.abc_fade_out);
                finish();
            }
        };

        handler.sendEmptyMessageDelayed(0, 1000);
    }
}
