package com.example.finalproject;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CategoryBooksActivity extends AppCompatActivity {
    private static final String TAG = "CategoryBooksActivity";
    ArrayList<Book> books;
    int currentCategoryId = -1;
    RecyclerView rvCategoryBooks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_books);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        this.rvCategoryBooks =  findViewById(R.id.rvCategoryBooks);
    }
    public void onResume() {
        super.onResume();
        initData();
    }
    private void initData() {
        Log.d(TAG, "initData: ");
        Intent receivedIntent = getIntent();
        if (receivedIntent != null && receivedIntent.hasExtra("categoryId")) {
            this.currentCategoryId = receivedIntent.getIntExtra("categoryId", -1);
        }
        if (this.currentCategoryId == -1) {
            Toast.makeText(this, "Invalid category, Please select another category", Toast.LENGTH_SHORT).show();
            return;
        }
        DBHelper dbHelper = DBHelper.getInstance(this);
        Category category = dbHelper.getCategoryById(this.currentCategoryId);
        if (category != null) {
            setTitle(category.getName());
        }
        this.books = dbHelper.getCategoryBooks(this.currentCategoryId);
        CategoryBooksAdapter categoryBooksAdapter = new CategoryBooksAdapter(this.books);
        RecyclerView.LayoutManager lm = new LinearLayoutManager(this);
        this.rvCategoryBooks.setAdapter(categoryBooksAdapter);
        this.rvCategoryBooks.setLayoutManager(lm);
    }
}