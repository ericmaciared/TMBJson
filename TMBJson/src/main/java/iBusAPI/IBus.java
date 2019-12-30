package iBusAPI;

import com.google.gson.annotations.SerializedName;

public class IBus {
    private String line;
    private String routeId;
    @SerializedName("t-in-min")
    private int tMin;
    @SerializedName("t-in-sec")
    private int tSec;
    @SerializedName("text-ca")
    private String textCa;
    private String destination;

    @Override
    public String toString() {
        return "IBus{" +
                "line='" + line + '\'' +
                ", routeId='" + routeId + '\'' +
                ", tMin=" + tMin +
                ", tSec=" + tSec +
                ", textCa='" + textCa + '\'' +
                ", destination='" + destination + '\'' +
                '}';
    }

    public String getLine() {
        return line;
    }

    public int gettMin() {
        return tMin;
    }

    public String getDestination() {
        return destination;
    }
}