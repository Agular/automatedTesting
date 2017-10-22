package tests;

import com.fasterxml.jackson.databind.JsonNode;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import weatherapi.service.WeatherAPI;

public class ApiTests {

    private final int CITY_ID = 588409;
    private final String API_KEY = "fde9c3d325025e6e3e3fc555c0f71b9c";
    private WeatherAPI weatherApi;

    @Before
    public void setupBeforeEachTest() {
        weatherApi = new WeatherAPI();
    }

    @Test
    public void isInputReceiveSuccessful(){
        Assert.assertNotEquals(null, weatherApi.forecastQuery(CITY_ID, API_KEY));
    }

    @Test
    public void isCurrentTempDouble() {
        JsonNode node = weatherApi.weatherQuery(CITY_ID, API_KEY);
        Assert.assertTrue(node.get("main").get("temp").isDouble());
    }

    @Test
    public void isGeoLocationLongitudeDouble() {
        JsonNode node = weatherApi.weatherQuery(CITY_ID, API_KEY);
        Assert.assertTrue(node.get("coord").get("lon").isDouble());
    }

    @Test
    public void isGeoLocationLatitudeDouble() {
        JsonNode node = weatherApi.weatherQuery(CITY_ID, API_KEY);
        Assert.assertTrue(node.get("coord").get("lat").isDouble());
    }

    @Test
    public void is5DayForecastArrayLengthCorrect() {
        JsonNode node = weatherApi.forecastQuery(CITY_ID, API_KEY);
        Assert.assertEquals(40, node.get("list").size());
    }
}
