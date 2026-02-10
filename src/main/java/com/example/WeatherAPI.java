package com.example;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class WeatherAPI {
    private static final String API_KEY = "d79fd37097aae18d32f7e6504c631250";
   
    private static final String BASE_URL = "https://api.openweathermap.org/data/2.5/forecast";

    public String getForecast(String city) throws IOException {
        String encodedCity = URLEncoder.encode(city, StandardCharsets.UTF_8);
        String urlString = BASE_URL + "?q=" + encodedCity + "&appid=" + API_KEY + "&units=metric";

        URL url = new URL(urlString);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setConnectTimeout(10_000);
        connection.setReadTimeout(10_000);

        int status = connection.getResponseCode();
        BufferedReader reader = new BufferedReader(new InputStreamReader(
                status >= 200 && status < 300 ? connection.getInputStream() : connection.getErrorStream()
        ));

        StringBuilder response = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            response.append(line);
        }
        reader.close();
        connection.disconnect();

        return response.toString();
    }
}
