package com.example.jaikisan;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.annotations.NotNull;

public class RecyclerAdapterKisanProfile extends RecyclerView.Adapter<RecyclerAdapterKisanProfile.ViewHolder>{
    @NonNull
    @Override
    public RecyclerAdapterKisanProfile.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapterKisanProfile.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
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
