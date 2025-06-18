package com.example.a2025_st42_r04_surfaceview;

import android.graphics.Paint;

import java.util.ArrayList;
import java.util.Random;

public class CanvasThread implements Runnable {
    ArrayList<BallObj> ballObjs = new ArrayList();
    private final int BALL_MAX = 1;

    private GameTimer timer = new GameTimer();

    public  int screenWidth, screenHeight;

    public CanvasThread(){
        for (int i = 0; i < BALL_MAX; i++) {
            BallObj workball = new BallObj();
            workball.paint = new Paint();
            Random rand = new Random();
            workball.radius = rand.nextInt(200) + 20;
            workball.ballX = rand.nextInt(300) + 40;
            workball.ballY = rand.nextInt(300) + 40;
            workball.dx = rand.nextInt(10) - 5;
            workball.dy = rand.nextInt(10) - 5;

            int r = rand.nextInt(256);
            int g = rand.nextInt(256);
            int b = rand.nextInt(256);
            workball.paint.setARGB(255, r, g, b);

            ballObjs.add(workball);
        }
    }

    @Override
    public  void run() {
        timer.startCountDown();
        while (true){
            for (int i = 0; i < ballObjs.size(); i++) {
                ballObjs.get(i).move(screenWidth, screenHeight);
            }
            if (timer.timeCheck()){
                timer.TimerReset();
            }
        }
    }
}
