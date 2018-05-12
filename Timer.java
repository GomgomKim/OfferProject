package com.example.user.offerproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.TimerTask;

public class Timer extends AppCompatActivity{
    private Runnable mRunnable;
    private Handler mHandler;
    EditText minuteinput;
    EditText secondinput;
    int minute=0;
    int second=0;
    TimerTask tt;
    java.util.Timer timer;
    int timercount=0;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.timer);
//        mHandler=new Handler(); //마지막 5초 카운트 세기 위한 handler변수
//        mHandler.postDelayed(mRunnable,60000);
//
//
//
//        mRunnable=new Runnable(){
//            @Override
//            public void run() {
//                //딜레이 시간이 지나면 실행
//            }
//        };
     //   tt=timerTaskMaker();
        tt=new TimerTask() {
            @Override
            public void run() {
                if (second == 0 && minute!=0) {
                    second=59;
                    minute--;
                 }
                 else
                    second--;
                if(minute==0 && second==0){
                    if(timer !=null){
                        tt.cancel();
                        timer=null;
                    }
                }
                if (second < 10) {
                    secondinput.setText("0" + second);
                } else
                    secondinput.setText("" + second);
                if (minute < 10) {
                    minuteinput.setText("0" + minute);
                } else
                    minuteinput.setText("" + minute);
            }
        };

        minuteinput = (EditText) findViewById(R.id.minuteinput);
        secondinput = (EditText) findViewById(R.id.secondinput);
        Button minuteplus = (Button) findViewById(R.id.minuteplus);
        Button minuteminus = (Button) findViewById(R.id.minuteminus);
        Button secondplus = (Button) findViewById(R.id.secondplus);
        Button secondminus = (Button) findViewById(R.id.secondminus);
        Button start = (Button) findViewById(R.id.start);
        Button clear = (Button) findViewById(R.id.clear);
        minute = Integer.parseInt(minuteinput.getText().toString());
        second = Integer.parseInt(secondinput.getText().toString());

        minuteplus.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                minute++;
                if (minute < 10) {
                    minuteinput.setText("0" + minute);
                } else
                    minuteinput.setText("" + minute);
            }
        });

        secondplus.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                if (second != 60) {
                    second++;
                }
                if (second < 10) {
                    secondinput.setText("0" + second);
                } else
                    secondinput.setText("" + second);

            }
        });

        minuteminus.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (minute == 0) {
                    minute = 0;
                    Toast.makeText(Timer.this, "더이상 줄일 수 없습니다.", Toast.LENGTH_SHORT).show();
                } else
                    minute--;

                if (minute < 10) {
                    minuteinput.setText("0" + minute);
                } else
                    minuteinput.setText("" + minute);
            }
        });

        secondminus.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (second == 0) {
                    second = 0;
                } else
                    second--;

                if (second < 10) {
                    secondinput.setText("0" + second);
                } else
                    secondinput.setText("" + second);
            }
        });

        clear.setOnClickListener(new View.OnClickListener() { //초기화
            public void onClick(View v) {
                minute = 0;
                second = 0;
                minuteinput.setText("0" + minute);
                secondinput.setText("0" + second);
                if(timer !=null){
                    tt.cancel();
                    timer=null;
                }
            }
        });

        start.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
           //  tt=timerTask();
                    timer.schedule(tt,0,1000);

            }
        });
    }

}
