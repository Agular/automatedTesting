package weatherapi.service;

import com.fasterxml.jackson.databind.JsonNode;
import weatherapi.exceptions.IllegalArrayOrderException;
import weatherapi.exceptions.IllegalArraySizeException;
import weatherapi.weather.Coordinates;
import weatherapi.weather.FiveDayForecastMaxMinTemperatures;
import weatherapi.weather.Temperature;

import java.io.*;

public class WeatherServiceDao {

    private WeatherAPI weatherAPI;
    private WeatherHelper weatherHelper;

    WeatherServiceDao() {
        weatherAPI = new WeatherAPI();
        weatherHelper = new WeatherHelper();
    }

    Temperature getCurrentTemperature(int cityId, String apiKey) {
        JsonNode apiResponse = weatherAPI.weatherQuery(cityId, apiKey);
        return weatherHelper.convertKelvinStringToCelsiusTemperature(apiResponse.get("main").get("temp").toString());
    }

    Coordinates getCoordinates(int cityId, String apiKey) {
        JsonNode apiResponse = weatherAPI.weatherQuery(cityId, apiKey);
        return weatherHelper.getCoordinatesFromNode(apiResponse.get("coord"));
    }

    FiveDayForecastMaxMinTemperatures getFiveDayForecastMaxMinTemperatures(int cityId, String apiKey) throws IllegalArrayOrderException, IllegalArraySizeException {
        JsonNode apiResponse = weatherAPI.forecastQuery(cityId, apiKey);
        return weatherHelper.getFiveDayMaxMinTemperaturesFromNode(apiResponse);
    }

    int getCityIdFromFile(String filename) throws FileNotFoundException {
        File file = new File(filename);
        BufferedReader reader = null;
        String cityId = null;
        try {
            reader = new BufferedReader(new FileReader(file));
            cityId = reader.readLine();
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException("File was not found. Check your path and file name!");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (cityId == null) {
            throw new IllegalArgumentException("The file is empty!");
        }
        if (!cityId.matches("^\\d+$")) {
            throw new IllegalArgumentException("String is not numeric!");
        } else {
            return Integer.parseInt(cityId);
        }
    }
}
