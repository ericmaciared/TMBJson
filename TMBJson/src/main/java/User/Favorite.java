package User;

import DataModel.Location;

public class Favorite {
    private String type;
    private Location location;

    public Favorite(String type, Location location) {
        this.type = type;
        this.location = location;
    }

    public String getType() {
        return type;
    }

    public Location getLocation() {
        return location;
    }
}
