/*
 * Space Navigation library for Android
 * Copyright (c) 2016 Arman Chatikyan (https://github.com/armcha/Space-Navigation-View).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.luseen.spacenavigation;

import java.io.Serializable;

public class SpaceItem implements Serializable {

    private String itemName;

    private int itemIcon;

    private Integer itemLayout = null;

    public SpaceItem(String itemName, int itemIcon) {
        this.itemName = itemName;
        this.itemIcon = itemIcon;
    }

    public SpaceItem(String itemName, int itemIcon, int itemLayout) {
        this.itemName = itemName;
        this.itemIcon = itemIcon;
        this.itemLayout = itemLayout;
    }

    String getItemName() {
        return itemName;
    }

    void setItemName(String itemName) {
        this.itemName = itemName;
    }

    int getItemIcon() {
        return itemIcon;
    }

    void setItemIcon(int itemIcon) {
        this.itemIcon = itemIcon;
    }

    Integer getItemLayout() {
        return itemLayout;
    }

    void setItemLayout(int itemLayout) {
        this.itemLayout = itemLayout;
    }
}
