package com.example.user.offerproject;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class timer extends AppCompatActivity{
    //희주data
    /*private Runnable mRunnable;
    private Handler mHandler;
  */

    //기연data
    ArrayAdapter<String> arrayAdapter;
    ListView listview_m, listview_s;
    ArrayList<String> clock_num;
    TextView textview_m, textview_s;
    String minute="";
    String second="";
    int minutenum=0;
    int secondnum=0;
    TimerTask tt;
    Timer timer;
    boolean startcheck=false;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);


        //기연추가
        init();
        set_timer();


        tt=timerTaskMaker();
        timer=new Timer();


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

        //희주 코드 시작
        /*tt=new TimerTask() {
            @Override
            public void run() {
                if (second == 0 && minute!=0) {
                    second=59;
                    minute--;
                }
                else
                    second--;
                if(minute==0 && second==0){
                    if(activity_timer !=null){
                        tt.cancel();
                        activity_timer=null;
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
                if(activity_timer !=null){
                    tt.cancel();
                    activity_timer=null;
                }
            }
        });
        start.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                //  tt=timerTask();
                activity_timer.schedule(tt,0,1000);
            }
        });*/

        //희주코드 끝
    }

    //기연추가

    public void init(){
        listview_m = (ListView)findViewById(R.id.list_minute);
        listview_s = (ListView)findViewById(R.id.list_second);
        textview_m = (TextView)findViewById(R.id.text_minute);
        textview_s = (TextView)findViewById(R.id.text_second);
        Button clear = (Button) findViewById(R.id.clear);


    }

    public void set_timer(){
        clock_num = new ArrayList<>();
        for(int i=0; i<=60; i++){
            String num_str = String.valueOf(i);
            clock_num.add(num_str);
        }

        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, clock_num);
        listview_m.setAdapter(arrayAdapter);
        listview_s.setAdapter(arrayAdapter);
        listview_m.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String minute = adapterView.getAdapter().getItem(i).toString();
                if(minute.equals("0")||minute.equals("1")||minute.equals("2")||minute.equals("3")||minute.equals("4")||minute.equals("5")||
                        minute.equals("6")||minute.equals("7")||minute.equals("8")||minute.equals("9")){
                    textview_m.setText("0"+minute);
                }
                else
                    textview_m.setText(minute);
            }
        });
        listview_s.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String second = adapterView.getAdapter().getItem(i).toString();
                if(second.equals("0")||second.equals("1")||second.equals("2")||second.equals("3")||second.equals("4")||second.equals("5")||
                        second.equals("6")||second.equals("7")||second.equals("8")||second.equals("9")){
                    textview_s.setText("0"+second);
                }
                else
                textview_s.setText(second);
            }
        });

    }

    public void startTimer(View view) {
       // startcheck=true;
        minute = textview_m.getText().toString();
        second = textview_s.getText().toString();
        minutenum=Integer.parseInt(minute);
        secondnum=Integer.parseInt(second);
        if(minute.equals("0") && second.equals("0")){
            Toast.makeText(this, "먼저, 분과 초를 선택해 주세요 !", Toast.LENGTH_SHORT).show();
        }
        else{
            tt=timerTaskMaker();
            timer.schedule(tt,1000,1000);

        }
    }


    public void ClearClicked(View view) {
        minutenum = 0;
        secondnum = 0;
        textview_m.setText("0" + minutenum);
        textview_s.setText("0" + secondnum);
        tt.cancel();
    }


    public TimerTask timerTaskMaker(){
        TimerTask tempTask=new TimerTask(){
            @Override
            public void run(){
                if (secondnum == 0 && minutenum!=0) {
                    secondnum=59;
                    minutenum--;
                }
                else
                    secondnum--;
                if(minutenum==0 && secondnum==0){
                    tt.cancel();
                }
                if (secondnum < 10) {
                    textview_s.setText("0" + secondnum);
                } else
                    textview_s.setText("" + secondnum);
                if (minutenum < 10) {
                    textview_m.setText("0" + minutenum);
                } else
                    textview_m.setText("" + minutenum);
            }

        };
        return tempTask;
    }
}