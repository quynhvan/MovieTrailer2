package com.example.movietrailer.model;

public class ItemMovie {
    private String title;
    private String star;
    private int poster;
    private String time;
    public ItemMovie(String title, String star, int poster, String time) {
        this.title = title;
        this.star = star;
        this.poster = poster;
        this.time = time;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStar() {
        return star;
    }

    public void setStar(String star) {
        this.star = star;
    }

    public int getPoster() {
        return poster;
    }

    public void setPoster(int poster) {
        this.poster = poster;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}

