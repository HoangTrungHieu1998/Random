package com.example.random;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        for( int i =0; i<20; i++){
            double result = Math.floor(Math.random()*6)+5;
            Log.d("BBB", String.valueOf(result));
        }

    }
}