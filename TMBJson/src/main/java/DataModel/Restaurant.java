package DataModel;

import java.util.Arrays;

public class Restaurant extends Location{
    private String[] characteristics;

    public Restaurant(String name, double[] coordinates, String description, String[] characteristics) {
        super(name, coordinates, description);
        this.characteristics = characteristics;
    }

    @Override
    public void summaryInformation(){
        StringBuilder sb = new StringBuilder();
        for (String s: characteristics){
            sb.append(s);
            sb.append(" · ");
        }
        sb.delete(sb.length()-3, sb.length());

        System.out.println();
        System.out.println("Position: " + super.getCoordinates()[0] + ", " + super.getCoordinates()[1]);
        System.out.println("Description: ");
        System.out.println(super.getDescription());
        System.out.println("Characteristics: "+ sb);
        System.out.println();
    }
}
