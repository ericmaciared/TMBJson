package DataModel;

import java.util.Arrays;

public class DataModel {
    private Location[] locations;

    @Override
    public String toString() {
        return "DataModel{" +
                "locations=" + Arrays.toString(locations) +
                '}';
    }

    public Location[] getLocations() {
        return locations;
    }
}
