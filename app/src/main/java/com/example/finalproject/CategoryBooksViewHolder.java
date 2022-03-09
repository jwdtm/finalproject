package com.example.finalproject;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;



public class CategoryBooksViewHolder extends RecyclerView.ViewHolder {
    ImageView ivBookImage;
    TextView tvBookName;
    TextView tvBookReleaseYear;

    public CategoryBooksViewHolder(@NonNull View itemView) {
        super(itemView);
        this.ivBookImage = itemView.findViewById(R.id.ivBookImage);
        this.tvBookName = itemView.findViewById(R.id.tvBookName);
        this.tvBookReleaseYear = itemView.findViewById(R.id.tvBookReleaseYear);
    }
}
