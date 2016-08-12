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

    public void build(int bezierWidth, int bezierHeight) {
        this.bezierWidth = bezierWidth;
        this.bezierHeight = bezierHeight;
    }
}

