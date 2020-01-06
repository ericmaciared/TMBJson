package User;

import DataModel.Location;
import TransitAPI.Feature;
import TransitAPI.Stations;
import com.google.gson.Gson;
import iBusAPI.BusStops;
import iBusAPI.IBus;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.ArrayList;

public class Favorite {
    private String type;
    private Location location;
    private ArrayList<Stop> stops = new ArrayList<Stop>();

    public Favorite(String type, Location location, Stations metroStations) {
        this.type = type;
        this.location = location;

        loadStops(metroStations);
    }

    public Location getLocation() {
        return location;
    }

    public void loadStops(Stations metroStations){
        Stations busStops = null;
        String aux = null;

        String URL = "https://api.tmb.cat/v1/transit/parades";
        URL += "?app_id=7d22a8ce&app_key=5fd01a9d3990c923a46ff5acc149034d"; //Building whole URL String with access keys

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
            busStops = gson.fromJson(jsonData, Stations.class);

        } catch(IOException e){
            e.printStackTrace();
        }

        for (Feature f: busStops.getFeatures()){
            double distance = getDistanceFromLatLonInKm(location.getCoordinates()[0], location.getCoordinates()[1],
                    f.getGeometry().getCoordinates()[0], f.getGeometry().getCoordinates()[1]);
            String text = f.getProperties().getNOM_PARADA() + " (" + f.getProperties().getCODI_PARADA() + ") BUS";
            int codi = f.getProperties().getCODI_PARADA();

            if (distance <= 0.5){
                stops.add(new Stop(text, distance, codi));
            }
        }


        //Iterate over metroStations
        for (Feature f: metroStations.getFeatures()){
            double distance = getDistanceFromLatLonInKm(location.getCoordinates()[0], location.getCoordinates()[1],
                    f.getGeometry().getCoordinates()[0], f.getGeometry().getCoordinates()[1]);
            String text =  f.getProperties().getNOM_ESTACIO() + " (" + f.getProperties().getCODI_ESTACIO() + ") METRO";
            int codi = f.getProperties().getCODI_ESTACIO();

            if (distance <= 0.5){
                stops.add(new Stop(text, distance, codi));
            }
        }

        //Sort by closest
        int n = stops.size();
        Stop temp = null;
        for(int i=0; i < n; i++){
            for(int j=1; j < (n-i); j++){
                if(stops.get(j - 1).getDistance() > stops.get(j).getDistance()){
                    //swap elements
                    temp = stops.get(j - 1);
                    stops.set(j - 1, stops.get(j));
                    stops.set(j, temp);
                }
            }
        }
    }

    private double getDistanceFromLatLonInKm(double lat1, double lon1, double lat2, double lon2) {
        double R = 6371; // Radius of the earth in km
        double dLat = deg2rad(lat2-lat1);  // deg2rad below
        double dLon = deg2rad(lon2-lon1);
        double a =
                Math.sin(dLat/2) * Math.sin(dLat/2) +
                        Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) *
                                Math.sin(dLon/2) * Math.sin(dLon/2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
        double d = R * c; // Distance in km
        return d;
    }

    private double deg2rad(double deg) {
        return deg * (Math.PI/180);
    }

    public ArrayList<String> getStops() {
        ArrayList<String> stopStrings = new ArrayList<String>();
        for (Stop s: stops){
            stopStrings.add(s.getText());
        }
        return stopStrings;
    }

    public boolean checkFavorite(int code) {
        boolean found = false;

        for (Stop s: stops){
            if (s.getCodi() == code){
                found = true;
            }
        }
        return found;
    }
}
