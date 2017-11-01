package tests;

import org.junit.Assert;
import org.junit.Test;
import weatherapi.exceptions.IllegalArrayOrderException;
import weatherapi.exceptions.IllegalArraySizeException;
import weatherapi.weather.Coordinates;
import weatherapi.weather.FiveDayForecastMaxMinTemperatures;
import weatherapi.weather.OneDayMaxMinTemperatures;
import weatherapi.weather.Temperature;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ClassTests {

    private final int CITY_ID = 588409;
    private final String API_KEY = "fde9c3d325025e6e3e3fc555c0f71b9c";

    @Test(expected = IllegalArraySizeException.class)
    public void FiveDayTemperaturesConstructorArraySizeLessThanFiveNotAllowed() throws IllegalArrayOrderException, IllegalArraySizeException {
        ArrayList<OneDayMaxMinTemperatures> temperaturesArray = new ArrayList<>();
        LocalDate date = LocalDate.now();
        Temperature temperature = new Temperature(0.0);
        for (int i = 0; i < 4; i++) {
            temperaturesArray.add(new OneDayMaxMinTemperatures(date, temperature, temperature));
        }
        FiveDayForecastMaxMinTemperatures temperatures = new FiveDayForecastMaxMinTemperatures(temperaturesArray);
    }

    @Test(expected = IllegalArraySizeException.class)
    public void FiveDayTemperaturesConstructorArraySizeGreaterThanFiveNotAllowed() throws IllegalArrayOrderException, IllegalArraySizeException {
        ArrayList<OneDayMaxMinTemperatures> temperaturesArray = new ArrayList<>();
        LocalDate date = LocalDate.now();
        Temperature temperature = new Temperature(0.0);
        for (int i = 0; i < 6; i++) {
            temperaturesArray.add(new OneDayMaxMinTemperatures(date, temperature, temperature));
        }
        FiveDayForecastMaxMinTemperatures temperatures = new FiveDayForecastMaxMinTemperatures(temperaturesArray);
    }

    @Test(expected = IllegalArrayOrderException.class)
    public void FiveDayTemperaturesIsNotInAscendingOrder() throws IllegalArrayOrderException, IllegalArraySizeException {
        ArrayList<OneDayMaxMinTemperatures> temperaturesArray = new ArrayList<>();
        LocalDate date = LocalDate.now();
        Temperature temperature = new Temperature(0.0);
        for (int i = 4; i > 0; i--) {
            temperaturesArray.add(new OneDayMaxMinTemperatures(date.minusDays(i), temperature, temperature));
        }
        temperaturesArray.add(new OneDayMaxMinTemperatures(LocalDate.now().minusDays(2), temperature, temperature));
        FiveDayForecastMaxMinTemperatures temperatures = new FiveDayForecastMaxMinTemperatures(temperaturesArray);
    }

    @Test
    public void FiveDayTemperaturesInAscendingOrder() throws IllegalArrayOrderException, IllegalArraySizeException {
        ArrayList<OneDayMaxMinTemperatures> temperaturesArray = new ArrayList<>();
        LocalDate date = LocalDate.now();
        Temperature temperature = new Temperature(0.0);
        for (int i = 5; i > 0; i--) {
            temperaturesArray.add(new OneDayMaxMinTemperatures(date.minusDays(i), temperature, temperature));
        }
        FiveDayForecastMaxMinTemperatures temperatures = new FiveDayForecastMaxMinTemperatures(temperaturesArray);
    }

    @Test
    public void isCoordinatesEqualsCorrect(){
        Assert.assertEquals(new Coordinates(2.0,2.0), new Coordinates(2.0, 2.0));
    }

    @Test
    public void isCoordinatesHashCorrect(){
        Assert.assertEquals(-33553471, new Coordinates(1.0, 1.0).hashCode());
    }

    @Test
    public void isTemperatureEqualsCorrect(){
        Assert.assertEquals(new Temperature(1.0), new Temperature(1.0));
    }

    @Test
    public void isTemperatureHashCorrect(){
        Assert.assertEquals(1072693279,new Temperature(1.0).hashCode());
    }

    @Test
    public void isOneDayTemperaturesEqualsCorrect(){
        OneDayMaxMinTemperatures first = new OneDayMaxMinTemperatures(LocalDate.now(), new Temperature(1.0), new Temperature(1.0));
        OneDayMaxMinTemperatures second = new OneDayMaxMinTemperatures(LocalDate.now(), new Temperature(1.0), new Temperature(1.0));
        Assert.assertEquals(first, second);
    }

    @Test
    public void isOneDayTemperaturesHashCorrect(){
        OneDayMaxMinTemperatures temperatures = new OneDayMaxMinTemperatures(LocalDate.of(1996, 1, 12), new Temperature(1.0), new Temperature(1.0));

        Assert.assertEquals(-400034421, temperatures.hashCode());
    }

    @Test
    public void isFiveDayTemperaturesEqualsCorrect() throws IllegalArrayOrderException, IllegalArraySizeException {
        List<OneDayMaxMinTemperatures> temperaturesList1 = new ArrayList<>();
        OneDayMaxMinTemperatures temp1 = new OneDayMaxMinTemperatures(LocalDate.of(2017,10, 23), new Temperature(4.23), new Temperature(0.41));
        OneDayMaxMinTemperatures temp2 = new OneDayMaxMinTemperatures(LocalDate.of(2017,10, 24), new Temperature(4.65), new Temperature(-1.43));
        OneDayMaxMinTemperatures temp3 = new OneDayMaxMinTemperatures(LocalDate.of(2017,10, 25), new Temperature(1.06), new Temperature(-1.55));
        OneDayMaxMinTemperatures temp4 = new OneDayMaxMinTemperatures(LocalDate.of(2017,10, 26), new Temperature(4.19), new Temperature(0.54));
        OneDayMaxMinTemperatures temp5 = new OneDayMaxMinTemperatures(LocalDate.of(2017,10, 27), new Temperature(3.65), new Temperature(1.93));
        temperaturesList1.add(temp1);
        temperaturesList1.add(temp2);
        temperaturesList1.add(temp3);
        temperaturesList1.add(temp4);
        temperaturesList1.add(temp5);

        List<OneDayMaxMinTemperatures> temperaturesList2 = new ArrayList<>();
        OneDayMaxMinTemperatures temp21 = new OneDayMaxMinTemperatures(LocalDate.of(2017,10, 23), new Temperature(4.23), new Temperature(0.41));
        OneDayMaxMinTemperatures temp22 = new OneDayMaxMinTemperatures(LocalDate.of(2017,10, 24), new Temperature(4.65), new Temperature(-1.43));
        OneDayMaxMinTemperatures temp23 = new OneDayMaxMinTemperatures(LocalDate.of(2017,10, 25), new Temperature(1.06), new Temperature(-1.55));
        OneDayMaxMinTemperatures temp24 = new OneDayMaxMinTemperatures(LocalDate.of(2017,10, 26), new Temperature(4.19), new Temperature(0.54));
        OneDayMaxMinTemperatures temp25 = new OneDayMaxMinTemperatures(LocalDate.of(2017,10, 27), new Temperature(3.65), new Temperature(1.93));
        temperaturesList2.add(temp21);
        temperaturesList2.add(temp22);
        temperaturesList2.add(temp23);
        temperaturesList2.add(temp24);
        temperaturesList2.add(temp25);

        Assert.assertEquals(new FiveDayForecastMaxMinTemperatures(temperaturesList1), new FiveDayForecastMaxMinTemperatures(temperaturesList2));
    }

    @Test
    public void isFiveDayTemperaturesHashCorrect() throws IllegalArrayOrderException, IllegalArraySizeException {
        List<OneDayMaxMinTemperatures> temperaturesList1 = new ArrayList<>();
        OneDayMaxMinTemperatures temp1 = new OneDayMaxMinTemperatures(LocalDate.of(2017,10, 23), new Temperature(4.23), new Temperature(0.41));
        OneDayMaxMinTemperatures temp2 = new OneDayMaxMinTemperatures(LocalDate.of(2017,10, 24), new Temperature(4.65), new Temperature(-1.43));
        OneDayMaxMinTemperatures temp3 = new OneDayMaxMinTemperatures(LocalDate.of(2017,10, 25), new Temperature(1.06), new Temperature(-1.55));
        OneDayMaxMinTemperatures temp4 = new OneDayMaxMinTemperatures(LocalDate.of(2017,10, 26), new Temperature(4.19), new Temperature(0.54));
        OneDayMaxMinTemperatures temp5 = new OneDayMaxMinTemperatures(LocalDate.of(2017,10, 27), new Temperature(3.65), new Temperature(1.93));
        temperaturesList1.add(temp1);
        temperaturesList1.add(temp2);
        temperaturesList1.add(temp3);
        temperaturesList1.add(temp4);
        temperaturesList1.add(temp5);

        Assert.assertEquals(-148432624, new FiveDayForecastMaxMinTemperatures(temperaturesList1).hashCode());
    }

}
