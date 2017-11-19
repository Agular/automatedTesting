
import weatherapi.exceptions.IllegalArrayOrderException;
import weatherapi.exceptions.IllegalArraySizeException;
import weatherapi.service.WeatherClient;
import weatherapi.weather.Coordinates;
import weatherapi.weather.FiveDayForecastMaxMinTemperatures;
import weatherapi.weather.Temperature;

import java.io.FileNotFoundException;

public class Main {

    public static void main(String[] args) throws IllegalArrayOrderException, IllegalArraySizeException, FileNotFoundException {
        final int CITY_ID = 588409;
        final String API_KEY = "fde9c3d325025e6e3e3fc555c0f71b9c";

        WeatherClient weatherClient = new WeatherClient(API_KEY);
        Coordinates coords = weatherClient.getCoordinatesByCityId(CITY_ID);
        System.out.println(coords);

        Temperature currentTemp  = weatherClient.getCurrentTemperatureByCityId(CITY_ID);
        System.out.println(currentTemp);

        FiveDayForecastMaxMinTemperatures temperatures = weatherClient.getFiveDayForecastMaxMinTemperaturesByCityId(CITY_ID);
        System.out.println(temperatures);

        //weatherClient.getCoordinatesByCityIdFromTextFile("tallinn.txt");
        weatherClient.getCurrentTemperatureByCityIdFromTextFile("tallinn.txt");
        weatherClient.getFiveDayForecastMaxMinTemperaturesByCityIdFromTextFile("tallinn.txt");
    }
}
