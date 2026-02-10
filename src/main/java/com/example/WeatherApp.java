package com.example;

import java.io.IOException;
import java.util.Scanner;

public class WeatherApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("What city would you like to get the weather for? ");
        String city = scanner.nextLine();

        WeatherAPI api = new WeatherAPI();

        try {
            String forecastString = api.getForecast(city);

            WeatherResponseParser parser = new WeatherResponseParser();
            parser.parseAndPrint(city, forecastString);

        } catch (IOException e) {
            System.out.println("Network error: " + e.getMessage());
        }

        scanner.close();
    }
}

