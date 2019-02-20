package com.luseen.spacenavigation;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.v4.content.ContextCompat;
import android.widget.RelativeLayout;

@SuppressLint("ViewConstructor")
public class NavigationViewShadow extends RelativeLayout {
    private Paint paint;

    private int bezierWidth, bezierHeight, navigationWidth;

    private Context context;

    private boolean isLinear=false;

    int [] alphaArray = new int[]{25, 23, 18, 15, 13, 8, 5, 3};

    NavigationViewShadow(Context context) {
        super(context);
        this.context = context;
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setStrokeWidth(2);
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setBackgroundColor(ContextCompat.getColor(context, R.color.space_transparent));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        // translate canvas by shaow width (8 width of shadow). Otherwise canvas clips top part bezier curve
        canvas.translate(0, 8);

        for (int i = 0; i<8; i++){
            /**
             * Draw shadow
             */
            canvas.drawPath(drawPathWithOffset(alphaArray[i], i), paint);
        }
    }

    private Path drawPathWithOffset(int alpha, int offset){

        int offsettedBeizerHeight = bezierHeight - offset;

        Path path = new Path();
        /**
         * Reset path before drawing
         */
        path.reset();

        /**
         * Set paint color to draw the path with
         */
        paint.setColor(Color.BLACK);


        /**
         * Set alpha to change opacity of path
         */
        paint.setAlpha(alpha);

        /**
         * Start point for drawing
         */
        path.moveTo(0, offsettedBeizerHeight);

        /**
         * Draw line over left main-content
         */
        path.lineTo((navigationWidth-bezierWidth)/2, offsettedBeizerHeight);

        if(!isLinear){
            /**
             * Seth half path of bezier view
             */
            path.cubicTo(bezierWidth / 4+(navigationWidth-bezierWidth)/2, offsettedBeizerHeight, bezierWidth / 4+(navigationWidth-bezierWidth)/2, -offset, bezierWidth / 2+(navigationWidth-bezierWidth)/2, -offset);
            /**
             * Seth second part of bezier view
             */
            path.cubicTo((bezierWidth / 4) * 3 +(navigationWidth-bezierWidth)/2, -offset, (bezierWidth / 4) * 3 +(navigationWidth-bezierWidth)/2, offsettedBeizerHeight, bezierWidth+(navigationWidth-bezierWidth)/2, offsettedBeizerHeight);
        }

        /**
         * Draw line over right main-content
         */
        path.lineTo(navigationWidth, offsettedBeizerHeight);

        return path;
    }

    /**
     * Build bezier view with given width and height
     *
     * @param bezierWidth  Given width
     * @param bezierHeight Given height
     * @param isLinear True, if curves are not needed
     */
    void build(int navigationWidth, int bezierWidth, int bezierHeight,boolean isLinear) {
        this.navigationWidth = navigationWidth;
        this.bezierWidth = bezierWidth;
        this.bezierHeight = bezierHeight;
        this.isLinear=isLinear;
    }
}
