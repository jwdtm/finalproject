package com.example.finalproject;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;



public class CategoriesViewHolder extends RecyclerView.ViewHolder {

    TextView tvCategoryName;

    public CategoriesViewHolder(View itemView) {
        super(itemView);
        this.tvCategoryName = (TextView) itemView.findViewById(R.id.tvCategoryName);
    }
}
