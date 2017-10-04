package weatherapi.weather;

import weatherapi.exceptions.IllegalArraySizeException;
import weatherapi.exceptions.IllegalArrayOrderException;

import java.util.ArrayList;

public class FiveDayForecastMaxMinTemperatures {

    private ArrayList<OneDayMaxMinTemperatures> fiveDayMaxMinTemperatures;

    public FiveDayForecastMaxMinTemperatures(ArrayList<OneDayMaxMinTemperatures> fiveDayMaxMinTemperatures)
            throws IllegalArraySizeException, IllegalArrayOrderException {

        if (fiveDayMaxMinTemperatures == null) {
            throw new NullPointerException();
        } else if (arraySizeIsNotFive(fiveDayMaxMinTemperatures)) {
            throw new IllegalArraySizeException("Array must have exactly 5 day temperatures!");
        } else if (arrayIsNotOrderedByDate(fiveDayMaxMinTemperatures)) {
            throw new IllegalArrayOrderException("Array objects must be ordered by date in ascending order");
        }
        this.fiveDayMaxMinTemperatures = fiveDayMaxMinTemperatures;
    }

    private boolean arraySizeIsNotFive(ArrayList<OneDayMaxMinTemperatures> fiveDayMaxMinTemperatures) {

        return fiveDayMaxMinTemperatures.size() != 5;
    }

    private boolean arrayIsNotOrderedByDate(ArrayList<OneDayMaxMinTemperatures> fiveDayMaxMinTemperatures) {

        for (int i = 0; i < fiveDayMaxMinTemperatures.size() - 1; i++) {
            if (fiveDayMaxMinTemperatures.get(i).getDate().plusDays(1).compareTo(
                    fiveDayMaxMinTemperatures.get(i + 1).getDate()) != 0) {
                return true;
            }
        }
        return false;
    }

    private ArrayList<OneDayMaxMinTemperatures> getFiveDayMaxMinTemperatures() {
        return fiveDayMaxMinTemperatures;
    }
}
