package tests;

import org.junit.Assert;
import org.junit.Test;
import weatherapi.exceptions.IllegalArrayOrderException;
import weatherapi.exceptions.IllegalArraySizeException;
import weatherapi.service.QueryMaster;
import weatherapi.weather.FiveDayForecastMaxMinTemperatures;
import weatherapi.weather.OneDayMaxMinTemperatures;
import weatherapi.weather.Temperature;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;

public class ClassTests {

    private final int CITY_ID = 588409;
    private final String API_KEY = "fde9c3d325025e6e3e3fc555c0f71b9c";

    @Test(expected = IllegalArraySizeException.class)
    public void FiveDayTemperaturesConstructorArraySizeLessThanFiveNotAllowed() throws IllegalArrayOrderException, IllegalArraySizeException {
        ArrayList<OneDayMaxMinTemperatures> temperaturesArray = new ArrayList<>();
        LocalDate date = LocalDate.now();
        Temperature temperature = new Temperature(BigDecimal.TEN);
        for (int i = 0; i < 4; i++) {
            temperaturesArray.add(new OneDayMaxMinTemperatures(date, temperature, temperature));
        }
        FiveDayForecastMaxMinTemperatures temperatures = new FiveDayForecastMaxMinTemperatures(temperaturesArray);
    }

    @Test(expected = IllegalArraySizeException.class)
    public void FiveDayTemperaturesConstructorArraySizeGreaterThanFiveNotAllowed() throws IllegalArrayOrderException, IllegalArraySizeException {
        ArrayList<OneDayMaxMinTemperatures> temperaturesArray = new ArrayList<>();
        LocalDate date = LocalDate.now();
        Temperature temperature = new Temperature(BigDecimal.TEN);
        for (int i = 0; i < 6; i++) {
            temperaturesArray.add(new OneDayMaxMinTemperatures(date, temperature, temperature));
        }
        FiveDayForecastMaxMinTemperatures temperatures = new FiveDayForecastMaxMinTemperatures(temperaturesArray);
    }

    @Test(expected = IllegalArrayOrderException.class)
    public void FiveDayTemperaturesIsNotInAscendingOrder() throws IllegalArrayOrderException, IllegalArraySizeException {
        ArrayList<OneDayMaxMinTemperatures> temperaturesArray = new ArrayList<>();
        LocalDate date = LocalDate.now();
        Temperature temperature = new Temperature(BigDecimal.TEN);
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
        Temperature temperature = new Temperature(BigDecimal.TEN);
        for (int i = 5; i > 0; i--) {
            temperaturesArray.add(new OneDayMaxMinTemperatures(date.minusDays(i), temperature, temperature));
        }
        FiveDayForecastMaxMinTemperatures temperatures = new FiveDayForecastMaxMinTemperatures(temperaturesArray);
    }

    @Test
    public void queryMasterGetConstructedQueryEmpty(){
        QueryMaster queryMaster = new QueryMaster();
        Assert.assertEquals("", queryMaster.getConstructedQuery());
    }

    @Test(expected = IllegalStateException.class)
    public void queryMasterConstructQueryCityIdUnset() {
        QueryMaster queryMaster = new QueryMaster();
        queryMaster.setApiKey(API_KEY);
        queryMaster.setTypeOfQuery("weather");
        queryMaster.constructQuery();
    }

    @Test(expected = IllegalStateException.class)
    public void queryMasterConstructQueryApiKeyUnset() {
        QueryMaster queryMaster = new QueryMaster();
        queryMaster.setCityId(CITY_ID);
        queryMaster.setTypeOfQuery("weather");
        queryMaster.constructQuery();
    }

    @Test(expected = IllegalStateException.class)
    public void queryMasterConstructQueryTypeOfQueryUnset() {
        QueryMaster queryMaster = new QueryMaster();
        queryMaster.setApiKey(API_KEY);
        queryMaster.setCityId(CITY_ID);
        queryMaster.constructQuery();
    }

    @Test
    public void queryMasterConstructQuerySuccessfulConstruction(){
        QueryMaster queryMaster = new QueryMaster();
        queryMaster.setApiKey(API_KEY);
        queryMaster.setTypeOfQuery("weather");
        queryMaster.setCityId(CITY_ID);
        queryMaster.constructQuery();
        String expected = "http://api.openweathermap.org/data/2.5/weather?id=112312&appid=fde9c3d325025e6e3e3fc555c0f71b9c";
        Assert.assertEquals(expected, queryMaster.getConstructedQuery());
    }

    @Test(expected = IllegalStateException.class)
    public void queryMasterDoQueryWithUnconstructedQuery(){
        QueryMaster queryMaster = new QueryMaster();
        queryMaster.doQuery();
    }

}
