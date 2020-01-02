package User;

import DataModel.Location;

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

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setBirthYear(int birthYear) {
        this.birthYear = birthYear;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
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

    public void addFavorite(Location favoriteLocation, String type) {
        favoriteLocations.add(new Favorite(type, favoriteLocation));
    }
}
