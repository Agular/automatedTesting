package tests;

import com.fasterxml.jackson.databind.JsonNode;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import weatherapi.exceptions.IllegalArrayOrderException;
import weatherapi.exceptions.IllegalArraySizeException;
import weatherapi.service.WeatherAPI;
import weatherapi.service.WeatherClient;

import java.io.*;
import java.util.Scanner;

public class ApiTests {

    private final int CITY_ID = 588409;
    private final String API_KEY = "fde9c3d325025e6e3e3fc555c0f71b9c";
    private WeatherAPI weatherApi;
    private WeatherClient weatherClient;

    @Before
    public void setupBeforeEachTest() {

        weatherApi = new WeatherAPI();
        weatherClient = new WeatherClient(API_KEY);
    }

    @Test
    public void isInputReceiveSuccessful() {
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

    @Test(expected = FileNotFoundException.class)
    public void getCityIdFromFileThrowsFileNotFoundException() throws FileNotFoundException {
        weatherClient.getCoordinatesByCityIdFromTextFile("unfindable_textfile.txt");
    }

    @Test(expected = IllegalArgumentException.class)
    public void getCityIDFromFileWhenFileIsEmptyThrowsIllegalArgumentException() throws FileNotFoundException {
        weatherClient.getCoordinatesByCityIdFromTextFile("empty_file.txt");
    }

    @Test(expected = IllegalArgumentException.class)
    public void getCityIDFromFileStringIsNotNumericThrowsIllegalArgumentException() throws FileNotFoundException {
        weatherClient.getCoordinatesByCityIdFromTextFile("hasNotNumericString.txt");
    }

    @Test()
    public void getGeoLocationWithCityIdFromTextFileSuccessfulQuery() throws FileNotFoundException {
        weatherClient.getCoordinatesByCityIdFromTextFile("tallinn.txt");
    }

    @Test()
    public void getCoordinatesWithCityIdFromFileCorrectOutput() throws IOException {
        weatherClient.getCoordinatesByCityIdFromTextFile("tallinn.txt");
        BufferedReader br = new BufferedReader(new FileReader("output.txt"));
        String output = br.readLine();
        br.close();
        Assert.assertEquals("lon: 24.75 lat: 59.44", output);
    }

    @Test
    public void getTemperatureWithCityIdFromFileCorrectOutput() throws IOException {
        weatherClient.getCurrentTemperatureByCityIdFromTextFile("tallinn.txt");
        BufferedReader br = new BufferedReader(new FileReader("output.txt"));
        String output = br.readLine();
        br.close();
        Assert.assertTrue(output.matches("^Temperature: [0-9]+(\\.[0-9]+)$"));

    }

    @Test
    public void getMaxMinTemperaturesWithCityIdFromFileCorrectOutput() throws FileNotFoundException, IllegalArrayOrderException, IllegalArraySizeException {
        weatherClient.getFiveDayForecastMaxMinTemperaturesByCityIdFromTextFile("tallinn.txt");
        String output = new Scanner(new File("output.txt")).useDelimiter("\\Z").next();
        Assert.assertTrue(output.equals("" +
                "Date: 2017-11-20 Max Temperature: Temperature: 2.84 Min Temperature: Temperature: 0.67\n" +
                "Date: 2017-11-21 Max Temperature: Temperature: 2.83 Min Temperature: Temperature: -1.89\n" +
                "Date: 2017-11-22 Max Temperature: Temperature: 0.65 Min Temperature: Temperature: -1.63\n" +
                "Date: 2017-11-23 Max Temperature: Temperature: 4.74 Min Temperature: Temperature: -0.91\n" +
                "Date: 2017-11-24 Max Temperature: Temperature: 8.16 Min Temperature: Temperature: 5.41"));
    }
}
