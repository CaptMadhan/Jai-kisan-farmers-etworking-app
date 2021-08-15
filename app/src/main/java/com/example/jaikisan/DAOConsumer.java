package com.example.jaikisan;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DAOConsumer {
    private DatabaseReference databaseReference;
    FirebaseDatabase db = FirebaseDatabase.getInstance();
    public Task<Void> add(String name, String city, String state, String address, String phone){
        databaseReference = db.getReference("ConsumerUserDetails");
        ConsumerUserDetails user = new ConsumerUserDetails(name,city,state,address,phone);
        return databaseReference.child(phone).setValue(user);
    }
}
