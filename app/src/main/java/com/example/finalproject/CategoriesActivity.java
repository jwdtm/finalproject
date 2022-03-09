package com.example.finalproject;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CategoriesActivity extends AppCompatActivity {
    ArrayList<Category> categories;
    RecyclerView rvCategories;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);
        setTitle("library");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        this.rvCategories = findViewById(R.id.rvCategoryBooks);
        this.categories = DBHelper.getInstance(this).getAllCategories();
        CategoriesAdapter categoriesAdapter = new CategoriesAdapter(this.categories);
        rvCategories.setAdapter(categoriesAdapter);
        rvCategories.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rvCategories.setLayoutManager(layoutManager);

    }
}