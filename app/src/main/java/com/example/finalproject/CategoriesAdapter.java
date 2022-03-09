package com.example.finalproject;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesViewHolder> {

    private ArrayList<Category> categories;

    Context context;

    public CategoriesAdapter(ArrayList<Category> categories) {
        this.categories = categories;
    }

    @NonNull
    @Override
    public CategoriesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View v = LayoutInflater.from(context).inflate(R.layout.item_category, parent, false);
        return new CategoriesViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoriesViewHolder holder, int position) {

        holder.tvCategoryName.setText(this.categories.get(position).getName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 Intent intent = new Intent(context, CategoryBooksActivity.class);
                  intent.putExtra("categoryId", categories.get(holder.getAdapterPosition()).getId());
                  context.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return categories.size();
    }
}
