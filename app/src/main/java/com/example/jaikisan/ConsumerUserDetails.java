package com.example.jaikisan;

public class ConsumerUserDetails {
    public String consumer_name;
    public String consumer_city;
    public String consumer_state;
    public String consumer_address;
    public String consumer_phone;

    public ConsumerUserDetails() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public ConsumerUserDetails(String name, String city,String state,String address, String phone) {
        consumer_name = name;
        consumer_city = city;
        consumer_state = state;
        consumer_address = address;
        consumer_phone = phone;
    }
}
