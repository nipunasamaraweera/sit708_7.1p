package com.example.lostfound;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

// Class definition for Item_Adapter extending RecyclerView.Adapter
public class Item_Adapter extends RecyclerView.Adapter<Item_Adapter.MyViewHolder> {

    // Declare static variables for dataList and context
    private static List<String[]> dataList;
    private static Context context;

    // Constructor to initialize context and dataList
    public Item_Adapter(Context context, List<String[]> dataList) {
        this.context = context;
        this.dataList = dataList;
    }

    // onCreateViewHolder method to inflate item layout and create MyViewHolder
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Inflate the item layout and create a new MyViewHolder instance
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        return new MyViewHolder(view);
    }

    // onBindViewHolder method to bind data to ViewHolder at a specific position
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        // Retrieve data for the item at the given position
        String[] data = dataList.get(position);
        // Set text for the TextView in the ViewHolder
        holder.textView.setText(data[6].toUpperCase() + ": " + data[1]);
    }

    // getItemCount method to return the size of the dataList
    @Override
    public int getItemCount() {
        return dataList.size();
    }

    // MyViewHolder class representing each item's view and metadata
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView textView;

        // Constructor to initialize views and set click listener
        public MyViewHolder(View itemView) {
            super(itemView);
            // Find and assign TextView from the item layout
            textView = itemView.findViewById(R.id.tv_item_name);
            // Set OnClickListener for the item view to navigate to ViewItem activity
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Create an Intent to navigate to ViewItem activity
                    Intent i = new Intent(context, ViewItem.class);
                    // Pass data associated with the clicked item to ViewItem activity
                    i.putExtra("data", dataList.get(getAdapterPosition()));
                    // Start ViewItem activity using the context
                    context.startActivity(i);
                }
            });
        }
    }
}