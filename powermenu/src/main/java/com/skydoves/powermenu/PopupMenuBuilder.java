
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
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.LayoutRes;
import androidx.lifecycle.LifecycleOwner;

@SuppressWarnings("WeakerAccess")
public class PopupMenuBuilder {

    protected LifecycleOwner lifecycleOwner = null;
    protected View.OnClickListener backgroundClickListener = null;
    protected OnDismissedListener onDismissedListener = null;
    protected OnShowListener onShowListener;

    protected boolean showBackground = MenuDefaults.showBackground;
    protected MenuAnimation menuAnimation = MenuAnimation.DROP_DOWN;
    protected int animationStyle = MenuDefaults.animationStyle;
    protected float menuRadius = MenuDefaults.menuRadius;
    protected float menuShadow = MenuDefaults.menuShadow;
    protected int width = 0;
    protected int height = 0;
    protected int backgroundColor = MenuDefaults.backgroundColor;
    protected float backgroundAlpha = MenuDefaults.backgroundAlpha;
    protected boolean focusable = MenuDefaults.focusable;
    protected boolean isClipping = MenuDefaults.isClipping;
    protected boolean autoDismiss = MenuDefaults.autoDismiss;
    protected boolean showDebugMessages = false;

    protected @LayoutRes int contentViewId;
    protected View contentView;
    protected InitView initView;

    public PopupMenuBuilder setShowBackground(boolean showBackground) {
        this.showBackground = showBackground;
        return this;
    }

    public PopupMenuBuilder setLifecycleOwner(LifecycleOwner lifecycleOwner) {
        this.lifecycleOwner = lifecycleOwner;
        return this;
    }

    public PopupMenuBuilder setBackgroundClickListener(View.OnClickListener backgroundClickListener) {
        this.backgroundClickListener = backgroundClickListener;
        return this;
    }

    public PopupMenuBuilder setOnDismissedListener(OnDismissedListener onDismissedListener) {
        this.onDismissedListener = onDismissedListener;
        return this;
    }

    public PopupMenuBuilder setMenuAnimation(MenuAnimation menuAnimation) {
        this.menuAnimation = menuAnimation;
        return this;
    }

    public PopupMenuBuilder setAnimationStyle(int animationStyle) {
        this.animationStyle = animationStyle;
        return this;
    }

    public PopupMenuBuilder setMenuRadius(float menuRadius) {
        this.menuRadius = menuRadius;
        return this;
    }

    public PopupMenuBuilder setMenuShadow(float menuShadow) {
        this.menuShadow = menuShadow;
        return this;
    }

    public PopupMenuBuilder setWidth(int width) {
        this.width = width;
        return this;
    }

    public PopupMenuBuilder setHeight(int height) {
        this.height = height;
        return this;
    }

    public PopupMenuBuilder setBackgroundColor(int backgroundColor) {
        this.backgroundColor = backgroundColor;
        return this;
    }

    public PopupMenuBuilder setBackgroundAlpha(float backgroundAlpha) {
        this.backgroundAlpha = backgroundAlpha;
        return this;
    }

    public PopupMenuBuilder setFocusable(boolean focusable) {
        this.focusable = focusable;
        return this;
    }

    public PopupMenuBuilder setClipping(boolean clipping) {
        isClipping = clipping;
        return this;
    }

    public PopupMenuBuilder setAutoDismiss(boolean autoDismiss) {
        this.autoDismiss = autoDismiss;
        return this;
    }

    public PopupMenuBuilder setContentView(int contentViewId, InitView initView) {
        this.contentViewId = contentViewId;
        this.initView = initView;
        return this;
    }

    public PopupMenuBuilder setContentView(View contentView, InitView initView) {
        this.contentView = contentView;
        this.initView = initView;
        return this;
    }


    public PopupMenuBuilder setOnShowListener(OnShowListener onShowListener) {
        this.onShowListener = onShowListener;
        return this;
    }

    public PopupMenu build(Context context) {
        return new PopupMenu(context, this);
    }

    public PopupMenuBuilder setAnimation(MenuAnimation animation) {
        this.menuAnimation = animation;
        return this;
    }

    public PopupMenuBuilder setOnDismissListener(OnDismissedListener onDismissedListener) {
        this.onDismissedListener = onDismissedListener;
        return this;
    }

    public PopupMenuBuilder setShowDebugMessages(boolean showDebugMessages) {
        this.showDebugMessages = showDebugMessages;
        return this;
    }
}
