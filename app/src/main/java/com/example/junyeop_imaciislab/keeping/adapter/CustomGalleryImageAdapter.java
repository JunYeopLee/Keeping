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

    public CustomGalleryImageAdapter(Context c) {
        mContext = c;
        mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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
        View mview = convertView;
        if (mview == null) {
            mview = mInflater.inflate(R.layout.gallery_item, null);
        }
        image = (ImageView) mview.findViewById(R.id.image);
        return mview;
    }
}
