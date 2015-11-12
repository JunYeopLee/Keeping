package com.example.junyeop_imaciislab.keeping.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;

import com.example.junyeop_imaciislab.keeping.R;

/**
 * Created by LeeJunYeop on 2015-11-12.
 */
public class CustomGalleryImageAdapter extends BaseAdapter {
    private Context context;
    private ImageView image;
    private LayoutInflater mInflater;
    private int count;

    public CustomGalleryImageAdapter(Context c,int count) {
        context = c;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.count = count;
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
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.gallery_item, null);
        }
        float scale = context.getResources().getDisplayMetrics().density;
        int dp = (int) (scale + 0.5f);
        Gallery.LayoutParams lp = new Gallery.LayoutParams(271 * dp, 246 * dp);
        convertView.setLayoutParams(lp);
        return convertView;
    }
}
