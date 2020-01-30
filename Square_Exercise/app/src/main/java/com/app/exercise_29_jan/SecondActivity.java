package com.app.exercise_29_jan;

import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;


public class SecondActivity extends AppCompatActivity {

    boolean isPause = false;
    boolean isTop = true;
    boolean isBottom = false;

    boolean goLeft = true;
    boolean goRight = false;
    int screenHeight;
    int screenWidth;
    private int mInterval = 10;
    private Handler threadHandler;
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
        threadHandler = new Handler();

        float randomX = (float)getRandomDoubleBetweenRange(xEdge - 350);
        float randomY = (float)getRandomDoubleBetweenRange(yEdge - 350);

        System.out.println(randomX);
        System.out.println(randomY);

        bounceView.setX(randomX);
        bounceView.setY(randomY);

        startRepeatingTask();

        //Pause the box from moving on tap
        findViewById(R.id.bounceLayout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isPause) {
                    isPause = false;
                    startRepeatingTask();
                } else {
                    isPause = true;
                    stopRepeatingTask();
                }
            }
        });
    }

    Runnable bounceBoxTask = new Runnable() {
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

                        if (bounceView.getX() > xEdge - 50) {
                            bounceView.setX(xEdge - 50);
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

                        if (bounceView.getX() > xEdge - 50) {
                            bounceView.setX(xEdge - 50);
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
            } finally {
                threadHandler.postDelayed(bounceBoxTask, mInterval);
            }
        }
    };
    void startRepeatingTask() {
        bounceBoxTask.run();
    }

    void stopRepeatingTask() {
        threadHandler.removeCallbacks(bounceBoxTask);
    }
    public static int getScreenWidth() {
        return Resources.getSystem().getDisplayMetrics().widthPixels;
    }

    public static int getScreenHeight() {
        return Resources.getSystem().getDisplayMetrics().heightPixels;
    }

    public static double getRandomDoubleBetweenRange(double max){
        double randomDouble = Math.random();
        randomDouble = randomDouble * max + 1;
        return randomDouble;
    }
}
