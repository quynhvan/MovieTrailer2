package com.example.movietrailer.model.movie;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ApiResponseMovieCast {

    @Expose
    @SerializedName("crew")
    private List<Crew> crew;
    @Expose
    @SerializedName("cast")
    private List<Cast> cast;
    @Expose
    @SerializedName("id")
    private int id;

    public List<Crew> getCrew() {
        return crew;
    }

    public void setCrew(List<Crew> crew) {
        this.crew = crew;
    }

    public List<Cast> getCast() {
        return cast;
    }

    public void setCast(List<Cast> cast) {
        this.cast = cast;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public static class Crew {
        @Expose
        @SerializedName("profile_path")
        private String profilePath;
        @Expose
        @SerializedName("name")
        private String name;
        @Expose
        @SerializedName("job")
        private String job;
        @Expose
        @SerializedName("id")
        private int id;
        @Expose
        @SerializedName("gender")
        private int gender;
        @Expose
        @SerializedName("department")
        private String department;
        @Expose
        @SerializedName("credit_id")
        private String creditId;

        public String getProfilePath() {
            return profilePath;
        }

        public void setProfilePath(String profilePath) {
            this.profilePath = profilePath;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getJob() {
            return job;
        }

        public void setJob(String job) {
            this.job = job;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getGender() {
            return gender;
        }

        public void setGender(int gender) {
            this.gender = gender;
        }

        public String getDepartment() {
            return department;
        }

        public void setDepartment(String department) {
            this.department = department;
        }

        public String getCreditId() {
            return creditId;
        }

        public void setCreditId(String creditId) {
            this.creditId = creditId;
        }
    }

    public static class Cast {
        @Expose
        @SerializedName("profile_path")
        private String profilePath;
        @Expose
        @SerializedName("order")
        private int order;
        @Expose
        @SerializedName("name")
        private String name;
        @Expose
        @SerializedName("id")
        private int id;
        @Expose
        @SerializedName("gender")
        private int gender;
        @Expose
        @SerializedName("credit_id")
        private String creditId;
        @Expose
        @SerializedName("character")
        private String character;
        @Expose
        @SerializedName("cast_id")
        private int castId;

        public String getProfilePath() {
            return profilePath;
        }

        public void setProfilePath(String profilePath) {
            this.profilePath = profilePath;
        }

        public int getOrder() {
            return order;
        }

        public void setOrder(int order) {
            this.order = order;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getGender() {
            return gender;
        }

        public void setGender(int gender) {
            this.gender = gender;
        }

        public String getCreditId() {
            return creditId;
        }

        public void setCreditId(String creditId) {
            this.creditId = creditId;
        }

        public String getCharacter() {
            return character;
        }

        public void setCharacter(String character) {
            this.character = character;
        }

        public int getCastId() {
            return castId;
        }

        public void setCastId(int castId) {
            this.castId = castId;
        }
    }
}
