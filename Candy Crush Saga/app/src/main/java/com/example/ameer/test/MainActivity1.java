package com.example.ameer.test;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity1 extends AppCompatActivity {

    BoardView b;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        b = new BoardView(this);
        setContentView(b);
    }
}
