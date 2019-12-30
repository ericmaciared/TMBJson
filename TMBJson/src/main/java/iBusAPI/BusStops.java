package iBusAPI;

import java.util.ArrayList;

public class BusStops {
    private Data data;
    private String status;

    @Override
    public String toString() {
        return "BusStops{" +
                "data=" + data +
                '}';
    }

    public Data getData() {
        return data;
    }

    public ArrayList<String> makeBusWaitList() {
        ArrayList<String> list = new ArrayList<String>();

        for (IBus i: data.getIbus()){
            list.add(i.getLine() + " - " + i.getDestination() + " - " + i.gettMin() + "min");
        }

        return list;
    }
}
