package com.example.movietrailer.model.movie;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ApiResponseMovie implements Parcelable {
    @Expose
    @SerializedName("results")
    private List<Movie> movies;
    @Expose
    @SerializedName("total_pages")
    private int totalPages;
    @Expose
    @SerializedName("total_results")
    private int totalResults;
    @Expose
    @SerializedName("page")
    private int page;

    private ApiResponseMovieDetail movieDetail;

    protected ApiResponseMovie(Parcel in) {
        totalPages = in.readInt();
        totalResults = in.readInt();
        page = in.readInt();
        movieDetail = in.readParcelable(ApiResponseMovieDetail.class.getClassLoader());
    }

    public static final Creator<ApiResponseMovie> CREATOR = new Creator<ApiResponseMovie>() {
        @Override
        public ApiResponseMovie createFromParcel(Parcel in) {
            return new ApiResponseMovie(in);
        }

        @Override
        public ApiResponseMovie[] newArray(int size) {
            return new ApiResponseMovie[size];
        }
    };

    public ApiResponseMovieDetail getMovieDetail() {
        return movieDetail;
    }

    public void setMovieDetail(ApiResponseMovieDetail movieDetail) {
        this.movieDetail = movieDetail;
    }

    public List<Movie> getResults() {
        return movies;
    }

    public void setResults(List<Movie> movies) {
        this.movies = movies;
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
        dest.writeParcelable(movieDetail, flags);
    }

    public static class Movie implements Parcelable {
        @Expose
        @ColumnInfo(name = "release_date")
        @SerializedName("release_date")
        private String releaseDate;
        @Expose
        @SerializedName("overview")
        private String overview;
        @Expose
        @ColumnInfo(name = "vote_average")
        @SerializedName("vote_average")
        private double voteAverage;
        @Expose
        @ColumnInfo(name = "title")
        @SerializedName("title")
        private String title;
        @Expose
        @ColumnInfo(name = "genre")
        @SerializedName("genre_ids")
        private List<Integer> genreIds;
        @Expose
        @SerializedName("original_title")
        private String originalTitle;
        @Expose
        @SerializedName("original_language")
        private String originalLanguage;
        @Expose
        @SerializedName("backdrop_path")
        private String backdropPath;
        @Expose
        @SerializedName("adult")
        private boolean adult;
        @Expose
        @ColumnInfo(name = "id")
        @SerializedName("id")
        private int id;
        @Expose
        @ColumnInfo(name = "poster_path")
        @SerializedName("poster_path")
        private String posterPath;
        @Expose
        @SerializedName("video")
        private boolean video;
        @Expose
        @SerializedName("vote_count")
        private int voteCount;
        @Expose
        @SerializedName("popularity")
        private double popularity;

        public Movie(Parcel in) {
            releaseDate = in.readString();
            overview = in.readString();
            voteAverage = in.readDouble();
            title = in.readString();
            originalTitle = in.readString();
            originalLanguage = in.readString();
            backdropPath = in.readString();
            adult = in.readByte() != 0;
            id = in.readInt();
            posterPath = in.readString();
            video = in.readByte() != 0;
            voteCount = in.readInt();
            popularity = in.readDouble();
        }

        public static final Creator<Movie> CREATOR = new Creator<Movie>() {
            @Override
            public Movie createFromParcel(Parcel in) {
                return new Movie(in);
            }

            @Override
            public Movie[] newArray(int size) {
                return new Movie[size];
            }
        };

        public String getReleaseDate() {
            return releaseDate;
        }

        public void setReleaseDate(String releaseDate) {
            this.releaseDate = releaseDate;
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

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public List<Integer> getGenreIds() {
            return genreIds;
        }

        public void setGenreIds(List<Integer> genreIds) {
            this.genreIds = genreIds;
        }

        public String getOriginalTitle() {
            return originalTitle;
        }

        public void setOriginalTitle(String originalTitle) {
            this.originalTitle = originalTitle;
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

        public boolean getAdult() {
            return adult;
        }

        public void setAdult(boolean adult) {
            this.adult = adult;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getPosterPath() {
            return posterPath;
        }

        public void setPosterPath(String posterPath) {
            this.posterPath = posterPath;
        }

        public boolean getVideo() {
            return video;
        }

        public void setVideo(boolean video) {
            this.video = video;
        }

        public int getVoteCount() {
            return voteCount;
        }

        public void setVoteCount(int voteCount) {
            this.voteCount = voteCount;
        }

        public double getPopularity() {
            return popularity;
        }

        public void setPopularity(double popularity) {
            this.popularity = popularity;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(releaseDate);
            dest.writeString(overview);
            dest.writeDouble(voteAverage);
            dest.writeString(title);
            dest.writeString(originalTitle);
            dest.writeString(originalLanguage);
            dest.writeString(backdropPath);
            dest.writeByte((byte) (adult ? 1 : 0));
            dest.writeInt(id);
            dest.writeString(posterPath);
            dest.writeByte((byte) (video ? 1 : 0));
            dest.writeInt(voteCount);
            dest.writeDouble(popularity);
        }
        public void Moive(Integer voteAverage, Integer id, String posterPath, String releaseDate, List<Integer> genreIds, String title){
            this.voteAverage = voteAverage;
            this.id = id;
            this.posterPath = posterPath;
            this.title = title;
            this.releaseDate = releaseDate;
            this.genreIds = genreIds;
        }
    }

}
