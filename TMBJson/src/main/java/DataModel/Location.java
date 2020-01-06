package DataModel;

public class Location {
    private String name;
    private double[] coordinates;
    private String description;

    public String getName() {
        return name;
    }

    public double[] getCoordinates() {
        return coordinates;
    }

    public String getDescription() {
        return description;
    }

    public Location(String name, double[] coordinates, String description) {
        this.name = name;
        this.coordinates = coordinates;
        this.description = description;
    }

    public void summaryInformation(){
        System.out.println();
        System.out.println("Position: " + coordinates[0] + ", " + coordinates[1]);
        System.out.println("Description: ");
        System.out.println(description);
        System.out.println();
    }
}
