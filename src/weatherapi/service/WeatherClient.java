package weatherapi.service;

import weatherapi.exceptions.IllegalArrayOrderException;
import weatherapi.exceptions.IllegalArraySizeException;
import weatherapi.weather.Coordinates;
import weatherapi.weather.FiveDayForecastMaxMinTemperatures;
import weatherapi.weather.Temperature;

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


}
