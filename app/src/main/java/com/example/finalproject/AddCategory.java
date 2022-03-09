package com.example.finalproject;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AddCategory extends AppCompatActivity {
    Button btnCreateCategory;
    EditText Name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_category);
        setTitle("Create New Category");   //Page name
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);  // go back
        //Define variables in xml
        Name = findViewById(R.id.Name);
        btnCreateCategory = findViewById(R.id.btnCreateCategory);

        btnCreateCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBHelper dbHelper = new DBHelper(AddCategory.this);
                //Define the variable to give it the value of the text

                //If the variable is Empty
                if (Name.getText().toString().isEmpty()) {
                    Toast.makeText(view.getContext(), "Category Name Can't be empty", Toast.LENGTH_SHORT).show();
                    return;
                }
                String nameCategory = Name.getText().toString();
                Category category = new Category(nameCategory);
                dbHelper.insertCategory(category);
                Toast.makeText(view.getContext(), "Category Created", Toast.LENGTH_SHORT).show();
                onBackPressed();

            }
        });


    }


}