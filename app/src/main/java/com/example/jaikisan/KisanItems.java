package com.example.jaikisan;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class KisanItems {
    public String itemName;
    public String quantity;
    public String price;
    //public String userID;

    public KisanItems() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public KisanItems(String itemName, String quantity,String price) {
        this.itemName = itemName;
        this.quantity = quantity;
        this.price = price;
        //this.userID = userID;
    }
}