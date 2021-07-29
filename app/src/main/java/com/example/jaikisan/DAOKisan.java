package com.example.jaikisan;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DAOKisan {
    private DatabaseReference databaseReference;
    public Task<Void> add(String name, String city,String state,String address, String phone){
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        databaseReference = db.getReference("KisanUserDetails");
        KisanUserDetails user = new KisanUserDetails(name,city,state,address,phone);
        return databaseReference.child(phone).setValue(user);
    }
    public Task<Void> addItems(String itemName, String quantity,String price,String userID){
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        databaseReference = db.getReference("Kisan_Items");
        KisanItems items = new KisanItems(itemName,quantity,price,userID);
        return databaseReference.child(userID).child(itemName).setValue(items);
    }

    /*public void writeNewUser(String name, String city,String state,String address, String phone) {
        DatabaseReference mDatabase;
        mDatabase = FirebaseDatabase.getInstance().getReference();
        MainActivity.KisanUserDetails user = new MainActivity.KisanUserDetails(name,city,state,address,phone);
        mDatabase.child("users").child(phone).setValue(user);
    }*/
}
