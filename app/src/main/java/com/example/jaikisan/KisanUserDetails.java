package com.example.jaikisan;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class KisanUserDetails {
    public String name;
    public String city;
    public String state;
    public String address;
    public String phone;

    public KisanUserDetails() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public KisanUserDetails(String name, String city,String state,String address, String phone) {
        this.name = name;
        this.city = city;
        this.state = state;
        this.address = address;
        this.phone = phone;
    }
}
