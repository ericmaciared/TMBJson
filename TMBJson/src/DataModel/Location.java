package DataModel;

import java.util.Arrays;

public class Location {
    private String name;
    private double[] coordinates;
    private String description;

    @Override
    public String toString() {
        return "Location{" +
                "name='" + name + '\'' +
                ", coordinates=" + Arrays.toString(coordinates) +
                ", description='" + description + '\'' +
                '}';
    }
}
