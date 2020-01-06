package PlannerAPI;

import java.util.Arrays;

public class Itinerary {
    private int duration;
    private String endTime;
    private Leg[] legs;
    private String startTime;
    private int transfers;
    private int transitTime;
    private int waitingTime;
    private int walkTime;

    @Override
    public String toString() {
        return "Itinerary{" +
                "duration=" + duration +
                ", endTime=" + endTime +
                ", legs=" + Arrays.toString(legs) +
                ", startTime=" + startTime +
                ", transfers=" + transfers +
                ", transitTime=" + transitTime +
                ", waitingTime=" + waitingTime +
                ", walkTime=" + walkTime +
                '}';
    }

    public int getDuration() {
        return duration;
    }

    public String getEndTime() {
        return endTime;
    }

    public Leg[] getLegs() {
        return legs;
    }

    public String getStartTime() {
        return startTime;
    }

    public int getTransfers() {
        return transfers;
    }

    public int getTransitTime() {
        return transitTime;
    }

    public int getWaitingTime() {
        return waitingTime;
    }

    public int getWalkTime() {
        return walkTime;
    }
}
