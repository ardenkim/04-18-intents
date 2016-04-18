package edu.uw.intentdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "**Main**";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        View launchButton = findViewById(R.id.btnLaunch);
        launchButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.v(TAG, "Launch button pressed");

                // send message: hey other activities you should start!
                // create a letter to send
                //explicit intent
                Intent intent = new Intent(MainActivity.this, SecondActivity.class); // Intent(who are we sending this from, sent to);
                                            // no "this" because it's currently in OnClickListener() -> MainActivity.this
                intent.putExtra("edu.uw.intentdemo.message", "Hello from MainActivity!"); //putExtra(key, value);

                // send the letter
                startActivity(intent);

            }
        });

    }

    public void callNumber(View v) {
        Log.v(TAG, "Call button pressed");


    }

    public void takePicture(View v) {
        Log.v(TAG, "Camera button pressed");


    }

    public void sendMessage(View v) {
        Log.v(TAG, "Message button pressed");


    }

}
/*
Intent: message sent from one activity to another activity (between objects/classes) in order to communicate
    tell an object to do something
    need reference to send messages over to another
 */