package customer;

import object.Calculate;
import object.Characteristics;
import object.Cordination;
import java.util.ArrayList;
import java.util.List;

public class Camera extends Cordination {
    private double sightCorner;         //goc nhin
    private double scope;               //tam xa
    public Camera(){}
    public double getSightCorner() {
        return sightCorner;
    }

    public void setSightCorner(double sightCorner) {
        this.sightCorner = sightCorner;
    }

    public double getScope() {
        return scope;
    }

    public void setScope(double scope) {
        this.scope = scope;
    }

    public Camera(double x, double y, double z, double sightCorner) {
        super(x, y, z);
        this.sightCorner = sightCorner;

    }
    private Cordination pointA;
    private Cordination pointB;
    private Cordination pointC;
    private Cordination pointD;

    public Cordination getPointA() {
        return pointA;
    }

    public void setPointA(Cordination pointA) {
        this.pointA = pointA;
    }

    public Cordination getPointB() {
        return pointB;
    }

    public void setPointB(Cordination pointB) {
        this.pointB = pointB;
    }

    public Cordination getPointC() {
        return pointC;
    }

    public void setPointC(Cordination pointC) {
        this.pointC = pointC;
    }

    public Cordination getPointD() {
        return pointD;
    }

    public void setPointD(Cordination pointD) {
        this.pointD = pointD;
    }

    @Override
    public String toString() {
        return "Camera{" +
                "pointA=" + pointA +
                ", pointB=" + pointB +
                ", pointC=" + pointC +
                ", pointD=" + pointD +
                '}';
    }

    public int checkVisibleArea( Camera camera, Calculate obj)
    {
        // tinh y :
        Calculate calculate = new Calculate();
        Cordination cam = new Cordination(camera.getX(), camera.getY(), camera.getZ());
        double edge =  (camera.getX() * Math.tan(camera.getSightCorner() / 2) ) * 2;
        // xet truogn hop camera dat tren OYZ
        // xet x chay tu camera
        int countPoint = 0 ;
        int count = 0;
        int count1 = 0 ;
        int count2 = 0 ;
        int count3 = 0;
        double y = camera.getY();
        double x = camera.getX();
        System.out.println(" scope: "+camera.getScope());
        System.out.println(" x: "+ x*edge);
        for(x = camera.getX() + 0.01 ;  x < camera.getScope() ; x += 0.01)
        {
            count1++;
            System.out.println(" x :"+ x * edge);
            for(y = camera.getY() ; y < x * edge  ; y += 0.01)
            {
                System.out.println(" y" + y);
                count2++;
                for( double z = camera.getZ() ; z < x * edge ; z += 0.01)
                {
                    count3++;
                    Cordination currentPoint = new Cordination( x, y ,z);
                    if(calculate.checkPointInCamera(currentPoint, camera))
                    {
                        // tinh vector chi phuon gcua duong thang noi diem dang xet voi camera
                        Cordination vectorU = calculate.makeVectorU(cam, currentPoint);
                        // tinh vector phap tuyen cua 5 mat phang can phai xet
                        // xet doi voi tung vat the co trong phong
                        for( int k = 1 ; k < obj.objectsList.size() ; k++) {
                            count ++;
                            System.out.println(" count : " + count);
                            Cordination vectorAA1D1 = calculate.makeVectorN(obj.objectsList.get(k).getPointA(),
                                    obj.objectsList.get(k).getPointA1(), obj.objectsList.get(k).getPointD1());
                            Cordination vectorAA1B1 = calculate.makeVectorN(obj.objectsList.get(k).getPointA(),
                                    obj.objectsList.get(k).getPointA1(), obj.objectsList.get(k).getPointB1());
                            Cordination vectorA1B1C1 = calculate.makeVectorN(obj.objectsList.get(k).getPointA1(),
                                    obj.objectsList.get(k).getPointB1(), obj.objectsList.get(k).getPointC1());
                            Cordination vectorBB1C1 = calculate.makeVectorN(obj.objectsList.get(k).getPointB(),
                                    obj.objectsList.get(k).getPointB1(), obj.objectsList.get(k).getPointC1());
                            Cordination vectorCC1D1 = calculate.makeVectorN(obj.objectsList.get(k).getPointC(),
                                    obj.objectsList.get(k).getPointC1(), obj.objectsList.get(k).getPointD1());
                            // xet xem duong thang co cat phang phang khong ( cos chay tu -1 den 1)
                            double cos1 = (vectorU.getX() * vectorAA1D1.getX() + vectorU.getY() * vectorAA1D1.getY()
                                    + vectorU.getZ() * vectorAA1D1.getZ()) / (Math.sqrt((Math.pow(vectorU.getX(), 2)) + (Math.pow(vectorU.getY(), 2))
                                    + (Math.pow(vectorU.getZ(), 2))) *  Math.sqrt((Math.pow(vectorAA1D1.getX(), 2)) + (Math.pow(vectorAA1D1.getY(), 2))
                                    + (Math.pow(vectorAA1D1.getZ(), 2))));
                            System.out.println("cos1 : "+ cos1);
                            double cos2 = (vectorU.getX() * vectorAA1B1.getX() + vectorU.getY() * vectorAA1B1.getY()
                                    + vectorU.getZ() * vectorAA1B1.getZ()) / (Math.sqrt((Math.pow(vectorU.getX(), 2)) + (Math.pow(vectorU.getY(), 2))
                                    + (Math.pow(vectorU.getZ(), 2))) *  Math.sqrt((Math.pow(vectorAA1B1.getX(), 2)) + (Math.pow(vectorAA1B1.getY(), 2))
                                    + (Math.pow(vectorAA1B1.getZ(), 2))));
                            System.out.println("cos2 : "+ cos2);
                            double cos3 = (vectorU.getX() * vectorA1B1C1.getX() + vectorU.getY() * vectorA1B1C1.getY()
                                    + vectorU.getZ() * vectorA1B1C1.getZ()) / (Math.sqrt((Math.pow(vectorU.getX(), 2)) + (Math.pow(vectorU.getY(), 2))
                                    + (Math.pow(vectorU.getZ(), 2))) *  Math.sqrt((Math.pow(vectorA1B1C1.getX(), 2)) + (Math.pow(vectorA1B1C1.getY(), 2))
                                    + (Math.pow(vectorA1B1C1.getZ(), 2))));
                            System.out.println("cos3 : "+ cos3);
                            double cos4 = (vectorU.getX() * vectorBB1C1.getX() + vectorU.getY() * vectorBB1C1.getY()
                                    + vectorU.getZ() * vectorBB1C1.getZ()) / (Math.sqrt((Math.pow(vectorU.getX(), 2)) + (Math.pow(vectorU.getY(), 2))
                                    + (Math.pow(vectorU.getZ(), 2))) *  Math.sqrt((Math.pow(vectorBB1C1.getX(), 2)) + (Math.pow(vectorBB1C1.getY(), 2))
                                    + (Math.pow(vectorBB1C1.getZ(), 2))));
                            System.out.println("cos4 : "+ cos4);
                            double cos5 = (vectorU.getX() * vectorCC1D1.getX() + vectorU.getY() * vectorCC1D1.getY()
                                    + vectorU.getZ() * vectorCC1D1.getZ()) / (Math.sqrt((Math.pow(vectorU.getX(), 2)) + (Math.pow(vectorU.getY(), 2))
                                    + (Math.pow(vectorU.getZ(), 2))) *  Math.sqrt((Math.pow(vectorCC1D1.getX(), 2)) + (Math.pow(vectorCC1D1.getY(), 2))
                                    + (Math.pow(vectorCC1D1.getZ(), 2))));
                            System.out.println("cos5 : "+ cos5);
                            if( cos1 > -1 && cos1 < 1 || cos2 > -1 && cos2 < 1 || cos3 > -1 && cos3 < 1 || cos4 > -1 && cos4 < 1
                                    || cos5 > -1 && cos5 < 1)
                            {
                                countPoint++;
                            }
                        }
                    }
                }
            }
        }
        System.out.println("count1 : "+ count1);
        System.out.println("count2 : "+ count2);
        System.out.println("count3 : "+ count3);
        return countPoint;
    }
    public double ratioVisibleArea( int countPoint)
    {
        // double a =
        return  1;
    }
    public void printParameter() {
        System.out.println("Cordination: (" + this.getX() + ", " + this.getY() + ", " + this.getZ() + ")");
        System.out.println("Sight corner: " + this.sightCorner);
        System.out.println("Scope: " + this.scope);
    }

}
