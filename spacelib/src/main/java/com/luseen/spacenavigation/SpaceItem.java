package com.luseen.spacenavigation;

/**
 * Created by Chatikyan on 13.08.2016-21:10.
 */

public class SpaceItem {

    private String itemName;

    private int itemIcon;

    public SpaceItem(String itemName, int itemIcon) {
        this.itemName = itemName;
        this.itemIcon = itemIcon;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getItemIcon() {
        return itemIcon;
    }

    public void setItemIcon(int itemIcon) {
        this.itemIcon = itemIcon;
    }
}
