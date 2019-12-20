package DataModel;

import java.util.Arrays;

public class DataModel {
    private Location[] locations;
    private User user;

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    @Override
    public String toString() {
        return "DataModel{" +
                "locations=" + Arrays.toString(locations) +
                '}';
    }
}
