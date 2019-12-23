package com.sagedata.toastcounter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

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
            Toast message = Toast.makeText(this,R.string.tap_message,Toast.LENGTH_SHORT);
            message.show();
            showCount.setText(Integer.toString(count));
            message.cancel();
        }

    }

}
