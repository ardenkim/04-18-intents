package edu.uw.intentdemo;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

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
//                sendBroadcast(intent);  // here is intent! whoever can handle this take it.
            }
        });

    }

    public void callNumber(View v) {
        Log.v(TAG, "Call button pressed");

        // implicit intent
        // somebody who can dial this number! this letter is for you
        Intent intent = new Intent(Intent.ACTION_DIAL);

        //Specify the data type
        // what data do I want to dial
        intent.setData(Uri.parse("tel:206-685-1622"));

        // hey intent! who are you going to deliver to?
        if(intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }

    }

    private static int REQUEST_PICTURE_CODE = 1;

    public void takePicture(View v) {
        Log.v(TAG, "Camera button pressed");

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        if(intent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(intent, REQUEST_PICTURE_CODE);
            // REQUEST_PICTURE_CODE = when I get RGP? get, I know who it came from
        }
    }

    public void sendMessage(View v) {
        Log.v(TAG, "Message button pressed");

        SmsManager smsManager = SmsManager.getDefault();
        smsManager.sendTextMessage("5554", null, "This is a message", null, null);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if(requestCode == REQUEST_PICTURE_CODE && resultCode == RESULT_OK) {
            // I got picture!!
            Bundle extras = data.getExtras(); // from Intent data
            Bitmap imageBitmap = (Bitmap)extras.get("data");

            ImageView imageView = (ImageView)findViewById(R.id.imgThumbnail);
            imageView.setImageBitmap(imageBitmap);
        }

        super.onActivityResult(requestCode, resultCode, data);
    }
}
/*
Intent: message sent from one activity to another activity (between objects/classes) in order to communicate
    tell an object to do something
    need reference to send messages over to another
 */
/*
specify actions or data
 */
/*
after taking pictures, you want those pictures saved/sent (give you results back)
 */
/*
In order to relieve implicit intent, declare to let operating systems to know
hey who can response to this (dialog)? ME ME ME!
 */