package com.example.jaikisan;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class Famers_Dashboard extends AppCompatActivity {
    Button add_itemButton,remove_itemButton;
    RecyclerView recyclerView;
    CardView LogOutAlertBoxCard;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_famers_dashboard);
        add_itemButton = findViewById(R.id.add_item_button);
        remove_itemButton=findViewById(R.id.remove_item_button);
        recyclerView=findViewById(R.id.recyclerview);
        LogOutAlertBoxCard=findViewById(R.id.LogOutAlertBoxCard);
    }

    public void add_Item_Function(View view) {
    }

    public void remove_item_function(View view) {
    }
// CardView Add Item
    public void cancel_item(View view) {
    }

    public void clearItemCard(View view) {
    }

    public void addItem_to_db(View view) {
    }

    public void logout_common_function(View view) {
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
        startActivity(intent);
    }

    public void cancelLogoutButton(View view) {
        LogOutAlertBoxCard.setVisibility(View.GONE);
    }
    int doubleBackToExitPressed = 1;
    @RequiresApi
    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressed == 1) {
            LogOutAlertBoxCard.setVisibility(View.VISIBLE);
        }
        if (doubleBackToExitPressed == 2) {
            LogOutAlertBoxCard.setVisibility(View.GONE);
        }
        if (doubleBackToExitPressed == 3) {
            finishAffinity();
            System.exit(0);
        }
        else {
            doubleBackToExitPressed++;
            Toast.makeText(this, "Please press Back again to exit", Toast.LENGTH_SHORT).show();
        }

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                doubleBackToExitPressed=1;
            }
        }, 2000);
    }
}