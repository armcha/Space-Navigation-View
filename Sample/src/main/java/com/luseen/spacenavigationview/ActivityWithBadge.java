package com.luseen.spacenavigationview;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.luseen.spacenavigation.SpaceItem;
import com.luseen.spacenavigation.SpaceNavigationView;
import com.luseen.spacenavigation.SpaceOnClickListener;
import com.luseen.spacenavigation.SpaceOnLongClickListener;

import java.util.ArrayList;
import java.util.List;


public class ActivityWithBadge extends AppCompatActivity {

    private SpaceNavigationView spaceNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_badge);

        Button btnShowBadge = (Button) findViewById(R.id.btnBadge);
        btnShowBadge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                spaceNavigationView.shouldShowFullBadgeText(true);
                spaceNavigationView.showBadgeAtIndex(0, 2, Color.RED);
                spaceNavigationView.showBadgeAtIndex(1, 3, Color.DKGRAY);
                spaceNavigationView.showBadgeAtIndex(2, 4, Color.MAGENTA);
                spaceNavigationView.showBadgeAtIndex(3, 23, Color.BLUE);
            }
        });

        spaceNavigationView = (SpaceNavigationView) findViewById(R.id.space);
        spaceNavigationView.initWithSaveInstanceState(savedInstanceState);
        spaceNavigationView.addSpaceItem(new SpaceItem(R.id.navigation_first,"HOME", R.drawable.account));
        spaceNavigationView.addSpaceItem(new SpaceItem(R.id.navigation_second, "SEARCH", R.drawable.magnify));
        spaceNavigationView.addSpaceItem(new SpaceItem(R.id.navigation_third, "HOME", R.drawable.account));
        spaceNavigationView.addSpaceItem(new SpaceItem(R.id.navigation_forth, "SEARCH", R.drawable.magnify));
        spaceNavigationView.shouldShowFullBadgeText(false);

        spaceNavigationView.setCentreButtonId(R.id.navigation_centre);
        spaceNavigationView.setCentreButtonIconColorFilterEnabled(false);

        spaceNavigationView.setSpaceOnClickListener(new SpaceOnClickListener() {
            @Override
            public void onCentreButtonClick() {
                Log.d("onCentreButtonClick ", "onCentreButtonClick");
            }

            @Override
            public void onItemClick(int itemIndex, String itemName) {
                Log.d("onItemClick ", "" + itemIndex + " " + itemName);
            }

            @Override
            public void onItemReselected(int itemIndex, String itemName) {
                Log.d("onItemReselected ", "" + itemIndex + " " + itemName);
            }
        });

        spaceNavigationView.setSpaceOnLongClickListener(new SpaceOnLongClickListener() {
            @Override
            public void onCentreButtonLongClick() {
                Toast.makeText(ActivityWithBadge.this, "onCentreButtonLongClick", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemLongClick(int itemIndex, String itemName) {
                Toast.makeText(ActivityWithBadge.this, itemIndex + " " + itemName, Toast.LENGTH_SHORT).show();
            }
        });
        spaceNavigationView.showIconOnly();
        setUpRecyclerView();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        spaceNavigationView.onSaveInstanceState(outState);
    }

    private void setUpRecyclerView() {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        RecyclerAdapter adapter = new RecyclerAdapter(dummyStrings());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerView.setAdapter(adapter);
        adapter.setRecyclerClickListener(new RecyclerAdapter.RecyclerClickListener() {
            @Override
            public void onClick(int position) {
                if (position == 0) {
                    spaceNavigationView.showBadgeAtIndex(1, 54, ContextCompat.getColor(ActivityWithBadge.this, R.color.badge_background_color));
                } else if (position == 1) {
                    spaceNavigationView.hideBudgeAtIndex(1);
                }
            }
        });
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