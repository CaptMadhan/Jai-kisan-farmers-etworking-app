package com.example.jaikisan;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

public class Consumer_farmer_profile extends AppCompatActivity {
    TextView layout_title,name,state,city,phoneNo;
    RecyclerView recyclerView_consumer;
    Button call,message;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consumer_farmer_profile);
        layout_title=findViewById(R.id.title_farmers_name);
        name=findViewById(R.id.fullName);
        state = findViewById(R.id.state);
        phoneNo = findViewById(R.id.phoneNumber);
        city= findViewById(R.id.city);
        recyclerView_consumer = findViewById(R.id.recyclerview);
        call = findViewById(R.id.call_button);
        message = findViewById(R.id.message_button);
        Bundle extras = getIntent().getExtras();
        layout_title.setText(extras.getString("Farmer Name") +"'s Dashboard");
        name.setText(extras.getString("Farmer Name"));
        phoneNo.setText(extras.getString("Farmer phone"));
        //address = extras.getString("Farmer address");
        state.setText(extras.getString("Farmer state"));
        city.setText(extras.getString("Farmer city"));



    }

    public void call_Farmer(View view) {
    }

    public void message_Farmer(View view) {
    }
}