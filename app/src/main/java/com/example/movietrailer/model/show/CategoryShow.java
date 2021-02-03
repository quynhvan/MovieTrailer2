package com.example.movietrailer.model.show;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.movietrailer.model.movie.ApiResponseMovie;

import java.util.ArrayList;

public class CategoryShow implements Parcelable {
    private String nameCategory;
    private ArrayList<ApiResponseShow.Show> listShow = new ArrayList<>();

    public CategoryShow(String nameCategory, ArrayList<ApiResponseShow.Show> listShow) {
        this.nameCategory = nameCategory;
        this.listShow = listShow;
    }

    protected CategoryShow(Parcel in) {
        nameCategory = in.readString();
    }

    public static final Creator<CategoryShow> CREATOR = new Creator<CategoryShow>() {
        @Override
        public CategoryShow createFromParcel(Parcel in) {
            return new CategoryShow(in);
        }

        @Override
        public CategoryShow[] newArray(int size) {
            return new CategoryShow[size];
        }
    };

    public String getNameCategory() {
        return nameCategory;
    }

    public void setNameCategory(String nameCategory) {
        this.nameCategory = nameCategory;
    }

    public ArrayList<ApiResponseShow.Show> getListShow() {
        return listShow;
    }

    public void setListShow(ArrayList<ApiResponseShow.Show> listShow) {
        this.listShow = listShow;
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
