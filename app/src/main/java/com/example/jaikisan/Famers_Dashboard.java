package com.example.jaikisan;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
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
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Famers_Dashboard extends AppCompatActivity {
    Button add_itemButton, remove_itemButton;
    RecyclerView recyclerView;
    CardView LogOutAlertBoxCard, remove_Item_cardView, add_Item_cardView;

    EditText itemNameEdit, itemQuantityEdit, itemPriceEdit;

    ListView itemListCard;
    String[] itemNameArray;
    List<String> itemNameList = new ArrayList<>();
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_famers_dashboard);
        Objects.requireNonNull(getSupportActionBar()).hide();
        add_itemButton = findViewById(R.id.add_item_button);
        remove_itemButton = findViewById(R.id.remove_item_button);
        recyclerView = findViewById(R.id.recyclerview);

        LogOutAlertBoxCard = findViewById(R.id.LogOutAlertBoxCard);
        remove_Item_cardView = findViewById(R.id.remove_Item_cardView);
        add_Item_cardView = findViewById(R.id.add_Item_cardView);

        itemNameEdit = findViewById(R.id.itemNameEdit);
        itemQuantityEdit = findViewById(R.id.itemQuantityEdit);
        itemPriceEdit = findViewById(R.id.itemPriceEdit);
        itemListCard = findViewById(R.id.itemListCard);

        databaseReference = FirebaseDatabase.getInstance().getReference();


    }

    public void add_Item_Function(View view) {
        add_Item_cardView.setVisibility(View.VISIBLE);
        remove_Item_cardView.setVisibility(View.GONE);
    }

    public void remove_item_function(View view) {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        add_Item_cardView.setVisibility(View.GONE);
        remove_Item_cardView.setVisibility(View.VISIBLE);

        // To retreive data and store item name in itemNameList
        databaseReference.child("Kisan_Items").child(user.getPhoneNumber())
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            KisanItems items = snapshot.getValue(KisanItems.class);
                            itemNameList.add(items.itemName);
                            //Toast.makeText(getApplicationContext(),items.itemName , Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                    }
                });
        itemNameArray = itemNameList.toArray(new String[0]);
        ArrayAdapter adapter = new ArrayAdapter<String>(
                getApplicationContext(),
                android.R.layout.simple_list_item_1,
                itemNameArray
        );
        itemListCard.setAdapter(adapter);
        itemNameList.clear();
    }



// CardView Add Item
    public void cancel_item(View view) {
        remove_Item_cardView.setVisibility(View.GONE);
        add_Item_cardView.setVisibility(View.GONE);
    }

    public void clearItemCard(View view) {
        itemNameEdit.setText("");
        itemQuantityEdit.setText("");
        itemPriceEdit.setText("");
    }

    public void addItem_to_db(View view) {
        String itemName,quantity,price,userID;
        itemName = itemNameEdit.getText().toString();
        quantity = itemQuantityEdit.getText().toString();
        price = itemPriceEdit.getText().toString();
        if(itemName.isEmpty()||quantity.isEmpty()||price.isEmpty()){
            Toast.makeText(getApplicationContext(),"Enter valid details for all fields",Toast.LENGTH_LONG).show();
            return;
        }

        try{
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            userID = Objects.requireNonNull(user).getPhoneNumber();
            DAOKisan dao = new DAOKisan();
            dao.addItems(itemName,quantity,price,userID);
            Toast.makeText(getApplicationContext(),"Item Added",Toast.LENGTH_LONG).show();
            itemNameEdit.setText("");
            itemQuantityEdit.setText("");
            itemPriceEdit.setText("");
            add_Item_cardView.setVisibility(View.GONE);
            remove_Item_cardView.setVisibility(View.GONE);
        }catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Some Error Occurred"+e, Toast.LENGTH_LONG).show();
        }
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
            add_Item_cardView.setVisibility(View.GONE);
            remove_Item_cardView.setVisibility(View.GONE);
        }
        if (doubleBackToExitPressed == 2) {
            LogOutAlertBoxCard.setVisibility(View.VISIBLE);
            //Toast.makeText(this, "Please press Back again to exit", Toast.LENGTH_SHORT).show();
        }
        if (doubleBackToExitPressed == 3) {
            finishAffinity();
            System.exit(0);
        }
        else {
            doubleBackToExitPressed++;
        }

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                doubleBackToExitPressed=1;
            }
        }, 2000);
    }
}