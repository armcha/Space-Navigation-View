package com.luseen.spacenavigation;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

/**
 * Created by Chatikyan on 10.08.2016-15:19.
 */

public class CircleView extends View {

    Paint paint;

    public CircleView(Context context) {
        super(context);
        paint = new Paint();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int x = getWidth();
        int y = getHeight();
        int radius;
        radius = 80;
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.GRAY);
        canvas.drawPaint(paint);
        paint.setColor(Color.parseColor("#CD5C5C"));
        //canvas.drawCircle(x / 2, y / 2, radius, paint);
        canvas.translate(getWidth() / 2f, y / 2f);
        canvas.drawCircle(0,4, radius, paint);
    }
}
