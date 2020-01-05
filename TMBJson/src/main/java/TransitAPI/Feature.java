package TransitAPI;

public class Feature {
    private Geometry geometry;
    private String geometry_name;
    private String id;
    private String type;
    private MetroStationFeatures properties;

    @Override
    public String toString() {
        return "Feature{" +
                "properties=" + properties +
                '}';
    }

    public MetroStationFeatures getProperties() {
        return properties;
    }

    public Geometry getGeometry() {
        return geometry;
    }
}
