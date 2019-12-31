package com.sagedata.toastcounter;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.datalogic.decode.BarcodeManager;
import com.datalogic.decode.DecodeException;
import com.datalogic.decode.DecodeResult;
import com.datalogic.decode.ReadListener;
import com.datalogic.device.ErrorManager;


public class MainActivity extends Activity {
    //For Log_Tag
    private static final String LOG_TAG =
            MainActivity.class.getSimpleName();
    //Define the Key for intent
    public static final String EXTRA_MESSAGE =
            "com.example.android.twoactivities.extra.MESSAGE";

    private int count = 0;
    private TextView showCount;
    private TextView check;

    //For scan
    BarcodeManager decoder = null;
    ReadListener listener = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        showCount = (TextView) findViewById(R.id.show_count);

        Log.d(LOG_TAG, "-------");
        Log.d(LOG_TAG, "onCreate");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(LOG_TAG,"onResume:");

        // If the decoder instance is null, create it.
        if (decoder == null) { // Remember an onPause call will set it to null.
            decoder = new BarcodeManager();
        }
        // From here on, we want to be notified with exceptions in case of errors.
        ErrorManager.enableExceptions(true);

        try {
            // Create an anonymous class.
            listener = new ReadListener() {

                // Implement the callback method.
                @Override
                public void onRead(DecodeResult decodeResult) {
                    // Change the displayed text to the current received result.
                    showCount.setText(decodeResult.getText());
                }
            };
            // Remember to add it, as a listener.
            decoder.addReadListener(listener);

        } catch (DecodeException e) {
            Log.d(LOG_TAG,"Error while trying to bind a listener to BarcodeManager");
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(LOG_TAG,"onPause:");
        // If we have an instance of BarcodeManager.
        if (decoder != null) {
            try {
                // Unregister our listener from it and free resources.
                decoder.removeReadListener(listener);

                // Let the garbage collector take care of our reference.
                decoder = null;
            } catch (Exception e) {
                Log.d(LOG_TAG, "Error while trying to remove a listener from BarcodeManager");
            }
        }
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

//    @Override
//    public void onSaveInstanceState(Bundle outState) {
//        super.onSaveInstanceState(outState);
//
//        if (check.getVisibility() == View.VISIBLE) {
//            outState.putBoolean("reply_visible", true);
//        }
//
//
//    }

}
