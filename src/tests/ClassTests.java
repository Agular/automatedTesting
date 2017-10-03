package tests;

import org.junit.Test;
import weatherapi.exceptions.IllegalArrayOrderException;
import weatherapi.exceptions.IllegalArraySizeException;
import weatherapi.weather.Coordinates;
import weatherapi.weather.FiveDayForecastMaxMinTemperatures;
import weatherapi.weather.OneDayMaxMinTemperatures;
import weatherapi.weather.Temperature;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;

public class ClassTests {

    @Test(expected = NullPointerException.class)
    public void TemperatureConstructorNullNotAllowed(){
        Temperature temperature = new Temperature(null);
    }

    @Test(expected = NullPointerException.class)
    public void CoordinatesConstructorLongitudeNullNotAllowed(){
        Coordinates coordinates = new Coordinates(null, 1);
    }

    @Test(expected = NullPointerException.class)
    public void CoordinatesConstructorLatitudeNullNotAllowed(){
        Coordinates coordinates = new Coordinates(1, null);
    }

    @Test(expected = NullPointerException.class)
    public void OneDayTemperaturesConstructorDateNullNotAllowed(){
        Temperature maxTemperature = new Temperature(BigDecimal.ONE);
        Temperature minTemperature = new Temperature(BigDecimal.ZERO);
        OneDayMaxMinTemperatures temperatures = new OneDayMaxMinTemperatures(null, maxTemperature, minTemperature);
    }

    @Test(expected = NullPointerException.class)
    public void OneDayTemperaturesConstructorMaxTemperatureNullNotAllowed(){
        LocalDate date = LocalDate.now();
        Temperature minTemperature = new Temperature(BigDecimal.ZERO);
        OneDayMaxMinTemperatures temperatures = new OneDayMaxMinTemperatures(date, null, minTemperature);
    }

    @Test(expected = NullPointerException.class)
    public void OneDayTemperaturesConstructorMinTemperatureNullNotAllowed(){
        LocalDate date = LocalDate.now();
        Temperature maxTemperature = new Temperature(BigDecimal.ONE);
        OneDayMaxMinTemperatures temperatures = new OneDayMaxMinTemperatures(date, maxTemperature, null);
    }

    @Test(expected = NullPointerException.class)
    public void FiveDayTemperaturesConstructorNullNotAllowed() throws IllegalArrayOrderException, IllegalArraySizeException {
        FiveDayForecastMaxMinTemperatures temperatures = new FiveDayForecastMaxMinTemperatures(null);
    }

    @Test(expected = IllegalArraySizeException.class)
    public void FiveDayTemperaturesConstructorArraySizeLessThanFiveNotAllowed() throws IllegalArrayOrderException, IllegalArraySizeException {
        ArrayList<OneDayMaxMinTemperatures> temperaturesArray = new ArrayList<>();
        LocalDate date = LocalDate.now();
        Temperature temperature = new Temperature(BigDecimal.TEN);
        for (int i = 0; i < 4; i++){
            temperaturesArray.add(new OneDayMaxMinTemperatures(date, temperature, temperature));
        }
        FiveDayForecastMaxMinTemperatures temperatures = new FiveDayForecastMaxMinTemperatures(temperaturesArray);
    }
    @Test(expected = IllegalArraySizeException.class)
    public void FiveDayTemperaturesConstructorArraySizeGreaterThanFiveNotAllowed() throws IllegalArrayOrderException, IllegalArraySizeException {
        ArrayList<OneDayMaxMinTemperatures> temperaturesArray = new ArrayList<>();
        LocalDate date = LocalDate.now();
        Temperature temperature = new Temperature(BigDecimal.TEN);
        for (int i = 0; i < 6; i++){
            temperaturesArray.add(new OneDayMaxMinTemperatures(date, temperature, temperature));
        }
        FiveDayForecastMaxMinTemperatures temperatures = new FiveDayForecastMaxMinTemperatures(temperaturesArray);
    }

//    @Test(expected = IllegalArrayOrderException.class)
//    public void FiveDayTemperaturesInAscendingOrder() throws IllegalArrayOrderException, IllegalArraySizeException {
//        ArrayList<OneDayMaxMinTemperatures> temperaturesArray = new ArrayList<>();
//
//
//        FiveDayForecastMaxMinTemperatures temperatures = new FiveDayForecastMaxMinTemperatures(temperaturesArray);
//    }
}
