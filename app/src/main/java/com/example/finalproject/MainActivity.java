package com.example.finalproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    CardView cardCreatCategory, cardlibrary, cardCreatBook, cardFavourites;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cardCreatCategory = findViewById(R.id.cardCreatCategory);
        cardCreatCategory.setOnClickListener(this);

        cardCreatBook = findViewById(R.id.cardCreatBook);
        cardCreatBook.setOnClickListener((this));

        cardFavourites = findViewById(R.id.cardFavourites);
        cardFavourites.setOnClickListener((this));

        cardlibrary = findViewById(R.id.cardlibrary);
        cardlibrary.setOnClickListener((this));
    }

    @Override
    public void onClick(View view) {
        Intent i;
        switch (view.getId()) {
            case R.id.cardCreatCategory:
                i = new Intent(this, AddCategory.class);
                startActivity(i);
                break;
            case R.id.cardCreatBook:
                i = new Intent(this, AddNewBookActivity.class);
                startActivity(i);
                break;
            case R.id.cardlibrary:
                i = new Intent(this, CategoriesActivity.class);
                startActivity(i);
                break;

        }
    }
}