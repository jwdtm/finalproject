package com.example.finalproject;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class BookDetailsActivity extends AppCompatActivity {
    Button btnEditBook;

    Book currentBook = null;
    ImageView BookImage;
    TextView BookAuthorName;
    TextView BookCategory;
    TextView BookName;
    TextView BookNumberOfPages;
    TextView BookReleaseYear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_details);
        setContentView((int) R.layout.activity_book_details);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        BookImage = findViewById(R.id.ivBookImage);
        BookName = findViewById(R.id.tvBookName);
        BookAuthorName = findViewById(R.id.tvBookAuthorName);
        BookReleaseYear = findViewById(R.id.tvBookReleaseYear);
        BookNumberOfPages = findViewById(R.id.tvBookNumberOfPages);
        BookCategory = findViewById(R.id.tvBookCategory);
        btnEditBook = findViewById(R.id.btnEditBook);


        Intent receivedIntent = getIntent();
        if (receivedIntent != null && receivedIntent.hasExtra("book")) {
            this.currentBook = (Book) receivedIntent.getSerializableExtra("book");
        }
        Book book = this.currentBook;
        setTitle(book.getName());

        BookName.setText(this.currentBook.getName());
        BookAuthorName.setText(this.currentBook.getAuthorName());
        BookReleaseYear.setText(String.valueOf(this.currentBook.getReleaseYear()));
        BookNumberOfPages.setText(String.valueOf(this.currentBook.getPagesNumber()));
        BookCategory.setText(String.valueOf(this.currentBook.getCategoryId()));
        btnEditBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), EditBookActivity.class);
                intent.putExtra("book", currentBook);
                startActivity(intent);
                finish();
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.delete:
                DBHelper.getInstance(getApplicationContext()).deleteBook(currentBook.getId());
                onBackPressed();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}
