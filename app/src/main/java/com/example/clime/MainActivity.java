package com.example.clime;

import android.os.Bundle;
import android.util.Log;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.clime.adapters.ForecastAdapter;
import com.example.clime.models.CurrentWeatherResponse;
import com.example.clime.models.ForecastResponse;
import com.example.clime.models.ListItem;
import com.example.clime.network.RetrofitClient;
import com.example.clime.network.WeatherApiService;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    // IMPORTANT: Replace this with your own API key from OpenWeatherMap
    private static final String API_KEY = "d0dbf8698a88cf3d1c12fcef54f7d685";
    private static final String TAG = "MainActivity";

    private WeatherApiService weatherApiService;

    private TextView cityNameText, temperatureText, weatherDescriptionText;
    private EditText searchEditText;
    private ImageView searchButton;
    private RecyclerView forecastRecyclerView;
    private ForecastAdapter forecastAdapter;
    private List<ListItem> forecastList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize Views
        initViews();

        // Initialize Retrofit Service
        weatherApiService = RetrofitClient.getClient().create(WeatherApiService.class);

        // Set up search functionality
        setupSearch();

        // Fetch initial weather data for a default city (e.g., London)
        fetchWeatherData("Colombo");
    }

    private void initViews() {
        cityNameText = findViewById(R.id.city_name_text);
        temperatureText = findViewById(R.id.temperature_text);
        weatherDescriptionText = findViewById(R.id.weather_description_text);
        searchEditText = findViewById(R.id.search_edit_text);
        searchButton = findViewById(R.id.search_button);

        // Setup RecyclerView
        forecastRecyclerView = findViewById(R.id.forecast_recycler_view);
        forecastRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        forecastAdapter = new ForecastAdapter(this, forecastList);
        forecastRecyclerView.setAdapter(forecastAdapter);
    }

    private void setupSearch() {
        searchButton.setOnClickListener(v -> performSearch());

        searchEditText.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                performSearch();
                return true;
            }
            return false;
        });
    }

    private void performSearch() {
        String city = searchEditText.getText().toString().trim();
        if (!city.isEmpty()) {
            fetchWeatherData(city);
        } else {
            Toast.makeText(MainActivity.this, "Please enter a city name", Toast.LENGTH_SHORT).show();
        }
    }

    private void fetchWeatherData(String city) {
        if (API_KEY.equals("YOUR_API_KEY_HERE")) {
            Toast.makeText(this, "Please replace 'YOUR_API_KEY_HERE' in MainActivity.java", Toast.LENGTH_LONG).show();
            return;
        }

        // Fetch Current Weather
        weatherApiService.getCurrentWeather(city, API_KEY, "metric").enqueue(new Callback<CurrentWeatherResponse>() {
            @Override
            public void onResponse(Call<CurrentWeatherResponse> call, Response<CurrentWeatherResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    updateCurrentWeatherUI(response.body());
                } else {
                    handleApiError("current weather");
                }
            }

            @Override
            public void onFailure(Call<CurrentWeatherResponse> call, Throwable t) {
                handleApiFailure("current weather", t);
            }
        });

        // Fetch 5-day/3-hour Forecast
        weatherApiService.getForecast(city, API_KEY, "metric").enqueue(new Callback<ForecastResponse>() {
            @Override
            public void onResponse(Call<ForecastResponse> call, Response<ForecastResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    updateForecastUI(response.body());
                } else {
                    handleApiError("forecast");
                }
            }

            @Override
            public void onFailure(Call<ForecastResponse> call, Throwable t) {
                handleApiFailure("forecast", t);
            }
        });
    }

    private void updateCurrentWeatherUI(CurrentWeatherResponse data) {
        cityNameText.setText(data.name);
        temperatureText.setText(String.format(Locale.getDefault(), "%.0f°", data.main.temp));
        if (data.weather != null && !data.weather.isEmpty()) {
            String description = data.weather.get(0).description;
            // Capitalize the first letter
            weatherDescriptionText.setText(description.substring(0, 1).toUpperCase() + description.substring(1));
        }
    }

    private void updateForecastUI(ForecastResponse data) {
        // The API returns data every 3 hours. We need to filter it to get one entry per day.
        // We'll pick the forecast for midday (12:00:00) each day.
        // Replaced Stream with a traditional for-loop for compatibility with API level 23.
        List<ListItem> dailyForecasts = new ArrayList<>();
        if (data != null && data.list != null) {
            for (ListItem item : data.list) {
                if (item.dt_txt != null && item.dt_txt.contains("12:00:00")) {
                    dailyForecasts.add(item);
                }
            }
        }

        forecastList.clear();
        forecastList.addAll(dailyForecasts);
        forecastAdapter.notifyDataSetChanged();
    }

    private void handleApiError(String requestType) {
        Log.e(TAG, "API Error: Failed to fetch " + requestType);
        Toast.makeText(MainActivity.this, "Could not find weather data for the specified city.", Toast.LENGTH_SHORT).show();
    }

    private void handleApiFailure(String requestType, Throwable t) {
        Log.e(TAG, "API Failure: Failed to fetch " + requestType, t);
        Toast.makeText(MainActivity.this, "Network error. Please check your connection.", Toast.LENGTH_SHORT).show();
    }
}
