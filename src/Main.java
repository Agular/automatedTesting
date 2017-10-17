
import weatherapi.service.WeatherClient;
import weatherapi.weather.Coordinates;
import weatherapi.weather.Temperature;

public class Main {

    public static void main(String[] args){
        final int CITY_ID = 588409;
        final String API_KEY = "fde9c3d325025e6e3e3fc555c0f71b9c";

        WeatherClient weatherClient = new WeatherClient(API_KEY);
        Coordinates  coords = weatherClient.getCoordinatesByCityId(CITY_ID);
        System.out.println(coords);

        Temperature currentTemp  = weatherClient.getCurrentTemperatureByCityId(CITY_ID);
        System.out.println(currentTemp);
    }
}
