package com.example.junyeop_imaciislab.keeping;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.TextView;

import com.example.junyeop_imaciislab.keeping.util.Constant;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class HomeActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        String name = getIntent().getExtras().getString("name");
        String bloodType = getIntent().getExtras().getString("bloodType");
        ((TextView)findViewById(R.id.home_name)).setText(name);
                ((TextView)findViewById(R.id.home_bloodtype)).setText(bloodType);

        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();
        params.add("userId",getIntent().getExtras().getString("userId"));
        client.get(Constant.getQueryUserInfo(), params, new UserInfoAsyncHttpResponseHandler());
    }

    private class UserInfoAsyncHttpResponseHandler extends JsonHttpResponseHandler {
        ProgressDialog dialog;
        @Override
        public void onStart() {
            dialog = new ProgressDialog(HomeActivity.this);
            dialog.setMessage("잠시만 기다려 주세요.");
            dialog.setCancelable(false);
            dialog.show();
        }
        @Override
        public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
            try {
                if(response.getBoolean("success")) {
                    JSONObject jsonObject = response.getJSONObject("data");
                    ((TextView)findViewById(R.id.home_id)).setText(jsonObject.getString("id"));
                    ((TextView)findViewById(R.id.home_docnt)).setText(jsonObject.getString("doCnt"));
                    ((TextView)findViewById(R.id.home_getcnt)).setText(jsonObject.getString("getCnt"));
                    ((TextView)findViewById(R.id.home_givecnt)).setText(jsonObject.getString("giveCnt"));
                    ((TextView)findViewById(R.id.home_recentblooddate)).setText(jsonObject.getString("recentBloodDate"));
                    ((TextView)findViewById(R.id.home_nextblooddate)).setText(jsonObject.getString("nextBloodDate"));
                    if(dialog != null && dialog.isShowing()){
                        dialog.dismiss();
                    }
                } else {
                    AlertDialog.Builder alert = new AlertDialog.Builder(HomeActivity.this);
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
            AlertDialog.Builder alert = new AlertDialog.Builder(HomeActivity.this);
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
