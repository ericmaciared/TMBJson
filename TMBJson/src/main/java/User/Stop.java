package User;

public class Stop {
    String text;
    double distance;
    int codi;

    public Stop(String text, double distance, int codi) {
        this.text = text;
        this.distance = distance;
        this.codi = codi;
    }

    public String getText() {
        return text;
    }

    public double getDistance() {
        return distance;
    }

    public int getCodi(){
        return codi;
    }
}
