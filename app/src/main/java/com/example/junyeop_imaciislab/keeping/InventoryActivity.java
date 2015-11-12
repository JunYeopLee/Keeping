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
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Spinner;

import com.example.junyeop_imaciislab.keeping.adapter.inventoryListAdapter;
import com.example.junyeop_imaciislab.keeping.adapter.sortSpinnerAdapter;
import com.example.junyeop_imaciislab.keeping.util.Constant;
import com.example.junyeop_imaciislab.keeping.util.inventoryListDAO;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import cz.msebera.android.httpclient.Header;

public class InventoryActivity extends Activity {
    public Spinner sortSpinner;
    private Context context;
    private sortSpinnerAdapter sortSpinnerAdapter;
    private ArrayList<String> spinnerArrayList = new ArrayList<>();

    private inventoryListDAO inventoryListDAO;
    private inventoryListAdapter inventoryListAdapter;
    private ListView inventoryListview;
    private ArrayList<inventoryListDAO> inventoryListDAOArrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventory);
        context = this;
        findViewById(R.id.btn_inven_refresh).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resume();
            }
        });
        init();
    }

    @Override
    protected void onResume() {
        super.onResume();
        resume();
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

    private void init() {
        sortSpinner = (Spinner)findViewById(R.id.spinner_sort);
        spinnerArrayList.add("donationDateDesc");
        spinnerArrayList.add("donationDateAsc");
        spinnerArrayList.add("givenCard");
        spinnerArrayList.add("myCard");
        sortSpinnerAdapter = new sortSpinnerAdapter(this,spinnerArrayList);
        sortSpinner.setAdapter(sortSpinnerAdapter);

        sortSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                inventoryListview = (ListView) findViewById(R.id.listview_cardlist);
                if (i == 0) {
                    Collections.sort(inventoryListDAOArrayList, new Comparator<inventoryListDAO>() {
                        public int compare(inventoryListDAO s1, inventoryListDAO s2) {
                            return s2.getDonationDate().compareTo(s1.getDonationDate());
                        }
                    });
                    inventoryListAdapter = new inventoryListAdapter((Activity) context, inventoryListDAOArrayList);
                    inventoryListview.setAdapter(inventoryListAdapter);
                } else if (i == 1) {
                    Collections.sort(inventoryListDAOArrayList, new Comparator<inventoryListDAO>() {
                        public int compare(inventoryListDAO s1, inventoryListDAO s2) {
                            return s1.getDonationDate().compareTo(s2.getDonationDate());
                        }
                    });
                    inventoryListAdapter = new inventoryListAdapter((Activity) context, inventoryListDAOArrayList);
                    inventoryListview.setAdapter(inventoryListAdapter);
                } else if (i == 2) {
                    ArrayList<inventoryListDAO> newArrayList = new ArrayList<>();
                    for (int j = 0; j < inventoryListDAOArrayList.size(); j++) {
                        if (inventoryListDAOArrayList.get(j).getIsGiven())
                            newArrayList.add(inventoryListDAOArrayList.get(j));
                    }
                    inventoryListAdapter = new inventoryListAdapter((Activity) context, newArrayList);
                    inventoryListview.setAdapter(inventoryListAdapter);
                } else if (i == 3) {
                    ArrayList<inventoryListDAO> newArrayList = new ArrayList<>();
                    for (int j = 0; j < inventoryListDAOArrayList.size(); j++) {
                        if (!inventoryListDAOArrayList.get(j).getIsGiven())
                            newArrayList.add(inventoryListDAOArrayList.get(j));
                    }
                    inventoryListAdapter = new inventoryListAdapter((Activity) context, newArrayList);
                    inventoryListview.setAdapter(inventoryListAdapter);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                Log.d("NOTSPINNER", adapterView.toString());
            }
        });
    }

    private void resume() {
        inventoryListDAOArrayList = new ArrayList<>();
        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();
        String u = Constant.getUserId();
        params.add("userId", Constant.getUserId());
        client.get(Constant.getQueryBloodInfo(), params, new InventoryListAsyncHttpResponseHandler(this));

    }

    private class InventoryListAsyncHttpResponseHandler extends JsonHttpResponseHandler {
        ProgressDialog dialog;
        Context context;
        public InventoryListAsyncHttpResponseHandler(Context context) {
            this.context = context;
        }

        @Override
        public void onStart() {
            dialog = new ProgressDialog(InventoryActivity.this);
            dialog.setMessage("잠시만 기다려 주세요.");
            dialog.setCancelable(false);
            dialog.show();
        }
        @Override
        public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
            try {
                if(response.getBoolean("success")) {
                    JSONObject jsonObject;
                    JSONArray jsonArray = response.getJSONArray("data");
                    inventoryListDAOArrayList = new ArrayList<>();
                    for( int i = 0 ; i < jsonArray.length() ; i++ ) {
                        jsonObject = jsonArray.getJSONObject(i);
                        inventoryListDAO = new inventoryListDAO();
                        inventoryListDAO.setCardNumber(jsonObject.getString("bloodID"));
                        inventoryListDAO.setName(jsonObject.getString("human_NAME"));
                        inventoryListDAO.setId(jsonObject.getString("sender_ID"));
                        inventoryListDAO.setBirthDay(jsonObject.getString("birth_DATE"));
                        inventoryListDAO.setDonationCategory(jsonObject.getString("blood_AMOUNT"));
                        inventoryListDAO.setDonationDate(jsonObject.getString("blood_DATE"));
                        inventoryListDAO.setGender(jsonObject.getString("blood_SEX"));
                        inventoryListDAO.setDonationLocation(jsonObject.getString("blood_LOC"));
                        inventoryListDAO.setIsGiven(jsonObject.getInt("is_DONATE")==1);
                        inventoryListDAO.setMessage(jsonObject.getString("fighting_MSG"));
                        inventoryListDAOArrayList.add(inventoryListDAO);
                    }
                    inventoryListAdapter = new inventoryListAdapter((Activity)this.context,inventoryListDAOArrayList);
                    inventoryListview = (ListView)findViewById(R.id.listview_cardlist);
                    inventoryListview.setAdapter(inventoryListAdapter);
                    inventoryListview.invalidate();
                    if(dialog != null && dialog.isShowing()){
                        dialog.dismiss();
                    }
                } else {
                    AlertDialog.Builder alert = new AlertDialog.Builder(InventoryActivity.this);
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
            Intent intent = new Intent(InventoryActivity.this, MainActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.abc_fade_in, R.anim.abc_fade_out);
            finish();

            if(dialog != null && dialog.isShowing()){
                dialog.dismiss();
            }
            AlertDialog.Builder alert = new AlertDialog.Builder(InventoryActivity.this);
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
