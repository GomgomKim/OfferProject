package com.example.user.offerproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.util.Timer;
import java.util.TimerTask;

public class timer extends AppCompatActivity{
    EditText minuteinput;
    EditText secondinput;
    int minute=0;
    int second=0;
    TimerTask tt;
    Timer timer;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.timer);

        tt=timerTaskMaker();
        timer=new Timer();

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
                    Toast.makeText(timer.this, "더이상 줄일 수 없습니다.", Toast.LENGTH_SHORT).show();
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
                tt.cancel();

            }
        });

        start.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                tt=timerTaskMaker();
                timer.schedule(tt,0,1000);

            }
        });
    }

    public TimerTask timerTaskMaker(){
        TimerTask tempTask=new TimerTask(){
            @Override
            public void run(){
                if (second == 0 && minute!=0) {
                    second=59;
                    minute--;
                }
                else
                    second--;
                if(minute==0 && second==0){
                        tt.cancel();
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
        return tempTask;
    }

}
