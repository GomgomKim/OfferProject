package org.androidtown.offerproject;


import android.graphics.Color;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.androidtown.offerproject.R;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class timer extends AppCompatActivity{

    ArrayAdapter<String> arrayAdapter;
    ListView listview_m, listview_s;
    ArrayList<String> clock_num;
    TextView textview_m, textview_s;
    TextView upper_text_m, upper_Text_s;
    String minute="";
    String second="";
    int minutenum=0;
    int secondnum=0;
    int minuteanswer=0; //사용자 input저장
    int secondanswer=0;
    TimerTask tt;
    Timer timer;
    boolean startcheck=false;
    Button start, clear;



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
        upper_text_m = (TextView)findViewById(R.id.textView__upper_m);
        upper_Text_s = (TextView)findViewById(R.id.textView__upper_s);
        Typeface typeface=Typeface.createFromAsset(getAssets(),"fonts/210 앱굴림R.ttf");
        textview_s.setTypeface(typeface);
        textview_m.setTypeface(typeface);
        upper_text_m.setTypeface(typeface);
        upper_Text_s.setTypeface(typeface);

        start = (Button) findViewById(R.id.start);
        clear = (Button) findViewById(R.id.clear);

    }

    public void set_timer(){
        clock_num = new ArrayList<>();
        for(int i=0; i<=60; i++){
            String num_str = String.valueOf(i);
            clock_num.add(num_str);
        }


        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, clock_num){
            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                TextView tv = (TextView) view.findViewById(android.R.id.text1);
                tv.setTextColor(Color.BLACK);
                Typeface typeface=Typeface.createFromAsset(getAssets(),"fonts/210 앱굴림R.ttf");
                tv.setTypeface(typeface);
                return view;
            }
        };
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

        if(textview_m.getText().equals("00") && textview_s.getText().equals("00")){
            start.setText("START");
            clear.setText("초기화");
        }

    }

    public void startTimer(View view) {
        if(startcheck==false){
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
//                System.out.println(minuteanswer);
//                System.out.println(secondanswer);
                if(start.getText().equals("START")){
                    tt=timerTaskMaker();
                    timer.schedule(tt,1000,1000);
                    start.setText("일시정지");
                    clear.setText("정지");
                }
                else if(start.getText().equals("일시정지")){
                    tt.cancel();
                    start.setText("START");
                    clear.setText("초기화");
                }
            }
        }

    }


    public void ClearClicked(View view) {
        if(clear.getText().equals("초기화")){
            startcheck=false;
            minutenum = 0;
            secondnum = 0;
            textview_m.setText("0" + minutenum);
            textview_s.setText("0" + secondnum);
            tt.cancel();
        }
        else if(clear.getText().equals("정지")){
            tt.cancel();
            textview_m.setText("00");
            textview_s.setText("00");
            start.setText("START");
            clear.setText("초기화");
            startcheck=false;
        }

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
