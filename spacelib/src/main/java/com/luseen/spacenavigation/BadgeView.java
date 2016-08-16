package com.luseen.spacenavigation;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewCompat;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by Chatikyan on 15.08.2016-22:22.
 */

public class BadgeView extends RelativeLayout {

    private Paint paint;

    private Paint circlePaint;

    private Rect bounds;

    private float cx, cy;

    int height;
    int width;

    private String badgeText;

    public BadgeView(Context context, int backgroundColor, String badgeText) {
        super(context);
        this.badgeText = badgeText;
        paint = new Paint();
        circlePaint = new Paint();
        bounds = new Rect();
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(backgroundColor);
        paint.setStrokeWidth(0);
        paint.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        ViewGroup.LayoutParams params = getLayoutParams();
        params.width = width;
        params.height = height;
        setBackgroundColor(Color.GREEN);
        setLayoutParams(params);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        paint.setColor(Color.WHITE);
        paint.setTextSize(25f);
        paint.setAntiAlias(true);
        paint.setTextAlign(Paint.Align.CENTER);


        paint.getTextBounds(badgeText, 0, badgeText.length(), bounds);

        circlePaint.setColor(Color.RED);
        circlePaint.setAntiAlias(true);

        canvas.drawCircle(cx, cy, bounds.width(), circlePaint);

        canvas.drawText(badgeText, cx, cy, paint);
    }

    public void build(float cx, float cy , int height, int width) {
        this.cx = cx;
        this.cy = cy;
        this.height = height;
        this.width = width;
    }

    public void showBadge() {
        ViewCompat.animate(this)
                .setDuration(500)
                .scaleX(1)
                .scaleY(1)
                .start();
    }

    public void hideBadge() {
        ViewCompat.animate(this)
                .setDuration(500)
                .scaleX(0)
                .scaleY(0)
                .start();
    }
}
