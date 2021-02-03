package com.example.movietrailer.model.show;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public abstract class ApiResponseShowVideo {
    @Expose
    @SerializedName("results")
    private List<Results> results;
    @Expose
    @SerializedName("id")
    private int id;

    public List<Results> getResults() {
        return results;
    }

    public void setResults(List<Results> results) {
        this.results = results;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public static class Results {
        @Expose
        @SerializedName("type")
        private String type;
        @Expose
        @SerializedName("size")
        private int size;
        @Expose
        @SerializedName("site")
        private String site;
        @Expose
        @SerializedName("name")
        private String name;
        @Expose
        @SerializedName("key")
        private String key;
        @Expose
        @SerializedName("iso_3166_1")
        private String iso31661;
        @Expose
        @SerializedName("iso_639_1")
        private String iso6391;
        @Expose
        @SerializedName("id")
        private String id;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }

        public String getSite() {
            return site;
        }

        public void setSite(String site) {
            this.site = site;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public String getIso31661() {
            return iso31661;
        }

        public void setIso31661(String iso31661) {
            this.iso31661 = iso31661;
        }

        public String getIso6391() {
            return iso6391;
        }

        public void setIso6391(String iso6391) {
            this.iso6391 = iso6391;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }
    }
}
