package com.example.toucheventdispatchdemo;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.Button;

public class Button1 extends androidx.appcompat.widget.AppCompatButton {
    public Button1(Context context) {
        super(context);
    }

    public Button1(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public Button1(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        boolean result = super.dispatchTouchEvent(event);
        System.out.println("Button1:dispatchTouchEvent->" + result);
        return result;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        boolean result = super.onTouchEvent(event);
        System.out.println("Button1:onTouchEvent->" + result);
        return result;
    }

    @Override
    public boolean performClick() {
        boolean result =  super.performClick();
        System.out.println("Button1:performClick->" + result);
        return result;
    }
}
