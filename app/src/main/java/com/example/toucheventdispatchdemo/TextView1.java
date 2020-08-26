package com.example.toucheventdispatchdemo;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class TextView1 extends TextView {
    public TextView1(Context context) {
        super(context);
    }

    public TextView1(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public TextView1(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public TextView1(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        boolean b = super.dispatchTouchEvent(event);
        System.out.println("TextView1:dispatchTouchEvent->" + b);
        return b;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        boolean b = super.onTouchEvent(event);
        System.out.println("TextView1:onTouchEvent->" + b);
        return b;
    }

    @Override
    public boolean performClick() {
        boolean b = super.performClick();
        System.out.println("TextView1:performClick->" + b);
        return b;
    }
}
