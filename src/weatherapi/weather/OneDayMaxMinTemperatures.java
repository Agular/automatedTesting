package weatherapi.weather;

import java.time.LocalDate;

public class OneDayMaxMinTemperatures{
    private LocalDate date;
    private Temperature maxTemperature;
    private Temperature minTemperature;

    public OneDayMaxMinTemperatures(LocalDate date, Temperature maxTemperature, Temperature minTemperature){
        if(date == null || maxTemperature == null || minTemperature == null){
            throw new IllegalArgumentException();
        }
        this.date = date;
        this.maxTemperature = maxTemperature;
        this.minTemperature = minTemperature;
    }

    public Temperature getMaxTemperature() {
        return maxTemperature;
    }

    public LocalDate getDate() {
        return date;
    }

    public Temperature getMinTemperature() {
        return minTemperature;
    }
}