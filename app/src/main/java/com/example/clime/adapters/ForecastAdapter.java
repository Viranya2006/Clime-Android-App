package com.example.clime.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.clime.R;
import com.example.clime.models.ListItem;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class ForecastAdapter extends RecyclerView.Adapter<ForecastAdapter.ForecastViewHolder> {

    private final List<ListItem> forecastList;
    private final Context context;

    public ForecastAdapter(Context context, List<ListItem> forecastList) {
        this.context = context;
        this.forecastList = forecastList;
    }

    @NonNull
    @Override
    public ForecastViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_forecast, parent, false);
        return new ForecastViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ForecastViewHolder holder, int position) {
        ListItem item = forecastList.get(position);

        // Set Day
        holder.dayText.setText(formatDateToDay(item.dt_txt));

        // Set Temperature
        String temp = String.format(Locale.getDefault(), "%.0f° / %.0f°", item.main.temp_max, item.main.temp_min);
        holder.tempText.setText(temp);

        // Set Weather Icon using Glide
        String iconUrl = "https://openweathermap.org/img/wn/" + item.weather.get(0).icon + "@2x.png";
        Glide.with(context).load(iconUrl).into(holder.weatherIcon);
    }

    @Override
    public int getItemCount() {
        return forecastList.size();
    }

    private String formatDateToDay(String dateString) {
        try {
            SimpleDateFormat inFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
            Date date = inFormat.parse(dateString);
            SimpleDateFormat outFormat = new SimpleDateFormat("EEEE", Locale.getDefault());
            return outFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
            // Return a part of the original string as a fallback
            return dateString.split(" ")[0];
        }
    }

    public static class ForecastViewHolder extends RecyclerView.ViewHolder {
        TextView dayText, tempText;
        ImageView weatherIcon;

        public ForecastViewHolder(@NonNull View itemView) {
            super(itemView);
            dayText = itemView.findViewById(R.id.day_text);
            tempText = itemView.findViewById(R.id.temp_text);
            weatherIcon = itemView.findViewById(R.id.weather_icon);
        }
    }
}
