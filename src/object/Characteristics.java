package object;

import java.util.ArrayList;
import java.util.List;

public class Characteristics {
    protected double length;
    protected double width;
    protected double height;
//    public List<Characteristics> objectsList = new ArrayList<>();
    public void setWidth(double width) {
        this.width = width;
    }

    protected Cordination pointA = new Cordination();
    protected Cordination pointB = new Cordination();
    protected Cordination pointC = new Cordination();
    protected Cordination pointD = new Cordination();
    protected Cordination pointA1 = new Cordination();
    protected Cordination pointB1 = new Cordination();
    protected Cordination pointC1 = new Cordination();
    protected Cordination pointD1 = new Cordination();
    public Characteristics(double width, double length,  double height,  Cordination pointA, Cordination pointB,
                  Cordination pointC, Cordination pointD,
                  Cordination pointA1, Cordination pointB1, Cordination pointC1, Cordination pointD1) {
        this.width = width;
        this.length = length;
        this.height = height;
        this.pointA = pointA;
        this.pointB = pointB;
        this.pointC = pointC;
        this.pointD = pointD;
        this.pointA1 = pointA1;
        this.pointB1 = pointB1;
        this.pointC1 = pointC1;
        this.pointD1 = pointD1;

    }
    public Characteristics(double height,  Cordination pointA, Cordination pointB,
                  Cordination pointC, Cordination pointD,
                  Cordination pointA1, Cordination pointB1, Cordination pointC1, Cordination pointD1) {
        this.height = height;
        this.pointA = pointA;
        this.pointB = pointB;
        this.pointC = pointC;
        this.pointD = pointD;
        this.pointA1 = pointA1;
        this.pointB1 = pointB1;
        this.pointC1 = pointC1;
        this.pointD1 = pointD1;

    }

    public Characteristics() {
    }

    public Cordination getPointA() {
        return pointA;
    }
    public Cordination getPointB() {
        return pointB;
    }
    public Cordination getPointC() {
        return pointC;
    }
    public Cordination getPointD() {
        return pointD;
    }
    public Cordination getPointA1() {
        return pointA1;
    }
    public Cordination getPointB1() {
        return pointB1;
    }
    public Cordination getPointC1() {
        return pointC1;
    }
    public Cordination getPointD1() {
        return pointD1;
    }
    public double getLength() {
        return length;
    }
    public void setLength(double length) {
        this.length = length;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getWidth() {
        return width;
    }
    public double getHeight() {
        return height;
    }
}
