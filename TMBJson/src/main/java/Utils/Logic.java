package Utils;

import DataModel.DataModel;
import DataModel.Location;
import User.User;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;

public class Logic {
    final private String PATH = "TMBJson/data/localitzacions.json";

    Menu menu = new Menu();
    UserManagement userManagement = new UserManagement();
    private DataModel data = new DataModel();
    private User user;

    /**
     * Runs main loop with options shown graphically in the Menu class
     * @see Menu
     * @see UserManagement
     */
    public void run(){
        do {
            do {
                menu.printMenu();
                menu.askForOption();
            } while (!menu.validOption());
            if (menu.getOption() == 1){
                do{
                    do {
                        userManagement.printMenu();
                        userManagement.askForOption();
                    } while (!userManagement.validOption());

                    doOption(userManagement.getOption());

                }while(!userManagement.exit());
            }
            else{
                doOption(menu.getOption());
            }

        } while(!menu.exit());
    }

    /**
     * The function imports a list of locations in the json file in the data folder to the datamodel class.
     * @throws FileNotFoundException
     */
    public void loadLocations() throws FileNotFoundException {
        Gson gson = new Gson();
        JsonReader reader;

        reader = new JsonReader(new FileReader(PATH));
        data = gson.fromJson(reader, DataModel.class);

        //System.out.println(data.toString());      //test for importation correctly
    }

    /**
     * Switches option to display/output
     * @param option Integer representing the option in the menu that the user chooses from main menu
     */
    private void doOption(int option){
        switch (option){
            case 2:
                searchLocation();
                break;
            case 3:
                System.out.println("option 3");
                break;
            case 4:

                break;
        }
    }

    /**
     * Switches option to display/output
     * @param option String representing option from user management menu that user chooses
     */
    private void doOption(String option){
        if ("a".equals(option)) {
            myLocations();
        } else if ("b".equals(option)) {
            System.out.println("b option");
        } else if ("c".equals(option)) {
            System.out.println("c option");
        } else if ("d".equals(option)) {
            System.out.println("d option");
        } else if ("e".equals(option)) {
            System.out.println("e option");
        }
    }

    /**
     * Sets active user data in the datamodel object
     * @see DataModel
     */
    public void login(){
        user = menu.login();
    }

    private void myLocations(){
        boolean addLocation = false;
        Location newLocation;

        do {
            addLocation = userManagement.myLocations(user);
            if (addLocation){
                newLocation = userManagement.askLocationInfo(data.getLocations());
                user.getUserLocations().add(newLocation);
            }
        } while (addLocation);
    }

    private void searchLocation() {
        String name;
        boolean found = false;

        name = menu.askLocationName();

        for (Location l: data.getLocations()){
            if (l.getName().equalsIgnoreCase(name)){
                menu.showLocationInfo(l);
                found = true;
            }
        }

        for (Location l: user.getUserLocations()){
            if (l.getName().equalsIgnoreCase(name)){
                menu.showLocationInfo(l);
                found = true;
            }
        }

        if (!found){
            System.err.println("\nSorry, there is no location with this name.\n");
        }
        else{
            menu.askFavorite();
        }
    }
}

