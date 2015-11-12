package com.example.junyeop_imaciislab.keeping.adapter;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.junyeop_imaciislab.keeping.CertificheckAcitivty;
import com.example.junyeop_imaciislab.keeping.R;
import com.example.junyeop_imaciislab.keeping.util.inventoryListDAO;

import java.util.ArrayList;

/**
 * Created by junyeop_imaciislab on 2015. 11. 13..
 */
public class CertifiChoiceListAdapter  extends ArrayAdapter<inventoryListDAO> {
    private final Activity context;
    private ArrayList<inventoryListDAO> inventoryListDAOArrayList;
    private float scale;

    public CertifiChoiceListAdapter(Activity context, ArrayList<inventoryListDAO> inventoryListDAOArrayList) {
        super(context, R.layout.listview_item_inventorylist, inventoryListDAOArrayList);
        this.context = context;
        this.inventoryListDAOArrayList = inventoryListDAOArrayList;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        if (view == null) {
            view = inflater.inflate(R.layout.listview_item_inventorylist, null);
        }
        scale = context.getResources().getDisplayMetrics().density;
        int dp = (int) (scale + 0.5f);
        AbsListView.LayoutParams lp = new AbsListView.LayoutParams(320 * dp, 60 * dp);
        view.setLayoutParams(lp);
        //((LinearLayout)view).setLayoutParams(lp);

        final inventoryListDAO inventoryListDAO = inventoryListDAOArrayList.get(position);
        TextView cardNumberTextView = (TextView)view.findViewById(R.id.txt_card_number);
        cardNumberTextView.setText(inventoryListDAO.getCardNumber());

        TextView dateTextView = (TextView)view.findViewById(R.id.txt_date);
        dateTextView.setText(inventoryListDAO.getDonationDate());

        LinearLayout layoutBackground = (LinearLayout)view.findViewById(R.id.layout_listview_inven);
        if(inventoryListDAO.getIsGiven()) {
            layoutBackground.setBackground(context.getResources().getDrawable(R.drawable.cabinet_list_certif2_head_new));
        } else {
            layoutBackground.setBackground(context.getResources().getDrawable(R.drawable.cabinet_list_certif_head_new));
        }

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, CertificheckAcitivty.class).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("bloodID",inventoryListDAO.getCardNumber());
                context.startActivity(intent);
            }
        });
        return view;
    }
}
