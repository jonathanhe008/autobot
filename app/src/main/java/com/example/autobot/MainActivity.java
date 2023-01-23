package com.example.autobot;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.telephony.SmsManager;
import android.widget.Toast;
public class MainActivity extends AppCompatActivity {
    private EditText txtMobile;
    private EditText txtMessage;
    private EditText numberofmsg;
    private Button btnSms;
    private TextView loading;
    private ProgressBar progressBar;
    private int increment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtMobile = (EditText)findViewById(R.id.mblTxt);
        txtMessage = (EditText)findViewById(R.id.msgTxt);
        btnSms = (Button)findViewById(R.id.btnSend);
        loading = (TextView)findViewById(R.id.loading);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        loading.setVisibility(View.INVISIBLE);
        btnSms.setOnClickListener(new View.OnClickListener() {
        public void onClick(View v) {
            progressBar.setProgress(0);
            smsSend();
        }
        });
        /*numberofmsg = (EditText)findViewById(R.id.nmbrMsg);
        String value = numberofmsg.getText().toString();
        try {
            final int numValue = Integer.parseInt(value);
            if (numValue >= 0) {
                btnSms.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        try {
                            for (int i = 0; i < numValue; i++) {
                                SmsManager smgr = SmsManager.getDefault();
                                smgr.sendTextMessage(txtMobile.getText().toString(), null, txtMessage.getText().toString(), null, null);
                                if (i == (numValue - 1)) {
                                    Toast.makeText(MainActivity.this, i + " SMS Messages Sent Successfully.", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(MainActivity.this, "SMS Sent: " + i, Toast.LENGTH_SHORT).show();
                                }
                            }
                        } catch (Exception e) {
                            Toast.makeText(MainActivity.this, "SMS Failed to Send. Please try again", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            } else {
                Toast.makeText(MainActivity.this, "Negative Numerical Input. Pleas input a positive number.", Toast.LENGTH_SHORT).show();

            }
        }
        catch (Exception e) {
            Toast.makeText(MainActivity.this, "Invalid Numerical Input. Pleas try again", Toast.LENGTH_SHORT).show();
        }*/


        /*Button button = (Button) findViewById(R.id.Enter);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LaunchMainAction();
            }
        });*/


    }
    private void smsSend() {
        txtMobile = (EditText)findViewById(R.id.mblTxt);
        txtMessage = (EditText)findViewById(R.id.msgTxt);
        btnSms = (Button)findViewById(R.id.btnSend);
        numberofmsg = (EditText)findViewById(R.id.nmbrMsg);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        loading = (TextView)findViewById(R.id.loading);
        loading.setVisibility(View.INVISIBLE);
        String value = numberofmsg.getText().toString();
        Handler handler1 = new Handler();
        try {
            loading.setVisibility(View.VISIBLE);
            loading.setText("In Progress");
            final int numValue = Integer.parseInt(value);
            increment = 100 / numValue;
            final int inc = increment;
            if (numValue >= 0) {
                for (int i = 0; i < numValue; i++) {
                    int j = i;
                    handler1.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            SmsManager smgr = SmsManager.getDefault();
                            smgr.sendTextMessage(txtMobile.getText().toString(), null, txtMessage.getText().toString(), null, null);
                            progressBar.setProgress(increment * (j + 1));
                            //increment += inc;
                            if ((j + 1) == (numValue)) {
                                Toast.makeText(MainActivity.this, (j + 1) + " SMS Messages Sent Successfully.", Toast.LENGTH_SHORT).show();
                                loading.setText("Complete");
                                progressBar.setProgress(100);
                            } else {
                                Toast.makeText(MainActivity.this, "SMS Sent: " + (j + 1), Toast.LENGTH_SHORT).show();
                            }
                        }
                    }, 2000 * i);
                }
            }
        } catch (Exception e) {
            Toast.makeText(MainActivity.this, "SMS Failed to Send. Please try again", Toast.LENGTH_SHORT).show();
        }
    }

    /*private void LaunchMainAction() {
        Intent intent = new Intent(this, MainAction.class);
        startActivity(intent);
    }*/
}
