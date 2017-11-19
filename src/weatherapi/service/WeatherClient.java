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

    public void getCurrentTemperatureByCityIdFromTextFile(String filename) throws FileNotFoundException {
        int cityId = weatherServiceDao.getCityIdFromFile(filename);
        weatherServiceDao.getCurrentTemperatureToFile(cityId, apiKey);
    }

    public void getCoordinatesByCityIdFromTextFile(String filename) throws FileNotFoundException {
        int cityId = weatherServiceDao.getCityIdFromFile(filename);
        weatherServiceDao.getCoordinatesToFile(cityId, apiKey);
    }

    public void getFiveDayForecastMaxMinTemperaturesByCityIdFromTextFile(String filename) throws IllegalArrayOrderException, IllegalArraySizeException, FileNotFoundException {
        int cityId = weatherServiceDao.getCityIdFromFile(filename);
        weatherServiceDao.getFiveDayForecastMaxMinTemperaturesToFile(cityId, apiKey);
    }

}
