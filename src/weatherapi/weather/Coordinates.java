package weatherapi.weather;

public class Coordinates {
    private Double longitude;
    private Double latitude;

    public Coordinates(Double longitude, Double latitude) {
        if (longitude == null || latitude == null) {
            throw new IllegalArgumentException();
        }
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public Double getlongitude() {
        return longitude;
    }

    public Double getlatitude() {
        return latitude;
    }

    @Override
    public String toString(){
        return "lon: " + longitude + " lat: " + latitude;
    }
}
