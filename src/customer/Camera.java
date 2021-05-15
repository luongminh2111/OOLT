//package customer;
//
//import object.Cordination;
//import object.Object;
//
//import java.util.Scanner;
//public class Camera extends Cordination {
//    private double sightCorner;         //goc nhin
//    private double scope;               //tam xa
//    public Camera(){}
//    public double getSightCorner() {
//        return sightCorner;
//    }
//
//    public void setSightCorner(double sightCorner) {
//        this.sightCorner = sightCorner;
//    }
//
//    public double getScope() {
//        return scope;
//    }
//
//    public void setScope(double scope) {
//        this.scope = scope;
//    }
//
//    public Camera(double x, double y, double z, double sightCorner, double scope) {
//        super(x, y, z);
//        this.sightCorner = sightCorner;
//        this.scope = scope;
//    }
//
//    //Khoi tao cac thong so cho camera
//    public void init(Object room) {
//        Scanner keyboard = new Scanner(System.in);
////        double xCor, yCor, zCor, tmpScope, tmpSightCorner;
//        System.out.println("Enter x-cordinate of the camera:");
//        setX(keyboard.nextDouble());
//        System.out.println("Enter y-cordinate of the camera:");
//        setY(keyboard.nextDouble());
//        System.out.println("Enter the z-cordinate of the camera:");
//        setZ(keyboard.nextDouble());
//
//        if (getY() < 0 || getY() > room.getLength() || getZ() < 0 || getZ() > room.getHeight() ||getX() < 0 || getX() > room.getWidth() ) {
//            System.out.println("installation location is not valid");
//        }
//        if ((getX() != 0 && getY() !=  0 && getZ() != 0 ) ||
//                (getZ() == room.getHeight() && (getX() != 0 || getY() != 0 )) ||
//                (getZ() == room.getHeight() && (getY() != room.getLength())) ||
//                (getZ() == room.getHeight() && (getX() != room.getWidth()))){
//            System.out.println("installation location is not valid");
//        }
//        System.out.println("Enter the sight corner of the camera:");
//        double tmpSightCorner = keyboard.nextDouble();
//        if (tmpSightCorner > 90 || tmpSightCorner < 0) {
//            System.out.println("fail, the sight corner between 0 and 90");
//        }
//        setSightCorner(tmpSightCorner);
//        System.out.println("Enter the scope of the camera (dm):");
//        double tmpScope = keyboard.nextDouble();
//        if (tmpScope < 0 || tmpScope > 1000) {
//            System.out.println("fail, the scope between 0 and 100");
//        }
//        setScope(tmpScope);
//    }
//}
