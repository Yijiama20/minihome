package com.example.minihome;

import android.content.Context;
import android.content.pm.ResolveInfo;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.content.pm.PackageManager;
import android.widget.ImageView;
import android.widget.TextClock;
import android.widget.TextView;

import java.util.List;

public class GridViewAdapter extends BaseAdapter {

    private List<ResolveInfo> appList;
    private Context context;

    public GridViewAdapter(List<ResolveInfo> appList,Context context){
        this.context = context;
        this.appList = appList;
    }

    @Override
    public int getCount() {
        return appList.size();
    }

    @Override
    public Object getItem(int i) {
        return appList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ImageView imageView;

        if (view == null) {
            // create the sub component of grid view
            imageView = new ImageView(context);
            imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
            imageView.setLayoutParams(new ViewGroup.LayoutParams(100,100));
        } else {
            imageView = (ImageView) view;
        }
//        Log.i("appList",appList.toString());
        ResolveInfo appInfo = appList.get(i);
        imageView.setImageDrawable(appInfo.activityInfo.loadIcon(context.getPackageManager()));
        return imageView;
    }
}
