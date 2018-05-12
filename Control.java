package com.example.gpsk1.offerproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.SeekBar;
import android.widget.TextView;

import org.w3c.dom.Text;

public class Control extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_control);

        SeekBar seekBar = (SeekBar)findViewById(R.id.seekBar);
        int fireValue;
        final TextView showValue = (TextView)findViewById(R.id.textView);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                //드래그 하는 중에 발생
                showValue.setText("현재 불세기: "+i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                //최초에 탭하여 드래그 시작할때
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                //드래그 멈출때 발생
            }
        });
    }
}
