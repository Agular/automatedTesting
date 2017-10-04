package tests;

import org.junit.Test;
import weatherapi.exceptions.IllegalArrayOrderException;
import weatherapi.exceptions.IllegalArraySizeException;
import weatherapi.weather.FiveDayForecastMaxMinTemperatures;
import weatherapi.weather.OneDayMaxMinTemperatures;
import weatherapi.weather.Temperature;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;

public class ClassTests {

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

    @Test(expected = IllegalArgumentException.class)
    public void queryMasterConstructQueryCityIdMissingFromMap() {

    }

    @Test(expected = IllegalArgumentException.class)
    public void queryMasterConstructQueryAppIdMissingFromMap() {

    }

    @Test(expected = IllegalArgumentException.class)
    public void queryMasterConstructQueryBaseQueryMissingFromMap() {

    }
}
