package DataModel;

import java.util.ArrayList;

public class User {
    private String username;
    private String email;
    private int birthYear;
    private ArrayList<Location> userLocations = new ArrayList<Location>();

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
}
