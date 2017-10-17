package weatherapi.service;

import weatherapi.weather.Coordinates;
import weatherapi.weather.FiveDayForecastMaxMinTemperatures;
import weatherapi.weather.Temperature;

public class WeatherClient {

    private QueryMaster queryMaster;
    private String apiKey;

    public WeatherClient(String apiKey){
        this.apiKey = apiKey;
        queryMaster = new QueryMaster();
        queryMaster.setApiKey(apiKey);
    }


    public Temperature getCurrentTemperatureByCityId(int cityId){
        queryMaster.setTypeOfQuery("weather");
        queryMaster.setCityId(cityId);
        queryMaster.constructQuery();
        queryMaster.doQuery();
        return queryMaster.getCurrentTemperature();
    }

    public Coordinates getCoordinatesByCityId(int cityId){
        //TODO
        queryMaster.setTypeOfQuery("weather");
        queryMaster.setCityId(cityId);
        queryMaster.constructQuery();
        queryMaster.doQuery();
        return queryMaster.getCoordinates();
    }

    public FiveDayForecastMaxMinTemperatures getFiveDayForecastMaxMinTemperaturesByCityId(int cityId){
        //TODO
        return null;
    }
}
