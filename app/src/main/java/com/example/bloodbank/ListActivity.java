package com.example.bloodbank;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

// after
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.auth.User;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {

    ListView listView;
    FirebaseDatabase database;
    DatabaseReference ref;
    ArrayList<String> list;
    ArrayAdapter<String> adapter;
    ListClass listClass;
    String[] phoneNumber;
    ImageView imageView;

    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        listClass = new ListClass();
        listView = (ListView) findViewById(R.id.list);
        database = FirebaseDatabase.getInstance();
        ref = database.getReference("Donors");
        list = new ArrayList<String>();
//adapter=new ArrayAdapter<String >(this,R.layout.list_view,R.id.name,list);
        list = new ArrayList<>();
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list) {
            @SuppressLint("ResourceType")
            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                if (position % 2 == 0) {
                    view.setBackgroundColor(getResources().getColor(
                            android.R.color.holo_blue_light
                    ));
                } else {
                    view.setBackgroundColor(getResources().getColor(
                            android.R.color.holo_red_light
                    ));

                }
                return view;
            }
        };
        listView = (ListView) findViewById(R.id.list);
        ref = database.getReference().child("Donors");

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    listClass = dataSnapshot.getValue(ListClass.class);
                    list.add(listClass.getName().toString() + "\n" + listClass.getPhone().toString() + "\n" + listClass.getBlood().toString() + "\n" + listClass.getCity().toString());
                }

                listView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView t = (TextView) findViewById(R.id.name);
                String text = listView.getItemAtPosition(position).toString();

                Log.w(text, "Your msg");
                phoneNumber = text.split("\\\n");
                Toast.makeText(ListActivity.this, text, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(ListActivity.this, DetailActivity.class);
String yourData=listClass.getName().toString() + "\n" + listClass.getPhone().toString() + "\n" + listClass.getBlood().toString() + "\n" + listClass.getCity().toString();
                intent.putExtra("number", phoneNumber[1]);
                intent.putExtra("data",text);
               intent.putExtra("yourData",yourData);
                startActivity(intent);
            }
        });

    }
}







