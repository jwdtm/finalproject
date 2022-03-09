package com.example.finalproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {
    public static final String BOOK_COLUMN_AUTHOR_NAME = "author_name";
    public static final String BOOK_COLUMN_CATEGORY_ID = "category_id";
    public static final String BOOK_COLUMN_IMAGE_URL = "image_url";
    public static final String BOOK_COLUMN_PAGES_NUMBER = "pages_number";
    public static final String BOOK_COLUMN_RELEASE_YEAR = "release_year";
    public static final String BOOK_TABLE_NAME = "books";
    public static final String CATEGORY_TABLE_NAME = "categories";
    public static final String DATABASE_NAME = "MyDBName.db";
    Context context;

    static DBHelper instance;

    public static DBHelper getInstance(Context context) {
        if (instance == null) {
            instance = new DBHelper(context);
        }
        return instance;
    }

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, (SQLiteDatabase.CursorFactory) null, 1);
        this.context = context;
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE categories(id INTEGER PRIMARY KEY AUTOINCREMENT ,name TEXT )");
        db.execSQL("CREATE TABLE books(id INTEGER PRIMARY KEY AUTOINCREMENT ,name TEXT ,author_name TEXT ,image_url TEXT ,release_year INTEGER ,pages_number INTEGER ,category_id INTEGER ,FOREIGN KEY (category_id) REFERENCES name(id) ON DELETE CASCADE)");
    }

    public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
        database.execSQL("DROP TABLE IF EXISTS categories");
        database.execSQL("DROP TABLE IF EXISTS books");
        onCreate(database);
    }

    public ArrayList<Category> getAllCategories() {
        ArrayList<Category> allCategories = new ArrayList<>();
        Cursor res = getReadableDatabase().rawQuery("SELECT * FROM categories", (String[]) null);
        res.moveToFirst();
        int ID = res.getColumnIndex("id");
        int Name = res.getColumnIndex("name");
        while (!res.isAfterLast()) {
            int id = res.getInt(ID);
            String name = res.getString(Name);
            Category category = new Category();
            category.setId(id);
            category.setName(name);
            allCategories.add(category);
            res.moveToNext();
        }
        return allCategories;
    }

    public Category getCategoryById(int categoryId) {
        ArrayList<Category> allCategories = new ArrayList<>();
        Cursor res = getReadableDatabase().rawQuery("SELECT * FROM categories WHERE id =? ", new String[]{String.valueOf(categoryId)});
        res.moveToFirst();
        int i_id = res.getColumnIndex("id");
        int i_name = res.getColumnIndex("name");
        while (!res.isAfterLast()) {
            int id = res.getInt(i_id);
            String name = res.getString(i_name);
            Category category = new Category();
            category.setId(id);
            category.setName(name);
            allCategories.add(category);
            res.moveToNext();
        }
        if (!allCategories.isEmpty()) {
            return allCategories.get(0);
        }
        return null;
    }

    public void insertCategory(Category category) {
        //Objective definition SQl
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", category.getName());
        db.insert(CATEGORY_TABLE_NAME, (String) null, contentValues);
    }

    public ArrayList<Book> getCategoryBooks(int categoryId) {
        ArrayList<Book> categoryBooks = new ArrayList<>();
        Cursor res = getReadableDatabase().rawQuery("SELECT * FROM books WHERE category_id  = ? ", new String[]{Integer.toString(categoryId)});
        res.moveToFirst();
        int id = res.getColumnIndex("id");
        int name = res.getColumnIndex("name");
        int authorName = res.getColumnIndex(BOOK_COLUMN_AUTHOR_NAME);
        int releaseYear = res.getColumnIndex(BOOK_COLUMN_RELEASE_YEAR);
        int image = res.getColumnIndex(BOOK_COLUMN_IMAGE_URL);
        int pagesNumber = res.getColumnIndex(BOOK_COLUMN_PAGES_NUMBER);
        int categoryid = res.getColumnIndex(BOOK_COLUMN_CATEGORY_ID);
        while (!res.isAfterLast()) {
            Book book = new Book();
            book.setId(res.getInt(id));
            book.setName(res.getString(name));
            book.setAuthorName(res.getString(authorName));
            book.setReleaseYear(res.getInt(releaseYear));
            book.setImage(res.getString(image));
            book.setPagesNumber(res.getInt(pagesNumber));
            book.setCategoryId(res.getInt(categoryid));
            categoryBooks.add(book);
            res.moveToNext();
        }
        return categoryBooks;
    }

    public void insertBook(Book book) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", book.getName());
        contentValues.put(BOOK_COLUMN_AUTHOR_NAME, book.getAuthorName());
        contentValues.put(BOOK_COLUMN_RELEASE_YEAR, book.getReleaseYear());
        contentValues.put(BOOK_COLUMN_IMAGE_URL, book.getImage());
        contentValues.put(BOOK_COLUMN_PAGES_NUMBER, book.getPagesNumber());
        contentValues.put(BOOK_COLUMN_CATEGORY_ID, book.getCategoryId());
        db.insert(BOOK_TABLE_NAME, (String) null, contentValues);
    }

    public void updateBook(Book book) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", book.getName());
        contentValues.put(BOOK_COLUMN_AUTHOR_NAME, book.getAuthorName());
        contentValues.put(BOOK_COLUMN_RELEASE_YEAR, Integer.valueOf(book.getReleaseYear()));
        contentValues.put(BOOK_COLUMN_IMAGE_URL, book.getImage());
        contentValues.put(BOOK_COLUMN_PAGES_NUMBER, Integer.valueOf(book.getPagesNumber()));
        contentValues.put(BOOK_COLUMN_CATEGORY_ID, Integer.valueOf(book.getCategoryId()));
        db.update(BOOK_TABLE_NAME, contentValues, "id = ? ", new String[]{Integer.toString(book.getId())});
    }

    public void deleteBook(int bookId) {
        Integer.valueOf(getWritableDatabase().delete(BOOK_TABLE_NAME, "id = ? ", new String[]{Integer.toString(bookId)}));
    }
}
