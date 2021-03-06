package com.example.movietrailer.model.movie;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ApiResponseMovieReview {
    @Expose
    @SerializedName("total_results")
    private int totalResults;
    @Expose
    @SerializedName("total_pages")
    private int totalPages;
    @Expose
    @SerializedName("results")
    private List<ReviewMovie> reviewMovies;
    @Expose
    @SerializedName("page")
    private int page;
    @Expose
    @SerializedName("id")
    private int id;

    public int getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public List<ReviewMovie> getResults() {
        return reviewMovies;
    }

    public void setResults(List<ReviewMovie> reviewMovies) {
        this.reviewMovies = reviewMovies;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public static class ReviewMovie {
        @Expose
        @SerializedName("url")
        private String url;
        @Expose
        @SerializedName("id")
        private String id;
        @Expose
        @SerializedName("content")
        private String content;
        @Expose
        @SerializedName("author")
        private String author;

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }
    }
}
