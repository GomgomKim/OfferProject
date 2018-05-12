package org.androidtown.offerproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.sql.Time;

public class Menu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }

    public void btn_off(View view) {
        Intent intent = new Intent(Menu.this, TurnOff.class);
        startActivity(intent);
    }

    public void btn_ctrl(View view) {
        Intent intent = new Intent(Menu.this, Control.class);
        startActivity(intent);
    }

    public void btn_timer(View view) {
        Intent intent = new Intent(Menu.this, Timer.class);
        startActivity(intent);
    }

    public void btn_howto(View view) {
        Intent intent = new Intent(Menu.this, HowTo.class);
        startActivity(intent);
    }
}
