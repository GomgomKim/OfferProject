package com.example.gpsk1.offerproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

public class TurnOff extends AppCompatActivity {
    int count =0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_turn_off);
    }
    public void onClicked(View args){
        ImageButton btn = (ImageButton)findViewById(R.id.turnon);
        count++;
        if(count==1){
            if(((ImageButton)args).getId() == R.id.turnon){
                btn.setImageResource(R.drawable.turnoff);
            }
        }
        if(count==2){
            Toast.makeText(this, "이미 불이 꺼져있습니다.", Toast.LENGTH_SHORT).show();
            count=0;
        }
    }
}
