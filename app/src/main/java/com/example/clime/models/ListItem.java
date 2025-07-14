package com.example.clime.models;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class ListItem {
    @SerializedName("dt_txt")
    public String dt_txt;
    @SerializedName("main")
    public Main main;
    @SerializedName("weather")
    public List<Weather> weather;
}