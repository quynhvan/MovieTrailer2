package com.example.movietrailer.model;

public class Book {
    private int idImage;
    private String tvTitle;

    public int getIdImage() {
        return idImage;
    }

    public void setIdImage(int idImage) {
        this.idImage = idImage;
    }

    public String getTvTitle() {
        return tvTitle;
    }

    public void setTvTitle(String tvTitle) {
        this.tvTitle = tvTitle;
    }

    public Book(int idImage, String tvTitle) {
        this.idImage = idImage;
        this.tvTitle = tvTitle;
    }
}
