package TransitAPI;

import java.util.ArrayList;
import java.util.Arrays;

public class Stations {
    private Crs crs;
    private int totalFeatures;
    private String type;
    private Feature[] features;

    @Override
    public String toString() {
        return "Stations{" +
                "features=" + Arrays.toString(features) +
                '}';
    }

    public Feature[] getFeatures() {
        return features;
    }

    public ArrayList<String> metroStationsInauguratedInYear(int year){
        ArrayList<String> stations = new ArrayList<String>();
        int aux_date;

        for (Feature f: features){
            aux_date = Integer.parseInt(f.getProperties().getDATA_INAUGURACIO().substring(0,4));
            if (aux_date == year){
                stations.add(f.getProperties().getNOM_ESTACIO() + " " + f.getProperties().getNOM_LINIA());
            }
        }

        return stations;
    }
}