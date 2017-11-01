package weatherapi.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Scanner;

public class WeatherAPI {

    private final String BASE_URL = "http://api.openweathermap.org/data/2.5/";

    private enum typeOfQueries {
        weather, forecast
    }

    public JsonNode weatherQuery(int cityId, String apiKey) {
        return query("weather", cityId, apiKey);
    }

    public JsonNode forecastQuery(int cityId, String apiKey) {
        return query("forecast", cityId, apiKey);
    }

    private JsonNode query(String typeOfQuery, int cityId, String apiKey) {
        String queryUrl = BASE_URL +
                typeOfQuery + "?" +
                "id=" + String.valueOf(cityId) + "&" +
                "appid=" + apiKey;

        try {
            InputStream response = new URL(queryUrl).openStream();
            Scanner scanner = new Scanner(response);
            String responseBody = scanner.useDelimiter("\\A").next();
            ObjectMapper mapper = new ObjectMapper();
            System.out.println(mapper.readTree(responseBody));
            return  mapper.readTree(responseBody);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
