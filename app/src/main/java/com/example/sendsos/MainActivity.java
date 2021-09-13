package com.example.sendsos;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

//import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.model.LatLng;
//import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity extends AppCompatActivity {

    private EditText txtNumber;
    private Button btnSend;
    private LocationManager locationManager;
    public static Location location;
    public static LatLng latLng;
    Boolean sendAlerts = false;
    private LocationListener locationListener;
    private final long MIN_TIME =1000;
    private final long MIN_DISTANCE=5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        ActivityCompat.requestPermissions(this , new String[]{Manifest.permission.SEND_SMS},PackageManager.PERMISSION_GRANTED);
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.ACCESS_FINE_LOCATION}, PackageManager.PERMISSION_GRANTED);
            ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.ACCESS_COARSE_LOCATION}, PackageManager.PERMISSION_GRANTED);

            return;
        }
//        if(locationManager.NETWORK_PROVIDER == null) {
//            location.setLatitude(35);
//            location.setLongitude(55);
//            location = locationManager.getLastKnownLocation(locationManager.NETWORK_PROVIDER);
//        }

    }
    public void SendSMS(View view) {
        txtNumber = findViewById(R.id.number);
        btnSend = findViewById(R.id.send);

        String number = txtNumber.getText().toString().trim();
        String message;
        sendAlerts = true;
        while(sendAlerts){
            try {
                if( !(number.equals("" ))){
                    SmsManager smsManager = SmsManager.getDefault();
                    LatLng sydney = new LatLng(-34, 151);
                    message = "I'm Nirasha IM/2017/033. please Help me I'm in "+"http://maps.google.com/?q="+sydney.latitude+","+sydney.longitude;
                    smsManager.sendTextMessage(number, null, message, null, null);
                    Toast.makeText(this, "Message sent", Toast.LENGTH_SHORT).show();
                }

            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(this, "Sms failed", Toast.LENGTH_SHORT).show();
            }
            try {
                Thread.sleep(60000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    public void StopAlert(View view){
        sendAlerts = false;
    }

    public void getLocation(){
        locationListener= new LocationListener() {
            @Override
            public void onLocationChanged(@NonNull Location location) {
                latLng = new LatLng(location.getLatitude(), location.getLongitude());

            }
        };
        try {
            locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,MIN_TIME,MIN_DISTANCE,locationListener);
        }catch (SecurityException e)
        {
            e.printStackTrace();}

    }
    }

//public class MainActivity extends AppCompatActivity {
////    implements LocationListener
//    private LocationListener locationListener;
//    private LocationManager locationManager;
//    private final long MIN_TIME =1000;
//    private final long MIN_DISTANCE=5;
//    public static LatLng latLng;
//    private EditText txtNumber;
//    private Button btnSend;
//    private Button btnStop;
//    Boolean sendAlerts = false;
//    private String message;
////    MapsActivity mapsActivity = new MapsActivity();
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SEND_SMS}, PackageManager.PERMISSION_GRANTED);

//
//
//    }
//
//    public void SendAlert (View view) throws InterruptedException {
//        sendAlerts = true;
//        txtNumber = findViewById(R.id.number);
//        btnSend = findViewById(R.id.send);
//        btnStop = findViewById(R.id.stop);
//        LatLng sydney = new LatLng(-34, 151);
////        while (sendAlerts){
////        locationListener = new LocationListener() {
////                @Override
////                public void onLocationChanged(@NonNull Location location) {
////                    latLng = new LatLng(location.getLatitude(), location.getLongitude());
////
////                }
////            };
////            try {
////                locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
////                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, MIN_TIME, MIN_DISTANCE, locationListener);
////            } catch (SecurityException e) {
////                e.printStackTrace();
////            }
//
//            String number = txtNumber.getText().toString().trim();
//
//            try {
//                if (!(number.equals(""))) {
//                    SmsManager smsManager = SmsManager.getDefault();
//                    message = "I'm Nirasha IM/2017/033. please Help me I'm in "+"http://maps.google.com/?q="+sydney.longitude+","+sydney.longitude+",";
////                    System.out.println(latLng.latitude+""+latLng.longitude);
//                    smsManager.sendTextMessage(number, null, "text", null, null);
////                    txtNumber.setText("");
//                    Toast.makeText(this, "Message sent", Toast.LENGTH_SHORT).show();
//                }else{
//                    Toast.makeText(this, "There is no number selected", Toast.LENGTH_SHORT).show();
//                }
//
//
//            } catch (Exception e) {
//                e.printStackTrace();
//                Toast.makeText(this, "Sms failed", Toast.LENGTH_SHORT).show();
//            }
//
//
////        String number = txtNumber.getText().toString().trim();
////        String message = txtMessage.getText().toString().trim();
////        try {
////            if( !(number.equals("" )|| message.equals(""))){
////                SmsManager smsManager = SmsManager.getDefault();
////                smsManager.sendTextMessage(number, null, message, null, null);
//////                txtNumber.setText("");
//////                txtMessage.setText("");
////                Toast.makeText(this, "Message sent", Toast.LENGTH_SHORT).show();
////            }
////
////
////
////        } catch (Exception e) {
////            e.printStackTrace();
////            Toast.makeText(this, "Sms failed", Toast.LENGTH_SHORT).show();
////        }
////            noinspection InsertLiteralUnderscores
//            Thread.sleep(60000);
////        }
//
//    }
//
//    public void StopAlert(View view){
//        sendAlerts = false;
//    }
//
////    @Override
////    public void onLocationChanged(@NonNull Location location) {
////        double longitude = location.getLongitude();
////        double latitude = location.getLatitude();
////    }




//}