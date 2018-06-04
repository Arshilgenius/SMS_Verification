package com.example.apple.sms_verification;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Verification_Activity extends AppCompatActivity {

    EditText e1;
    Button b1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification_);


        b1 = (Button) findViewById(R.id.verify);
        e1 = (EditText) findViewById(R.id.edittext);

        final String s = e1.getText().toString().trim();
        Intent intent = getIntent();
        final String easyPuzzle = intent.getExtras().getString("epuzzle");

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(s==easyPuzzle)
                {
                    Toast.makeText(Verification_Activity.this, "OTP IS VERIFIED SUCCESSFULLY", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(Verification_Activity.this, "FAILED TO VERIFY OTP", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
