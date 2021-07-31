package com.example.jaikisan;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Comment;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static android.content.ContentValues.TAG;

public class Famers_Dashboard extends AppCompatActivity {
    Button add_itemButton,remove_itemButton;
    RecyclerView recyclerView;
    CardView LogOutAlertBoxCard,remove_Item_cardView,add_Item_cardView;

    EditText itemNameEdit,itemQuantityEdit,itemPriceEdit;

    ListView itemListCard;
    String[] itemNameArray;
    Map<String,String> map = new HashMap<String, String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_famers_dashboard);
        Objects.requireNonNull(getSupportActionBar()).hide();
        add_itemButton = findViewById(R.id.add_item_button);
        remove_itemButton=findViewById(R.id.remove_item_button);
        recyclerView=findViewById(R.id.recyclerview);

        LogOutAlertBoxCard=findViewById(R.id.LogOutAlertBoxCard);
        remove_Item_cardView=findViewById(R.id.remove_Item_cardView);
        add_Item_cardView=findViewById(R.id.add_Item_cardView);

        itemNameEdit=findViewById(R.id.itemNameEdit);
        itemQuantityEdit=findViewById(R.id.itemQuantityEdit);
        itemPriceEdit=findViewById(R.id.itemPriceEdit);
        itemListCard=findViewById(R.id.itemListCard);

        DatabaseReference databaseReference;
        databaseReference = FirebaseDatabase.getInstance().getReference();


        KisanItems demo1 = new KisanItems("demo1","50","60","123");
        KisanItems demo2 = new KisanItems("demo2","506","606","456");
        Map<String,String> map = new HashMap<String, String>();
        map.put(demo1.userID,demo1.itemName);
        map.put(demo2.userID,demo2.itemName);
        String[] itemNameArray = map.values().toArray(new String[0]);

        ArrayAdapter adapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                itemNameArray
        );
        itemListCard.setAdapter(adapter);

        ChildEventListener childEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String previousChildName) {
                Log.d(TAG, "onChildAdded:" + dataSnapshot.getKey());

                // A new comment has been added, add it to the displayed list
                //Comment comment = dataSnapshot.getValue(Comment.class);
                // ...
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String previousChildName) {
                Log.d(TAG, "onChildChanged:" + dataSnapshot.getKey());

                // A comment has changed, use the key to determine if we are displaying this
                // comment and if so displayed the changed comment.
                Comment newComment = dataSnapshot.getValue(Comment.class);
                String commentKey = dataSnapshot.getKey();

                // ...
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                Log.d(TAG, "onChildRemoved:" + dataSnapshot.getKey());

                // A comment has changed, use the key to determine if we are displaying this
                // comment and if so remove it.
                String commentKey = dataSnapshot.getKey();

                // ...
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String previousChildName) {
                Log.d(TAG, "onChildMoved:" + dataSnapshot.getKey());

                // A comment has changed position, use the key to determine if we are
                // displaying this comment and if so move it.
                Comment movedComment = dataSnapshot.getValue(Comment.class);
                String commentKey = dataSnapshot.getKey();

                // ...
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w(TAG, "postComments:onCancelled", databaseError.toException());
                Toast.makeText(getApplicationContext(), "Failed to load comments.",
                        Toast.LENGTH_SHORT).show();
            }
        };
        databaseReference.addChildEventListener(childEventListener);
    }

    public void add_Item_Function(View view) {
        add_Item_cardView.setVisibility(View.VISIBLE);
        remove_Item_cardView.setVisibility(View.GONE);
    }

    public void remove_item_function(View view) {
        add_Item_cardView.setVisibility(View.GONE);
        remove_Item_cardView.setVisibility(View.VISIBLE);
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
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        try{
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