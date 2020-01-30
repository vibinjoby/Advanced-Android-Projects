package com.app.exercise_29_jan;

import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;


public class SecondActivity extends AppCompatActivity {


    boolean isTop = true;
    boolean isBottom = false;

    boolean goLeft = true;
    boolean goRight = false;
    int screenHeight;
    int screenWidth;
    private int mInterval = 10;
    private Handler mHandler;
    View bounceView;
    int xEdge;
    int yEdge;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_activity);
        bounceView = (View) findViewById(R.id.bounceSqView);
        screenHeight = getScreenHeight();
        screenWidth = getScreenWidth();
        xEdge = screenWidth  - 100;
        yEdge = screenHeight  - 100;
        mHandler = new Handler();



        float randomX = 5;//getRandomDoubleBetweenRange(0,xEdge);
        float randomY = 0;//getRandomDoubleBetweenRange(0,yEdge);


        bounceView.setX(randomX);
        bounceView.setY(randomY);

        startRepeatingTask();


        System.out.println("screen width " + (xEdge));
        System.out.println("screen height " + (yEdge));

    }
    Runnable mStatusChecker = new Runnable() {
        @Override
        public void run() {
            try {
                // two directions

                // for bottom
                // -x and +y after reaching edge
                // +x and +y after reaching edge

                //for top
                // -x and -y after reaching edge
                // x and -y after reaching edge

                // -x and + y

                if(isBottom) {
                    if (goLeft) {
                        bounceView.setX(bounceView.getX() - 5);
                        bounceView.setY(bounceView.getY() + 5);

                        if (bounceView.getX() < 0) {
                            bounceView.setX(5);
                            goRight = true;
                            goLeft = false;
                        }

                    } if(goRight) {
                        bounceView.setX(bounceView.getX() + 5);
                        bounceView.setY(bounceView.getY() + 5);

                        if (bounceView.getX() > xEdge) {
                            bounceView.setX(xEdge);
                            goRight = false;
                            goLeft = true;
                        }
                    }

                    if(bounceView.getY() > yEdge - 350) {
                        isBottom = false;
                        isTop = true;
                        if(goLeft) {
                            goRight = false;
                        } else {
                            goRight = true;
                        }
                    }
                }
                if(isTop) {
                    if (goLeft) {
                        bounceView.setX(bounceView.getX() - 5);
                        bounceView.setY(bounceView.getY() - 5);

                        if (bounceView.getX() < 0) {
                            bounceView.setX(5);
                            goRight = true;
                            goLeft = false;
                        }

                    } if(goRight) {
                        bounceView.setX(bounceView.getX() + 5);
                        bounceView.setY(bounceView.getY() - 5);

                        if (bounceView.getX() > xEdge) {
                            bounceView.setX(xEdge);
                            goRight = false;
                            goLeft = true;
                        }
                    }
                    if(bounceView.getY() <= 0) {
                        isBottom = true;
                        isTop = false;
                        if(goLeft) {
                            goRight = false;
                        } else {
                            goRight = true;
                        }
                    }
                }
                System.out.println(" square x position ==> "+bounceView.getX());
                System.out.println(" square y position ==> "+bounceView.getY());

            } finally {
                mHandler.postDelayed(mStatusChecker, mInterval);
            }
        }
    };
    void startRepeatingTask() {
        mStatusChecker.run();
    }

    void stopRepeatingTask() {
        mHandler.removeCallbacks(mStatusChecker);
    }
    public static int getScreenWidth() {
        return Resources.getSystem().getDisplayMetrics().widthPixels;
    }

    public static int getScreenHeight() {
        return Resources.getSystem().getDisplayMetrics().heightPixels;
    }

    public static float getRandomDoubleBetweenRange(double min, double max){
        double x = (Math.random()*((max-min)+1))+min;
        return (float)x;
    }
}
