package weatherapi.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import weatherapi.weather.Coordinates;
import weatherapi.weather.Temperature;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.net.URL;
import java.util.Scanner;

public class QueryMaster {

    private final String BASE_ADDRESS = "http://api.openweathermap.org/data/2.5/";
    private String constructedQuery;
    private String apiKey;
    private Integer cityId;
    private String typeOfQuery;
    private JsonNode apiResponse;

    public QueryMaster() {
        constructedQuery = "";
    }

    public void constructQuery() {
        if(parametersAreNotSet()){
            throw new IllegalStateException();
        }
        constructedQuery = BASE_ADDRESS +
                            typeOfQuery + "?" +
                            "id=" + String.valueOf(cityId) + "&" +
                            "appid=" + apiKey;
    }

    private boolean parametersAreNotSet(){
        return apiKey == null || cityId == null || typeOfQuery == null;
    }

    public void doQuery() {
        if(parametersAreNotSet() || queryIsUnconstructed()){
            throw new IllegalStateException();
        }
        try {
            InputStream response = new URL(constructedQuery).openStream();
            Scanner scanner = new Scanner(response);
            String responseBody = scanner.useDelimiter("\\A").next();
            ObjectMapper mapper = new ObjectMapper();
            apiResponse = mapper.readTree(responseBody);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //TODO
    }

    private boolean queryIsUnconstructed() {
        return constructedQuery.equals("");
    }

    public Coordinates getCoordinates() {
        if(apiResponse == null) {
            return null;
        }
        JsonNode lonLatCoords = apiResponse.get("coord");
        return new Coordinates(lonLatCoords.get("lon").asDouble(), lonLatCoords.get("lat").asDouble());
    }

    public Temperature getCurrentTemperature(){
        if(apiResponse == null) {
            return null;
        }
        JsonNode main = apiResponse.get("main");
        return new Temperature(new BigDecimal(main.get("temp").toString()));
    }

    public String getConstructedQuery() {
        return constructedQuery;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public String getTypeOfQuery() {
        return typeOfQuery;
    }

    public void setTypeOfQuery(String typeOfQuery) {
        this.typeOfQuery = typeOfQuery;
    }
}
