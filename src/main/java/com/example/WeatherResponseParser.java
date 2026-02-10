package com.example;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;

public class WeatherResponseParser {

    public void parseAndPrint(String city, String jsonResponse) {
        JSONObject jsonObject = new JSONObject(jsonResponse);

        // OpenWeather /forecast-та "cod" көбіне String "200" болып келеді
        String cod = jsonObject.optString("cod", "");
        if ("200".equals(cod)) {
            System.out.println("Weather forecast for " + city + ":");

            JSONArray forecasts = jsonObject.getJSONArray("list");
            for (int i = 0; i < forecasts.length(); i++) {
                JSONObject forecast = forecasts.getJSONObject(i);

                long timestamp = forecast.getLong("dt");
                String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(timestamp * 1000));

                double temperature = forecast.getJSONObject("main").getDouble("temp");
                String description = forecast.getJSONArray("weather")
                        .getJSONObject(0)
                        .getString("description");

                System.out.println(date + ": " + temperature + "°C, " + description);
            }
        } else {
            // Қате болса API error message шығару
            String message = jsonObject.optString("message", "Unknown error");
            System.out.println("Request contains an error: " + message);
        }
    }
}
