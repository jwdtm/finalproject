package com.example.finalproject;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Iterator;

public class AddNewBookActivity extends AppCompatActivity {
    FloatingActionButton btnChooseImageFromGallery;
    Button btnCreat;
    ArrayList<Category> categories;
    EditText BookAuthorName;
    EditText BookName;
    EditText BookNumberOfPages;
    EditText BookReleaseYear;
    String imagePath = "";
    ImageView ivBookImage;
    int selectedCategoryId = -1;
    Spinner spBookCategory;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_book);
        setTitle("Add New Book"); //Page name
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //go back

        ivBookImage = findViewById(R.id.ivBookImage);
        BookName = findViewById(R.id.etBookName);
        BookAuthorName = findViewById(R.id.etBookAuthorName);
        BookReleaseYear = findViewById(R.id.etBookReleaseYear);
        BookNumberOfPages = findViewById(R.id.etBookNumberOfPages);
        spBookCategory = findViewById(R.id.spBookCategory);
        btnChooseImageFromGallery = findViewById(R.id.btnChooseImageFromGallery);
        btnCreat = findViewById(R.id.btnSave);

        categories = DBHelper.getInstance(getApplicationContext()).getAllCategories();
        ArrayList<String> categoriesNames = new ArrayList<>();
        Iterator<Category> iterator = this.categories.iterator();
        while (iterator.hasNext()) {
            // تخزين قيم categoroy داخل arrylist
            categoriesNames.add(iterator.next().getName());
        }
        // Adapter to seperator and add data
        spBookCategory.setAdapter(
                new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, categoriesNames));

        spBookCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                AddNewBookActivity addNewBookActivity = AddNewBookActivity.this;
                addNewBookActivity.selectedCategoryId = addNewBookActivity.categories.get(position).getId();
            }

            public void onNothingSelected(AdapterView<?> adapterView) {
                AddNewBookActivity.this.selectedCategoryId = -1;
            }
        });
        btnCreat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBHelper dbHelper = new DBHelper(AddNewBookActivity.this);
                //Define the variable to give it the value of the text
                String name = BookName.getText().toString();
                String authorName = BookAuthorName.getText().toString();
                int releaseYear = Integer.parseInt(BookReleaseYear.getText().toString());
                int numberOfPages = Integer.parseInt(BookNumberOfPages.getText().toString());

                if (name.isEmpty()) {
                    Toast.makeText(view.getContext(), "Invalid Book Name", Toast.LENGTH_SHORT).show();
                } else if (authorName.isEmpty()) {
                    Toast.makeText(view.getContext(), "Invalid Author Name", Toast.LENGTH_SHORT).show();
                } else if (releaseYear < 1000) {
                    Toast.makeText(view.getContext(), "Invalid Release Year", Toast.LENGTH_SHORT).show();
                } else if (numberOfPages <= 0) {
                    Toast.makeText(view.getContext(), "Please choose a valid page number", Toast.LENGTH_SHORT).show();
                } else if (selectedCategoryId <= 0) {
                    Toast.makeText(view.getContext(), "Please select a Category", Toast.LENGTH_SHORT).show();
                } else if (imagePath.isEmpty()) {
                    Toast.makeText(view.getContext(), "Invalid image, Please select valid image", Toast.LENGTH_SHORT).show();
                } else {

                    Book book = new Book(name, authorName, imagePath, releaseYear, numberOfPages, selectedCategoryId);
                    dbHelper.insertBook(book);
                    onBackPressed();
                }
            }
        });
        btnChooseImageFromGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction("android.intent.action.GET_CONTENT");
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), 100);
            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100 && resultCode == RESULT_OK) {
            Uri uri = data.getData();
            imagePath = uri.getPath();
            ivBookImage.setImageURI(uri);

        }
    }


}

