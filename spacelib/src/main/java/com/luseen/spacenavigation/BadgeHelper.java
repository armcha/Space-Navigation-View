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

import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.os.Build;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorListenerAdapter;
import android.view.View;
import android.widget.TextView;

class BadgeHelper {

    /**
     * Show badge
     *
     * @param badgeTextView      target badge
     * @param badgeItem BadgeItem object
     */
    static void showBadge(TextView badgeTextView, BadgeItem badgeItem, boolean shouldShowBadgeWithNinePlus) {
        Utils.changeViewVisibilityVisible(badgeTextView);
        if (shouldShowBadgeWithNinePlus)
            badgeTextView.setText(badgeItem.getBadgeText());
        else
            badgeTextView.setText(badgeItem.getFullBadgeText());

        badgeTextView.setScaleX(0);
        badgeTextView.setScaleY(0);

        ViewCompat.animate(badgeTextView)
                .setDuration(200)
                .scaleX(1)
                .scaleY(1)
                .setListener(new ViewPropertyAnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(View view) {
                        Utils.changeViewVisibilityVisible(view);
                    }
                })
                .start();
    }

    /**
     * Show badge
     *
     * @param view target badge
     */
    static void hideBadge(View view) {
        ViewCompat.animate(view)
                .setDuration(200)
                .scaleX(0)
                .scaleY(0)
                .setListener(new ViewPropertyAnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(final View view) {
                        Utils.changeViewVisibilityGone(view);
                    }
                })
                .start();
    }

    /**
     * Force show badge without animation
     *
     * @param badgeTextView      target budge
     * @param badgeItem BadgeItem object
     */
    static void forceShowBadge(TextView badgeTextView, BadgeItem badgeItem, boolean shouldShowBadgeWithNinePlus) {
        Utils.changeViewVisibilityVisible(badgeTextView);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            badgeTextView.setBackground(makeShapeDrawable(badgeItem.getBadgeColor()));
        } else {
            badgeTextView.setBackgroundDrawable(makeShapeDrawable(badgeItem.getBadgeColor()));
        }

        if (shouldShowBadgeWithNinePlus)
            badgeTextView.setText(badgeItem.getBadgeText());
        else
            badgeTextView.setText(badgeItem.getFullBadgeText());
    }

    /**
     * Make circle drawable for badge background
     *
     * @param color background color
     * @return return colored circle drawable
     */
    static ShapeDrawable makeShapeDrawable(int color) {
        ShapeDrawable badgeBackground = new ShapeDrawable(new OvalShape());
        badgeBackground.setIntrinsicWidth(10);
        badgeBackground.setIntrinsicHeight(10);
        badgeBackground.getPaint().setColor(color);
        return badgeBackground;
    }
}
