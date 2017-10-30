package weatherapi.service;

import weatherapi.exceptions.IllegalArrayOrderException;
import weatherapi.exceptions.IllegalArraySizeException;
import weatherapi.weather.Coordinates;
import weatherapi.weather.FiveDayForecastMaxMinTemperatures;
import weatherapi.weather.Temperature;

import java.io.FileNotFoundException;

public class WeatherClient {

    private String apiKey;
    private WeatherServiceDao weatherServiceDao;

    public WeatherClient(String apiKey) {
        this.apiKey = apiKey;
        weatherServiceDao = new WeatherServiceDao();
    }

    public Temperature getCurrentTemperatureByCityId(int cityId) {
        return weatherServiceDao.getCurrentTemperature(cityId, apiKey);
    }

    public Coordinates getCoordinatesByCityId(int cityId) {
        return weatherServiceDao.getCoordinates(cityId, apiKey);
    }

    public FiveDayForecastMaxMinTemperatures getFiveDayForecastMaxMinTemperaturesByCityId(int cityId) throws IllegalArrayOrderException, IllegalArraySizeException {
        return weatherServiceDao.getFiveDayForecastMaxMinTemperatures(cityId, apiKey);
    }

    public Temperature getCurrentTemperatureByCityIdFromTextFile(String filename) throws FileNotFoundException {
        int cityId = weatherServiceDao.getCityIdFromFile(filename);
        return weatherServiceDao.getCurrentTemperature(cityId, apiKey);
    }

    public Coordinates getCoordinatesByCityIdFromTextFile(String filename) throws FileNotFoundException {
        int cityId = weatherServiceDao.getCityIdFromFile(filename);
        return weatherServiceDao.getCoordinates(cityId, apiKey);
    }

    public FiveDayForecastMaxMinTemperatures getFiveDayForecastMaxMinTemperaturesByCityIdFromTextFile(String filename) throws IllegalArrayOrderException, IllegalArraySizeException, FileNotFoundException {
        int cityId = weatherServiceDao.getCityIdFromFile(filename);
        return weatherServiceDao.getFiveDayForecastMaxMinTemperatures(cityId, apiKey);
    }

}
