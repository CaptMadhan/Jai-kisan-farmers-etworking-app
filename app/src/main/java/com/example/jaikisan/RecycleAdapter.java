package com.example.jaikisan;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.annotations.NotNull;

import java.util.List;

class RecycleAdapter extends RecyclerView.Adapter<RecycleAdapter.ViewHolder>
{
    List<KisanItems> data;
    Context context;

    public RecycleAdapter(List<KisanItems> data, Context context) {
        this.data = data;
        this.context = context;
    }

    @NonNull
    @NotNull
    @Override
    // create new view when the recycler view is opened
    public RecycleAdapter.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view =layoutInflater.inflate(R.layout.farmer_items,parent,false);
        return new ViewHolder(view);
    }

    @Override
    //Bind the data to the views i.e serve the data to the views
    public void onBindViewHolder(@NonNull @NotNull RecycleAdapter.ViewHolder holder, int position) {
        holder.itemName.setText(data.get(position).itemName);
        holder.quantity.setText(data.get(position).quantity);
        holder.increaseQuantity.setOnClickListener(v -> increase_quantity(position,holder));
        holder.decreaseQuantity.setOnClickListener(v -> decrease_quantity(position,holder));
        holder.price.setText(data.get(position).price);
    }

    @Override
    // for number of views
    public int getItemCount() {

        return data.size();
    }
    // Fetch the custom view created
    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView itemName,quantity,price;
        Button increaseQuantity,decreaseQuantity;
        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            itemName = itemView.findViewById(R.id.itemName);
            quantity = itemView.findViewById(R.id.quantity);
            increaseQuantity = itemView.findViewById(R.id.increaseQuantity);
            decreaseQuantity = itemView.findViewById(R.id.decreaseQuantity);
            price = itemView.findViewById(R.id.price);
        }
    }
    void decrease_quantity(int position, ViewHolder holder){

    }
    void increase_quantity(int position, RecycleAdapter.ViewHolder holder){

    }

}
