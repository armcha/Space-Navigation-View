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
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Chatikyan on 10.08.2016-11:38.
 */

public class SpaceNavigationView extends RelativeLayout {

    private static final int NOT_DEFINED = -1;

    private static final String TAG = "SpaceNavigationView";

    private static final String CURRENT_SELECTED_ITEM_BUNDLE_KEY = "currentItem";

    private static final String BUDGES_ITEM_BUNDLE_KEY = "budgeItem";

    private List<SpaceItem> spaceItems = new ArrayList<>();

    private List<View> spaceItemList = new ArrayList<>();

    private List<RelativeLayout> badgeList = new ArrayList<>();

    private HashMap<Integer, Object> badgeSaveInstanceHashMap = new HashMap<>();

    private SpaceOnClickListener spaceOnClickListener;

    private Bundle savedInstanceState;

    private Typeface customFont;

    private Context context;

    private final int spaceNavigationHeight = (int) getResources().getDimension(com.luseen.spacenavigation.R.dimen.space_navigation_height);

    private final int centreContentMargin = (int) getResources().getDimension(com.luseen.spacenavigation.R.dimen.fab_margin);

    private final int mainContentHeight = (int) getResources().getDimension(com.luseen.spacenavigation.R.dimen.main_content_height);

    private final int centreContentWight = (int) getResources().getDimension(com.luseen.spacenavigation.R.dimen.centre_content_width);

    private int spaceItemIconSize = NOT_DEFINED;

    private int spaceItemIconOnlySize = NOT_DEFINED;

    private int spaceItemTextSize = NOT_DEFINED;

    private int spaceBackgroundColor = NOT_DEFINED;

    private int centreButtonColor = NOT_DEFINED;

    private int centreButtonIcon = NOT_DEFINED;

    private int activeSpaceItemColor = NOT_DEFINED;

    private int inActiveSpaceItemColor = NOT_DEFINED;

    private int currentSelectedItem = 0;

    private int contentWidth;

    private boolean textOnly = false;

    private boolean iconOnly = false;

    private boolean isCustomFont = false;


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
            Resources resources = getResources();

            TypedArray typedArray = context.obtainStyledAttributes(attrs, com.luseen.spacenavigation.R.styleable.SpaceNavigationView);
            spaceItemIconSize = typedArray.getDimensionPixelSize(R.styleable.SpaceNavigationView_space_item_icon_size, resources.getDimensionPixelSize(R.dimen.space_item_icon_default_size));
            spaceItemIconOnlySize = typedArray.getDimensionPixelSize(R.styleable.SpaceNavigationView_space_item_icon_only_size, resources.getDimensionPixelSize(R.dimen.space_item_icon_only_size));
            spaceItemTextSize = typedArray.getDimensionPixelSize(R.styleable.SpaceNavigationView_space_item_text_size, resources.getDimensionPixelSize(R.dimen.space_item_text_default_size));
            spaceItemIconOnlySize = typedArray.getDimensionPixelSize(R.styleable.SpaceNavigationView_space_item_icon_only_size, resources.getDimensionPixelSize(R.dimen.space_item_icon_only_size));
            spaceBackgroundColor = typedArray.getColor(R.styleable.SpaceNavigationView_space_background_color, resources.getColor(R.color.default_color));
            centreButtonColor = typedArray.getColor(R.styleable.SpaceNavigationView_centre_button_color, resources.getColor(R.color.centre_button_color));
            activeSpaceItemColor = typedArray.getColor(R.styleable.SpaceNavigationView_active_item_color, resources.getColor(R.color.white));
            inActiveSpaceItemColor = typedArray.getColor(R.styleable.SpaceNavigationView_inactive_item_color, resources.getColor(R.color.default_inactive_item_color));

            typedArray.recycle();
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        /**
         * Set default colors and sizes
         */
        if (spaceBackgroundColor == NOT_DEFINED)
            spaceBackgroundColor = ContextCompat.getColor(context, R.color.default_color);

        if (centreButtonColor == NOT_DEFINED)
            centreButtonColor = ContextCompat.getColor(context, R.color.centre_button_color);

        if (centreButtonIcon == NOT_DEFINED)
            centreButtonIcon = R.drawable.near_me;

        if (activeSpaceItemColor == NOT_DEFINED)
            activeSpaceItemColor = ContextCompat.getColor(context, R.color.white);

        if (inActiveSpaceItemColor == NOT_DEFINED)
            inActiveSpaceItemColor = ContextCompat.getColor(context, R.color.default_inactive_item_color);

        if (spaceItemTextSize == NOT_DEFINED)
            spaceItemTextSize = (int) getResources().getDimension(R.dimen.space_item_text_default_size);

        if (spaceItemIconSize == NOT_DEFINED)
            spaceItemIconSize = (int) getResources().getDimension(R.dimen.space_item_icon_default_size);

        if (spaceItemIconOnlySize == NOT_DEFINED)
            spaceItemIconOnlySize = (int) getResources().getDimension(R.dimen.space_item_icon_only_size);

        /**
         * Set main layout size and color
         */
        ViewGroup.LayoutParams params = getLayoutParams();
        params.width = ViewGroup.LayoutParams.MATCH_PARENT;
        params.height = spaceNavigationHeight;
        setBackgroundColor(ContextCompat.getColor(context, R.color.transparent));
        setLayoutParams(params);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        /**
         * Restore current item index from savedInstance
         */
        restoreCurrentItem();

        /**
         * Trow exceptions if items size is greater than 4 or lesser than 2
         */
        if (spaceItems.size() < 2) {
            throw new NullPointerException("Your space item count must be greater than 1 ," +
                    " your current items count is : " + spaceItems.size());
        }

        if (spaceItems.size() > 4) {
            throw new IndexOutOfBoundsException("Your items count maximum can be 4," +
                    " your current items count is : " + spaceItems.size());
        }

        /**
         * Get left or right content width
         */
        contentWidth = (w - spaceNavigationHeight) / 2;

        /**
         * Removing all view for not being duplicated
         */
        removeAllViews();

        /**
         * Views initializations and customizing
         */
        RelativeLayout mainContent = new RelativeLayout(context);
        RelativeLayout centreBackgroundView = new RelativeLayout(context);

        LinearLayout leftContent = new LinearLayout(context);
        LinearLayout rightContent = new LinearLayout(context);

        BezierView centreContent = buildBezierView();

        FloatingActionButton fab = new FloatingActionButton(context);
        fab.setSize(FloatingActionButton.SIZE_NORMAL);
        fab.setBackgroundTintList(ColorStateList.valueOf(centreButtonColor));
        fab.setImageResource(centreButtonIcon);
        fab.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (spaceOnClickListener != null)
                    spaceOnClickListener.onCentreButtonClick();
            }
        });

        /**
         * Set fab layout params
         */
        LayoutParams fabParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
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
        LayoutParams leftContentParams = new LayoutParams(contentWidth, ViewGroup.LayoutParams.MATCH_PARENT);
        leftContentParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        leftContentParams.addRule(LinearLayout.HORIZONTAL);

        /**
         * Right content size
         */
        LayoutParams rightContentParams = new LayoutParams(contentWidth, ViewGroup.LayoutParams.MATCH_PARENT);
        rightContentParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        rightContentParams.addRule(LinearLayout.HORIZONTAL);

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

        /**
         * Adding current space items to left and right content
         */
        addSpaceItems(leftContent, rightContent);

        /**
         * Redraw main view to make subviews visible
         */
        Utils.postRequestLayout(this);
    }

    /**
     * Adding given space items to content
     *
     * @param leftContent  to left content
     * @param rightContent and right content
     */
    private void addSpaceItems(LinearLayout leftContent, LinearLayout rightContent) {

        /**
         * Removing all views for not being duplicated
         */
        if (leftContent.getChildCount() > 0 || rightContent.getChildCount() > 0) {
            leftContent.removeAllViews();
            rightContent.removeAllViews();
        }

        /**
         * Clear spaceItemList and badgeList for not being duplicated
         */
        spaceItemList.clear();
        badgeList.clear();

        /**
         * Getting LayoutInflater to inflate space item view from XML
         */
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        for (int i = 0; i < spaceItems.size(); i++) {
            final int index = i;
            int targetWidth;
            if (spaceItems.size() > 2)
                targetWidth = contentWidth / 2;
            else
                targetWidth = contentWidth;

            RelativeLayout.LayoutParams textAndIconContainerParams = new RelativeLayout.LayoutParams(
                    targetWidth, mainContentHeight);
            RelativeLayout textAndIconContainer = (RelativeLayout) inflater.inflate(R.layout.space_item_view, this, false);
            textAndIconContainer.setLayoutParams(textAndIconContainerParams);

            ImageView spaceItemIcon = (ImageView) textAndIconContainer.findViewById(R.id.space_icon);
            TextView spaceItemText = (TextView) textAndIconContainer.findViewById(R.id.space_text);
            RelativeLayout badgeContainer = (RelativeLayout) textAndIconContainer.findViewById(R.id.badge_container);
            spaceItemIcon.setImageResource(spaceItems.get(i).getItemIcon());
            spaceItemText.setText(spaceItems.get(i).getItemName());
            spaceItemText.setTextSize(TypedValue.COMPLEX_UNIT_PX, spaceItemTextSize);

            /**
             * Set custom font to space item textView
             */
            if (isCustomFont)
                spaceItemText.setTypeface(customFont);

            /**
             * Hide item icon and show only text
             */
            if (textOnly)
                Utils.changeViewVisibilityGone(spaceItemIcon);

            /**
             * Hide item text and change icon size
             */
            ViewGroup.LayoutParams iconParams = spaceItemIcon.getLayoutParams();
            if (iconOnly) {
                iconParams.height = spaceItemIconOnlySize;
                iconParams.width = spaceItemIconOnlySize;
                spaceItemIcon.setLayoutParams(iconParams);
                Utils.changeViewVisibilityGone(spaceItemText);
            } else {
                iconParams.height = spaceItemIconSize;
                iconParams.width = spaceItemIconSize;
                spaceItemIcon.setLayoutParams(iconParams);
            }

            /**
             * Adding space items to item list for future
             */
            spaceItemList.add(textAndIconContainer);

            /**
             * Adding badge items to badge list for future
             */
            badgeList.add(badgeContainer);

            /**
             * Adding sub views to left and right sides
             */
            if (spaceItems.size() == 2 && leftContent.getChildCount() == 1) {
                rightContent.addView(textAndIconContainer, textAndIconContainerParams);
            } else if (spaceItems.size() > 2 && leftContent.getChildCount() == 2) {
                rightContent.addView(textAndIconContainer, textAndIconContainerParams);
            } else {
                leftContent.addView(textAndIconContainer, textAndIconContainerParams);
            }

            /**
             * Changing current selected item tint
             */
            if (i == currentSelectedItem) {
                spaceItemText.setTextColor(activeSpaceItemColor);
                Utils.changeImageViewTint(spaceItemIcon, activeSpaceItemColor);
            } else {
                spaceItemText.setTextColor(inActiveSpaceItemColor);
                Utils.changeImageViewTint(spaceItemIcon, inActiveSpaceItemColor);
            }

            textAndIconContainer.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    updateSpaceItems(index);
                }
            });
        }

        /**
         * Restore available badges from saveInstance
         */
        restoreBadges();
    }

    /**
     * Update selected item and change it's and non selected item tint
     *
     * @param selectedIndex item in index
     */
    private void updateSpaceItems(final int selectedIndex) {

        /**
         * return if item already selected
         */
        if (currentSelectedItem == selectedIndex)
            return;

        /**
         * Change active and inactive icon and text color
         */
        for (int i = 0; i < spaceItemList.size(); i++) {
            if (i == selectedIndex) {
                RelativeLayout textAndIconContainer = (RelativeLayout) spaceItemList.get(selectedIndex);
                ImageView spaceItemIcon = (ImageView) textAndIconContainer.findViewById(R.id.space_icon);
                TextView spaceItemText = (TextView) textAndIconContainer.findViewById(R.id.space_text);
                spaceItemText.setTextColor(activeSpaceItemColor);
                Utils.changeImageViewTint(spaceItemIcon, activeSpaceItemColor);
            } else if (i == currentSelectedItem) {
                RelativeLayout textAndIconContainer = (RelativeLayout) spaceItemList.get(i);
                ImageView spaceItemIcon = (ImageView) textAndIconContainer.findViewById(R.id.space_icon);
                TextView spaceItemText = (TextView) textAndIconContainer.findViewById(R.id.space_text);
                spaceItemText.setTextColor(inActiveSpaceItemColor);
                Utils.changeImageViewTint(spaceItemIcon, inActiveSpaceItemColor);
            }
        }

        /**
         * Set a listener that gets fired when the selected item changes
         *
         * @param listener a listener for monitoring changes in item selection
         */
        if (spaceOnClickListener != null)
            spaceOnClickListener.onItemClick(selectedIndex, spaceItems.get(selectedIndex).getItemName());

        /**
         * Change current selected item index
         */
        currentSelectedItem = selectedIndex;
    }

    /**
     * Restore current item index from savedInstance
     */
    private void restoreCurrentItem() {
        Bundle restoredBundle = savedInstanceState;
        if (restoredBundle != null) {
            if (restoredBundle.containsKey(CURRENT_SELECTED_ITEM_BUNDLE_KEY))
                currentSelectedItem = restoredBundle.getInt(CURRENT_SELECTED_ITEM_BUNDLE_KEY, 0);
        }
    }

    /**
     * Restore available badges from saveInstance
     */
    @SuppressWarnings("unchecked")
    private void restoreBadges() {
        Bundle restoredBundle = savedInstanceState;
        if (restoredBundle != null && restoredBundle.containsKey(BUDGES_ITEM_BUNDLE_KEY)) {
            badgeSaveInstanceHashMap = (HashMap<Integer, Object>) savedInstanceState.getSerializable(BUDGES_ITEM_BUNDLE_KEY);
            if (badgeSaveInstanceHashMap != null) {
                for (Integer integer : badgeSaveInstanceHashMap.keySet()) {
                    BadgeHelper.forceShowBadge(badgeList.get(integer), (BadgeItem) badgeSaveInstanceHashMap.get(integer));
                }
            }
        }
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
     * Initialization with savedInstanceState to save current selected
     * position and current budges
     *
     * @param savedInstanceState bundle to saveInstance
     */
    public void initWithSaveInstanceState(Bundle savedInstanceState) {
        this.savedInstanceState = savedInstanceState;
    }

    /**
     * Save budges and current position
     *
     * @param outState bundle to saveInstance
     */
    public void onSaveInstanceState(Bundle outState) {
        outState.putInt(CURRENT_SELECTED_ITEM_BUNDLE_KEY, currentSelectedItem);
        outState.putSerializable(BUDGES_ITEM_BUNDLE_KEY, badgeSaveInstanceHashMap);
    }

    /**
     * Set centre circle button background color
     *
     * @param centreButtonColor target color
     */
    public void setCentreButtonColor(@ColorInt int centreButtonColor) {
        this.centreButtonColor = centreButtonColor;
    }

    /**
     * Set main background color
     *
     * @param spaceBackgroundColor target color
     */
    public void setSpaceBackgroundColor(@ColorInt int spaceBackgroundColor) {
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

    /**
     * Set active item text color
     *
     * @param activeSpaceItemColor color to change
     */
    public void setActiveSpaceItemColor(@ColorInt int activeSpaceItemColor) {
        this.activeSpaceItemColor = activeSpaceItemColor;
    }

    /**
     * Set inactive item text color
     *
     * @param inActiveSpaceItemColor color to change
     */
    public void setInActiveSpaceItemColor(@ColorInt int inActiveSpaceItemColor) {
        this.inActiveSpaceItemColor = inActiveSpaceItemColor;
    }

    /**
     * Set item icon size
     *
     * @param spaceItemIconSize target size
     */
    public void setSpaceItemIconSize(int spaceItemIconSize) {
        this.spaceItemIconSize = spaceItemIconSize;
    }

    /**
     * Set item icon size if showIconOnly() called
     *
     * @param spaceItemIconOnlySize target size
     */
    public void setSpaceItemIconSizeInOnlyIconMode(int spaceItemIconOnlySize) {
        this.spaceItemIconOnlySize = spaceItemIconOnlySize;
    }

    /**
     * Set item text size
     *
     * @param spaceItemTextSize target size
     */
    public void setSpaceItemTextSize(int spaceItemTextSize) {
        this.spaceItemTextSize = spaceItemTextSize;
    }

    /**
     * Show only text in item
     */
    public void showTextOnly() {
        textOnly = true;
    }

    /**
     * Show only icon in item
     */
    public void showIconOnly() {
        iconOnly = true;
    }

    /**
     * Add space item to navigation
     *
     * @param spaceItem item to add
     */
    public void addSpaceItem(SpaceItem spaceItem) {
        spaceItems.add(spaceItem);
    }

    /**
     * Set item and centre click
     *
     * @param spaceOnClickListener space click listener
     */
    public void setSpaceOnClickListener(SpaceOnClickListener spaceOnClickListener) {
        this.spaceOnClickListener = spaceOnClickListener;
    }

    /**
     * Change current selected item to given index
     *
     * @param indexToChange given index
     */
    public void changeCurrentItem(int indexToChange) {
        if (indexToChange < 0 || indexToChange > spaceItems.size())
            throw new ArrayIndexOutOfBoundsException("Please be more careful, we do't have such item : " + indexToChange);
        else
            updateSpaceItems(indexToChange);
    }

    /**
     * Show badge at index
     *
     * @param itemIndex index
     * @param badgeText badge count text
     */
    public void showBadgeAtIndex(int itemIndex, int badgeText, @ColorInt int badgeColor) {
        if (itemIndex < 0 || itemIndex > spaceItems.size()) {
            throw new ArrayIndexOutOfBoundsException("Your item index can't be 0 or greater than space item size," +
                    " your items size is " + spaceItems.size() + ", your current index is :" + itemIndex);
        } else {
            RelativeLayout badgeView = badgeList.get(itemIndex);

            /**
             * Set circle background to badge view
             */
            badgeView.setBackground(BadgeHelper.makeShapeDrawable(badgeColor));

            BadgeItem badgeItem = new BadgeItem(itemIndex, badgeText, badgeColor);
            BadgeHelper.showBadge(badgeView, badgeItem);
            badgeSaveInstanceHashMap.put(itemIndex, badgeItem);
        }
    }

    /**
     * Hide badge at index
     *
     * @param index badge index
     */
    public void hideBudgeAtIndex(final int index) {
        if (badgeList.get(index).getVisibility() == GONE) {
            Log.d(TAG, "Budge at index: " + index + " already hidden");
        } else {
            BadgeHelper.hideBadge(badgeList.get(index));
            badgeSaveInstanceHashMap.remove(index);
        }
    }

    /**
     * Hiding all available badges
     */
    public void hideAllBudges() {
        for (RelativeLayout badge : badgeList) {
            if (badge.getVisibility() == VISIBLE)
                BadgeHelper.hideBadge(badge);
        }
        badgeSaveInstanceHashMap.clear();
    }

    /**
     * Change badge text at index
     *
     * @param badgeIndex target index
     * @param badgeText  badge count text to change
     */
    public void changeBadgeTextAtIndex(int badgeIndex, int badgeText) {
        if (badgeSaveInstanceHashMap.get(badgeIndex) != null &&
                (((BadgeItem) badgeSaveInstanceHashMap.get(badgeIndex)).getIntBadgeText() != badgeText)) {
            BadgeItem currentBadgeItem = (BadgeItem) badgeSaveInstanceHashMap.get(badgeIndex);
            BadgeItem badgeItemForSave = new BadgeItem(badgeIndex, badgeText, currentBadgeItem.getBadgeColor());
            BadgeHelper.forceShowBadge(badgeList.get(badgeIndex), badgeItemForSave);
            badgeSaveInstanceHashMap.put(badgeIndex, badgeItemForSave);
        }
    }

    /**
     * Set custom font for space item textView
     *
     * @param customFont custom font
     */
    public void setFont(Typeface customFont) {
        isCustomFont = true;
        this.customFont = customFont;
    }
}
