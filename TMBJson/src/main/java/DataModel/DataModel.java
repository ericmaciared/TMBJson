package DataModel;

import java.util.ArrayList;
import java.util.Arrays;

public class DataModel {
    private ArrayList<Location> importedLocations = new ArrayList<Location>();
    private GenericLocation[] locations;

    @Override
    public String toString() {
        return "DataModel{" +
                "locations=" + Arrays.toString(locations) +
                '}';
    }

    public ArrayList<Location> getLocations() {
        return importedLocations;
    }

    /**
     * This function gets the generic location from the JSON file and drives them to the general locations
     * ArrayList by using default constructors for each type.
     */
    public void transformLocations() {
        for (GenericLocation g: locations){
            if (g.getStars() != null){ //Location is hotel
                importedLocations.add(new Hotel(g.getName(), g.getCoordinates(), g.getDescription(), g.getStars()));
            }
            else if (g.getCharacteristics() != null){   //Location is restaurant
                importedLocations.add(new Restaurant(g.getName(), g.getCoordinates(), g.getDescription(), g.getCharacteristics()));
            }
            else if (g.getArchitect() != null){ //Location is monument
                importedLocations.add(new Monument(g.getName(), g.getCoordinates(), g.getDescription(), g.getArchitect(), g.getInauguration()));
            }
            else{
                importedLocations.add(new Location(g.getName(), g.getCoordinates(), g.getDescription()));
            }
        }
    }
}
