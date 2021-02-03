package com.example.movietrailer.model.show;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ApiResponseShow implements Parcelable {
    @Expose
    @SerializedName("results")
    private List<Show> show;
    @Expose
    @SerializedName("total_pages")
    private int totalPages;
    @Expose
    @SerializedName("total_results")
    private int totalResults;
    @Expose
    @SerializedName("page")
    private int page;

    protected ApiResponseShow(Parcel in) {
        totalPages = in.readInt();
        totalResults = in.readInt();
        page = in.readInt();
    }

    public static final Creator<ApiResponseShow> CREATOR = new Creator<ApiResponseShow>() {
        @Override
        public ApiResponseShow createFromParcel(Parcel in) {
            return new ApiResponseShow(in);
        }

        @Override
        public ApiResponseShow[] newArray(int size) {
            return new ApiResponseShow[size];
        }
    };

    public List<Show> getResults() {
        return show;
    }

    public void setResults(List<Show> show) {
        this.show = show;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(totalPages);
        dest.writeInt(totalResults);
        dest.writeInt(page);
    }

    public static class Show implements Parcelable{
        @Expose
        @SerializedName("poster_path")
        private String posterPath;
        @Expose
        @SerializedName("overview")
        private String overview;
        @Expose
        @SerializedName("vote_average")
        private double voteAverage;
        @Expose
        @SerializedName("id")
        private int id;
        @Expose
        @SerializedName("original_language")
        private String originalLanguage;
        @Expose
        @SerializedName("backdrop_path")
        private String backdropPath;
        @Expose
        @SerializedName("first_air_date")
        private String firstAirDate;
        @Expose
        @SerializedName("vote_count")
        private int voteCount;
        @Expose
        @SerializedName("origin_country")
        private List<String> originCountry;
        @Expose
        @SerializedName("popularity")
        private double popularity;
        @Expose
        @SerializedName("name")
        private String title;
        @Expose
        @SerializedName("genre_ids")
        private List<Integer> genreIds;
        @Expose
        @SerializedName("original_name")
        private String originalName;

        protected Show(Parcel in) {
            posterPath = in.readString();
            overview = in.readString();
            voteAverage = in.readDouble();
            id = in.readInt();
            originalLanguage = in.readString();
            backdropPath = in.readString();
            firstAirDate = in.readString();
            voteCount = in.readInt();
            originCountry = in.createStringArrayList();
            popularity = in.readDouble();
            title = in.readString();
            originalName = in.readString();
        }

        public static final Creator<Show> CREATOR = new Creator<Show>() {
            @Override
            public Show createFromParcel(Parcel in) {
                return new Show(in);
            }

            @Override
            public Show[] newArray(int size) {
                return new Show[size];
            }
        };

        public String getPosterPath() {
            return posterPath;
        }

        public void setPosterPath(String posterPath) {
            this.posterPath = posterPath;
        }

        public String getOverview() {
            return overview;
        }

        public void setOverview(String overview) {
            this.overview = overview;
        }

        public double getVoteAverage() {
            return voteAverage;
        }

        public void setVoteAverage(double voteAverage) {
            this.voteAverage = voteAverage;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getOriginalLanguage() {
            return originalLanguage;
        }

        public void setOriginalLanguage(String originalLanguage) {
            this.originalLanguage = originalLanguage;
        }

        public String getBackdropPath() {
            return backdropPath;
        }

        public void setBackdropPath(String backdropPath) {
            this.backdropPath = backdropPath;
        }

        public String getFirstAirDate() {
            return firstAirDate;
        }

        public void setFirstAirDate(String firstAirDate) {
            this.firstAirDate = firstAirDate;
        }

        public int getVoteCount() {
            return voteCount;
        }

        public void setVoteCount(int voteCount) {
            this.voteCount = voteCount;
        }

        public List<String> getOriginCountry() {
            return originCountry;
        }

        public void setOriginCountry(List<String> originCountry) {
            this.originCountry = originCountry;
        }

        public double getPopularity() {
            return popularity;
        }

        public void setPopularity(double popularity) {
            this.popularity = popularity;
        }

        public String getTitle() {
            return title;
        }

        public void setName(String title) {
            this.title = title;
        }

        public List<Integer> getGenreIds() {
            return genreIds;
        }

        public void setGenreIds(List<Integer> genreIds) {
            this.genreIds = genreIds;
        }

        public String getOriginalName() {
            return originalName;
        }

        public void setOriginalName(String originalName) {
            this.originalName = originalName;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(posterPath);
            dest.writeString(overview);
            dest.writeDouble(voteAverage);
            dest.writeInt(id);
            dest.writeString(originalLanguage);
            dest.writeString(backdropPath);
            dest.writeString(firstAirDate);
            dest.writeInt(voteCount);
            dest.writeStringList(originCountry);
            dest.writeDouble(popularity);
            dest.writeString(title);
            dest.writeString(originalName);
        }
    }
}
