package com.example.jaikisan;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.annotations.NotNull;

import java.util.ArrayList;

public class RecyclerAdapterKisanProfile extends RecyclerView.Adapter<RecyclerAdapterKisanProfile.ViewHolder>{
    ArrayList<KisanItems> data;
    Context context;

    public RecyclerAdapterKisanProfile(ArrayList<KisanItems> data, Context context) {
        this.data = data;
        this.context = context;
    }
    @NonNull
    @NotNull
    @Override
    public RecyclerAdapterKisanProfile.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view =layoutInflater.inflate(R.layout.farmer_items_profile,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapterKisanProfile.ViewHolder holder, int position) {
        holder.itemName.setText(data.get(position).itemName);
        holder.quantity.setText(data.get(position).quantity);
        holder.price.setText(data.get(position).price);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
    // Fetch the custom view created
    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView itemName,quantity,price;
        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            itemName = itemView.findViewById(R.id.itemName);
            quantity = itemView.findViewById(R.id.quantity);
            price = itemView.findViewById(R.id.price);
        }
    }
}
