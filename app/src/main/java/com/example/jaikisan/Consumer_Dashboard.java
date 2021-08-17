package com.example.jaikisan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Consumer_Dashboard extends AppCompatActivity {
    EditText searchET;
    Button searchButton;
    RecyclerView farmersListRecyclerView;
    RecyclerAdapterConsumer adapter;
    List<KisanUserDetails> kisanList;
    DatabaseReference databaseReference;
    int count =0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consumer_dashboard);
        Objects.requireNonNull(getSupportActionBar()).hide();
        searchET=findViewById(R.id.searchET);
        searchButton=findViewById(R.id.searchButton);
        farmersListRecyclerView=findViewById(R.id.farmersListRecyclerView);

        databaseReference = FirebaseDatabase.getInstance().getReference();
        kisanList= new ArrayList<>();

        farmersListRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new RecyclerAdapterConsumer(kisanList,this);
        farmersListRecyclerView.setAdapter(adapter);

    }
    public void Refresh_Recycler_View(View view) {
        // To retreive data and store items
        databaseReference.child("KisanUserDetails")
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            KisanUserDetails items = snapshot.getValue(KisanUserDetails.class);
                            kisanList.add(items);
                            //Toast.makeText(getApplicationContext(),items.quantity , Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                    }
                });
        HashSet<KisanUserDetails> kisanItemsHashSet = new HashSet<>(kisanList);
        kisanList = new ArrayList<>(kisanItemsHashSet);
        adapter = new RecyclerAdapterConsumer(kisanList,this);
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            public void run() {
                if(count<=2)
                    farmersListRecyclerView.setAdapter(adapter);
                else
                    adapter.notifyDataSetChanged();
                count++;
            }
        });
    }

    public void search_farmer(View view) {
    }

    //Back Button functions
    boolean doubleBackToExitPressedOnce = false;
    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {

            super.onBackPressed();
            return;
        }
        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

        new Handler(Looper.getMainLooper()).postDelayed(() -> doubleBackToExitPressedOnce=false, 2000);
    }
}