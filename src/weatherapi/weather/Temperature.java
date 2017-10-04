package weatherapi.weather;

import java.math.BigDecimal;

public class Temperature {

    private BigDecimal temperature;

    public Temperature(BigDecimal temperature){
        if(temperature == null){
            throw new IllegalArgumentException();
        }
        this.temperature = temperature;
    }

    public BigDecimal getTemperature() {
        return temperature;
    }
}
