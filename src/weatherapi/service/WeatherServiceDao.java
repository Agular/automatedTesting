package weatherapi.service;

import com.fasterxml.jackson.databind.JsonNode;
import weatherapi.exceptions.IllegalArrayOrderException;
import weatherapi.exceptions.IllegalArraySizeException;
import weatherapi.weather.Coordinates;
import weatherapi.weather.FiveDayForecastMaxMinTemperatures;
import weatherapi.weather.Temperature;

public class WeatherServiceDao {

    private WeatherAPI weatherAPI;
    private WeatherHelper weatherHelper;

    WeatherServiceDao(){
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
}
