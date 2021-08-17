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

import com.google.firebase.database.annotations.NotNull;

import java.util.List;

class RecyclerAdapterKisan extends RecyclerView.Adapter<RecyclerAdapterKisan.ViewHolder>
{
    List<KisanItems> data;
    Context context;

    public RecyclerAdapterKisan(List<KisanItems> data, Context context) {
        this.data = data;
        this.context = context;
    }

    @NonNull
    @NotNull
    @Override
    // create new view when the recycler view is opened
    public RecyclerAdapterKisan.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view =layoutInflater.inflate(R.layout.farmer_items,parent,false);
        return new ViewHolder(view);
    }

    @Override
    //Bind the data to the views i.e serve the data to the views
    public void onBindViewHolder(@NonNull @NotNull RecyclerAdapterKisan.ViewHolder holder, int position) {
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
        DAOKisan dao = new DAOKisan();
        int newQuantity = Integer.parseInt(data.get(position).quantity);
        newQuantity-=1;
        data.get(position).quantity = String.valueOf(newQuantity);
        dao.modifyQuantity(data.get(position).itemName,String.valueOf(newQuantity)).addOnSuccessListener(suc->
                Toast.makeText(context," Quantity Updated to "+ data.get(position).quantity,Toast.LENGTH_SHORT).show()).addOnFailureListener(er-> Toast.makeText(context,"Error Occurred, Please try again",Toast.LENGTH_LONG).show());
        holder.quantity.setText(String.valueOf(newQuantity));

    }
    void increase_quantity(int position, RecyclerAdapterKisan.ViewHolder holder){
        DAOKisan dao = new DAOKisan();
        int newQuantity = Integer.parseInt(data.get(position).quantity);
        newQuantity+=1;
        data.get(position).quantity = String.valueOf(newQuantity);
        dao.modifyQuantity(data.get(position).itemName,String.valueOf(newQuantity))
                .addOnSuccessListener(suc->
                Toast.makeText(context," Quantity Updated to "+ data.get(position).quantity,Toast.LENGTH_SHORT).show())
                .addOnFailureListener(er->
                        Toast.makeText(context,"Error Occurred, Please try again",Toast.LENGTH_LONG).show());
        holder.quantity.setText(String.valueOf(newQuantity));

    }

}
