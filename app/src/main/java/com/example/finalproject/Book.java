package com.example.finalproject;

import java.io.Serializable;

public class Book implements Serializable {
    private String authorName;
    private int categoryId;
    private int id;
    private String image;
    private String name;
    private int pagesNumber;
    private int releaseYear;
    private boolean fav;

    public Book() {
    }

    public Book(String name, String authorName, String image, int releaseYear, int pagesNumber, int categoryId) {
        this.name = name;
        this.authorName = authorName;
        this.image = image;
        this.releaseYear = releaseYear;
        this.pagesNumber = pagesNumber;
        this.categoryId = categoryId;
        fav = false;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthorName() {
        return this.authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getImage() {
        return this.image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getReleaseYear() {
        return this.releaseYear;
    }

    public void setReleaseYear(int releaseYear2) {
        this.releaseYear = releaseYear2;
    }

    public int getCategoryId() {
        return this.categoryId;
    }

    public void setCategoryId(int categoryId2) {
        this.categoryId = categoryId2;
    }

    public int getPagesNumber() {
        return this.pagesNumber;
    }

    public void setPagesNumber(int pagesNumber2) {
        this.pagesNumber = pagesNumber2;
    }


}
