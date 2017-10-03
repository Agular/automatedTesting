package weatherapi.weather;

public class Coordinates {
    private Integer longitude;
    private Integer latitude;

    public Coordinates(Integer longitude, Integer latitude) {
        if (longitude == null || latitude == null) {
            throw new NullPointerException();
        }
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public Integer getlongitude() {
        return longitude;
    }

    public Integer getlatitude() {
        return latitude;
    }
}
