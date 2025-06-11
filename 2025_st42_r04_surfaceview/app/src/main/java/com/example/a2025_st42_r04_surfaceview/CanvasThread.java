package com.example.a2025_st42_r04_surfaceview;

import android.graphics.Paint;

import java.util.Random;

public class CanvasThread {
    private final int BALL_MAX = 1;

    public CanvasThread(){
        for (int i = 0; i < BALL_MAX; i++) {
            BallObj workball = new BallObj();
            workball.paint = new Paint();
            Random rand = new Random();
            workball.radius = rand.nextInt(200) + 20;
            workball.ballX = rand.nextInt(300) + 40;
            workball.ballY = rand.nextInt(300) + 40;
        }
    }

    public  void run() {
        // This method would contain the logic to update the canvas and draw the balls
        // For example, updating positions based on dx and dy, checking for collisions, etc.
        // This is a placeholder for the actual drawing logic.
    }
}
