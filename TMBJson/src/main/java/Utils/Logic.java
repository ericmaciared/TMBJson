package Utils;

import PlannerAPI.Itinerary;
import PlannerAPI.RequestParameters;
import TransitAPI.Stations;
import DataModel.DataModel;
import DataModel.Location;
import PlannerAPI.Planner;
import User.User;
import User.Favorite;
import User.Stop;

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
import java.util.Arrays;

public class Logic {
    final private String PATH = "TMBJson/data/localitzacions.json";
    final private String ACCESS = "?app_id=7d22a8ce&app_key=5fd01a9d3990c923a46ff5acc149034d";

    private Menu menu = new Menu();
    private UserManagement userManagement = new UserManagement();
    private DataModel data = new DataModel();
    private Stations metroStations = new Stations();
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
        data.transformLocations();
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
            metroStations = gson.fromJson(jsonData, Stations.class);

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
            userManagement.myRoutes(user.getUserItineraries());
        } else if ("d".equals(option)) {
            showFavoriteStopsAndStations();
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
                newLocation = userManagement.askLocationInfo(data.getLocations(), user.getUserLocations());
                user.getUserLocations().add(newLocation);
            }
        } while (addLocation);
    }

    //Option 1d
    private void showFavoriteStopsAndStations(){
        if (user.getFavoriteLocations().isEmpty()){
            System.err.println("Error! In order to have favorite stops and stations it is necessary to create a favorite location previously.\n");
        }
        else{
            for (Favorite f: user.getFavoriteLocations()){
                userManagement.showFavoriteStops(f.getStops(), f.getLocation().getName());
            }
        }
    }

    //Option 2
    private void searchLocation() {
        String name;
        boolean found = false;
        boolean addFavorite = false;
        Location favoriteLocation = null;
        String type = null;

        name = menu.askLocationName();

        //Search for file imported locations
        for (Location l: data.getLocations()){
            if (l.getName().equalsIgnoreCase(name)){
                menu.showLocationInfo(l);
                found = true;
                favoriteLocation = l;
                history.add(0, l); //Add location to history at beginning

            }
        }

        //Search for user-made locations
        for (Location l: user.getUserLocations()){
            if (l.getName().equalsIgnoreCase(name)){
                menu.showLocationInfo(l);
                found = true;
                favoriteLocation = l;
                history.add(0, l);  //Add location to history at beginning
            }
        }

        if (!found){
            System.err.println("\nSorry, there is no location with this name.\n");
        }
        else{
            addFavorite = menu.askAddNewFavorite();

            if (addFavorite){
                type = menu.askFavoriteType(favoriteLocation.getName());
                user.addFavorite(favoriteLocation, type, metroStations);
            }
        }
    }

    //Option3
    private void planRoute() {
        StringBuilder URL = new StringBuilder();
        String aux;
        String origin = null;
        String destination = null;
        String arrival;
        String date;
        String hour;
        String maxDistance;
        Planner plans = null;
        boolean correct = false;
        int minDuration = 100000;
        Itinerary route = null;

        do {
            URL.delete(0, URL.length());
            URL.append("https://api.tmb.cat/v1/planner/plan");
            URL.append(ACCESS);

            //Get origin
            do {
                aux = menu.getRouteLocation(true);
            } while(!validLocation(aux));
            if (aux.contains(",")){
                origin = aux.replaceAll("\\s+",""); //delete spaces
            }
            else{
                for (Location l: data.getLocations()){
                    if (l.getName().equalsIgnoreCase(aux)){
                        origin = l.getCoordinates()[1] + ","+l.getCoordinates()[0];
                        //origin = Arrays.toString(l.getCoordinates()).replaceAll("\\s+","");
                        //origin = origin.replaceAll("\\[", "").replaceAll("\\]","");
                    }
                }
            }

            //Get destination
            do {
                aux = menu.getRouteLocation(false);
            } while(!validLocation(aux));
            if (aux.contains(",")){
                destination = aux.replaceAll("\\s+",""); //delete spaces
            }
            else{
                for (Location l: data.getLocations()){
                    if (l.getName().equalsIgnoreCase(aux)){
                        destination = l.getCoordinates()[1] + ","+l.getCoordinates()[0];
                        /*destination = Arrays.toString(l.getCoordinates()).replaceAll("\\s+","");
                        destination = destination.replaceAll("\\[", "").replaceAll("\\]","");*/
                    }
                }
            }

            //Get departure/arrival
            do {
                aux = menu.getRouteArrival();
                if (aux.equalsIgnoreCase("d")){
                    arrival = "false";
                }
                else if (aux.equalsIgnoreCase("a")){
                    arrival = "true";
                }
                else{
                    menu.wrongArrival();
                    arrival = "null";
                }
            } while (!arrival.equalsIgnoreCase("true") && !arrival.equalsIgnoreCase("false"));

            //Get date
            date = menu.getDate();

            //Get hour
            hour = menu.getHour();

            //Get walking distance
            maxDistance = menu.getMaxWalkingDistanceInMeters();

            URL.append("&fromPlace=");
            URL.append(origin);
            URL.append("&toPlace=");
            URL.append(destination);
            URL.append("&date=");
            URL.append(date);
            URL.append("&time=");
            URL.append(hour);
            URL.append("&arriveBy=");
            URL.append(arrival);
            URL.append("&mode=TRANSIT,WALK");
            URL.append("&maxWalkDistance=");
            URL.append(maxDistance);
            URL.append("&showIntermediateStops=true");

        } while (!validRoute(hour, date));

        plans = loadRoute(URL);

        //Get the shortest itinerary
        for (Itinerary i: plans.getPlan().getItineraries()){
            i.getDuration();
            if (i.getDuration() <= minDuration){
                System.out.println(i.getDuration());
                route = i;
                minDuration = i.getDuration();
            }
        }

        user.makeRoute(plans.getRequestParameters(), route);


    }

    private boolean validRoute(String hour, String date) {
        boolean correct = true;

        if (!hour.matches("/^([0-9]|0[0-9]|1[0-9]|2[0-3]):[0-5][0-9]?([AaPp][Mm])$/")){
            correct = false;
            System.out.println("hour");
        }
        if (!date.matches("^(1[0-2]|0[1-9])/(3[01]|[12][0-9]|0[1-9])/[0-9]{4}$")){
            correct = false;
            System.out.println("date");
        }

        if (!correct){
            menu.errorWrongParameter();
        }

        return correct;
    }


    private Planner loadRoute(StringBuilder URL) {
        Planner planner = null;

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(String.valueOf(URL))
                .build();
        Response responses;
        try{
            responses = client.newCall(request).execute();
            String jsonData = null;
            if (responses.body() != null){
                jsonData = responses.body().string();
            }

            Gson gson = new Gson();
            planner = gson.fromJson(jsonData, Planner.class);

        } catch(IOException e){
            planner = null;
            e.printStackTrace();
        } catch(IllegalStateException e){
            planner = null;
            e.printStackTrace();;
        }
        return planner;
    }

    private boolean validLocation(String origin) {
        boolean answer = false;
        double[] coordinates = new double[1];
        double lat, lon;

        //Check if location exists in data model
        for (Location l: data.getLocations()){
            if (origin.equalsIgnoreCase(l.getName())) {
                answer = true;
                break;
            }
        }

        if (!answer){   //try parsing string into doubles
            coordinates = parseCoordinates(origin);
            if (coordinates[0] != -181){
                answer = true;
            }
        }

        if (!answer){
            menu.wrongLocation();
        }

        return answer;
    }

    private double[] parseCoordinates(String origin) {
        double[] coordinates = new double[2];
        try{
            if (origin.contains(",")){
                coordinates[0] = Double.parseDouble(origin.substring(0, origin.indexOf(",")));
                System.out.println("-" + coordinates[0] + "-");
                coordinates[1] = Double.parseDouble(origin.substring(origin.indexOf(", ")+1));
                System.out.println("-" + coordinates[1] + "-");
            }

        } catch (NumberFormatException e){
            coordinates[0] = -181; //Invalid coordinate
            coordinates[1] = -181;
        }
        return coordinates;
    }

    //Option4
    private void busWaitTime(){
        BusStops busStops = null;
        ArrayList<String> list = null;
        boolean empty = false;
        int code = menu.askForCode();
        boolean favorite = false;

        do {
            empty = false;
            String URL = "https://api.tmb.cat/v1/ibus/stops/";
            URL += code + ACCESS; //Building whole URL String with access keys

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

            //Check if favorite
            for (Favorite f: user.getFavoriteLocations()){
                favorite = f.checkFavorite(code);
                if (favorite){
                    userManagement.favoriteStopMessage();
                    break;
                }
            }

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

