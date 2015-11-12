package com.example.junyeop_imaciislab.keeping;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.junyeop_imaciislab.keeping.util.Constant;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class CertificheckAcitivty extends Activity {
    private String bloodId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_certificheck_acitivty);
        bloodId = getIntent().getExtras().getString("bloodID");
        ((TextView) findViewById(R.id.txt_bloodid)).setText(bloodId);

        final String finalbloodId = bloodId;
        ((TextView) findViewById(R.id.txt_check_use_card)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final AsyncHttpClient client = new AsyncHttpClient();
                final RequestParams params = new RequestParams();
                params.add("userId", Constant.getUserId());
                params.add("bloodId", finalbloodId);
                AlertDialog.Builder alert_confirm = new AlertDialog.Builder(CertificheckAcitivty.this);
                alert_confirm.setMessage("사용 하시겠습니까?").setCancelable(false).setPositiveButton("확인",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                client.get(Constant.getQueryUse(), params, new UseCardAsyncHttpResponseHandler());
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

    private class UseCardAsyncHttpResponseHandler extends JsonHttpResponseHandler {
        ProgressDialog dialog;
        @Override
        public void onStart() {
            dialog = new ProgressDialog(CertificheckAcitivty.this);
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
                    AlertDialog.Builder alert = new AlertDialog.Builder(CertificheckAcitivty.this);
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
            AlertDialog.Builder alert = new AlertDialog.Builder(CertificheckAcitivty.this);
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
