package User;

import DataModel.Location;
import TransitAPI.Stations;

import java.util.ArrayList;

public class User {
    private String username;
    private String email;
    private int birthYear;
    private ArrayList<Location> userLocations = new ArrayList<Location>();
    private ArrayList<Favorite> favoriteLocations = new ArrayList<Favorite>();

    public User(String username, String email, int birthYear) {
        this.username = username;
        this.email = email;
        this.birthYear = birthYear;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", birthYear=" + birthYear +
                ", userLocations=" + userLocations +
                '}';
    }

    public int getBirthYear() {
        return birthYear;
    }

    public ArrayList<Location> getUserLocations() {
        return userLocations;
    }

    public ArrayList<Favorite> getFavoriteLocations() {
        return favoriteLocations;
    }

    public void addFavorite(Location favoriteLocation, String type, Stations metroStations) {
        favoriteLocations.add(new Favorite(type, favoriteLocation, metroStations));
    }
}
