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
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorInt;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * Created by Chatikyan on 14.08.2016-21:56.
 */

class Utils {

    /**
     * Change given image view tint
     *
     * @param imageView target image view
     * @param color     tint color
     */
    static void changeImageViewTint(ImageView imageView, int color) {
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

    //// FIXME: 17.08.2016 
    public static int adjustAlpha(int color, float factor) {
        int alpha = Math.round(Color.alpha(color) * factor);
        int red = Color.red(color);
        int green = Color.green(color);
        int blue = Color.blue(color);
        return Color.argb(alpha, red, green, blue);
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
