package com.example.movietrailer.model.movie;


import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;


public class ApiResponseMovieImages {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("backdrops")
    @Expose
    private List<Backdrop> backdrops = null;
    @SerializedName("posters")
    @Expose
    private List<Poster> posters = null;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Backdrop> getBackdrops() {
        return backdrops;
    }

    public void setBackdrops(List<Backdrop> backdrops) {
        this.backdrops = backdrops;
    }

    public List<Poster> getPosters() {
        return posters;
    }

    public void setPosters(List<Poster> posters) {
        this.posters = posters;
    }


    public static class Backdrop implements Parcelable {

        @SerializedName("aspect_ratio")
        @Expose
        private double aspectRatio;
        @SerializedName("file_path")
        @Expose
        private String filePath;
        @SerializedName("height")
        @Expose
        private int height;
        @SerializedName("iso_639_1")
        @Expose
        private Object iso6391;
        @SerializedName("vote_average")
        @Expose
        private float voteAverage;
        @SerializedName("vote_count")
        @Expose
        private int voteCount;
        @SerializedName("width")
        @Expose
        private int width;


        protected Backdrop(Parcel in) {
            aspectRatio = in.readDouble();
            filePath = in.readString();
            height = in.readInt();
            voteAverage = in.readFloat();
            voteCount = in.readInt();
            width = in.readInt();
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeDouble(aspectRatio);
            dest.writeString(filePath);
            dest.writeInt(height);
            dest.writeFloat(voteAverage);
            dest.writeInt(voteCount);
            dest.writeInt(width);
        }

        @Override
        public int describeContents() {
            return 0;
        }

        public static final Creator<Backdrop> CREATOR = new Creator<Backdrop>() {
            @Override
            public Backdrop createFromParcel(Parcel in) {
                return new Backdrop(in);
            }

            @Override
            public Backdrop[] newArray(int size) {
                return new Backdrop[size];
            }
        };

        public double getAspectRatio() {
            return aspectRatio;
        }

        public void setAspectRatio(double aspectRatio) {
            this.aspectRatio = aspectRatio;
        }

        public String getFilePath() {
            return filePath;
        }

        public void setFilePath(String filePath) {
            this.filePath = filePath;
        }

        public int getHeight() {
            return height;
        }

        public void setHeight(int height) {
            this.height = height;
        }

        public Object getIso6391() {
            return iso6391;
        }

        public void setIso6391(Object iso6391) {
            this.iso6391 = iso6391;
        }

        public float getVoteAverage() {
            return voteAverage;
        }

        public void setVoteAverage(float voteAverage) {
            this.voteAverage = voteAverage;
        }

        public int getVoteCount() {
            return voteCount;
        }

        public void setVoteCount(int voteCount) {
            this.voteCount = voteCount;
        }

        public int getWidth() {
            return width;
        }

        public void setWidth(int width) {
            this.width = width;
        }
    }


    public static class Poster {

        @SerializedName("aspect_ratio")
        @Expose
        private double aspectRatio;
        @SerializedName("file_path")
        @Expose
        private String filePath;
        @SerializedName("height")
        @Expose
        private int height;
        @SerializedName("iso_639_1")
        @Expose
        private String iso6391;
        @SerializedName("vote_average")
        @Expose
        private float voteAverage;
        @SerializedName("vote_count")
        @Expose
        private float voteCount;
        @SerializedName("width")
        @Expose
        private int width;

        public double getAspectRatio() {
            return aspectRatio;
        }

        public void setAspectRatio(double aspectRatio) {
            this.aspectRatio = aspectRatio;
        }

        public String getFilePath() {
            return filePath;
        }

        public void setFilePath(String filePath) {
            this.filePath = filePath;
        }

        public int getHeight() {
            return height;
        }

        public void setHeight(int height) {
            this.height = height;
        }

        public String getIso6391() {
            return iso6391;
        }

        public void setIso6391(String iso6391) {
            this.iso6391 = iso6391;
        }

        public float getVoteAverage() {
            return voteAverage;
        }

        public void setVoteAverage(float voteAverage) {
            this.voteAverage = voteAverage;
        }

        public float getVoteCount() {
            return voteCount;
        }

        public void setVoteCount(float voteCount) {
            this.voteCount = voteCount;
        }

        public int getWidth() {
            return width;
        }

        public void setWidth(int width) {
            this.width = width;
        }
    }
}