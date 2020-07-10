package com.example.bloodbank;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class AdditionalActivity extends AppCompatActivity {
    EditText city, name, phone, blood;
    Button b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_additional);
        city = (EditText) findViewById(R.id.city);

        phone = (EditText) findViewById(R.id.mobile);
        blood = (EditText) findViewById(R.id.blood);
        b = (Button) findViewById(R.id.createAccount);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String name1, city1, phone1, blood1;
                name1 = getIntent().getStringExtra("name");
                city1 = city.getText().toString().trim();
                phone1 = phone.getText().toString().trim();
                blood1 = blood.getText().toString().trim();
                HashMap<String, Object> hashMap = new HashMap<>();
                hashMap.put("name", name1);
                hashMap.put("city", city1);
                hashMap.put("phone", phone1);
                hashMap.put("blood", blood1);
                FirebaseDatabase.getInstance().getReference("Donors").child(phone1).setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                    Intent intent=    new Intent(AdditionalActivity.this, LoginActivity.class);

                        startActivity(intent);
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                    }
                });
            }
        });

    }
}