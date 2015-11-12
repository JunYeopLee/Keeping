package com.example.junyeop_imaciislab.keeping;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.junyeop_imaciislab.keeping.util.Constant;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class DonationPersonalActivity extends Activity {
    private ImageButton certifiChoiceButton;
    private Button commitButton;
    private String selectedBloodID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donation_personal);
        certifiChoiceButton = (ImageButton)findViewById(R.id.btn_certifi_choice);
        certifiChoiceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DonationPersonalActivity.this,CertifiChoiceActivity.class);
                intent.putExtra("activityFrom","DonationActivity");
                startActivityForResult(intent, 1234);
            }
        });

        final Context context = this;
        commitButton = (Button)findViewById(R.id.btn_commit_donation_personal);
        commitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final AsyncHttpClient client = new AsyncHttpClient();
                final RequestParams params = new RequestParams();

                if(selectedBloodID == null || selectedBloodID.compareTo("")==0) return;
                params.add("bloodId",selectedBloodID);
                if(Constant.getUserId() == null || Constant.getUserId().compareTo("")==0) return;
                params.add("sendUserId",Constant.getUserId());
                String receiveUser = ((EditText)findViewById(R.id.edit_to_id)).getText().toString();
                if(receiveUser == null || receiveUser.compareTo("")==0) return;
                params.add("getUserId",receiveUser);
                params.add("fightingMsg", ((EditText) findViewById(R.id.edit_message)).getText().toString());
                AlertDialog.Builder alert_confirm = new AlertDialog.Builder(DonationPersonalActivity.this);
                alert_confirm.setMessage("기부 하시겠습니까?").setCancelable(false).setPositiveButton("확인",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                client.get(Constant.getQueryDonation(), params, new SendBloodCardAsyncHttpResponseHandler());
                                finish();
                            }
                        }).setNegativeButton("취소",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                return;
                            }
                        });
                AlertDialog alert = alert_confirm.create();
                alert.show();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode,int resultCode,Intent intent) {
        super.onActivityResult(requestCode,resultCode,intent);
        if(requestCode==1234) {
            selectedBloodID = intent.getExtras().getString("bloodID");
            ((EditText)findViewById(R.id.edit_bloodid)).setText(selectedBloodID);
            Log.d("BLDID",selectedBloodID);
        }
    }

    private class SendBloodCardAsyncHttpResponseHandler extends JsonHttpResponseHandler {
        ProgressDialog dialog;
        @Override
        public void onStart() {
            dialog = new ProgressDialog(DonationPersonalActivity.this);
            dialog.setMessage("잠시만 기다려 주세요.");
            dialog.setCancelable(false);
            dialog.show();
        }
        @Override
        public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
            try {
                if(response.getBoolean("success")) {
                    if(dialog != null && dialog.isShowing()){
                        dialog.dismiss();
                    }
                } else {
                    AlertDialog.Builder alert = new AlertDialog.Builder(DonationPersonalActivity.this);
                    alert.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    alert.setMessage("[연결 실패]네트워크 연결이 불안정 합니다").show();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        @Override
        public void onFailure(int statusCode, Header[] headers, Throwable throwable,JSONObject errorResponse) {
            if(dialog != null && dialog.isShowing()){
                dialog.dismiss();
            }
            AlertDialog.Builder alert = new AlertDialog.Builder(DonationPersonalActivity.this);
            alert.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            alert.setMessage("[연결 실패]네트워크 연결이 불안정 합니다").show();
        }
    }
}
