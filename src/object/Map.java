package object;

public class Map {
    private double distance;
    private double x;
    private double y;
    private double z;

    public Map(){
    }

    public Map(double distance, double x, double y, double z) {
        this.distance = distance;
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getZ() {
        return z;
    }

    public void setZ(double z) {
        this.z = z;
    }
}
