package com.example.toucheventdispatchdemo;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

public class LinearLayout2 extends LinearLayout {
    public LinearLayout2(Context context) {
        super(context);
    }

    public LinearLayout2(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public LinearLayout2(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public LinearLayout2(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        boolean result =  super.dispatchTouchEvent(ev);
        System.out.println("LinearLayout2:dispatchTouchEvent->" + result);
        return result;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        boolean result = super.onInterceptTouchEvent(ev);
        System.out.println("LinearLayout2:onInterceptTouchEvent->" + result);
        return result;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        boolean result = super.onTouchEvent(event);
        System.out.println("LinearLayout2:onTouchEvent->" + result);
        return result;
    }
}
