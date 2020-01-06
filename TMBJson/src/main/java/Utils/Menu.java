package Utils;

import User.User;
import DataModel.Location;

import java.util.ArrayList;
import java.util.Scanner;

public class Menu {
    final private String option1 = "1. User Management";
    final private String option2 = "2. Search Locations";
    final private String option3 = "3. Plan a Route";
    final private String option4 = "4. Bus Wait Time";
    final private String exit = "5. Exit";
    final private String ask = "Select an option: ";
    final private int MAX = 5;
    final private int MIN = 1;

    private int option;
    private Scanner scanner;

    public Menu() {
        scanner = new Scanner(System.in);
        option = -1;
    }

    public int getOption() {
        return option;
    }

    //Menu printing options
    public void printMenu(){
        System.out.println(option1);
        System.out.println(option2);
        System.out.println(option3);
        System.out.println(option4);
        System.out.println(exit);
    }

    public void askForOption(){
        String aux;

        System.out.println();
        System.out.println(ask);
        aux = scanner.nextLine();
        try{
            option = Integer.parseInt(aux.toLowerCase());
        }
        catch (NumberFormatException e){
            option = -1;
        }
        System.out.println();
    }

    public boolean validOption() {
        if (!(option <= MAX && option >= MIN)){
            System.err.println("\nERROR: Invalid option.\n");
        }
        return option <= MAX && option >= MIN;
    }

    public boolean exit() {
        return option == MAX;
    }

    //User login
    public User login() {
        String username;
        String email;
        int birthYear;

        System.out.println("Welcome to the TMBJson application! Please enter the requested information.\n");
        System.out.println("Username: ");
        username = scanner.nextLine();
        System.out.println("\nE-mail: ");
        email = scanner.nextLine();
        System.out.println("\nBirth Year: ");
        birthYear = scanner.nextInt();
        System.out.println("\nThe information has been successfully registered!\n");
        String buffer = scanner.nextLine();     //Collect residual \n from birthYear int (unused)

        return new User(username, email, birthYear);
    }

    //Search Location (Option 2)
    public String askLocationName() {
        String name;

        System.out.println("Enter the name of a location: ");
        name = scanner.nextLine();

        return name;
    }

    public void showLocationInfo(Location l) {
        l.summaryInformation();
    }

    public boolean askAddNewFavorite() {
        String answer;

        do {
            System.out.println("Do you want to add this location as your favorite? (yes/no)");
            answer = scanner.nextLine();
            if (answer.equalsIgnoreCase("yes")){
                System.out.println();
                return true;
            }
            else if (answer.equalsIgnoreCase("no")){
                System.out.println();
                return false;
            }
            System.err.println("Error! Insert (yes/no) only.");
        }  while (true);
    }

    public String askFavoriteType(String favoriteLocation) {
        String answer;

        do {
            System.out.println("Type (home / work / studies / leisure / culture): ");
            answer = scanner.nextLine();
            System.out.println();
        } while (!validType(answer));

        System.out.println(favoriteLocation + " has been assigned as a new favorite location.\n");

        return answer;
    }

    private boolean validType(String answer) {
        boolean b = answer.equalsIgnoreCase("home") || answer.equalsIgnoreCase("work") ||
                answer.equalsIgnoreCase("studies") || answer.equalsIgnoreCase("leisure") ||
                answer.equalsIgnoreCase("culture");

        if (!b){
            System.err.println("Error! You have to enter \"home\", \"work\", \"studies\",\"leisure\" or \"culture\".");
            System.out.println();
        }

        return b;
    }

    //Plan Route (Option 3)
    /**
     *
     * @param isOrigin: true if asking for origin, false is asking for destination
     * @return: returns string containing user input
     */
    public String getRouteLocation(boolean isOrigin) {
        if (isOrigin){
            System.out.println("Origin? (lat, lon / name location)");
        }
        else{
            System.out.println("Destination? (lat, lon / name location)");
        }
        return scanner.nextLine();
    }

    public String getRouteArrival() {
        System.out.println("Departure or arrival? (d / a)");
        return scanner.nextLine();
    }

    public String getDate() {
        System.out.println("Day? (MM-DD-YYYY)");
        return scanner.nextLine();
    }

    public String getHour() {
        System.out.println("Hour? (HH:MMam / HH:MMpm)");
        return scanner.nextLine();
    }

    public String getMaxWalkingDistanceInMeters(){
        System.out.println("Maximum walking distance in meters?");
        return scanner.nextLine();
    }

    public void wrongLocation() {
        System.err.println("Sorry! This location is not valid.\n");
    }

    public void wrongArrival() {
        System.err.println("Error! Your must enter \"d\" or \"a\"!");
    }

    public void errorInaccessibleRoute() {
        System.err.println("TMB is doing its best to make the bus and subway make this route in the future.\n");
    }

    //Show Bus Wait Times (Option 4)
    public int askForCode() {
        int code = -1;

        do {
            System.out.println("Enter the stop code:");

            try{
                code = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e){
                System.err.println("ERROR! Code not valid!");
                code = -1;
            }
        } while(code == -1);

        return code;
    }

    public void showBusWaitTimes(ArrayList<String> list) {
        for (String s: list){
            System.out.println(s);
        }

        System.out.println();
    }

    public void errorWrongParameter() {
        System.err.println("Error, there is some wrong parameter :(");
    }
}
