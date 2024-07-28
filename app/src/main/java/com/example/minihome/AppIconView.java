package com.example.minihome;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.text.TextPaint;
import android.util.AttributeSet;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;


/*
* self defined view component 
* for displaying app with app name
*/
public class AppIconView extends AppCompatImageView {

    private String appName;
    private Typeface textTypeface;
    private Paint paint;

    // TODO:how to make atr avalible to set in layout.xml
    public void setAppName(@NonNull String appName){
        this.appName = appName;
    }

    public AppIconView(@NonNull Context context,@NonNull String appName) {
        super(context);
        initPaint();
        this.appName = appName;
    }

    public AppIconView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initPaint();
    }

    public AppIconView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();
    }

    // TODO:the text paint could be more fexible
    private void initPaint() {
        paint = new TextPaint();
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setTextSize(30);
    }

    // customise feature: app name show
    @Override
    protected void onDraw(Canvas canvas){
        // rescale the app icon in imageview so that there's space for app name in current canvas
        getDrawable().setBounds(getLeft()+100,getTop()+100,getRight()-100,getBottom()-100);
        super.onDraw(canvas);

        if (appName == null || appName.isEmpty()) {
            return;
        }

        canvas.drawText(appName,canvas.getWidth()/2,canvas.getHeight()-75,paint);
    }


}

