package tests;

import org.junit.Assert;
import org.junit.Test;
import weatherapi.service.WeatherService;
import weatherapi.weather.Coordinates;

public class LogicTests {

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
        WeatherService weatherService = new WeatherService();
        int cityId = 588409;
        Coordinates expectedCoordinates = new Coordinates(248, 544);
        Coordinates actualCoordinates = weatherService.getCoordinatesByCityId(588409);
        Assert.assertEquals(expectedCoordinates, actualCoordinates);
    }
}
