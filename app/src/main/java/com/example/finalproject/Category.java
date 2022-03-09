package com.example.finalproject;

public class Category {


    private int  id;
    private String name;

    public Category() {
    }

    public Category(String name2) {
        this.name = name2;
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

    public void setName(String name2) {
        this.name = name2;
    }
}
