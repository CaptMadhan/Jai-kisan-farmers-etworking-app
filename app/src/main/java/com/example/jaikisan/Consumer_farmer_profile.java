package com.example.jaikisan;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Consumer_farmer_profile extends AppCompatActivity {
    TextView layout_title,name,state,city,phoneNo;
    RecyclerView recyclerView_consumer;
    Button call,message;
    String nameStr,stateStr,cityStr,phoneNoStr;
    DatabaseReference databaseReference;
    List<KisanItems> kisanItemsList;
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
        nameStr=extras.getString("Farmer Name");
        stateStr=extras.getString("Farmer state");
        cityStr=extras.getString("Farmer city");
        phoneNoStr=extras.getString("Farmer phone");
        layout_title.setText( nameStr+"'s Dashboard");
        name.setText(nameStr);
        phoneNo.setText(phoneNoStr);
        //address = extras.getString("Farmer address");
        state.setText(stateStr);
        city.setText(cityStr);

        databaseReference = FirebaseDatabase.getInstance().getReference();
        kisanItemsList=getItems(phoneNoStr);

    }
    public static ArrayList<KisanItems> getItems(String phoneNoStr){
        ArrayList<KisanItems> kisanItemsList = new ArrayList<>();
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
        databaseReference.child("Kisan_Items").child("+91"+phoneNoStr)
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            KisanItems items = snapshot.getValue(KisanItems.class);
                            kisanItemsList.add(items);
                            //Toast.makeText(getApplicationContext(),items.quantity , Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                    }
                });
        return kisanItemsList;
    }

    public void call_Farmer(View view) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:+91 "+phoneNoStr));
        startActivity(intent);
    }

    public void message_Farmer(View view) {
        Uri uri = Uri.parse("smsto:+91 "+phoneNoStr);
        Intent intent = new Intent(Intent.ACTION_SENDTO, uri);
        intent.putExtra("sms_body", "Hello, I would like to contact you regarding your farming goods");
        startActivity(intent);
    }
}