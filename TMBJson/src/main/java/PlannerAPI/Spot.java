package PlannerAPI;

public class Spot {
    private String lat;
    private String lon;
    private String name;
    private String orig;
    private String stopCode;
    private String vertexType;

    @Override
    public String toString() {
        return "Spot{" +
                "lat=" + lat +
                ", lon=" + lon +
                ", name='" + name + '\'' +
                ", orig='" + orig + '\'' +
                ", stopCode='" + stopCode + '\'' +
                ", vertexType='" + vertexType + '\'' +
                '}';
    }

    public String getLat() {
        return lat;
    }

    public String getLon() {
        return lon;
    }

    public String getName() {
        return name;
    }

    public String getOrig() {
        return orig;
    }

    public String getStopCode() {
        return stopCode;
    }

    public String getVertexType() {
        return vertexType;
    }
}
