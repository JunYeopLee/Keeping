package com.example.junyeop_imaciislab.keeping.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.junyeop_imaciislab.keeping.DonationCampaignActivity;
import com.example.junyeop_imaciislab.keeping.R;

import java.util.ArrayList;

/**
 * Created by LeeJunYeop on 2015-11-12.
 */
public class CustomGalleryImageAdapter extends BaseAdapter {
    private Context context;
    private ImageView image;
    private LayoutInflater mInflater;
    private ArrayList<Integer> DrawableList;
    private int count;

    public CustomGalleryImageAdapter(Context c,int count) {
        context = c;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.count = count;
        DrawableList = new ArrayList<>();
        DrawableList.add(R.drawable.donation_campaign_card1);
        DrawableList.add(R.drawable.donation_campaign_card2);
        DrawableList.add(R.drawable.donation_campaign_card3);
    }

    @Override
    public int getCount() {
        return count;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.gallery_item, null);
        }
        float scale = context.getResources().getDisplayMetrics().density;
        int dp = (int) (scale + 0.5f);
        Gallery.LayoutParams lp = new Gallery.LayoutParams(271 * dp, 246 * dp);
        convertView.setLayoutParams(lp);
        ((RelativeLayout)convertView.findViewById(R.id.layout_campaign)).setBackground(context.getResources().getDrawable(DrawableList.get(position)));
        ((ImageButton)convertView.findViewById(R.id.btn_lets_donation_campaign)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DonationCampaignActivity.class).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("position",position);
                context.startActivity(intent);
            }
        });
        return convertView;
    }
}
