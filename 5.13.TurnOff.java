package org.androidtown.offerproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

public class TurnOff extends AppCompatActivity {
    ImageButton btn;
    int count =0;
    int isoff =0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_turn_off);
        init();
    }

    public void init(){
        btn = (ImageButton)findViewById(R.id.turnon);
        Intent intent = getIntent();
        isoff = intent.getIntExtra("isoff", 0);
        if(isoff ==1){
            btn.setImageResource(R.drawable.turnoff);
            count ++;
        }
    }

    public void onClicked(View args){
        count++;
        isoff =1;
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

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(TurnOff.this, Menu.class);
        intent.putExtra("isoff", isoff);
        startActivity(intent);
    }
}
