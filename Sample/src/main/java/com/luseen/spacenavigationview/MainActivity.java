package com.luseen.spacenavigationview;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.luseen.spacenavigation.SpaceItem;
import com.luseen.spacenavigation.SpaceNavigationView;
import com.luseen.spacenavigation.SpaceOnClickListener;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private SpaceNavigationView spaceNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        spaceNavigationView = (SpaceNavigationView) findViewById(R.id.space);
        spaceNavigationView.initWithSaveInstanceState(savedInstanceState);
        spaceNavigationView.addSpaceItem(new SpaceItem("HOME", R.drawable.home));
        spaceNavigationView.addSpaceItem(new SpaceItem("SEARCH", R.drawable.magnify));
        spaceNavigationView.addSpaceItem(new SpaceItem("LIKE", R.drawable.bell));
        spaceNavigationView.addSpaceItem(new SpaceItem("ACCOUNT", R.drawable.account));
        //spaceNavigationView.setCentreButtonIcon(R.drawable.testfab);
        //spaceNavigationView.showIconOnly();

        spaceNavigationView.setSpaceOnClickListener(new SpaceOnClickListener() {
            @Override
            public void onCentreButtonClick() {
                spaceNavigationView.changeCenterButtonIcon(R.drawable.audiobook);
                Log.d("onCentreButtonClick ", "onCentreButtonClick");
                spaceNavigationView.changeItemTextAtPosition(0,"A");
                spaceNavigationView.changeItemTextAtPosition(1,"B");
                spaceNavigationView.changeItemTextAtPosition(2,"C");
                spaceNavigationView.changeItemTextAtPosition(3,"D");
            }

            @Override
            public void onItemClick(int itemIndex, String itemName) {
                Log.d("onItemClick ", "" + itemIndex + " " + itemName);
            }

            @Override
            public void onItemReselected(int itemIndex, String itemName) {
                Log.e("onItemReselected ", "resel");
                spaceNavigationView.changeItemIconAtPosition(0,R.drawable.account);
                spaceNavigationView.changeItemIconAtPosition(1,R.drawable.audiobook);
                spaceNavigationView.changeItemIconAtPosition(2,R.drawable.camera);
                spaceNavigationView.changeItemIconAtPosition(3,R.drawable.google_maps);
            }
        });

        RecyclerAdapter adapter = new RecyclerAdapter(dummyStrings());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerView.setAdapter(adapter);
        adapter.setRecyclerClickListener(new RecyclerAdapter.RecyclerClickListener() {
            @Override
            public void onClick(int position) {
                if (position == 0) {
                    //spaceNavigationView.changeCurrentItem(3);
                    spaceNavigationView.showBadgeAtIndex(1, 4, ContextCompat.getColor(MainActivity.this, R.color.badge_background_color));
                    //spaceNavigationView.showBadgeAtIndex(2, 4, ContextCompat.getColor(MainActivity.this, R.color.badge_background_color));
                    //spaceNavigationView.showBadgeAtIndex(2, 7, ContextCompat.getColor(MainActivity.this, android.R.color.holo_blue_light));
                    //spaceNavigationView.showBadgeAtIndex(3, 3, ContextCompat.getColor(MainActivity.this, android.R.color.holo_red_dark));

                } else if (position == 1) {
                    //spaceNavigationView.changeCurrentItem(3);
                    spaceNavigationView.hideBudgeAtIndex(1);
                }
            }
        });

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