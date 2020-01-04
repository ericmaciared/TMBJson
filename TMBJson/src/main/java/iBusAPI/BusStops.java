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
        int n = data.getIbus().length;
        IBus temp = null;

        for(int i=0; i < n; i++){
            for(int j=1; j < (n-i); j++){
                if(data.getIbus()[j-1].gettMin() > data.getIbus()[j].gettMin()){
                    //swap elements
                    temp = data.getIbus()[j-1];
                    data.getIbus()[j-1] = data.getIbus()[j];
                    data.getIbus()[j] = temp;
                }
            }
        }

        for (IBus i: data.getIbus()){
            list.add(i.getLine() + " - " + i.getDestination() + " - " + i.gettMin() + "min");
        }

        return list;
    }
}
