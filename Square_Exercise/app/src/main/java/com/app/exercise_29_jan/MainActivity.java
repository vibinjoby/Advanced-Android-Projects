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
            private GestureDetector gestureDetector = new GestureDetector(MainActivity.this,
                    new GestureDetector.SimpleOnGestureListener() {
                @Override
                public boolean onDoubleTap(MotionEvent e) {
                    return super.onDoubleTap(e);
                }
            });

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                touchX = (int)event.getX();
                touchY = (int)event.getY();
                startRepeatingTask();
                gestureDetector.onTouchEvent(event);
                return true;
            }
        });
    }
    Runnable bounceBoxTask = new Runnable() {
        @Override
        public void run() {
            try {
                // four coordinates

                // -x and + y
                if (touchX < squareView.getX() && touchY > squareView.getY()) {
                    squareView.setX(squareView.getX() - 5);
                    squareView.setY(squareView.getY() + 5);
                    if (touchX > squareView.getX()) {
                        squareView.setX(touchX);
                        stopRepeatingTask();
                    }
                }
                // x and -y
                else if (touchX > squareView.getX() && touchY < squareView.getY()) {
                    squareView.setX(squareView.getX() + 5);
                    squareView.setY(squareView.getY() - 5);
                    if (touchX < squareView.getX()) {
                        squareView.setX(touchX);
                        stopRepeatingTask();
                    }
                }
                // -x and -y
                else if (touchX < squareView.getX() && touchY < squareView.getY()) {
                    squareView.setX(squareView.getX() -5);
                    squareView.setY(squareView.getY() - 5);
                    if (touchX > squareView.getX()) {
                        squareView.setX(touchX);
                        stopRepeatingTask();
                    }
                }
                // x and y
                else if (touchX > squareView.getX() && touchY > squareView.getY()) {
                    squareView.setX(squareView.getX() +5);
                    squareView.setY(squareView.getY() + 5);
                    if (touchX < squareView.getX()) {
                        squareView.setX(touchX);
                        stopRepeatingTask();
                    }
                }

                else if (touchX == squareView.getX() && touchY < squareView.getY()) {
                    squareView.setY(squareView.getY() - 5);
                }
                else if (touchX == squareView.getX() && touchY > squareView.getY()) {
                    squareView.setY(squareView.getY() + 5);
                }

                else if (touchY == squareView.getY() && touchX < squareView.getX()) {
                    squareView.setX(squareView.getX() - 5);
                }
                else if (touchY == squareView.getY() && touchX > squareView.getX()) {
                    squareView.setX(squareView.getX() + 5);
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
