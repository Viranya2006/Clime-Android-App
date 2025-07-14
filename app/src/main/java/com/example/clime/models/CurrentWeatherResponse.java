package com.example.clime.models;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class CurrentWeatherResponse {
    @SerializedName("weather")
    public List<Weather> weather;
    @SerializedName("main")
    public Main main;
    @SerializedName("wind")
    public Wind wind;
    @SerializedName("name")
    public String name;
}