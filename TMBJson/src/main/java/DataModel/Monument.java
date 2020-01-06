package DataModel;

public class Monument extends Location{
    private String architect;
    private int inauguration;

    public Monument(String name, double[] coordinates, String description, String architect, int inauguration) {
        super(name, coordinates, description);
        this.architect = architect;
        this.inauguration = inauguration;
    }

    @Override
    public void summaryInformation(){
        System.out.println();
        System.out.println("Position: " + super.getCoordinates()[0] + ", " + super.getCoordinates()[1]);
        System.out.println("Description: ");
        System.out.println(super.getDescription());
        System.out.println("Architect: " + architect);
        System.out.println("Inauguration: " + inauguration);
        System.out.println();
    }

}
