package com.example.junyeop_imaciislab.keeping.util;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.widget.ListView;
import android.widget.Toast;

import com.example.junyeop_imaciislab.keeping.R;
import com.example.junyeop_imaciislab.keeping.adapter.inventoryListAdapter;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

/**
 * Created by LeeJunYeop on 2015-11-11.
 */
public class inventoryListHandler extends JsonHttpResponseHandler {
    private ArrayList<inventoryListDAO> inventoryListDAOArrayList;
    private ProgressDialog dialog;
    private Context context;
    private ListView inventoryListView;

    public inventoryListHandler(Context context) {
        this.context = context;
        inventoryListDAOArrayList = new ArrayList<>();
    }

    @Override
    public void onStart() {
        dialog = new ProgressDialog(context);
        dialog.setMessage("잠시만 기다려 주세요.");
        dialog.setCancelable(false);
        dialog.show();
    }

    @Override
    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
        try {
            if(response.getBoolean("success")) {
                JSONArray jsonArray = response.getJSONArray("result");
                for( int i  = 0 ; i < jsonArray.length() ; i++ ) { // get trade information
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    inventoryListDAO inventoryListDAO = convertJsonObjectToInventoryListDAO(jsonObject);
                    inventoryListDAOArrayList.add(inventoryListDAO);
                }
                inventoryListAdapter inventoryListAdapter = new inventoryListAdapter(((Activity)context),inventoryListDAOArrayList);
                inventoryListView = (ListView)((Activity) context).findViewById(R.id.listview_cardlist);
                inventoryListView.setAdapter(inventoryListAdapter);
                inventoryListView.invalidate();
            }
        } catch (JSONException e) {
            e.printStackTrace();
            if(dialog != null && dialog.isShowing()){
                dialog.dismiss();
            }
        }

        if(dialog != null && dialog.isShowing()){
            dialog.dismiss();
        }
        Toast.makeText(context, "검색 성공", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onFailure(int statusCode, Header[] headers, Throwable throwable,JSONObject errorResponse) {
        if(dialog != null && dialog.isShowing()){
            dialog.dismiss();
        }
        AlertDialog.Builder alert = new AlertDialog.Builder(context);
        alert.setPositiveButton("확인", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        alert.setMessage("[태그정보 수신 실패]네트워크 연결이 불안정 합니다").show();
    }

    private inventoryListDAO convertJsonObjectToInventoryListDAO(JSONObject jsonObject) throws JSONException {
        inventoryListDAO inventoryListDAO = new inventoryListDAO();

        return inventoryListDAO;
    }
}
