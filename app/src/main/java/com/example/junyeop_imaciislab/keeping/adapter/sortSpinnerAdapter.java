package com.example.junyeop_imaciislab.keeping.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.example.junyeop_imaciislab.keeping.R;

import java.util.ArrayList;

/**
 * Created by junyeop_imaciislab on 2015. 11. 11..
 */
public class sortSpinnerAdapter extends ArrayAdapter<String> {
    private Context context;
    ArrayList<String> stringArrayAdapter;

    public sortSpinnerAdapter(Context context, ArrayList<String> stringArrayAdapter) {
        super(context, R.layout.spinner_item_sortlist, stringArrayAdapter);
        this.context = context;
        this.stringArrayAdapter = stringArrayAdapter;
    }

    @Override
    public View getDropDownView(int position, View cnvtView, ViewGroup parent) {
        return getCustomView(position, cnvtView, parent);
    }

    @Override
    public View getView(int pos, View cnvtView, ViewGroup parent) {
        return getCustomView(pos, cnvtView, parent);
    }

    public View getCustomView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        convertView = inflater.inflate(R.layout.spinner_item_sortlist, parent, false);
        ImageView spinnerItemImageview = (ImageView)convertView.findViewById(R.id.img_spinner_item);
        String positionString = stringArrayAdapter.get(position);

        float scale = context.getResources().getDisplayMetrics().density;
        int dp = (int) (scale + 0.5f);
        if(positionString.compareTo("donationDateDesc")==0) {
            spinnerItemImageview.setImageResource(R.drawable.cabinet_popup_content1);
            spinnerItemImageview.getLayoutParams().width = 109*dp;
        } else if(positionString.compareTo("donationDateAsc")==0) {
            spinnerItemImageview.setImageResource(R.drawable.cabinet_popup_content2);
        } else if(positionString.compareTo("givenCard")==0) {
            spinnerItemImageview.setImageResource(R.drawable.cabinet_popup_content3);
            spinnerItemImageview.getLayoutParams().width = 82*dp;
        } else if(positionString.compareTo("myCard")==0) {
            spinnerItemImageview.setImageResource(R.drawable.cabinet_popup_content4);
            spinnerItemImageview.getLayoutParams().width = 91*dp;
        }
        return convertView;
    }

}
