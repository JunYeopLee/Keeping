package com.example.junyeop_imaciislab.keeping.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.junyeop_imaciislab.keeping.R;
import com.example.junyeop_imaciislab.keeping.util.inventoryListDAO;

import java.util.ArrayList;

/**
 * Created by LeeJunYeop on 2015-11-11.
 */
public class inventoryListAdapter  extends ArrayAdapter<inventoryListDAO> {
    private final Activity context;
    private ArrayList<inventoryListDAO> inventoryListDAOArrayList;
    private float scale;

    public inventoryListAdapter(Activity context, ArrayList<inventoryListDAO> inventoryListDAOArrayList) {
        super(context, R.layout.listview_item_inventorylist, inventoryListDAOArrayList);
        this.context = context;
        this.inventoryListDAOArrayList = inventoryListDAOArrayList;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        if(view==null) {
            view = inflater.inflate(R.layout.listview_item_inventorylist,null);
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
            layoutBackground.setBackground(context.getResources().getDrawable(R.drawable.cabinet_list_certif2_head));
        } else {
            layoutBackground.setBackground(context.getResources().getDrawable(R.drawable.cabinet_list_certif_head));
        }

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RelativeLayout donationCardLayout = (RelativeLayout)context.findViewById(R.id.layout_inven_card);
                TextView cardNumberOncardTextview = (TextView)context.findViewById(R.id.txt_card_number_oncard);
                TextView nameTextview = (TextView)context.findViewById(R.id.txt_name);
                TextView birthdayTextview = (TextView)context.findViewById(R.id.txt_birthday);
                TextView donationDateTextview = (TextView)context.findViewById(R.id.txt_donation_date);
                TextView donationLocaTextview = (TextView)context.findViewById(R.id.txt_donation_location);
                TextView idTextview = (TextView)context.findViewById(R.id.txt_id);
                TextView donationCateTextview = (TextView)context.findViewById(R.id.txt_donation_category);
                TextView genderTextview = (TextView)context.findViewById(R.id.txt_gender);
                TextView givingDateTextview = (TextView)context.findViewById(R.id.txt_giving_date);
                ImageButton messageOnCardButton = (ImageButton)context.findViewById(R.id.btn_message_oncard);
                RelativeLayout invenCardLayout = (RelativeLayout)context.findViewById(R.id.layout_inven_card);
                RelativeLayout invenCardMessageLayout = (RelativeLayout)context.findViewById(R.id.layout_inven_card_message);

                if(invenCardLayout.getVisibility()==View.GONE) {
                    invenCardLayout.setVisibility(View.VISIBLE);
                    invenCardLayout.invalidate();
                    invenCardMessageLayout.setVisibility(View.GONE);
                    invenCardMessageLayout.invalidate();
                }
                if(inventoryListDAO.getIsGiven()) {
                    donationCardLayout.setBackground(context.getResources().getDrawable(R.drawable.cabinet_certif2));
                    givingDateTextview.setVisibility(View.VISIBLE); givingDateTextview.invalidate();
                    messageOnCardButton.setVisibility(View.VISIBLE);
                    messageOnCardButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            RelativeLayout invenCardLayout = (RelativeLayout)context.findViewById(R.id.layout_inven_card);
                            invenCardLayout.setVisibility(View.GONE);
                            RelativeLayout invenCardMessageLayout = (RelativeLayout)context.findViewById(R.id.layout_inven_card_message);
                            invenCardMessageLayout.setVisibility(View.VISIBLE);
                            TextView messageTextView = (TextView)context.findViewById(R.id.txt_donation_message);
                            messageTextView.setText(inventoryListDAO.getMessage());
                        }
                    });
                } else {
                    donationCardLayout.setBackground(context.getResources().getDrawable(R.drawable.cabinet_certif));
                    givingDateTextview.setVisibility(View.GONE);
                    messageOnCardButton.setVisibility(View.GONE);
                    givingDateTextview.setText(inventoryListDAO.getGivingDate());
                }

                cardNumberOncardTextview.setText(inventoryListDAO.getCardNumber());
                nameTextview.setText(inventoryListDAO.getName());
                birthdayTextview.setText(inventoryListDAO.getBirthDay());
                donationDateTextview.setText(inventoryListDAO.getDonationDate());
                donationLocaTextview.setText(inventoryListDAO.getDonationLocation());
                idTextview.setText(inventoryListDAO.getId());
                donationCateTextview.setText(inventoryListDAO.getDonationCategory());
                genderTextview.setText(inventoryListDAO.getGender());

                donationCardLayout.invalidate();
            }
        });

        ImageButton backToCardButton = (ImageButton)context.findViewById(R.id.btn_back);
        backToCardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RelativeLayout invenCardLayout = (RelativeLayout)context.findViewById(R.id.layout_inven_card);
                invenCardLayout.setVisibility(View.VISIBLE);
                RelativeLayout invenCardMessageLayout = (RelativeLayout)context.findViewById(R.id.layout_inven_card_message);
                invenCardMessageLayout.setVisibility(View.GONE);
            }
        });
        return view;
    }
}