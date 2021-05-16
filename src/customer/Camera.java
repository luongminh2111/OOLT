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

    public Camera(double x, double y, double z, double sightCorner, double scope) {
        super(x, y, z);
        this.sightCorner = sightCorner;
        this.scope = scope;
    }
    //Khoi tao cac thong so cho camera
    public boolean checkCamare(Characteristics room, String s) {
        List<Double> list = new ArrayList<>();
//        Scanner keyboard = new Scanner(System.in);
//        double xCor, yCor, zCor, tmpScope, tmpSightCorner;
//        String s = keyboard.nextLine();
        s = s.trim();
        int i = 0 ;
        if (s.charAt(0) == '('){
            while ( i < s.length()){
                int j = i ;
                while (s.charAt(j) != ')'){
//                    System.out.println(s.charAt(j));
                    if (s.charAt(j) == ',' && s.charAt(j+1) == ' '){
//                        System.out.println(j);
                        String x =  s.substring(i+1,j);
//                        System.out.println(x);
                        double so = Double.parseDouble(x);
                        System.out.println(so);
                        if (so < 0) {
                            System.out.println("Error, the value must be positive .");
                            return false;
                        } else {
                            list.add(so);
                            i = j + 1;
                        }
                    }


                    j++;
                }
                if ((s.charAt(j) == ')') && (s.charAt(j+1)==' ')){
                    String x =  s.substring(i+2,j);
//                        System.out.println(x);
                    double so = Double.parseDouble(x);
                    System.out.println(so);
                    if (so < 0) {
                        System.out.println("Error, the value must be positive .");
                        return false;
                    } else {
                        list.add(so);
                        i = j + 1;
                    }
                }
                s = s.substring(i);
                s= s.trim();
                System.out.println(s);
                String[] s1 = s.split(" ");
                if ( s1.length == 2 ){
                    double so1 = Double.parseDouble(s1[0]);
                    if ( so1 > 0 && so1 <= 90){
                        System.out.println(so1);
                        setSightCorner(so1);
                    }
                    else {
                        return false;
                    }
                    so1 = Double.parseDouble(s1[1]);
                    if ( so1 > 0 && so1 <= 100){
                        System.out.println(so1);
                        setScope(so1);
                    }
                    else {
                        return false;
                    }
                }
                else {
                    return  false;
                }
            }
        }
        if (list.size() == 3 ){
            setX(list.get(0));
            setY(list.get(1));
            setZ(list.get(2));
            System.out.println(list.size());
        }
        else {
            return false;
        }
        Calculate calculate = new Calculate();
        double width = calculate.distancePointAB(room.getPointA(), room.getPointB());
        double length = calculate.distancePointAB(room.getPointA(), room.getPointD());
        double height = calculate.distancePointAB(room.getPointA(),room.getPointA1());
        if ((this.getX()== length)&& (this.getY() <=  width && (this.getZ() <=height))  ||
                ((this.getY() == width) && (this.getX()<= height) && (this.getZ() <= height))  ||
                ((this.getX()==0) && (this.getY() <= width) && (this.getZ() <= height)) ||
                ((this.getY() == 0) && (this.getX() <= length) && (this.getZ() <= height)) ){
            return true;
        } else {
            return false;
        }
    }

    public int checkVisibleArea( Camera camera, Calculate obj)
    {
        // tinh y :
        Calculate calculate = new Calculate();
        Cordination cam = new Cordination(camera.getX(), camera.getY(), camera.getZ());
         double edge =  (camera.getX() * Math.tan(camera.getSightCorner() / 2) ) * 2;
        // xet truogn hop camera dat tren OYZ
        // xet x chay tu camera
        for(double x = camera.getX() ;  x < camera.getScope() ; x += 0.01)
        {
            for(double y = camera.getY() ; y < x * edge  ; y += 0.01)
            {
                for( double z = camera.getZ() ; z < x * edge ; z += 0.01)
                {
                    Cordination currentPoint = new Cordination( x, y ,z);
                    if(calculate.checkPointInCamera(currentPoint, camera))
                    {
                        // tinh vector chi phuon gcua duong thang noi diem dang xet voi camera
                        calculate.makeVectorU(cam, currentPoint);
                        // tinh vector phap tuyen cua 5 mat phang can phai xet
                        // xet doi voi tung vat the co trong phong
                        for( int k = 1 ; k < obj.objectsList.size() ; k++) {
                            Cordination vectorAA1D1 = calculate.makeVectorN(obj.objectsList.get(k).getPointA(),
                                    obj.objectsList.get(k).getPointA1(), obj.objectsList.get(k).getPointD1());
                        }
                    }
                }
            }
        }
    }
}
