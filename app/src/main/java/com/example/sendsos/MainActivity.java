package com.example.sendsos;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText txtNumber;
    private Button btnSend;
    Boolean sendAlerts = false;
    MapsActivity mapsActivity = new MapsActivity();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SEND_SMS}, PackageManager.PERMISSION_GRANTED);

    }

    public void SendAlert(View view) throws InterruptedException {
        sendAlerts = true;
        txtNumber = findViewById(R.id.number);
        btnSend = findViewById(R.id.send);

        while (sendAlerts){
            String number = txtNumber.getText().toString().trim();
            String message = "I'm Nirasha IM/2017/033. please Help me I'm in "+"http://maps.google.com/?q="+mapsActivity.latLng.latitude+","+mapsActivity.latLng.longitude+",";

            try {
                if (!(number.equals(""))) {
                    SmsManager smsManager = SmsManager.getDefault();
//                    smsManager.sendTextMessage(number, null, message, null, null);
//                    txtNumber.setText("");
                    Toast.makeText(this, "Message sent", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(this, "There is no number selected", Toast.LENGTH_SHORT).show();
                }


            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(this, "Sms failed", Toast.LENGTH_SHORT).show();
            }
            //noinspection InsertLiteralUnderscores
            Thread.sleep(60000);
        }

    }

    public void StopAlert(View view){
        sendAlerts = false;
    }
}