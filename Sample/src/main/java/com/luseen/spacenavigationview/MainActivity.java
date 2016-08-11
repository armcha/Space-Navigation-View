package com.luseen.spacenavigationview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.luseen.spacenavigation.BezierView;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final BezierView centreContent = new BezierView(this);

//        FrameLayout r = (FrameLayout) findViewById(R.id.activity_main);
//        FrameLayout.LayoutParams mainContentParams =
//                new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//        centreContent.setBackgroundColor(ContextCompat.getColor(this, R.color.neee));
//        mainContentParams.setMargins(0, 0, 0, 0);
//        r.addView(centreContent, mainContentParams);

    }
}
