package com.luseen.spacenavigation;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.icu.math.BigDecimal;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

/**
 * Created by Chatikyan on 10.08.2016-15:19.
 */

public class BezierView extends RelativeLayout {

    private Paint paint;

    private Path path;

    private int bezierWidth, bezierHeight;

    private Context context;


    public BezierView(Context context) {
        super(context);
        this.context = context;
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        path = new Path();
        paint.setColor(Color.WHITE);
        paint.setStrokeWidth(3);
        paint.setStyle(Paint.Style.STROKE);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //setBackgroundColor(ContextCompat.getColor(context, R.color.white1));
    }

    @Override
    protected void onDraw(Canvas canvas) {

        /**
         * Start point for drawing
         */
        path.moveTo(0, bezierHeight);

        /**
         * Seth half path of bezier view
         */
        path.cubicTo(bezierHeight, bezierHeight, bezierHeight, 0, bezierWidth / 2, 0);

        /**
         * Move draw start path to first bezier view end path
         */
        path.moveTo(bezierWidth / 2, 0);

        /**
         * Seth second part of bezier view
         */
        path.cubicTo((bezierWidth / 4) * 3, 0, (bezierWidth / 4) * 3, bezierHeight, bezierWidth, bezierHeight);

        /**
         * Draw our bezier view
         */
        canvas.drawPath(path, paint);

        /**
         * Draw line to connect bezier view start and end points
         */
        canvas.drawLine(bezierWidth, bezierHeight, 0, bezierHeight, paint);
    }

    public void build(int bezierWidth, int bezierHeight) {
        this.bezierWidth = bezierWidth;
        this.bezierHeight = bezierHeight;
    }
}

