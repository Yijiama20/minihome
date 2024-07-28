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
        // TODO:replace the image view with AppIconView
        AppIconView appIconView;
        
        if (view == null) {
            // create the sub component of grid view
            imageView = new ImageView(context);
            imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
            imageView.setLayoutParams(new ViewGroup.LayoutParams(100,100));
            
            appIconView = new AppIconView(context);
            appIconView.setScaleType(AppIconView.ScaleType.FIT_CENTER);
            appIconView.setLayoutParams(new ViewGroup.LayoutParams(100,100));
        } else {
            appIconView = (AppIconView) view;
            imageView = (ImageView) view;
        }
//        Log.i("appList",appList.toString());
        ResolveInfo appInfo = appList.get(i);
        appIconView.setImageDrawable(appInfo.activityInfo.loadIcon(context.getPackageManager()));
        imageView.setImageDrawable(appInfo.activityInfo.loadIcon(context.getPackageManager()));
        // return appIconView;
        return imageView;
    }
}
