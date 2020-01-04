package DataModel;

import java.util.Arrays;

public class GenericLocation{
    private String name;
    private double[] coordinates;
    private String description;
    private String architect;
    private Integer inauguration;
    private String[] characteristics;
    private Integer stars;

    @Override
    public String toString() {
        return "GenericLocation{" +
                "name='" + name + '\'' +
                ", coordinates=" + Arrays.toString(coordinates) +
                ", description='" + description + '\'' +
                ", architect='" + architect + '\'' +
                ", inauguration=" + inauguration +
                ", characteristics=" + Arrays.toString(characteristics) +
                ", stars=" + stars +
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

    public String getArchitect() {
        return architect;
    }

    public int getInauguration() {
        return inauguration;
    }

    public String[] getCharacteristics() {
        return characteristics;
    }

    public Integer getStars() {
        return stars;
    }
}
