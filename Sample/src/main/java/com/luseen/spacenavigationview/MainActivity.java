package com.luseen.spacenavigationview;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.luseen.spacenavigation.SpaceItem;
import com.luseen.spacenavigation.SpaceNavigationView;
import com.luseen.spacenavigation.SpaceOnClickListener;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final SpaceNavigationView spaceNavigationView = (SpaceNavigationView) findViewById(R.id.space);
        spaceNavigationView.addSpaceItem(new SpaceItem("HOME", R.drawable.home));
        spaceNavigationView.addSpaceItem(new SpaceItem("CALL", R.drawable.bell));
//        spaceNavigationView.addSpaceItem(new SpaceItem("NEWS", R.drawable.home));
//       spaceNavigationView.addSpaceItem(new SpaceItem("MAP", R.drawable.bell));
        //spaceNavigationView.addSpaceItem(new SpaceItem("MAP", R.drawable.bell));
//        spaceNavigationView.showIconOnly();
//
        //spaceNavigationView.setSpaceItemIconSize((int) getResources().getDimension(R.dimen.space_item_icon_only_size));
        //spaceNavigationView.setSpaceItemTextSize((int) getResources().getDimension(R.dimen.space_item_text_default_sizsssssssssssse));


        spaceNavigationView.setSpaceOnClickListener(new SpaceOnClickListener() {
            @Override
            public void onCentreButtonClick() {
                Log.e("onCentreButtonClick ", "onCentreButtonClick");
            }

            @Override
            public void onItemClick(int itemIndex, String itemName) {
                Log.e("onItemClick ", "" + itemIndex + " " + itemName);
            }
        });

        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                spaceNavigationView.changeCurrentItem(3);
            }
        });

        //spaceNavigationView.addSpaceItem(new SpaceItem("Test2",android.R.drawable.presence_away));
        //spaceNavigationView.addSpaceItem(new SpaceItem("Test2",android.R.drawable.presence_away));
//        spaceNavigationView.setSpaceBackgroundColor(ContextCompat.getColor(this,android.R.color.holo_green_dark));
//        spaceNavigationView.setCentreButtonColor(ContextCompat.getColor(this,android.R.color.holo_orange_light));
        //spaceNavigationView.setCentreButtonIcon(android.R.drawable.sym_call_incoming);

//        FrameLayout r = (FrameLayout) findViewById(R.id.activity_main);
//        FrameLayout.LayoutParams mainContentParams =
//                new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//        centreContent.setBackgroundColor(ContextCompat.getColor(this, R.color.neee));
//        mainContentParams.setMargins(0, 0, 0, 0);
//        r.addView(centreContent, mainContentParams);

    }
}
