package com.example.clime.models;

import com.google.gson.annotations.SerializedName;

public class Main {
    @SerializedName("temp")
    public double temp;
    @SerializedName("temp_min")
    public double temp_min;
    @SerializedName("temp_max")
    public double temp_max;
}