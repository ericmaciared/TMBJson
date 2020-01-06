package PlannerAPI;

import java.util.Arrays;

public class Leg {
    private double distance;
    private int duration;
    private Spot to;
    private Spot from;
    private String mode;
    private IntermediateStop[] intermediateStops;

    public double getDistance() {
        return distance;
    }

    public int getDuration() {
        return duration;
    }

    public Spot getTo() {
        return to;
    }

    public Spot getFrom() {
        return from;
    }

    public String getMode() {
        return mode;
    }

    @Override
    public String toString() {
        return "Leg{" +
                "distance=" + distance +
                ", duration=" + duration +
                ", to=" + to +
                ", from=" + from +
                ", mode='" + mode + '\'' +
                ", intermediateStops=" + Arrays.toString(intermediateStops) +
                '}';
    }

    public IntermediateStop[] getIntermediateStops() {
        return intermediateStops;
    }
}
