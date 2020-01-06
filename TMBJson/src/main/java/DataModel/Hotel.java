package DataModel;

public class Hotel extends Location{
    private int stars;

    public Hotel(String name, double[] coordinates, String description, int stars) {
        super(name, coordinates, description);
        this.stars = stars;
    }

    @Override
    public void summaryInformation(){
        System.out.println();
        System.out.println("Position: " + super.getCoordinates()[0] + ", " + super.getCoordinates()[1]);
        System.out.println("Description: ");
        System.out.println(super.getDescription());
        System.out.println("Stars: " + stars);
        System.out.println();
    }
}
