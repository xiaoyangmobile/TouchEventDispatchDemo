package com.example.toucheventdispatchdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button1 button1 = findViewById(R.id.button);
        TextView1 textView1 = findViewById(R.id.text);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("按钮点击了");
            }
        });

        button1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                boolean dis = false;
                System.out.println("Button.Touch()=" + dis);
                return dis;
            }
        });

//        textView1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                System.out.println("文字点击了");
//            }
//        });
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        //分发事件
        boolean dis = super.dispatchTouchEvent(ev);
        System.out.println("Activity.dispatchTouchEvent()=" + dis);
        return dis;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //处理事件
        boolean dis = super.onTouchEvent(event);
        System.out.println("Activity.onTouchEvent()=" + dis);
        return dis;
    }
}