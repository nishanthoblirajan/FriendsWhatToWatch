package com.zaptrapp.friendswhattowatch.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Nishanth on 02-Oct-17.
 */

public class EpisodeInfo {

    @SerializedName("air_date")
    public String air_date;
    @SerializedName("crew")
    public List<Crew> crew;
    @SerializedName("episode_number")
    public int episode_number;
    @SerializedName("guest_stars")
    public List<Guest_stars> guest_stars;
    @SerializedName("name")
    public String name;
    @SerializedName("overview")
    public String overview;
    @SerializedName("id")
    public int id;
    @SerializedName("production_code")
    public String production_code;
    @SerializedName("season_number")
    public int season_number;
    @SerializedName("still_path")
    public String still_path;
    @SerializedName("vote_average")
    public double vote_average;
    @SerializedName("vote_count")
    public int vote_count;

    public static class Crew {
        @SerializedName("credit_id")
        public String credit_id;
        @SerializedName("department")
        public String department;
        @SerializedName("gender")
        public int gender;
        @SerializedName("id")
        public int id;
        @SerializedName("job")
        public String job;
        @SerializedName("name")
        public String name;
        @SerializedName("profile_path")
        public String profile_path;
    }

    public static class Guest_stars {
        @SerializedName("character")
        public String character;
        @SerializedName("credit_id")
        public String credit_id;
        @SerializedName("gender")
        public String gender;
        @SerializedName("id")
        public int id;
        @SerializedName("name")
        public String name;
        @SerializedName("order")
        public int order;
        @SerializedName("profile_path")
        public String profile_path;
    }
}
