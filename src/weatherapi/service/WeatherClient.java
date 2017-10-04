package weatherapi.service;

import weatherapi.weather.Coordinates;
import weatherapi.weather.FiveDayForecastMaxMinTemperatures;
import weatherapi.weather.Temperature;

public class WeatherClient {

    private QueryMaster queryMaster = new QueryMaster();

    public Temperature getCurrentTemperatureByCityId(int cityId){
        //TODO
        return null;
    }

    public Coordinates getCoordinatesByCityId(int cityId){
        //TODO
        return null;
    }

    public FiveDayForecastMaxMinTemperatures getFiveDayForecastMaxMinTemperaturesByCityId(int cityId){
        //TODO
        return null;
    }
}
