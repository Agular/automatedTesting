package tests;

import org.junit.Assert;
import org.junit.Test;
import weatherapi.service.WeatherClient;
import weatherapi.weather.Coordinates;

public class LogicTests {
    private final int CITY_ID = 588409;


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
        WeatherClient weatherClient = new WeatherClient();
        Coordinates expectedCoordinates = new Coordinates(248, 544);
        Coordinates actualCoordinates = weatherClient.getCoordinatesByCityId(CITY_ID);
        Assert.assertEquals(expectedCoordinates, actualCoordinates);
    }
}
