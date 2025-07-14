package com.example.clime.models;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class ForecastResponse {
    @SerializedName("list")
    public List<ListItem> list;
}