package com.example.bloodbank;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {
    EditText name, emailId, Password;
    Button register;
    FirebaseAuth fauth;
    ProgressBar progressBar;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        name = (EditText) findViewById(R.id.name);
        emailId = (EditText) findViewById(R.id.email);
        Password = (EditText) findViewById(R.id.password);
        fauth = FirebaseAuth.getInstance();
        register = (Button) findViewById(R.id.createAccount);
        textView = (TextView) findViewById(R.id.already);
        progressBar = (ProgressBar) findViewById(R.id.progressbar);
        if (fauth.getCurrentUser() != null) {
            startActivity(new Intent(RegisterActivity.this, MainActivity.class));
        }
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String name1, email1, password1;
                name1 = name.getText().toString();
                email1 = emailId.getText().toString();
                password1 = Password.getText().toString();
                if (TextUtils.isEmpty(name1) || TextUtils.isEmpty(password1) || TextUtils.isEmpty(email1)) {
                    Toast.makeText(RegisterActivity.this, "Name or email or Password is misssing", Toast.LENGTH_SHORT).show();
                }
                fauth.createUserWithEmailAndPassword(email1, password1).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(RegisterActivity.this, "Registered Successfully", Toast.LENGTH_SHORT).show();
                           Intent intent=new Intent(RegisterActivity.this, AdditionalActivity.class);
                           intent.putExtra("name",name1);
                            startActivity(intent);
                        } else {
                            Toast.makeText(RegisterActivity.this, "Something went wrong try again!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
            }
        });

    }
}