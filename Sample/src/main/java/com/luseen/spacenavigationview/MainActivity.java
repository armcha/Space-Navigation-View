package com.luseen.spacenavigationview;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.luseen.spacenavigation.SpaceItem;
import com.luseen.spacenavigation.SpaceNavigationView;
import com.luseen.spacenavigation.SpaceOnClickListener;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private SpaceNavigationView spaceNavigationView;

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        spaceNavigationView = (SpaceNavigationView) findViewById(R.id.space);
        spaceNavigationView.initWithSaveInstanceState(savedInstanceState);
        spaceNavigationView.addSpaceItem(new SpaceItem("HOME", R.drawable.home));
        spaceNavigationView.addSpaceItem(new SpaceItem("SEARCH", R.drawable.magnify));
        spaceNavigationView.addSpaceItem(new SpaceItem("LIKE", R.drawable.heart));
        spaceNavigationView.addSpaceItem(new SpaceItem("ACCOUNT", R.drawable.account));
        spaceNavigationView.setSpaceBackgroundColor(ContextCompat.getColor(this, R.color.default_color));
//        spaceNavigationView.setCentreButtonIcon(R.drawable.camera);
//        spaceNavigationView.setCentreButtonColor(ContextCompat.getColor(this,R.color.centre_color));
        spaceNavigationView.setActiveSpaceItemColor(ContextCompat.getColor(this, R.color.whiteNew));
//        spaceNavigationView.setInActiveSpaceItemColor(ContextCompat.getColor(this, R.color.inactive_color));

//        spaceNavigationView.addSpaceItem(new SpaceItem("AUDIO", R.drawable.audiobook));
        //spaceNavigationView.addSpaceItem(new SpaceItem("RING", R.drawable.bell));
//        spaceNavigationView.addSpaceItem(new SpaceItem("MAP", R.drawable.google_maps));
        spaceNavigationView.showIconOnly();
        //spaceNavigationView.setSpaceBackgroundColor(Color.parseColor("#ffffff"));

        //spaceNavigationView.setFont(Typeface.createFromAsset(getAssets(), "fonts/NotoSans-Regular.ttf"));
        //spaceNavigationView.addSpaceItem(new SpaceItem("MAP", R.drawable.bell));

        //spaceNavigationView.setSpaceItemIconSize((int) getResources().getDimension(R.dimen.space_item_icon_only_size));
        //spaceNavigationView.setSpaceItemTextSize((int) getResources().getDimension(R.dimen.space_item_text_default_sizsssssssssssse));


        spaceNavigationView.setSpaceOnClickListener(new SpaceOnClickListener() {
            @Override
            public void onCentreButtonClick() {
                Log.e("onCentreButtonClick ", "onCentreButtonClick");
                spaceNavigationView.changeBadgeTextAtIndex(0, 79);
            }

            @Override
            public void onItemClick(int itemIndex, String itemName) {
                Log.e("onItemClick ", "" + itemIndex + " " + itemName);
                spaceNavigationView.hideAllBudges();
            }
        });

        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //spaceNavigationView.changeCurrentItem(3);
                spaceNavigationView.showBadgeAtIndex(1, 233, ContextCompat.getColor(MainActivity.this, android.R.color.holo_orange_light));
                spaceNavigationView.showBadgeAtIndex(2, 4, ContextCompat.getColor(MainActivity.this, R.color.badge_background_color));
                //spaceNavigationView.showBadgeAtIndex(2, 7, ContextCompat.getColor(MainActivity.this, android.R.color.holo_blue_light));
                //spaceNavigationView.showBadgeAtIndex(3, 3, ContextCompat.getColor(MainActivity.this, android.R.color.holo_red_dark));
            }
        });

        findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //spaceNavigationView.changeCurrentItem(3);
                spaceNavigationView.hideBudgeAtIndex(1);
            }
        });

        //spaceNavigationView.addSpaceItem(new SpaceItem("Test2",android.R.drawable.presence_away));
        //spaceNavigationView.addSpaceItem(new SpaceItem("Test2",android.R.drawable.presence_away));
//        spaceNavigationView.setSpaceBackgroundColor(ContextCompat.getColor(this,android.R.color.holo_green_dark));
//        spaceNavigationView.setCentreButtonColor(ContextCompat.getColor(this,android.R.color.holo_orange_light));
        //spaceNavigationView.setCentreButtonIcon(android.R.drawable.sym_call_incoming);

//        FrameLayout r = (FrameLayout) findViewById(R.id.activity_main);
//        BadgeView badgeView = new BadgeView(this,ContextCompat.getColor(this,android.R.color.holo_orange_light),"23");
//        FrameLayout.LayoutParams mainContentParams =
//                new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
//
//        mainContentParams.setMargins(0, 0, 0, 0);
//        r.addView(badgeView, mainContentParams);

        RecyclerAdapter adapter = new RecyclerAdapter(dummyStrings());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerView.setAdapter(adapter);

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        spaceNavigationView.onSaveInstanceState(outState);
    }

    private List<String> dummyStrings() {
        List<String> colorList = new ArrayList<>();
        colorList.add("#354045");
        colorList.add("#20995E");
        colorList.add("#76FF03");
        colorList.add("#E26D1B");
        colorList.add("#911717");
        colorList.add("#9C27B0");
        colorList.add("#FFC107");
        colorList.add("#01579B");
        return colorList;
    }
}
