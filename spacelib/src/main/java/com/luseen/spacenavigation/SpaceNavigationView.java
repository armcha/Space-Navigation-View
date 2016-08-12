package com.luseen.spacenavigation;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import static android.net.sip.SipSession.State.NOT_DEFINED;

/**
 * Created by Chatikyan on 10.08.2016-11:38.
 */

public class SpaceNavigationView extends RelativeLayout {

    private Context context;

    private final int spaceNavigationHeight = (int) getResources().getDimension(com.luseen.spacenavigation.R.dimen.space_navigation_height);

    private final int centreContentMargin = (int) getResources().getDimension(com.luseen.spacenavigation.R.dimen.fab_margin);

    private final int mainContentHeight = (int) getResources().getDimension(com.luseen.spacenavigation.R.dimen.main_content_height);

    private final int centreContentWight = (int) getResources().getDimension(com.luseen.spacenavigation.R.dimen.centre_content_width);

    private int spaceBackgroundColor = NOT_DEFINED;

    private int centreButtonColor = NOT_DEFINED;

    private int centreButtonIcon = NOT_DEFINED;


    public SpaceNavigationView(Context context) {
        this(context, null);
    }

    public SpaceNavigationView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SpaceNavigationView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        if (attrs != null) {
            Resources res = getResources();

            TypedArray array = context.obtainStyledAttributes(attrs, com.luseen.spacenavigation.R.styleable.SpaceNavigationView);
//            withText = array.getBoolean(R.styleable.BottomNavigationView_bnv_with_text, true);
//            coloredBackground = array.getBoolean(R.styleable.BottomNavigationView_bnv_colored_background, true);
//            disableShadow = array.getBoolean(R.styleable.BottomNavigationView_bnv_shadow, false);
//            isTablet = array.getBoolean(R.styleable.BottomNavigationView_bnv_tablet, false);
//            viewPagerSlide = array.getBoolean(R.styleable.BottomNavigationView_bnv_viewpager_slide, true);
//            itemActiveColorWithoutColoredBackground = array.getColor(R.styleable.BottomNavigationView_bnv_active_color, -1);
//            textActiveSize = array.getDimensionPixelSize(R.styleable.BottomNavigationView_bnv_active_text_size, res.getDimensionPixelSize(R.dimen.bottom_navigation_text_size_active));
//            textInactiveSize = array.getDimensionPixelSize(R.styleable.BottomNavigationView_bnv_inactive_text_size, res.getDimensionPixelSize(R.dimen.bottom_navigation_text_size_inactive));

            array.recycle();
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        if (spaceBackgroundColor == NOT_DEFINED)
            spaceBackgroundColor = ContextCompat.getColor(context, R.color.default_color);
        if (centreButtonColor == NOT_DEFINED)
            centreButtonColor = ContextCompat.getColor(context, R.color.centre_button_color);
        if (centreButtonIcon == NOT_DEFINED)
            centreButtonIcon = android.R.drawable.stat_notify_call_mute;

        ViewGroup.LayoutParams params = getLayoutParams();
        params.width = ViewGroup.LayoutParams.MATCH_PARENT;
        params.height = spaceNavigationHeight;
        setBackgroundColor(ContextCompat.getColor(context, R.color.transparent));
        // setClickable(false); // FIXME: 10.08.2016
        setLayoutParams(params);
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        /**
         * Removing all view for not being duplicated
         */
        removeAllViews();

        /**
         * Layouts initializations
         */
        RelativeLayout mainContent = new RelativeLayout(context);
        RelativeLayout centreBackgroundView = new RelativeLayout(context);

        LinearLayout leftContent = new LinearLayout(context);
        LinearLayout rightContent = new LinearLayout(context);

        BezierView centreContent = buildBezierView();

        FloatingActionButton fab = new FloatingActionButton(context);
        fab.setSize(FloatingActionButton.SIZE_AUTO);
        fab.setBackgroundTintList(ColorStateList.valueOf(centreButtonColor));
        fab.setImageResource(centreButtonIcon);

        /**
         * Set fab layout params
         */
        LayoutParams fabParams = new LayoutParams(spaceNavigationHeight, ViewGroup.LayoutParams.MATCH_PARENT);
        fabParams.addRule(RelativeLayout.CENTER_IN_PARENT);
        fabParams.setMargins(centreContentMargin, centreContentMargin, centreContentMargin, centreContentMargin);

        /**
         * Main content size
         */
        LayoutParams mainContentParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, mainContentHeight);
        mainContentParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);

        /**
         * Centre content size
         */
        LayoutParams centreContentParams = new LayoutParams(centreContentWight, spaceNavigationHeight);
        centreContentParams.addRule(RelativeLayout.CENTER_HORIZONTAL);

        /**
         * Centre Background View content size and position
         */
        LayoutParams centreBackgroundViewParams = new LayoutParams(centreContentWight, mainContentHeight);
        centreBackgroundViewParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
        centreBackgroundViewParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);

        /**
         * Left content size
         */
        LayoutParams leftContentParams = new LayoutParams((w - spaceNavigationHeight) / 2, ViewGroup.LayoutParams.MATCH_PARENT);
        leftContentParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT);

        /**
         * Right content size
         */
        LayoutParams rightContentParams = new LayoutParams((w - spaceNavigationHeight) / 2, ViewGroup.LayoutParams.MATCH_PARENT);
        rightContentParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);

        /**
         * Adding views background colors
         */
        leftContent.setBackgroundColor(spaceBackgroundColor);
        rightContent.setBackgroundColor(spaceBackgroundColor);
        centreBackgroundView.setBackgroundColor(spaceBackgroundColor);

        /**
         * Adding view to centreContent
         */
        centreContent.addView(fab, fabParams);

        /**
         * Adding views to mainContent
         */
        mainContent.addView(leftContent, leftContentParams);
        mainContent.addView(rightContent, rightContentParams);

        /**
         * Adding views to mainView
         */
        addView(centreBackgroundView, centreBackgroundViewParams);
        addView(centreContent, centreContentParams);
        addView(mainContent, mainContentParams);

        postRequestLayout();
    }

    /**
     * Creating bezier view with params
     *
     * @return created bezier view
     */
    private BezierView buildBezierView() {
        BezierView bezierView = new BezierView(context, spaceBackgroundColor);
        bezierView.build(centreContentWight, spaceNavigationHeight - mainContentHeight);

        return bezierView;
    }

    /**
     * Indicate event queue that we have changed the View hierarchy during a layout pass
     */
    private void postRequestLayout() {
        getHandler().post(new Runnable() {
            @Override
            public void run() {
                requestLayout();
            }
        });
    }


    /**
     * Set centre circle button background color
     *
     * @param centreButtonColor target color
     */
    public void setCentreButtonColor(int centreButtonColor) {
        this.centreButtonColor = centreButtonColor;
    }

    /**
     * Set main background color
     *
     * @param spaceBackgroundColor target color
     */
    public void setSpaceBackgroundColor(int spaceBackgroundColor) {
        this.spaceBackgroundColor = spaceBackgroundColor;
    }

    /**
     * Set centre button icon
     *
     * @param centreButtonIcon target icon
     */
    public void setCentreButtonIcon(int centreButtonIcon) {
        this.centreButtonIcon = centreButtonIcon;
    }
}
