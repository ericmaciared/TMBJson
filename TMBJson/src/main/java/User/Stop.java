package User;

public class Stop {
    String text;
    double distance;

    public Stop(String text, double distance) {
        this.text = text;
        this.distance = distance;
    }

    public String getText() {
        return text;
    }

    public double getDistance() {
        return distance;
    }
}
