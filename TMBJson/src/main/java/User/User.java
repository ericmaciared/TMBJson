package User;

import DataModel.Location;
import PlannerAPI.Itinerary;
import PlannerAPI.Leg;
import PlannerAPI.RequestParameters;
import TransitAPI.Stations;

import java.util.ArrayList;

public class User {
    private String username;
    private String email;
    private int birthYear;
    private ArrayList<Location> userLocations = new ArrayList<Location>();
    private ArrayList<Favorite> favoriteLocations = new ArrayList<Favorite>();
    private ArrayList<Route> userItineraries = new ArrayList<Route>();

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

    public ArrayList<Route> getUserItineraries() {
        return userItineraries;
    }

    public void addFavorite(Location favoriteLocation, String type, Stations metroStations) {
        favoriteLocations.add(new Favorite(type, favoriteLocation, metroStations));
    }

    public void makeRoute(RequestParameters routeData, Itinerary path){
        ArrayList<String> route = new ArrayList<String>();

        route.add("\t-Origin: " + routeData.getFromPlace());
        route.add("\t-Destination: " + routeData.getToPlace());
        route.add("\t-Start Day: " + routeData.getDate() + " at " + routeData.getTime());
        route.add("\t-Max Walking Distance: " + routeData.getMaxWalkDistance() + " metres");
        route.add("\t-Fastest combination: ");
        route.add("\t\tTime taken: " + path.getDuration()/60 + "min");
        route.add("\t\tOrigin");
        route.add("\t\t|");
        for (Leg l: path.getLegs()){
            if (l.getMode().equalsIgnoreCase("WALK")){
                route.add("\t\tWalk " + l.getDuration()/60 + " min");
            }
            else{
                route.add("\t\t" + l.getFrom().getName() + " (" + l.getFrom().getStopCode() + ") -> "
                        + l.getTo().getName() + " (" + l.getTo().getStopCode() + ")" +l.getDuration()/60 + " min");
            }
            route.add("\t\t|");
        }
        route.add("\t\tDestination");

        userItineraries.add(new Route(route));
    }

}
