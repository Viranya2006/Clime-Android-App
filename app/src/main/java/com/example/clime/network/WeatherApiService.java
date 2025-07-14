package com.example.clime.network;

import com.example.clime.models.CurrentWeatherResponse;
import com.example.clime.models.ForecastResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherApiService {

    @GET("weather")
    Call<CurrentWeatherResponse> getCurrentWeather(
            @Query("q") String city,
            @Query("appid") String apiKey,
            @Query("units") String units
    );

    @GET("forecast")
    Call<ForecastResponse> getForecast(
            @Query("q") String city,
            @Query("appid") String apiKey,
            @Query("units") String units
    );
}
