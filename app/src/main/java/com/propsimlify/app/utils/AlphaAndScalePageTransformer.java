package com.propsimlify.app.utils;

import android.view.View;

import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

public class AlphaAndScalePageTransformer implements ViewPager2.PageTransformer {


    private static final float MIN_SCALE = 0.80f;

    @Override
    public void transformPage(View page, float position) {
        float scaleFactor = MIN_SCALE + (1 - MIN_SCALE) * (1 - Math.abs(position));
        page.setScaleX(scaleFactor);
        page.setScaleY(scaleFactor);
    }
}
