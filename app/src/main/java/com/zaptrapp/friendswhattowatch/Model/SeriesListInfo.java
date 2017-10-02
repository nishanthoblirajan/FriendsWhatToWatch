package com.zaptrapp.friendswhattowatch.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Nishanth on 12-Oct-17.
 */

public class SeriesListInfo {


    @SerializedName("page")
    public int page;
    @SerializedName("total_results")
    public int total_results;
    @SerializedName("total_pages")
    public int total_pages;
    @SerializedName("results")
    public List<SeriesInfo> results;
}
