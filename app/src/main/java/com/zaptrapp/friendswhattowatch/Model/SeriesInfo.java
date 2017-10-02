package com.zaptrapp.friendswhattowatch.Model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Nishanth on 02-Oct-17.
 */

public class SeriesInfo {



    @SerializedName("backdrop_path")
    public String backdrop_path;
    @SerializedName("created_by")
    public List<Created_by> created_by;
    @SerializedName("first_air_date")
    public String first_air_date;
    @SerializedName("genres")
    public List<Genres> genres;
    @SerializedName("homepage")
    public String homepage;
    @SerializedName("id")
    public int id;
    @SerializedName("in_production")
    public boolean in_production;
    @SerializedName("last_air_date")
    public String last_air_date;
    @SerializedName("name")
    public String name;
    @SerializedName("networks")
    public List<Networks> networks;
    @SerializedName("number_of_episodes")
    public int number_of_episodes;
    @SerializedName("number_of_seasons")
    public int number_of_seasons;
    @SerializedName("original_language")
    public String original_language;
    @SerializedName("original_name")
    public String original_name;
    @SerializedName("overview")
    public String overview;
    @SerializedName("popularity")
    public double popularity;
    @SerializedName("poster_path")
    public String poster_path;
    @SerializedName("production_companies")
    public List<Production_companies> production_companies;
    @SerializedName("seasons")
    public List<Seasons> seasons;
    @SerializedName("status")
    public String status;
    @SerializedName("type")
    public String type;
    @SerializedName("vote_average")
    public double vote_average;
    @SerializedName("vote_count")
    public int vote_count;

    public static class Created_by {
        @SerializedName("id")
        public int id;
        @SerializedName("name")
        public String name;
        @SerializedName("profile_path")
        public String profile_path;
    }

    public static class Genres {
        @SerializedName("id")
        public int id;
        @SerializedName("name")
        public String name;
    }

    public static class Networks {
        @SerializedName("id")
        public int id;
        @SerializedName("name")
        public String name;
    }

    public static class Production_companies {
        @SerializedName("name")
        public String name;
        @SerializedName("id")
        public int id;
    }

    public static class Seasons implements Parcelable {
        @SerializedName("air_date")
        public String air_date;
        @SerializedName("episode_count")
        public int episode_count;
        @SerializedName("id")
        public int id;
        @SerializedName("poster_path")
        public String poster_path;
        @SerializedName("season_number")
        public int season_number;

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.air_date);
            dest.writeInt(this.episode_count);
            dest.writeInt(this.id);
            dest.writeString(this.poster_path);
            dest.writeInt(this.season_number);
        }

        public Seasons() {
        }

        protected Seasons(Parcel in) {
            this.air_date = in.readString();
            this.episode_count = in.readInt();
            this.id = in.readInt();
            this.poster_path = in.readString();
            this.season_number = in.readInt();
        }

        public static final Parcelable.Creator<Seasons> CREATOR = new Parcelable.Creator<Seasons>() {
            @Override
            public Seasons createFromParcel(Parcel source) {
                return new Seasons(source);
            }

            @Override
            public Seasons[] newArray(int size) {
                return new Seasons[size];
            }
        };
    }
}
