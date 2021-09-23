package com.example.jaikisan;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class RecyclerAdapterConsumer extends RecyclerView.Adapter<RecyclerAdapterConsumer.ViewHolder> {
    List<KisanUserDetails> data;
    Context context;

    public RecyclerAdapterConsumer(List<KisanUserDetails> data, Context context) {
        this.data = data;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerAdapterConsumer.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view =layoutInflater.inflate(R.layout.kisan_name_city_list,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapterConsumer.ViewHolder holder, int position) {
        holder.KisanName.setText(data.get(position).name);
        holder.KisanCity.setText(data.get(position).city);
        holder.KisanName.setOnClickListener(v ->showFarmerProfile(data.get(position)));
        holder.KisanCity.setOnClickListener(v ->showFarmerProfile(data.get(position)));

    }

    @Override
    public int getItemCount() {
        return data.size();
    }
    void showFarmerProfile(KisanUserDetails data) {
        Intent intent = new Intent(context,Consumer_farmer_profile.class);
        intent.putExtra("Farmer Name",data.name);
        intent.putExtra("Farmer phone",data.phone);
        intent.putExtra("Farmer address",data.address);
        intent.putExtra("Farmer state",data.state);
        intent.putExtra("Farmer city",data.city);
        context.startActivity(intent);
    }
    // Fetch the custom view created
    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView KisanName,KisanCity;
        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            KisanName = itemView.findViewById(R.id.KisanName);
            KisanCity = itemView.findViewById(R.id.KisanCity);
        }
    }
}
