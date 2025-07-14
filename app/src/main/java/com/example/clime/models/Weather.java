package com.example.clime.models;

import com.google.gson.annotations.SerializedName;

public class Weather {
    @SerializedName("description")
    public String description;
    @SerializedName("icon")
    public String icon;
}