package com.example.a2025_st42_r04_surfaceview;

import android.content.Context;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;

public class St42SurfaceView extends SurfaceView implements SurfaceHolder.Callback {
    private SurfaceHolder holder;
    public St42SurfaceView(Context context, SurfaceView surfaceView) {
        super(context);
        holder = surfaceView.getHolder();
        holder.addCallback(this);
    }


    @Override
    public void surfaceCreated(@NonNull SurfaceHolder holder) {

    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder holder, int format, int width, int height) {
        CanvasThread cThread = new CanvasThread();
        cThread.screenWidth = width;
        cThread.screenHeight = height;
    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder holder) {

    }
}
