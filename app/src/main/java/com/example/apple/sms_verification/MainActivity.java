package com.example.apple.sms_verification;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button b1;
    EditText e1;
    String PINString;
    String phone_no;
    final int PERMISSION_ALL = 1;
    final String[] PERMISSIONS = {Manifest.permission.SEND_SMS, Manifest.permission.ACCESS_NETWORK_STATE, Manifest.permission.READ_PHONE_STATE};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        b1  = (Button) findViewById(R.id.btn);
        e1 = (EditText) findViewById(R.id.e1);

        int randomPIN = (int)(Math.random()*9000)+1000;
        PINString = String.valueOf(randomPIN);

        phone_no = e1.getText().toString().trim();
        b1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                if(!hasPermissions(MainActivity.this, PERMISSIONS)){
                    ActivityCompat.requestPermissions(MainActivity.this, PERMISSIONS, PERMISSION_ALL);
                }

            }
        });
    }


    public  boolean hasPermissions(Context context, String... permissions) {
        if (context != null && permissions != null) {
            for (String permission : permissions) {
                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
                else
                {
                    sendSMS(phone_no, PINString);
                    Intent intent = new Intent(MainActivity.this,Verification_Activity.class);
                    intent.putExtra("epuzzle", PINString);
                    startActivity(intent);

                }
            }
        }
        return true;
    }


    private void sendSMS(String phoneNumber, String message) {
        SmsManager sms = SmsManager.getDefault();
        sms.sendTextMessage(e1.getText().toString().trim(), null, message, null, null);
    }



}
