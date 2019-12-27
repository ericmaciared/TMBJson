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

    public String getName() {
        return name;
    }

    public double[] getCoordinates() {
        return coordinates;
    }

    public String getDescription() {
        return description;
    }

    public Location(String name, double[] coordinates, String description) {
        this.name = name;
        this.coordinates = coordinates;
        this.description = description;
    }
}
