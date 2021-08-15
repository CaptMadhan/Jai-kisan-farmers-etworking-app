package com.example.jaikisan;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Objects;

public class Consumer_Dashboard extends AppCompatActivity {
    EditText searchET;
    Button searchButton;
    RecyclerView farmersListRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consumer_dashboard);
        Objects.requireNonNull(getSupportActionBar()).hide();

        searchET=findViewById(R.id.searchET);
        searchButton=findViewById(R.id.searchButton);
        farmersListRecyclerView=findViewById(R.id.farmersListRecyclerView);
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

        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);
    }
}