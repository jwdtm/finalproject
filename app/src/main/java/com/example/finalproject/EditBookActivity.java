package com.example.finalproject;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class EditBookActivity extends AppCompatActivity {
    public static final int PICK_REQUEST_CODE = 10012;
    FloatingActionButton btnChooseImageFromGallery;
    Button btnUpdate;
    Book currentBook = null;
    EditText etBookAuthorName;
    EditText etBookCategoryId;
    EditText etBookName;
    EditText etBookNumberOfPages;
    EditText etBookReleaseYear;
    String imagePath = "";
    ImageView ivBookImage;
    Spinner spBookCategory;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100 && resultCode == RESULT_OK) {

            Uri uri = data.getData();
            imagePath = uri.getPath();
            ivBookImage.setImageURI(uri);

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_book);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ivBookImage = findViewById(R.id.ivBookImage);
        etBookName = findViewById(R.id.etBookName);
        etBookAuthorName = findViewById(R.id.etBookAuthorName);
        etBookReleaseYear = findViewById(R.id.etBookReleaseYear);
        etBookNumberOfPages = findViewById(R.id.etBookNumberOfPages);
        etBookCategoryId = findViewById(R.id.etBookCategoryId);
        spBookCategory = findViewById(R.id.spBookCategory);
        btnChooseImageFromGallery = findViewById(R.id.btnChooseImageFromGallery);
        btnUpdate = findViewById(R.id.btnUpdate);
        Intent receivedIntent = getIntent();
        if (receivedIntent != null && receivedIntent.hasExtra("book")) {
            this.currentBook = (Book) receivedIntent.getSerializableExtra("book");
        }
        Book book = this.currentBook;


        this.ivBookImage.setImageURI(Uri.parse(book.getImage()));
        this.etBookName.setText(this.currentBook.getName());
        this.etBookAuthorName.setText(this.currentBook.getAuthorName());
        this.etBookReleaseYear.setText(String.valueOf(this.currentBook.getReleaseYear()));
        this.etBookNumberOfPages.setText(String.valueOf(this.currentBook.getPagesNumber()));
        this.etBookCategoryId.setText(String.valueOf(this.currentBook.getCategoryId()));
        this.imagePath = this.currentBook.getImage();

        btnChooseImageFromGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction("android.intent.action.GET_CONTENT");
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), 100);
            }
        });
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int categoryId;
                int numberOfPages;
                int releaseYear;
                String name = etBookName.getText().toString();
                String authorName = etBookAuthorName.getText().toString();
                try {
                    releaseYear = Integer.parseInt(etBookReleaseYear.getText().toString());
                    numberOfPages = Integer.parseInt(etBookNumberOfPages.getText().toString());
                    categoryId = Integer.parseInt(etBookCategoryId.getText().toString());
                } catch (NumberFormatException numberFormatException) {
                    numberFormatException.printStackTrace();
                    releaseYear = -1;
                    numberOfPages = -1;
                    categoryId = -1;
                }
                if (name.isEmpty()) {
                    Toast.makeText(view.getContext(), "Invalid Book Name", Toast.LENGTH_SHORT).show();
                } else if (authorName.isEmpty()) {
                    Toast.makeText(view.getContext(), "Invalid Author Name", Toast.LENGTH_SHORT).show();
                } else if (releaseYear < 1000) {
                    Toast.makeText(view.getContext(), "Invalid Release Year", Toast.LENGTH_SHORT).show();
                } else if (numberOfPages <= 0) {
                    Toast.makeText(view.getContext(), "Number of pages can't be 0 or less", Toast.LENGTH_SHORT).show();
                } else if (categoryId <= 0) {
                    Toast.makeText(view.getContext(), "Category id can't be 0 or less", Toast.LENGTH_SHORT).show();
                } else if (imagePath.isEmpty()) {
                    Toast.makeText(view.getContext(), "Invalid image, Please select valid image", Toast.LENGTH_SHORT).show();
                } else {
                    Book book = new Book(name, authorName, imagePath, releaseYear, numberOfPages, categoryId);
                    book.setId(currentBook.getId());
                    DBHelper.getInstance(view.getContext()).updateBook(book);
                    onBackPressed();
                }
            }
        });

    }
}