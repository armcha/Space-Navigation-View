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

class BadgeItem implements Serializable {

    private static final int BADGE_TEXT_MAX_NUMBER = 9;

    private int badgeIndex;

    private int badgeText;

    private int badgeColor;

    BadgeItem(int badgeIndex, int badgeText, int badgeColor) {
        this.badgeIndex = badgeIndex;
        this.badgeText = badgeText;
        this.badgeColor = badgeColor;
    }

    int getBadgeIndex() {
        return badgeIndex;
    }

    int getBadgeColor() {
        return badgeColor;
    }

    int getIntBadgeText() {
        return badgeText;
    }

    String getFullBadgeText() {
        return String.valueOf(badgeText);
    }

    String getBadgeText() {
        String badgeStringText;
        if (badgeText > BADGE_TEXT_MAX_NUMBER) {
            badgeStringText = BADGE_TEXT_MAX_NUMBER + "+";
        } else {
            badgeStringText = String.valueOf(badgeText);
        }

        return badgeStringText;
    }
}
