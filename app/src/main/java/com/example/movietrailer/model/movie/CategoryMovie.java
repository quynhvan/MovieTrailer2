package com.example.movietrailer.model.movie;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class CategoryMovie implements Parcelable {
    private String nameCategory;
    private ArrayList<ApiResponseMovie.Movie> listMovie = new ArrayList<>();

    public CategoryMovie(String nameCategory, ArrayList<ApiResponseMovie.Movie> listMovie) {
        this.nameCategory = nameCategory;
        this.listMovie = listMovie;
    }

    protected CategoryMovie(Parcel in) {
        nameCategory = in.readString();
    }

    public static final Creator<CategoryMovie> CREATOR = new Creator<CategoryMovie>() {
        @Override
        public CategoryMovie createFromParcel(Parcel in) {
            return new CategoryMovie(in);
        }

        @Override
        public CategoryMovie[] newArray(int size) {
            return new CategoryMovie[size];
        }
    };

    public String getNameCategory() {
        return nameCategory;
    }

    public void setNameCategory(String nameCategory) {
        this.nameCategory = nameCategory;
    }

    public ArrayList<ApiResponseMovie.Movie> getListMovie() {
        return listMovie;
    }

    public void setListMovie(ArrayList<ApiResponseMovie.Movie> listMovie) {
        this.listMovie = listMovie;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(nameCategory);
    }
}
