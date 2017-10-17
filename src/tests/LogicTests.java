package tests;

import org.junit.Assert;
import org.junit.Test;
import weatherapi.service.WeatherClient;
import weatherapi.weather.Coordinates;

public class LogicTests {
    private final int CITY_ID = 588409;
    private final String API_KEY = "fde9c3d325025e6e3e3fc555c0f71b9c";

    @Test
    public void is3DayForecastMaxTemperatureCorrect() {

    }

    @Test
    public void is3DayForecastMinTemperatureCorrect(){

    }

    @Test
    public void is1DayForecastMaxTemperatureCorrect(){

    }

    @Test
    public void is1DayForecastMinTemperatureCorrect(){

    }

    @Test
    public void isCoordinatesCorrect(){
        WeatherClient weatherClient = new WeatherClient(API_KEY);
        Coordinates expectedCoordinates = new Coordinates(24.88, 54.4);
        Coordinates actualCoordinates = weatherClient.getCoordinatesByCityId(CITY_ID);
        Assert.assertEquals(expectedCoordinates, actualCoordinates);
    }
}
