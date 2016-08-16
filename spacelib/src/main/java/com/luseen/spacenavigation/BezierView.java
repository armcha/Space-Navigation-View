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

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.v4.content.ContextCompat;
import android.widget.RelativeLayout;

/**
 * Created by Chatikyan on 10.08.2016-15:19.
 */

class BezierView extends RelativeLayout {

    private Paint paint;

    private Path path;

    private int bezierWidth, bezierHeight;

    private Context context;


    public BezierView(Context context, int backgroundColor) {
        super(context);
        this.context = context;
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        path = new Path();
        paint.setColor(backgroundColor);
        paint.setStrokeWidth(0);
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setBackgroundColor(ContextCompat.getColor(context, R.color.transparent));
    }

    @Override
    protected void onDraw(Canvas canvas) {

        /**
         * Reset path before drawing
         */
        path.reset();

        /**
         * Start point for drawing
         */
        path.moveTo(0, bezierHeight);

        /**
         * Seth half path of bezier view
         */
        path.cubicTo(bezierWidth / 4, bezierHeight, bezierWidth / 4, 0, bezierWidth / 2, 0);

        /**
         * Seth second part of bezier view
         */
        path.cubicTo((bezierWidth / 4) * 3, 0, (bezierWidth / 4) * 3, bezierHeight, bezierWidth, bezierHeight);

        /**
         * Draw our bezier view
         */
        canvas.drawPath(path, paint);
    }

    void build(int bezierWidth, int bezierHeight) {
        this.bezierWidth = bezierWidth;
        this.bezierHeight = bezierHeight;
    }
}

