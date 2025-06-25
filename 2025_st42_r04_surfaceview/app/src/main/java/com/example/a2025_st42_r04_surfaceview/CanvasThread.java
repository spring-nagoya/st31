package com.example.a2025_st42_r04_surfaceview;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceHolder;

import java.util.ArrayList;
import java.util.Random;

public class CanvasThread implements Runnable {
    ArrayList<BallObj> ballObjs = new ArrayList<>();

    private GameTimer timer = new GameTimer();

    public  int screenWidth, screenHeight;

    public SurfaceHolder holder;

    public Thread thread = null;

    public CanvasThread(){
        int BALL_MAX = 1000;
        for (int i = 0; i < BALL_MAX; i++) {
            BallObj workball = new BallObj();
            workball.paint = new Paint();
            Random rand = new Random();
            workball.radius = rand.nextInt(200) + 20;
            workball.ballX = rand.nextInt(500) + 100;
            workball.ballY = rand.nextInt(2000) + 100;
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
            doDraw();
            if (timer.timeCheck()){
                break;
            }
        }
    }

    public  void  doDraw() {

        Canvas canvas = null;
        try {
            canvas = holder.lockCanvas();
            if (canvas != null) {
                canvas.drawColor(Color.WHITE);
                for (int i = 0; i < ballObjs.size(); i++) {
                    canvas.drawCircle(
                            ballObjs.get(i).ballX,
                            ballObjs.get(i).ballY,
                            ballObjs.get(i).radius,
                            ballObjs.get(i).paint
                    );
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (canvas != null) {
                holder.unlockCanvasAndPost(canvas);
            }
        }


    }
}
