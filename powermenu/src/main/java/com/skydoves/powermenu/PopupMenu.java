
/*
 * Copyright (C) 2018 skydoves
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

package com.skydoves.powermenu;


import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;

import androidx.cardview.widget.CardView;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LifecycleOwner;

@SuppressWarnings({"WeakerAccess", "unchecked", "unused"})
public class PopupMenu implements LifecycleObserver {

    protected View backgroundView;
    protected View menuView;
    protected View contentView;
    protected CardView menuCard;

    protected PopupWindow backgroundWindow;
    protected PopupWindow menuWindow;

    protected LifecycleOwner lifecycleOwner;

    protected OnDismissedListener onDismissedListener;
    protected OnShowListener onShowListener;


    protected boolean showBackground = true;
    protected boolean allowTouchBackground = false;
    protected boolean fixedHeight = false;

    protected boolean isShowing = false;

    protected int contentViewPadding;
    private boolean autoDismiss;
    private boolean showDebugMessages;

    protected PopupMenu(Context context) {
        initialize(context);
    }

    protected PopupMenu(Context context, PopupMenuBuilder builder) {
        initialize(context);
        if (builder.contentViewId != 0 && builder.contentView != null) {
            throw new RuntimeException("Set either contentView or contentView Id");
        }
        ViewGroup group = menuView.findViewById(R.id.menuContent);
        if (builder.contentView != null) {
            group.addView(builder.contentView);
            if (builder.initView != null) {
                builder.initView.onViewCreated(this, builder.contentView);
            }
            contentView = builder.contentView;
        }
        if (builder.contentViewId != 0) {
            contentView = LayoutInflater.from(context).inflate(builder.contentViewId, group, true);
            if (builder.initView != null) {
                builder.initView.onViewCreated(this, contentView);
            }
        }
        this.showDebugMessages = builder.showDebugMessages;
        setShowBackground(builder.showBackground);
        setAnimation(builder.menuAnimation);
        setMenuRadius(builder.menuRadius);
        setMenuShadow(builder.menuShadow);
        setBackgroundColor(builder.backgroundColor);
        setBackgroundAlpha(builder.backgroundAlpha);
        setFocusable(builder.focusable);
        setIsClipping(builder.isClipping);
        setAutoDismiss(builder.autoDismiss);


        setLifecycleOwner(builder.lifecycleOwner);
        setOnBackgroundClickListener(builder.backgroundClickListener);

        setOnDismissedListener(builder.onDismissedListener);
        setOnShowListener(builder.onShowListener);

        if (builder.animationStyle != -1)
            setAnimationStyle(builder.animationStyle);

        if (builder.width != 0)
            setWidth(builder.width);
        if (builder.height != 0)
            setHeight(builder.height);
    }

    private void setOnShowListener(OnShowListener onShowListener) {
        this.onShowListener = onShowListener;
    }


    @SuppressWarnings("ConstantConditions")
    private void initialize(Context context) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        backgroundView = layoutInflater.inflate(R.layout.menu_background, null);
        backgroundView.setOnClickListener(background_clickListener);
        backgroundView.setAlpha(0.5f);
        backgroundWindow = new PopupWindow(backgroundView, RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);

        menuView = layoutInflater.inflate(R.layout.menu_view, null);
        menuWindow = new PopupWindow(menuView, FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT);
        menuCard = menuView.findViewById(R.id.menu_card);

        setFocusable(false);
        setTouchInterceptor(onTouchListener);

        contentViewPadding = Utils.convertDpToPixel(10, context);
    }

    private void setLifecycleOwner(LifecycleOwner lifecycleOwner) {
        if (lifecycleOwner == null) {
            return;
        }
        lifecycleOwner.getLifecycle().addObserver(this);
        this.lifecycleOwner = lifecycleOwner;
    }

    private void setFocusable(boolean focusable) {
        menuWindow.setBackgroundDrawable(new BitmapDrawable());
        menuWindow.setOutsideTouchable(!focusable);
    }

    private void setTouchInterceptor(View.OnTouchListener onTouchListener) {
        this.menuWindow.setTouchInterceptor(onTouchListener);
    }


    private View.OnClickListener background_clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (!allowTouchBackground)
                dismiss();
        }
    };

    private View.OnTouchListener onTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            if (event.getAction() == MotionEvent.ACTION_OUTSIDE && !showBackground) {
                dismiss();
                return true;
            }
            return false;
        }
    };

    private void onShow(View anchor) {
        if (onShowListener != null) {
            onShowListener.onShowPopup(this, this.contentView, anchor);
        }
    }


    public void showAsDropDown(View anchor) {
        showAsDropdownInternal(anchor, 0, 0);
    }

    public void showAsDropDown(View anchor, int xOff, int yOff) {
        showAsDropdownInternal(anchor, xOff, yOff);
    }

    public void showAsAnchorLeftTop(View anchor) {
        showAsDropdownInternal(anchor, 0, -anchor.getMeasuredHeight());
    }

    public void showAsAnchorLeftBottom(View anchor) {
        showAsDropdownInternal(anchor, 0, -getContentViewPadding());
    }

    public void showAsAnchorRightTop(View anchor) {
        showAsDropdownInternal(anchor, anchor.getMeasuredWidth() / 2 + getContentViewWidth() / 2, -anchor.getMeasuredHeight());
    }

    public void showAsAnchorRightBottom(View anchor) {
        showAsDropdownInternal(anchor, anchor.getMeasuredWidth() / 2 + getContentViewWidth() / 2, -getContentViewPadding());
    }

    public void showAtCenter(View anchor) {
        showAtLocationInternal(anchor, Gravity.CENTER, 0, 0);
    }

    public void showAtCenter(View anchor, int xOff, int yOff) {
        showAtLocationInternal(anchor, Gravity.CENTER, xOff, yOff);
    }

    public void showAtLocation(View anchor, int xOff, int yOff) {
        showAtLocationInternal(anchor, Gravity.NO_GRAVITY, xOff, yOff);
    }

    public void showAtLocation(View anchor, int gravity, int xOff, int yOff) {
        showAtLocationInternal(anchor, gravity, xOff, yOff);
    }

    public void showAsAnchorCenter(View anchor) {
        showAsDropdownInternal(anchor, anchor.getMeasuredWidth() / 2 - getContentViewWidth() / 2,//
                -anchor.getMeasuredHeight() / 2 - getContentViewHeight() / 2);
    }

    private void showAtLocationInternal(View anchor, int gravity, int xOff, int yOff) {
        if (isShowing()) {
            log("Already showing menu");
            return;
        }
        prepareToShowPopup(anchor);
        menuWindow.showAtLocation(anchor, gravity, xOff, yOff);
    }

    private void showAsDropdownInternal(View anchor, int xoff, int yoff) {
        if (isShowing()) {
            log("Already showing menu");
            return;
        }
        prepareToShowPopup(anchor);
        menuWindow.showAsDropDown(anchor, xoff, yoff);
    }

    private void prepareToShowPopup(View anchor) {
        if (showBackground) backgroundWindow.showAtLocation(anchor, Gravity.CENTER, 0, 0);
        isShowing = true;
        onShow(anchor);
    }

    private void log(String message) {
        if (this.showDebugMessages) {
            Log.d("PopupMenu", message);
        }
    }

    public void dismiss() {
        if (isShowing()) {
            menuWindow.dismiss();
            backgroundWindow.dismiss();
            isShowing = false;
            if (onDismissedListener != null)
                onDismissedListener.onDismissed();
        }
    }

    public boolean isShowing() {
        return isShowing;
    }

    public int getContentViewWidth() {
        int width = menuWindow.getContentView().getWidth();
        if (width == 0) {
            return getMeasuredContentView().getMeasuredWidth();
        } else {
            return width;
        }
    }

    public int getContentViewHeight() {
        int width = menuWindow.getContentView().getHeight();
        if (width == 0) {
            return getMeasuredContentView().getMeasuredHeight();
        } else {
            return width;
        }
    }

    protected View getMeasuredContentView() {
        View contentView = menuWindow.getContentView();
        contentView.measure(
                View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
                View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
        return contentView;
    }

    protected int getContentViewPadding() {
        return this.contentViewPadding;
    }

    private void setWidth(int width) {
        int dpWidth = Utils.convertDpToPixel(width, this.menuView.getContext());
        this.menuWindow.setWidth(dpWidth);
    }

    private void setHeight(int height) {
        this.fixedHeight = true;
        this.menuWindow.setHeight(height);
    }


    private void setShowBackground(boolean show) {
        this.showBackground = show;
    }

    private void setOnDismissedListener(OnDismissedListener onDismissedListener) {
        this.onDismissedListener = onDismissedListener;
    }

    private void setOnBackgroundClickListener(View.OnClickListener onBackgroundClickListener) {
        this.backgroundView.setOnClickListener(onBackgroundClickListener);
    }

    private void setAnimation(MenuAnimation menuAnimation) {
        menuWindow.setAnimationStyle(menuAnimation.animationStyle);
        if (menuAnimation == MenuAnimation.FADE) {
            backgroundWindow.setAnimationStyle(R.style.FadeMenuAnimation);
        }
    }

    private void setAnimationStyle(int style) {
        this.menuWindow.setAnimationStyle(style);
    }

    private void setMenuRadius(float radius) {
        this.menuCard.setRadius(radius);
    }

    private void setMenuShadow(float shadow) {
        this.menuCard.setCardElevation(shadow);
    }

    private void setIsClipping(boolean isClipping) {
        this.menuWindow.setClippingEnabled(isClipping);
    }


    private void setBackgroundColor(int color) {
        backgroundView.setBackgroundColor(color);
    }

    private void setBackgroundAlpha(float alpha) {
        backgroundView.setAlpha(alpha);
    }

    private void setAutoDismiss(boolean autoDismiss) {
        this.autoDismiss = autoDismiss;
    }
}
