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

import android.animation.ArgbEvaluator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.support.v4.view.ViewCompat;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by Chatikyan on 14.08.2016-21:56.
 */

class Utils {

    /**
     * Change given image view tint
     *
     * @param context   current context
     * @param imageView target image view
     * @param color     tint color
     */
    static void changeImageViewTint(Context context, ImageView imageView, int color) {
        imageView.setColorFilter(color);
    }

    /**
     * Change view visibility
     *
     * @param view target view
     */
    static void changeViewVisibilityGone(View view) {
        if (view != null && view.getVisibility() == View.VISIBLE)
            view.setVisibility(View.GONE);
    }

    /**
     * Change view visibility
     *
     * @param view target view
     */
    static void changeViewVisibilityVisible(View view) {
        if (view != null && view.getVisibility() == View.GONE)
            view.setVisibility(View.VISIBLE);
    }

    /**
     * Change given image view tint with animation
     *
     * @param image     target image view
     * @param fromColor start animation from color
     * @param toColor   final color
     */
    static void changeImageViewTintWithAnimation(final ImageView image, int fromColor, int toColor) {
        ValueAnimator imageTintChangeAnimation = ValueAnimator.ofObject(new ArgbEvaluator(), fromColor, toColor);
        imageTintChangeAnimation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animator) {
                image.setColorFilter((Integer) animator.getAnimatedValue());
            }
        });
        imageTintChangeAnimation.setDuration(150);
        imageTintChangeAnimation.start();
    }

    /**
     * Indicate event queue that we have changed the View hierarchy during a layout pass
     */
    static void postRequestLayout(final ViewGroup viewGroup) {
        viewGroup.getHandler().post(new Runnable() {
            @Override
            public void run() {
                viewGroup.requestLayout();
            }
        });
    }

    /**
     * Show badge
     *
     * @param view       target badge
     * @param badgeCount badge count text
     */
    static void showBadge(RelativeLayout view, int badgeCount) {

        changeViewVisibilityVisible(view);
        TextView badgeTextView = (TextView) view.findViewById(R.id.badge_text_view);

        String badgeText;
        if (badgeCount > 9)
            badgeText = 9 + "+";
        else
            badgeText = String.valueOf(badgeCount);
        badgeTextView.setText(badgeText);

        view.setScaleX(0);
        view.setScaleY(0);

        ViewCompat.animate(view)
                .setDuration(200)
                .scaleX(1)
                .scaleY(1)
                .setListener(new SimpleViewAnimatorListener() {
                    @Override
                    public void onAnimationEnd(View view) {
                        changeViewVisibilityVisible(view);
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
                .setListener(new SimpleViewAnimatorListener() {
                    @Override
                    public void onAnimationEnd(final View view) {
                        changeViewVisibilityGone(view);
                    }
                })
                .start();
    }

    /**
     * Force show badge without animation
     *
     * @param view       target budge
     * @param badgeCount badge count text
     */
    static void forceShowBadge(RelativeLayout view, int badgeCount) {
        changeViewVisibilityVisible(view);
        TextView badgeTextView = (TextView) view.findViewById(R.id.badge_text_view);

        String badgeText;
        if (badgeCount > 9)
            badgeText = 9 + "+";
        else
            badgeText = String.valueOf(badgeCount);
        badgeTextView.setText(badgeText);
    }


    // TODO: 15.08.2016 add ripple effect programmatically
//    public static RippleDrawable getPressedColorRippleDrawable(int normalColor, int pressedColor)
//    {
//        return new RippleDrawable(getPressedColorSelector(normalColor, pressedColor), getColorDrawableFromColor(normalColor), null);
//    }
//
//    public static ColorStateList getPressedColorSelector(int normalColor, int pressedColor)
//    {
//        return new ColorStateList(
//                new int[][]
//                        {
//                                new int[]{android.R.attr.state_pressed},
//                                new int[]{android.R.attr.state_focused},
//                                new int[]{android.R.attr.state_activated},
//                                new int[]{}
//                        },
//                new int[]
//                        {
//                                pressedColor,
//                                pressedColor,
//                                pressedColor,
//                                normalColor
//                        }
//        );
//    }
//
//    public static ColorDrawable getColorDrawableFromColor(int color)
//    {
//        return new ColorDrawable(color);
//    }
}
