package com.sagedata.toastcounter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {
    //For Log_Tag
    private static final String LOG_TAG =
            MainActivity.class.getSimpleName();
    //Define the Key for intent
    public static final String EXTRA_MESSAGE =
            "com.example.android.twoactivities.extra.MESSAGE";

    private int count = 0;
    private TextView showCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        showCount = (TextView) findViewById(R.id.show_count);
    }

    public void clear(View view){
        count = 0;
        showCount.setText(Integer.toString(count));
    }

    public void add(View view){
        count++;
        if(showCount != null) {
            showCount.setText(Integer.toString(count));

        }
    }

    public void send(View view){
        Log.d(LOG_TAG, "Button clicked!");

        String message = showCount.getText().toString();

        Intent intent = new Intent(this, SecondActivity.class);

        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);

    }

}
