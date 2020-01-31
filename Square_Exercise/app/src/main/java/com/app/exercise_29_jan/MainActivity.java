package com.app.exercise_29_jan;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private int mInterval = 10;
    private Handler mHandler;
    View squareView;
    int touchX;
    int touchY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        squareView = (View)findViewById(R.id.squareView);
        mHandler = new Handler();

        findViewById(R.id.mainView).setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                touchX = (int)event.getX();
                touchY = (int)event.getY();

                startRepeatingTask();



                return true;
            }
        });
    }
    Runnable bounceBoxTask = new Runnable() {
        @Override
        public void run() {
            int incrSteps = 5;
            try {
                // four coordinates

                // -x and + y
                if (touchX < squareView.getX() && touchY > squareView.getY()) {
                    squareView.setX(squareView.getX() - incrSteps);
                    squareView.setY(squareView.getY() + incrSteps);
                    if (touchX > squareView.getX()) {
                        squareView.setX(touchX);
                        stopRepeatingTask();
                    }
                }
                // x and -y
                else if (touchX > squareView.getX() && touchY < squareView.getY()) {
                    squareView.setX(squareView.getX() + incrSteps);
                    squareView.setY(squareView.getY() - incrSteps);
                    if (touchX < squareView.getX()) {
                        squareView.setX(touchX);
                        stopRepeatingTask();
                    }
                }
                // -x and -y
                else if (touchX < squareView.getX() && touchY < squareView.getY()) {
                    squareView.setX(squareView.getX() -incrSteps);
                    squareView.setY(squareView.getY() - incrSteps);
                    if (touchX > squareView.getX()) {
                        squareView.setX(touchX);
                        stopRepeatingTask();
                    }
                }
                // x and y
                else if (touchX > squareView.getX() && touchY > squareView.getY()) {
                    squareView.setX(squareView.getX() +incrSteps);
                    squareView.setY(squareView.getY() + incrSteps);
                    if (touchX < squareView.getX()) {
                        squareView.setX(touchX);
                        stopRepeatingTask();
                    }
                }

                else if (touchX == squareView.getX() && touchY < squareView.getY()) {
                    squareView.setY(squareView.getY() - incrSteps);
                }
                else if (touchX == squareView.getX() && touchY > squareView.getY()) {
                    squareView.setY(squareView.getY() + incrSteps);
                }

                else if (touchY == squareView.getY() && touchX < squareView.getX()) {
                    squareView.setX(squareView.getX() - incrSteps);
                }
                else if (touchY == squareView.getY() && touchX > squareView.getX()) {
                    squareView.setX(squareView.getX() + incrSteps);
                }
                if (touchY == squareView.getY() && touchX == squareView.getX()) {
                    System.out.println("current x ==>" + squareView.getX());
                    System.out.println("current y ==>" + squareView.getY());

                    System.out.println("touch x ==> "+ touchX);
                    System.out.println("touch y ==> "+ touchY);
                    stopRepeatingTask();
                }
            } finally {
                mHandler.postDelayed(bounceBoxTask, mInterval);
            }
        }
    };
    void startRepeatingTask() {
        bounceBoxTask.run();
    }

    void stopRepeatingTask() {
        Random rnd = new Random();
        int color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
        squareView.setBackgroundColor(color);
        mHandler.removeCallbacks(bounceBoxTask);
    }
}
