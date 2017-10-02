package com.zaptrapp.friendswhattowatch.Model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nishanth on 02-Oct-17.
 */

public class SeasonInfo {


    @SerializedName("_id")
    public String _id;
    @SerializedName("air_date")
    public String air_date;
    @SerializedName("episodes")
    public List<Episodes> episodes;
    @SerializedName("name")
    public String name;
    @SerializedName("overview")
    public String overview;
    @SerializedName("id")
    public int id;
    @SerializedName("poster_path")
    public String poster_path;
    @SerializedName("season_number")
    public int season_number;

    public static class Crew implements Parcelable {

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

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.credit_id);
            dest.writeString(this.department);
            dest.writeInt(this.gender);
            dest.writeInt(this.id);
            dest.writeString(this.job);
            dest.writeString(this.name);
            dest.writeString(this.profile_path);
        }

        public Crew() {
        }

        protected Crew(Parcel in) {
            this.credit_id = in.readString();
            this.department = in.readString();
            this.gender = in.readInt();
            this.id = in.readInt();
            this.job = in.readString();
            this.name = in.readString();
            this.profile_path = in.readString();
        }

        public static final Parcelable.Creator<Crew> CREATOR = new Parcelable.Creator<Crew>() {
            @Override
            public Crew createFromParcel(Parcel source) {
                return new Crew(source);
            }

            @Override
            public Crew[] newArray(int size) {
                return new Crew[size];
            }
        };
    }

    public static class Guest_stars implements Parcelable {

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

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.character);
            dest.writeString(this.credit_id);
            dest.writeString(this.gender);
            dest.writeInt(this.id);
            dest.writeString(this.name);
            dest.writeInt(this.order);
            dest.writeString(this.profile_path);
        }

        public Guest_stars() {
        }

        protected Guest_stars(Parcel in) {
            this.character = in.readString();
            this.credit_id = in.readString();
            this.gender = in.readString();
            this.id = in.readInt();
            this.name = in.readString();
            this.order = in.readInt();
            this.profile_path = in.readString();
        }

        public static final Parcelable.Creator<Guest_stars> CREATOR = new Parcelable.Creator<Guest_stars>() {
            @Override
            public Guest_stars createFromParcel(Parcel source) {
                return new Guest_stars(source);
            }

            @Override
            public Guest_stars[] newArray(int size) {
                return new Guest_stars[size];
            }
        };
    }

    public static class Episodes implements Parcelable {

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

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.air_date);
            dest.writeList(this.crew);
            dest.writeInt(this.episode_number);
            dest.writeList(this.guest_stars);
            dest.writeString(this.name);
            dest.writeString(this.overview);
            dest.writeInt(this.id);
            dest.writeString(this.production_code);
            dest.writeInt(this.season_number);
            dest.writeString(this.still_path);
            dest.writeDouble(this.vote_average);
            dest.writeInt(this.vote_count);
        }

        public Episodes() {
        }

        protected Episodes(Parcel in) {
            this.air_date = in.readString();
            this.crew = new ArrayList<Crew>();
            in.readList(this.crew, Crew.class.getClassLoader());
            this.episode_number = in.readInt();
            this.guest_stars = new ArrayList<Guest_stars>();
            in.readList(this.guest_stars, Guest_stars.class.getClassLoader());
            this.name = in.readString();
            this.overview = in.readString();
            this.id = in.readInt();
            this.production_code = in.readString();
            this.season_number = in.readInt();
            this.still_path = in.readString();
            this.vote_average = in.readDouble();
            this.vote_count = in.readInt();
        }

        public static final Parcelable.Creator<Episodes> CREATOR = new Parcelable.Creator<Episodes>() {
            @Override
            public Episodes createFromParcel(Parcel source) {
                return new Episodes(source);
            }

            @Override
            public Episodes[] newArray(int size) {
                return new Episodes[size];
            }
        };
    }
}
