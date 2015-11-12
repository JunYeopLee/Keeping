package com.example.junyeop_imaciislab.keeping;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.example.junyeop_imaciislab.keeping.util.Constant;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.ButterKnife;
import butterknife.OnClick;
import cz.msebera.android.httpclient.Header;

public class LoginActivity extends Activity {
    public static final String TAG = "LoginActivity";
    private SharedPreferences LoginSharedPreferences;
    private SharedPreferences.Editor LoginSharedPreferencesEditor;
    private String username,password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.inject(this);
        LoginSharedPreferences = getSharedPreferences("login", MODE_PRIVATE);
        username = LoginSharedPreferences.getString("username", null);
        password = LoginSharedPreferences.getString("password", null);
        if (!"".equalsIgnoreCase(username) && username != null && password != null ) { // Auto login
            AsyncHttpClient client = new AsyncHttpClient();
            RequestParams params = new RequestParams();
            params.add("id",username);
            params.add("pw",password);
            client.get(Constant.getQueryLogin(), params, new LoginAsyncHttpResponseHandler());
        }
    }

    @OnClick(R.id.btn_log_in)
    public void onclickLogin() {
        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();
        //CookieStore cookieStore = new PersistentCookieStore(getApplicationContext());
        //client.setCookieStore(cookieStore);
        username = ((EditText) findViewById(R.id.edit_id)).getText().toString();
        password = ((EditText) findViewById(R.id.edit_pw)).getText().toString();
        params.add("id", username);
        params.add("pw", password);
        client.get(Constant.getQueryLogin(), params, new LoginAsyncHttpResponseHandler());
    }


    @OnClick(R.id.btn_signup)
    public void onClickSignup() {
        Intent intent = new Intent(LoginActivity.this,SignupActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.abc_fade_in, R.anim.abc_fade_out);
    }

    /**
     *
     * For Login AsyncTask
     *
     * */
    private class LoginAsyncHttpResponseHandler extends JsonHttpResponseHandler {
        ProgressDialog dialog;
        @Override
        public void onStart() {
            dialog = new ProgressDialog(LoginActivity.this);
            dialog.setMessage("잠시만 기다려 주세요.");
            dialog.setCancelable(false);
            dialog.show();
        }
        @Override
        public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
            try {
                if(response.getBoolean("success")) {
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    JSONObject jsonObject = response.getJSONObject("data");
                    intent.putExtra("name",jsonObject.getString("name"));
                    intent.putExtra("bloodType",jsonObject.getString("bloodType"));
                    intent.putExtra("userId",username);
                    startActivity(intent);
                    overridePendingTransition(R.anim.abc_fade_in, R.anim.abc_fade_out);
                    finish();
                    if(dialog != null && dialog.isShowing()){
                        dialog.dismiss();
                    }
                    LoginSharedPreferencesEditor = LoginSharedPreferences.edit();
                    LoginSharedPreferencesEditor.putString("username", username);
                    LoginSharedPreferencesEditor.putString("password", password);
                    LoginSharedPreferencesEditor.apply();
                } else {
                    AlertDialog.Builder alert = new AlertDialog.Builder(LoginActivity.this);
                    alert.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    alert.setMessage("[로그인 실패]아이디 혹은 비밀번호가 틀렸습니다").show();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        @Override
        public void onFailure(int statusCode, Header[] headers, Throwable throwable,JSONObject errorResponse) {
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.abc_fade_in, R.anim.abc_fade_out);
            finish();

            if(dialog != null && dialog.isShowing()){
                dialog.dismiss();
            }
            AlertDialog.Builder alert = new AlertDialog.Builder(LoginActivity.this);
            alert.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            alert.setMessage("[로그인 실패]네트워크 연결이 불안정 합니다").show();
        }
    }

    @OnClick(R.id.layout_login_bg)
    public void onclickBackground() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(findViewById(R.id.layout_login_bg).getWindowToken(), 0);
    }
}


