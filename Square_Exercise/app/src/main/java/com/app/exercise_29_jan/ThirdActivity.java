package com.app.exercise_29_jan;

import android.content.res.Resources;
import android.graphics.Point;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import java.util.Timer;
import java.util.TimerTask;

public class ThirdActivity  extends AppCompatActivity {
    ConstraintLayout.LayoutParams params;
    boolean isShrink = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.third_activity);
        final View sqrView =  findViewById(R.id.growingSquareView);

        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if(!isShrink && sqrView.getWidth() < Resources.getSystem().getDisplayMetrics().widthPixels) {
                    params = new ConstraintLayout.LayoutParams(sqrView.getWidth() + 20, sqrView.getHeight() + 20);
                } else if (sqrView.getWidth() >= 70){
                    isShrink = true;
                    params = new ConstraintLayout.LayoutParams(sqrView.getWidth() - 20, sqrView.getHeight() - 20);
                } else {
                    isShrink = false;
                }
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Point point = new Point();
                            getWindowManager().getDefaultDisplay().getSize(point);

                            int centerX = point.x / 2;
                            int centerY = point.y / 2;
                            sqrView.setLayoutParams(params);
                            sqrView.setTranslationX(centerX - (sqrView.getWidth() / 2));
                            sqrView.setTranslationY(centerY - (sqrView.getHeight() / 2));
                        }
                    });
                }
        }, 10, 10);
    }
}
