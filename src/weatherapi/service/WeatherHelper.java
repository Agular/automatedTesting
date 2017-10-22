package weatherapi.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import weatherapi.exceptions.IllegalArrayOrderException;
import weatherapi.exceptions.IllegalArraySizeException;
import weatherapi.weather.Coordinates;
import weatherapi.weather.FiveDayForecastMaxMinTemperatures;
import weatherapi.weather.OneDayMaxMinTemperatures;
import weatherapi.weather.Temperature;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;


public class WeatherHelper {

    public Temperature convertKelvinStringToCelsiusTemperature(String kelvinString) {
        double temperatureKelvin = Double.parseDouble(kelvinString);
        return new Temperature(temperatureKelvin - 273.15);
    }

    public void convertOneDayMaxMinTemperaturesToCelsius(OneDayMaxMinTemperatures kelvinTemperature) {
        kelvinTemperature.setMinTemperature(new Temperature(roundValue(kelvinTemperature.getMinTemperature().getValue() - 273.15)));
        kelvinTemperature.setMaxTemperature(new Temperature(roundValue(kelvinTemperature.getMaxTemperature().getValue() - 273.15)));
    }

    public Coordinates getCoordinatesFromNode(JsonNode coord) {
        double lon = coord.get("lon").asDouble();
        double lat = coord.get("lat").asDouble();
        return new Coordinates(lon, lat);
    }

    public FiveDayForecastMaxMinTemperatures getFiveDayMaxMinTemperaturesFromNode(JsonNode apiResponse) throws IllegalArrayOrderException, IllegalArraySizeException {

        List<OneDayMaxMinTemperatures> temperaturesList = new ArrayList<>();
        HashMap<String, OneDayMaxMinTemperatures> hashMapTemperatures = new HashMap<>();
        LocalDate dateToday = LocalDate.now();

        for (int daysToAdd = 1; daysToAdd <= 5; daysToAdd++) {
            OneDayMaxMinTemperatures oneDayMaxMinTemperatures = new OneDayMaxMinTemperatures();
            oneDayMaxMinTemperatures.setDate(LocalDate.now().plusDays(daysToAdd));
            oneDayMaxMinTemperatures.setMinTemperature(new Temperature(Double.MAX_VALUE));
            oneDayMaxMinTemperatures.setMaxTemperature(new Temperature(Double.MIN_VALUE));
            hashMapTemperatures.put(oneDayMaxMinTemperatures.getDate().toString(), oneDayMaxMinTemperatures);
        }

        ArrayNode listNode = (ArrayNode) apiResponse.get("list");
        Iterator<JsonNode> listIterator = listNode.elements();
        while (listIterator.hasNext()) {
            JsonNode threeHourNode = listIterator.next();
            String nodeDateString = threeHourNode.get("dt_txt").toString().split(" ")[0].replace("\"", "");
            if (nodeDateString.equals(dateToday.toString())) {
                continue;
            }
            OneDayMaxMinTemperatures oneDayMaxMinTemperatures = hashMapTemperatures.get(nodeDateString);
            double nodeTemperature = threeHourNode.get("main").get("temp").asDouble();
            if (oneDayMaxMinTemperatures.getMinTemperature().getValue() > nodeTemperature) {
                oneDayMaxMinTemperatures.setMinTemperature(new Temperature(nodeTemperature));
            }
            if (oneDayMaxMinTemperatures.getMaxTemperature().getValue() < nodeTemperature) {
                oneDayMaxMinTemperatures.setMaxTemperature(new Temperature(nodeTemperature));
            }
        }

        for (int daysToAdd = 1; daysToAdd <= 5; daysToAdd++) {
            OneDayMaxMinTemperatures oneDayTemperature = hashMapTemperatures.get(dateToday.plusDays(daysToAdd).toString());
            convertOneDayMaxMinTemperaturesToCelsius(oneDayTemperature);
            temperaturesList.add(oneDayTemperature);
        }
        return new FiveDayForecastMaxMinTemperatures(temperaturesList);
    }

    public double roundValue(double value){
        double temp = value * 100;
        temp = Math.round(temp);
        return temp / 100;
    }

}
