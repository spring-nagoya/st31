package com.example.a2025_st42_r04_surfaceview;

public class GameTimer {
    private final int TIME = 5;
    private  long mNowTime;
    private long mStartTime;

    public GameTimer(){
        TimerReset();
    }
    public void TimerReset() {
        this.mNowTime = TIME;
    }

    public void startCountDown() {
        mStartTime = System.currentTimeMillis();
    }

    public  long getNowTime(){
        return this.mNowTime;
    }
    public boolean timeCheck() {
        boolean flg = false;

        long carrent = System.currentTimeMillis();
        long time_diff = (carrent - mStartTime) / 1000;
        mNowTime = TIME - time_diff;
        if (time_diff >= 30) {
            mNowTime = 0;
        } else {
            mNowTime = TIME - time_diff;
        }
        if (mNowTime <= 0) {
            flg = true; // Time is up
        }
        return flg;
    }
}
