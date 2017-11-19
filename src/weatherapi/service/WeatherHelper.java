package weatherapi.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import weatherapi.exceptions.IllegalArrayOrderException;
import weatherapi.exceptions.IllegalArraySizeException;
import weatherapi.weather.Coordinates;
import weatherapi.weather.FiveDayForecastMaxMinTemperatures;
import weatherapi.weather.OneDayMaxMinTemperatures;
import weatherapi.weather.Temperature;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
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
        Iterator<JsonNode> listIterator = apiResponse.get("list").elements();
        OneDayMaxMinTemperatures oneDayMaxMinTemperatures = null;
        while (listIterator.hasNext()) {
            JsonNode threeHourNode = listIterator.next();
            String nodeDateString = getDateStringFromNode(threeHourNode);
            if (oneDayMaxMinTemperatures == null) {
                oneDayMaxMinTemperatures = new OneDayMaxMinTemperatures(LocalDate.parse(nodeDateString),
                        new Temperature(Double.MIN_VALUE), new Temperature(Double.MAX_VALUE));
            } else if (!oneDayMaxMinTemperatures.getDate().equals(LocalDate.parse(nodeDateString))) {
                temperaturesList.add(oneDayMaxMinTemperatures);
                oneDayMaxMinTemperatures = new OneDayMaxMinTemperatures(LocalDate.parse(nodeDateString),
                        new Temperature(Double.MIN_VALUE), new Temperature(Double.MAX_VALUE));
            }
            double nodeTemperature = threeHourNode.get("main").get("temp").asDouble();
            if (oneDayMaxMinTemperatures.getMinTemperature().getValue() > nodeTemperature) {
                oneDayMaxMinTemperatures.setMinTemperature(new Temperature(nodeTemperature));
            }
            if (oneDayMaxMinTemperatures.getMaxTemperature().getValue() < nodeTemperature) {
                oneDayMaxMinTemperatures.setMaxTemperature(new Temperature(nodeTemperature));
            }
        }
        temperaturesList.add(oneDayMaxMinTemperatures);
        convertKelvinTemperaturesListToCelsiusTemperaturesList(temperaturesList);
        return new FiveDayForecastMaxMinTemperatures(temperaturesList);
    }

    private String getDateStringFromNode(JsonNode node) {
        return node.get("dt_txt").toString().split(" ")[0].replace("\"", "");
    }

    private void convertKelvinTemperaturesListToCelsiusTemperaturesList(List<OneDayMaxMinTemperatures> temperaturesList) {
        for (int i = 0; i < temperaturesList.size(); i++) {
            convertOneDayMaxMinTemperaturesToCelsius(temperaturesList.get(i));
        }
    }

    public double roundValue(double value) {
        double temp = value * 100;
        temp = Math.round(temp);
        return temp / 100;
    }

     void writeResultToOutputFile(String result) throws FileNotFoundException {
        PrintWriter writer = new PrintWriter("output.txt");
        writer.print(result);
        writer.close();
    }
}
