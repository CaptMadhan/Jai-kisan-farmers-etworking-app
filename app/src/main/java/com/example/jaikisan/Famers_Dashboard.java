package com.example.jaikisan;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Famers_Dashboard extends AppCompatActivity {
    Button add_itemButton,remove_itemButton;
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_famers_dashboard);
        add_itemButton = findViewById(R.id.add_item_button);
        remove_itemButton=findViewById(R.id.remove_item_button);
        recyclerView=findViewById(R.id.recyclerview);
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
}