package com.luseen.spacenavigation;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.view.View;

/**
 * Created by Chatikyan on 10.08.2016-15:19.
 */

public class BezierView extends View {

    private Paint paint;

    private Path path;

    private int bezierWidth;

    public BezierView(Context context) {
        super(context);
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        path = new Path();
        paint.setColor(Color.RED);
        paint.setStrokeWidth(2);
        paint.setStyle(Paint.Style.STROKE);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        path.moveTo(0, 100);
        path.cubicTo(100, 100, 100, 25, 200, 20);
        path.moveTo(200, 20);
        path.cubicTo(300, 20, 300, 100, 400, 100);
        canvas.drawLine(400, 100, 0, 100, paint);
        canvas.drawPath(path, paint);
    }

    public void build(int bezierWidth) {
        this.bezierWidth = bezierWidth;
    }
}

