package com.geek.firstaid.activities.base;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;


public class AppBaseActivity extends BaseActivity {

    protected void setToolbar(int title) {
        setToolbar(getResources().getString(title), false);
    }

    protected void setToolbar(int title, boolean isBackArrow) {
        setToolbar(getResources().getString(title), true);
    }

    protected void setToolbar(int title, int resIdBackArrow) {
        setToolbar(getResources().getString(title), resIdBackArrow);
    }

    protected void setToolbar(int title, Drawable drawableBackArrow) {
        setToolbar(getResources().getString(title), drawableBackArrow);
    }

    protected void setToolbar(int title, int resIdLogo, boolean isBackArrow) {
        setToolbar(getResources().getString(title), resIdLogo, isBackArrow);
    }

    protected void setToolbar(int title, Drawable drawableLogo, boolean isBackArrow) {
        setToolbar(getResources().getString(title), drawableLogo, isBackArrow);
    }

    protected void setBackArrowColor(String color) {
        setBackArrowColor(Color.parseColor(color));
    }

    protected void setBackArrowColorResource(int color) {
        setBackArrowColor(ContextCompat.getColor(this, color));
    }

    protected void setToolbarTitle(int title) {
        setToolbarTitle(getResources().getString(title));
    }

    protected void setToolbarColor(String color) {
        setToolbarColor(Color.parseColor(color));
    }

    protected void setToolbarTitleColor(String color) {
        setToolbarTitleColor(Color.parseColor(color));
    }

    protected void setToolbarColorResource(int color) {
        setToolbarColor(ContextCompat.getColor(this, color));
    }

    protected void setStatusBarColor(String color) {
        setStatusBarColor(Color.parseColor(color));
    }

    protected void setStatusBarColorResource(int color) {
        setStatusBarColor(ContextCompat.getColor(this, color));
    }

    protected void setNavigationBarColor(String color) {
        setNavigationBarColor(Color.parseColor(color));
    }

    protected void setNavigationBarColorResource(int color) {
        setNavigationBarColor(ContextCompat.getColor(this, color));
    }
}
