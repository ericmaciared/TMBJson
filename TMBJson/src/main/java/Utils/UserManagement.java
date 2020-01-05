package Utils;

import TransitAPI.Stations;
import DataModel.Location;
import User.User;

import java.util.ArrayList;
import java.util.Scanner;

public class UserManagement {
    final private String optionA = "a)My locations";
    final private String optionB = "b)Location History";
    final private String optionC = "c)My routes";
    final private String optionD = "d)Favorite stops and stations";
    final private String optionE = "e)Stations inaugurated in birth year";
    final private String exit = "f)Back to the principal menu";
    final private String ask = "Select an option: ";

    private String option;
    private Scanner scanner;

    public UserManagement() {
        scanner = new Scanner(System.in);
    }

    //Menu printing
    public void printMenu(){
        System.out.println(optionA);
        System.out.println(optionB);
        System.out.println(optionC);
        System.out.println(optionD);
        System.out.println(optionE);
        System.out.println(exit);
    }

    public void askForOption(){
        String aux;

        System.out.println("");
        System.out.println(ask);
        option = scanner.nextLine();
        System.out.println("");
    }

    public boolean validOption() {
        if(!(option.equals("a") || option.equals("b") || option.equals("c") || option.equals("d") ||
                option.equals("e") || option.equals("f"))){
            System.err.println("ERROR: Invalid option.");
        }
        return option.equals("a") || option.equals("b") || option.equals("c") || option.equals("d") ||
                option.equals("e") || option.equals("f");
    }

    public boolean exit() {
        return option.equals("f");
    }

    public String getOption() {
        return this.option;
    }

    //My Locations (Option a)
    public boolean myLocations(User user) {
        ArrayList<Location> locations = user.getUserLocations();
        String answer;

        if (locations.size() == 0){
            System.out.println("You don't have any location created.\n");
        }
        else{
            System.out.println("");
            for (Location l: locations){
                System.out.println(l.getName());
            }
            System.out.println("");
        }

        do {
            System.out.println("Want to create a new location? (yes/no)");
            answer = scanner.nextLine();
            System.out.println("");
        } while(!yesOrNo(answer));

        return answer.equalsIgnoreCase("yes");
    }

    private boolean yesOrNo(String string){
        if (!(string.equalsIgnoreCase("yes") || string.equalsIgnoreCase("no"))){
            System.err.println("ERROR! You must enter \"yes\" or \"no\"");
        }
        return string.equalsIgnoreCase("yes") || string.equalsIgnoreCase("no");
    }

    public Location askLocationInfo(ArrayList<Location> locations, ArrayList<Location> userLocations) {
        String name;
        double[] coordinates = new double[2];
        String description;
        String buffer;
        boolean nameOK;
        boolean lengthOK;
        boolean latitudeOK;


        //Get name that is not an existing location name
        do {
            nameOK = true;
            System.out.println("Location Name: ");
            name = scanner.nextLine();
            for (Location l: locations){
                if (l.getName().equalsIgnoreCase(name)){
                    nameOK = false;
                    System.err.println("ERROR! This location already exists.\n");
                }
            }
            for (Location l: userLocations){
                if (l.getName().equalsIgnoreCase(name)){
                    nameOK = false;
                    System.err.println("ERROR! This location already exists.\n");
                }
            }
        } while(!nameOK);

        //Get length in correct EPSG 4326 format
        do {
            lengthOK = true;
            System.out.println("\nLength: ");
            buffer = scanner.nextLine();

            try {
                coordinates[0] = Double.parseDouble(buffer);
            } catch (NumberFormatException e) {
                lengthOK = false;
                System.err.println("ERROR! This length isn't in EPSG 4326 format");
            }

            if ((coordinates[0] > 180.0) || (coordinates[0] < -180.0) && !lengthOK){
                lengthOK = false;
                System.err.println("ERROR! This length isn't in EPSG 4326 format");
            }
        } while(!lengthOK);

        //Get latitude in correct EPSG 4326 format
        do {
            latitudeOK = true;
            System.out.println("\nLatitude: ");
            buffer = scanner.nextLine();

            try {
                coordinates[1] = Double.parseDouble(buffer);
            } catch (NumberFormatException e) {
                latitudeOK = false;
                System.err.println("ERROR! This latitude isn't in EPSG 4326 format");
            }

            if ((coordinates[1] > 90.0) || (coordinates[1] < -90.0) && !latitudeOK){
                latitudeOK = false;
                System.err.println("ERROR! This latitude isn't in EPSG 4326 format");
            }
        } while(!latitudeOK);

        //Get description
        System.out.println("\nDescription: ");
        description = scanner.nextLine();

        return new Location(name, coordinates, description);
    }

    //Show History (Option b)
    public void showHistory(ArrayList<Location> history) {
        if (history.isEmpty()){
            System.err.println("You have not searched for any locations!");
            System.err.println("To search for one, access option 2 in the principal menu.\n");
        }
        else{
            System.out.println("Searched locations:");
            for (Location l: history){
                System.out.println("\t-" + l.getName());
            }
            System.out.println();
        }
    }

    //Show My Routes (Option c)


    //Show Favorite Stops and Stations (Option d)
    public void showFavoriteStops(ArrayList<String> favorite, String locationName) {
        int index = 1;
        System.out.println("-"+locationName);
        for (String s : favorite){
            System.out.println("\t" + index + ") " + s);
        }
    }

    //Show Metro Stations Inaugurated in Year (Option e)
    public void stationsInauguratedInBirthYear(User user, Stations metroStations) {
        int found = 0;

        ArrayList<String> stations = metroStations.metroStationsInauguratedInYear(user.getBirthYear());

        System.out.println("");
        if (stations.isEmpty()){
            System.err.println("No subway station opened your birth year :(");
        }
        else{
            System.out.println("Stations inaugurated in " + user.getBirthYear() + ":");
            for (String s: stations){
                System.out.println("\t-"+s);
            }
        }
        System.out.println("");
    }
}
