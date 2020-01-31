package com.app.exercise_29_jan;

import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class ThirdActivity  extends AppCompatActivity {
    RelativeLayout.LayoutParams params;
    boolean isShrink = false;
    List<View> sqrViewLst = new ArrayList<>();
    int width;
    int height;
    int count = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.third_activity);
        final RelativeLayout layout = findViewById(R.id.growingBoxesLayout);
         width = 50;
         height = 50;

        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                // idea keep adding the views to the layout and change the height and width based on the last view's height and width
                // make the color random for each view and start removing the views one by one on shrink
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if(!isShrink && (sqrViewLst.size() == 0 || sqrViewLst.get(sqrViewLst.size() - 1).getWidth() < Resources.getSystem().getDisplayMetrics().widthPixels)) {
                                width += 70;
                                height += 70;

                                params = new RelativeLayout.LayoutParams(width , height );
                                params.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);

                                View view = LayoutInflater.from(getApplicationContext()).inflate(R.layout.square_view, null);
                                view.setLayoutParams(params);
                                Random rnd = new Random();

                                int color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
                                view.setBackgroundColor(color);
                                sqrViewLst.add(view);
                                layout.addView(view, count, params);
                                count++;

                                if (!sqrViewLst.isEmpty()) {
                                    sqrViewLst.get(count - 1).bringToFront();
                                } else {
                                    sqrViewLst.get(0).bringToFront();
                                }
                                if(count == 1) {
                                    layout.removeView(sqrViewLst.get(0));
                                    count -= 1;
                                }
                            } else if (!sqrViewLst.isEmpty() && sqrViewLst.get(sqrViewLst.size() - 1).getWidth() >= 70){
                                isShrink = true;
                                layout.removeView(sqrViewLst.get(sqrViewLst.size() - 1));
                                sqrViewLst.remove(sqrViewLst.size() - 1);
                                count -=1;
                            } else {
                                count = 0;
                                sqrViewLst.clear();
                                isShrink = false;
                                width = 70;
                                height = 70;
                            }
                        }
                    });
                }
        }, 10, 100);
    }
}
