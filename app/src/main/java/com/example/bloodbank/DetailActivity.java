package com.example.bloodbank;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class DetailActivity extends AppCompatActivity {
    Button phone, message, livechat;
    final int SEND_SMS_PERMISSION_GRANT_CODE=1;
    String phoneNumber;
    TextView details;
    String yourData;


    String messageText;
private static int REQUEST_CALL=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        String data=getIntent().getStringExtra("data");
        details=(TextView)findViewById(R.id.text);
        details.setText(data);
yourData=getIntent().getStringExtra("data");
        phoneNumber = getIntent().getStringExtra("number");
messageText="You are going to save a life now!\n"+"Donate blood:\n"+"Address:\n"+yourData;
message=(Button)findViewById(R.id.message);
message.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
      //  ActivityCompat.requestPermissions(DetailActivity.this,new String[] {Manifest.permission.SEND_SMS,Manifest.permission.READ_SMS},PackageManager.PERMISSION_GRANTED);
        SmsManager smsManager=SmsManager.getDefault();
        smsManager.sendTextMessage(phoneNumber,null,messageText,null,null);
    }
});

        phone = (Button) findViewById(R.id.phone);

        phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


              makePhoneCall();
            }
        });
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
       if(grantResults.length>0&&grantResults[0]==PackageManager.PERMISSION_GRANTED)
       {
           makePhoneCall();
       }
       else
       {
           Toast.makeText(this,"Permission Denied",Toast.LENGTH_SHORT).show();
       }
    }

    private void makePhoneCall()
    {


            if(ContextCompat.checkSelfPermission(DetailActivity.this,Manifest.permission.CALL_PHONE)!=PackageManager.PERMISSION_GRANTED)
            {
                ActivityCompat.requestPermissions(DetailActivity.this,new String[]{Manifest.permission.CALL_PHONE},REQUEST_CALL);
            }
            else {
                String dial="tel:"+phoneNumber;
                startActivity(new Intent(Intent.ACTION_CALL,Uri.parse(dial)));
            }


    }

    public boolean checkPermission(String permission)
    {
        int check=ContextCompat.checkSelfPermission(this,permission);

        return (check==PackageManager.PERMISSION_GRANTED);
    }

}
