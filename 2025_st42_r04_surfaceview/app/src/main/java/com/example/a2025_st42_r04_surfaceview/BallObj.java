package com.example.a2025_st42_r04_surfaceview;

import android.graphics.Paint;

public class BallObj {
    public float radius;
    public int ballX;
    public int ballY;
    public float dx;
    public float dy;
    public Paint paint;
    public void move(int screenWidth, int screenHeight) {
        if(ballX + radius > screenWidth || ballX - radius < 0) {
            dx = -dx;
        }
        if(ballY + radius > screenHeight || ballY - radius < 0) {
            dy = -dy;
        }

        ballX += dx;
        ballY += dy;
    }
}
