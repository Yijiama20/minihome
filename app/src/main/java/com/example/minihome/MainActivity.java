package com.example.minihome;

import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.util.Log;

import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    // ResolveInfo is from PackageManager： ctrl+click to see details
    private List<ResolveInfo> mApps;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadApps(); // get app list
        GridViewAdapter adapter = new GridViewAdapter(mApps,this); // create adapter
        GridView gridView = (GridView) findViewById(R.id.appGrid);
        gridView.setAdapter(adapter);

        // define onclick event
        gridView.setOnItemClickListener(listener);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.appGrid), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private AdapterView.OnItemClickListener listener = new AdapterView.OnItemClickListener(){
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            ResolveInfo appInfo = mApps.get(i);
            // get package name
            String pkName = appInfo.activityInfo.packageName;
            // get main class name
            String clsName = appInfo.activityInfo.name;
            // get the launcher activity component
            ComponentName component = new ComponentName(pkName,clsName);

            Intent intent = new Intent();
            intent.setComponent(component);
            startActivity(intent);
        }
    };


    private void loadApps() {
        // define a launcher discover pattern intent mainIntent
        Intent mainIntent = new Intent(Intent.ACTION_MAIN,null);
        mainIntent.addCategory(Intent.CATEGORY_LAUNCHER);

        // use PackageManager api to filter out all the activities defined as action_main and category_launcher
        mApps = getPackageManager().queryIntentActivities(mainIntent,0);

        // log check if mApps get data
//        Log.i("mApps",mApps.toString());
    }
}