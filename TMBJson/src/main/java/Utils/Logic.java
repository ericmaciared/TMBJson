package Utils;

import MetroLineAPI.MetroStations;
import DataModel.DataModel;
import DataModel.Location;
import User.User;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import iBusAPI.BusStops;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Logic {
    final private String PATH = "TMBJson/data/localitzacions.json";
    final private String ACCESS = "?app_id=7d22a8ce&app_key=5fd01a9d3990c923a46ff5acc149034d";

    private Menu menu = new Menu();
    private UserManagement userManagement = new UserManagement();
    private DataModel data = new DataModel();
    private MetroStations metroStations = new MetroStations();
    private User user;
    private ArrayList<Location> history = new ArrayList<Location>();

    /**
     * Runs main loop with options shown graphically in the Menu class
     * @see Menu
     * @see UserManagement
     */
    public void run(){

        loadMetroStations();

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

    private void loadMetroStations(){
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://api.tmb.cat/v1/transit/linies/metro/estacions?app_id=7d22a8ce&app_key=5fd01a9d3990c923a46ff5acc149034d")
                .build();
        Response responses;
        try{
            responses = client.newCall(request).execute();
            String jsonData = null;
            if (responses.body() != null){
                jsonData = responses.body().string();
            }

            Gson gson = new Gson();
            metroStations = gson.fromJson(jsonData, MetroStations.class);

        } catch(IOException e){
            e.printStackTrace();
        }

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
                planRoute();
                break;
            case 4:
                busWaitTime();
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
            userManagement.showHistory(history);
        } else if ("c".equals(option)) {
            System.out.println("c option");
        } else if ("d".equals(option)) {
            System.out.println("d option");
        } else if ("e".equals(option)) {
            userManagement.stationsInauguratedInBirthYear(user, metroStations);
        }
    }

    /**
     * Sets active user data in the datamodel object
     * @see DataModel
     */
    public void login(){
        user = menu.login();
    }

    //Option 1a
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

    //Option 1c
    private void myRoutes(){

    }

    //Option 2
    private void searchLocation() {
        String name;
        boolean found = false;

        name = menu.askLocationName();

        //Search for file imported locations
        for (Location l: data.getLocations()){
            if (l.getName().equalsIgnoreCase(name)){
                menu.showLocationInfo(l);
                found = true;
                history.add(0, l); //Add location to history at beginning

            }
        }

        //Search for user-made locations
        for (Location l: user.getUserLocations()){
            if (l.getName().equalsIgnoreCase(name)){
                menu.showLocationInfo(l);
                found = true;
                history.add(0, l);  //Add location to history at beginning
            }
        }

        if (!found){
            System.err.println("\nSorry, there is no location with this name.\n");
        }
        else{
            menu.askFavorite();
        }
    }

    //Option3
    private void planRoute() {

    }

    //Option4
    private void busWaitTime(){
        BusStops busStops = null;
        ArrayList<String> list = null;
        Boolean empty = false;

        do {
            empty = false;
            String URL = "https://api.tmb.cat/v1/ibus/stops/";
            URL += menu.askForCode() + ACCESS; //Building whole URL String with access keys

            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(URL)
                    .build();
            Response responses;
            try{
                responses = client.newCall(request).execute();
                String jsonData = null;
                if (responses.body() != null){
                    jsonData = responses.body().string();
                }

                Gson gson = new Gson();
                busStops = gson.fromJson(jsonData, BusStops.class);

            } catch(IOException e){
                e.printStackTrace();
                empty = true;
            }

            System.out.println(busStops.toString());

            try{
                list = busStops.makeBusWaitList();
            } catch (NullPointerException n){
                n.printStackTrace();
                empty = true;
            }
            
        } while (empty);
        

        menu.showBusWaitTimes(list);
    }

}

