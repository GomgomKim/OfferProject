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

    ArrayAdapter<String> arrayAdapter;
    ListView listview_m, listview_s;
    ArrayList<String> clock_num;
    TextView textview_m, textview_s;
    String minute="";
    String second="";
    int minutenum=0;
    int secondnum=0;
    int minuteanswer=0; //설정한 시간 저장
    int secondanswer=0;
    TimerTask tt;
    Timer timer;
    boolean startcheck=false;
    Button start;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);


        init();
        set_timer();


        tt=timerTaskMaker();
        timer=new Timer();


    }


    public void init(){
        listview_m = (ListView)findViewById(R.id.list_minute);
        listview_s = (ListView)findViewById(R.id.list_second);
        textview_m = (TextView)findViewById(R.id.text_minute);
        textview_s = (TextView)findViewById(R.id.text_second);
         start = (Button) findViewById(R.id.start);


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
        if(startcheck==false){
            startcheck=true;
            minute = textview_m.getText().toString();
            second = textview_s.getText().toString();
            minutenum=Integer.parseInt(minute);
            secondnum=Integer.parseInt(second);
            minuteanswer=Integer.parseInt(minute);
            secondanswer=Integer.parseInt(second);
            if((minutenum==0 && secondnum==0) ||(minutenum<=0 && secondnum<=0)){
                Toast.makeText(this, "먼저, 분과 초를 선택해 주세요 !", Toast.LENGTH_SHORT).show();
            }
            else{
                tt=timerTaskMaker();
                timer.schedule(tt,1000,1000);
//                System.out.println(minuteanswer);
//                System.out.println(secondanswer);

            }
        }

    }


    public void ClearClicked(View view) {
        startcheck=false;
        minutenum = 0;
        secondnum = 0;
        textview_m.setText("0" + minutenum);
        textview_s.setText("0" + secondnum);
        tt.cancel();
        startcheck=false;
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
                    startcheck=false;
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
