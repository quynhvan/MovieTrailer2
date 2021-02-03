package com.example.movietrailer.model.movie;

import android.os.Parcel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ApiUpNow extends ApiResponseMovie {

    @SerializedName("dates")
    @Expose
    private Dates dates;

    protected ApiUpNow(Parcel in) {
        super(in);
    }

    public Dates getDates() {
        return dates;
    }
    public void setDates(Dates dates) {
        this.dates = dates;
    }

    public static class Dates {

        @SerializedName("maximum")
        @Expose
        private String maximum;
        @SerializedName("minimum")
        @Expose
        private String minimum;

        public String getMaximum() {
            return maximum;
        }

        public void setMaximum(String maximum) {
            this.maximum = maximum;
        }

        public String getMinimum() {
            return minimum;
        }

        public void setMinimum(String minimum) {
            this.minimum = minimum;
        }

    }

}
