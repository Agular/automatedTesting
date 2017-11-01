package weatherapi.weather;

import weatherapi.exceptions.IllegalArraySizeException;
import weatherapi.exceptions.IllegalArrayOrderException;

import java.util.List;

public class FiveDayForecastMaxMinTemperatures {

    private List<OneDayMaxMinTemperatures> fiveDayMaxMinTemperatures;

    public FiveDayForecastMaxMinTemperatures(List<OneDayMaxMinTemperatures> fiveDayMaxMinTemperatures)
            throws IllegalArraySizeException, IllegalArrayOrderException {

        if (fiveDayMaxMinTemperatures == null) {
            throw new IllegalArgumentException();
        } else if (arrayIsNotOrderedByDate(fiveDayMaxMinTemperatures)) {
            throw new IllegalArrayOrderException("Array objects must be ordered by date in ascending order");
        }
        this.fiveDayMaxMinTemperatures = fiveDayMaxMinTemperatures;
    }

    private boolean arrayIsNotOrderedByDate(List<OneDayMaxMinTemperatures> fiveDayMaxMinTemperatures) {

        for (int i = 0; i < fiveDayMaxMinTemperatures.size() - 1; i++) {
            if (fiveDayMaxMinTemperatures.get(i).getDate().plusDays(1).compareTo(
                    fiveDayMaxMinTemperatures.get(i + 1).getDate()) != 0) {
                return true;
            }
        }
        return false;
    }

    private List<OneDayMaxMinTemperatures> getFiveDayMaxMinTemperatures() {
        return fiveDayMaxMinTemperatures;
    }

    @Override
    public String toString() {
        if (fiveDayMaxMinTemperatures != null) {
            String string = "";
            for (int i = 0; i < fiveDayMaxMinTemperatures.size() ; i++) {
                string = string + fiveDayMaxMinTemperatures.get(i) + "\n";
            }
            return string;
        } else return "";
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other instanceof FiveDayForecastMaxMinTemperatures) {
            return this.fiveDayMaxMinTemperatures.equals(((FiveDayForecastMaxMinTemperatures)other).fiveDayMaxMinTemperatures);
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hashCode = 1;
        hashCode = 31 * hashCode + fiveDayMaxMinTemperatures.hashCode();
        return hashCode;
    }
}
