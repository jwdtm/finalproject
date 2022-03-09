package com.example.finalproject;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CategoryBooksAdapter extends RecyclerView.Adapter<CategoryBooksViewHolder> {
    private ArrayList<Book> books;
    Context context;

    public CategoryBooksAdapter(ArrayList<Book> books) {
        this.books = books;
    }

    @NonNull
    @Override
    public CategoryBooksViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        context = parent.getContext();
        View v = LayoutInflater.from(context).inflate(R.layout.item_book, parent, false);
        return new CategoryBooksViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryBooksViewHolder holder, int position) {
        Book currentBook = this.books.get(position);
        holder.ivBookImage.setImageURI(Uri.parse(currentBook.getImage()));
        holder.tvBookName.setText(currentBook.getName());
        holder.tvBookReleaseYear.setText(String.valueOf(currentBook.getReleaseYear()));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              Intent intent = new Intent(context, BookDetailsActivity.class);
                intent.putExtra("book",  books.get(holder.getAdapterPosition()));
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return books.size();
    }
}
