package PlannerAPI;

import java.util.Arrays;

public class Plan {
    private String date;
    private Itinerary[] itineraries;
    private Spot to;
    private Spot from;

    @Override
    public String toString() {
        return "Plan{" +
                "itineraries=" + Arrays.toString(itineraries) +
                '}';
    }

    public Itinerary[] getItineraries() {
        return itineraries;
    }
}
