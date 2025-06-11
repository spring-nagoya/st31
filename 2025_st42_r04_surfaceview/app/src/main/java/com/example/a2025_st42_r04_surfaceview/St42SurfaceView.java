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
    public void surfaceCreated(@NonNull SurfaceHolder surfaceHolder) {

    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder surfaceHolder) {

    }
}
