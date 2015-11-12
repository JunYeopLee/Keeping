package com.example.junyeop_imaciislab.keeping.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.example.junyeop_imaciislab.keeping.R;

/**
 * Created by LeeJunYeop on 2015-11-12.
 */
public class CustomGalleryImageAdapter extends BaseAdapter {
    private Context mContext;
    private ImageView image;
    private LayoutInflater mInflater;
    private int count;

    public CustomGalleryImageAdapter(Context c,int count) {
        mContext = c;
        mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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
        return convertView;
    }
}
