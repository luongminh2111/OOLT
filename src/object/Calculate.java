package object;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Calculate extends Characteristics {
    public List<Characteristics> objectsList = new ArrayList<>();
    public Calculate() {
    }
    // cac quy uoc cho bai toan
    // mat phang ABCD la mat phang san
    // AB song song truc OX
    // AD song song truc OY
    // xac dinh vector phap tuyen n cua mat phang tao boi 3 diem

    // xac dinh vector chi phuong cua duong thang di qua 2 diem trong khong gian
    public Cordination makeVectorU(Cordination pointX, Cordination pointY){
        Cordination vectorXY = new Cordination();
        vectorXY.setX(pointY.getX() - pointX.getX());
        vectorXY.setY(pointY.getY() - pointX.getY());
        vectorXY.setZ(pointY.getZ() - pointX.getZ());
        return vectorXY;
    }

    public boolean checkPerpendicular(Cordination vectorXY, Cordination vectorYZ){
        return vectorXY.getX() * vectorYZ.getX() + vectorXY.getY() * vectorYZ.getY() + vectorXY.getZ() * vectorYZ.getZ() == 0;
    }
    // check la hinh chu nhat
    public boolean checkRectangle(Cordination a, Cordination b, Cordination c, Cordination d){double ab = distancePointAB(a,b);
        double ac = distancePointAB(a,c);
        double ad = distancePointAB(a,d);
        Cordination vector1 = makeVectorN(a,b);
        Cordination vector2 = makeVectorN(a,c);
        Cordination vector3 = makeVectorN(a,d);
        if ((Math.round(Math.pow(ab,2)+Math.pow(ac,2)) == Math.round(Math.pow(ad,2)))) {
            return checkPerpendicular(vector1, vector2);

        }else
        if (Math.round(Math.pow(ab,2)+Math.pow(ad,2)) == Math.round(Math.pow(ac,2))){
            return checkPerpendicular(vector1, vector3);
        }else
        if (Math.round(Math.pow(ac,2)+Math.pow(ad,2)) == Math.round(Math.pow(ab,2))){
            return checkPerpendicular(vector2, vector3);
        }
        else {
            return false;
        }
    }
    public Cordination makeVectorN(Cordination pointX, Cordination pointY, Cordination pointZ )
    {
        Cordination vectorXY = new Cordination();
        Cordination vectorXZ = new Cordination();
        Cordination vectorN = new Cordination();

        vectorXY.setX(pointY.getX() - pointX.getX());
        vectorXY.setY(pointY.getY() - pointX.getY());
        vectorXY.setZ(pointY.getZ() - pointX.getZ());

        vectorXZ.setX(pointZ.getX() - pointX.getX());
        vectorXZ.setY(pointZ.getY() - pointX.getY());
        vectorXZ.setZ(pointZ.getZ() - pointX.getZ());

        vectorN.setX((vectorXY.getY() * vectorXZ.getZ()) - (vectorXY.getZ() * vectorXZ.getY()));
        vectorN.setY((vectorXY.getZ() * vectorXZ.getX()) - (vectorXY.getX() * vectorXZ.getZ()));
        vectorN.setZ((vectorXY.getX() * vectorXZ.getY()) - (vectorXY.getY() * vectorXZ.getX()));

        // phuong trinh mat phang la viet boi vector phap tuyen N
        // X(x - xo) + Y(y- yo) + Z(z-zo) = 0;
        return vectorN;
    }
    // xac dinh vector phap tuyen cua duong thang di qua 2 diem trong mat phang
    public Cordination makeVectorN(Cordination pointX, Cordination pointY)
    {
        return new Cordination((pointX.getY() - pointY.getY()), (pointY.getX() - pointX.getX()));
    }
    // tinh khoang cach giua 2 diem
    public double distancePointAB(Cordination pointA, Cordination pointB)
    {
        return Math.abs(Math.sqrt(Math.pow((pointB.getX()) - pointA.getX(), 2) + Math.pow(pointB.getY() - pointA.getY(), 2)
        + Math.pow(pointB.getZ() - pointA.getZ(), 2)));
    }
    // tinh khoang cach tu mot diem nam ngoai mat phang den mat phang do
    // K la diem thuoc mat phang cho truoc
    public double distance(Cordination pointM, Cordination vectorN, Cordination pointK)
    {
        return Math.abs((pointM.getX() * vectorN.getX())+ (pointM.getY() * vectorN.getY()) + (pointM.getZ() * vectorN.getZ())
                 +((vectorN.getX() * pointK.getX()) + (vectorN.getY() * pointK.getY()) + vectorN.getZ() * pointK.getZ()) * (-1))
                 / (Math.sqrt(Math.pow(vectorN.getX(),2) + Math.pow(vectorN.getY(), 2) + Math.pow(vectorN.getZ(), 2)));
    }
    // khoang cach tu mot diem den mot duong thang
    public  double distanceLine( Cordination pointM, Cordination vectorN, Cordination pointK)
    {
        return Math.abs((pointM.getX() * vectorN.getX())+ (pointM.getY() * vectorN.getY())
                +((vectorN.getX() * pointK.getX()) + (vectorN.getY() * pointK.getY())) * (-1))
                / (Math.sqrt(Math.pow(vectorN.getX(),2) + Math.pow(vectorN.getY(), 2)));
    }
    // tinh dien tich mat
    public double square(Cordination pointX, Cordination pointY, Cordination pointZ)
    {
        double XY = distancePointAB(pointX, pointY);
        double YZ = distancePointAB(pointY, pointZ);
        return XY * YZ;
    }
    // tinhs the tich hinh chop tu giac
    public double pyramidVolume (Cordination pointX, Cordination pointY, Cordination pointZ, Cordination pointM)
    {
        Cordination vectorN = makeVectorN(pointX, pointY, pointZ);
        double square = square(pointX, pointY, pointZ);
        double distance = distance(pointM, vectorN, pointX);
        return (square * distance) / 3;
    }
    // tinh the tich can phong
//    public double volumeOfRoom(int index)
//    {
//        double with = distancePointAB(objectsList.get(index).getPointA(), objectsList.get(index).getPointB());
//        double lenght = distancePointAB(objectsList.get(index).getPointA(), objectsList.get(index).getPointD());
//        double height = distancePointAB(objectsList.get(index).getPointA(), objectsList.get(index).getPointA1());
//        return with * lenght * height;
//    }
    public double volumeOfObject( Calculate obj, int indexObj)
    {
        double width = distancePointAB(obj.objectsList.get(indexObj).getPointA(), obj.objectsList.get(indexObj).getPointB());
        double length = distancePointAB(obj.objectsList.get(indexObj).getPointA(), obj.objectsList.get(indexObj).getPointD());
        double height = distancePointAB(obj.objectsList.get(indexObj).getPointA(), obj.objectsList.get(indexObj).getPointA1());
        return  width * length * height;
    }
    // tinh tong the tich 6 hinh chop
    public double sumOfVolume( Cordination pointM)
    {
        double volumeM1 = pyramidVolume(objectsList.get(0).getPointA(), objectsList.get(0).getPointB(), objectsList.get(0).getPointC(), pointM);
        double volumeM2 = pyramidVolume(objectsList.get(0).getPointA(), objectsList.get(0).getPointB(), objectsList.get(0).getPointB1(), pointM);
        double volumeM3 = pyramidVolume(objectsList.get(0).getPointA(), objectsList.get(0).getPointD(), objectsList.get(0).getPointD1(), pointM);
        double volumeM4 = pyramidVolume(objectsList.get(0).getPointB1(), objectsList.get(0).getPointB(), objectsList.get(0).getPointC(), pointM);
        double volumeM5 = pyramidVolume(objectsList.get(0).getPointA1(), objectsList.get(0).getPointB1(), objectsList.get(0).getPointC1(), pointM);
        double volumeM6 = pyramidVolume(objectsList.get(0).getPointC(), objectsList.get(0).getPointD(), objectsList.get(0).getPointD1(), pointM);
        return volumeM1 + volumeM2 + volumeM3 + volumeM4 + volumeM5 + volumeM6;
    }
    // kiem tra xem vat co ton tai trong danh sach khong

    // kiem tra xem vat co nam o tren san nha khong?
    // quy uoc OXY la mat phang san nha
    public double checkOnPlane(Cordination pointM)
    {
        Cordination vectorN = makeVectorN(objectsList.get(0).getPointA(), objectsList.get(0).getPointB(), objectsList.get(0).getPointC());
        return distance(pointM, vectorN, objectsList.get(0).getPointA());
    }
    //check 1 diem thuoc mot khung mat phang gioi han cho truoc hay khong
    // thuat toan xet su cung phia voi 4 duong thang xac dinh mat phang gioi han
    public boolean checkRelative(Cordination pointX, Cordination pointY, Cordination pointN, Cordination pointM)
    {
        //point M la diem thuoc duong thang da biet trc
        //AB:(y1−y2)(x−x1)+(x2−x1)(y−y1)=0
        Cordination vectorN = makeVectorN(pointN,pointM);
        double a = (vectorN.getX() * pointX.getX()) + (vectorN.getY() * pointX.getY())
                - (vectorN.getX() * pointM.getX() + vectorN.getY() * pointM.getY());
        double b = (vectorN.getX() * pointY.getX()) + (vectorN.getY() * pointY.getY())
                - (vectorN.getX() * pointM.getX() + vectorN.getY() * pointM.getY());
        return a * b >= 0;
    }
    public boolean checkRelativeObject(Cordination pointX, Cordination pointY, Cordination pointN, Cordination pointM)
    {
        //point M la diem thuoc duong thang da biet trc
        //AB:(y1−y2)(x−x1)+(x2−x1)(y−y1)=0
        Cordination vectorN = makeVectorN(pointN,pointM);
        double a = (vectorN.getX() * pointX.getX()) + (vectorN.getY() * pointX.getY())
                - (vectorN.getX() * pointM.getX() + vectorN.getY() * pointM.getY());
        double b = (vectorN.getX() * pointY.getX()) + (vectorN.getY() * pointY.getY())
                - (vectorN.getX() * pointM.getX() + vectorN.getY() * pointM.getY());
        // tra ve cung phia voi duong thang
        return a * b > 0;
    }
    public int checkPointOnFloor(Cordination point)
    {
        // lay toa do trung diem cua duong cheo san nha
        Cordination center = new Cordination();
        center.setX((objectsList.get(0).getPointA().getX() + objectsList.get(0).getPointC().getX()) / 2);
        center.setY((objectsList.get(0).getPointA().getY() + objectsList.get(0).getPointC().getY()) / 2);
        center.setZ(objectsList.get(0).getPointA().getZ());
        boolean check1 = checkRelative(center, point, objectsList.get(0).getPointA(), objectsList.get(0).getPointB());
        boolean check2 = checkRelative(center, point, objectsList.get(0).getPointA(), objectsList.get(0).getPointD());
        boolean check3 = checkRelative(center, point, objectsList.get(0).getPointB(), objectsList.get(0).getPointC());
        boolean check4 = checkRelative(center, point, objectsList.get(0).getPointC(), objectsList.get(0).getPointD());
        if (check1 && check2 && check3 && check4) {
            return 1;
        }
        else {
            return -1;
        }
    }
    // check cac vat co nam chong len nhau hay khong
    public void checkInObject( Cordination point, Calculate obj, int indexCurrentObj)
    {
        // y tuong la xet vi tri tuong doi cua diem can xet den tam cua hhcn, neu dong thoi cung phia voi 12 canh cua hhcn thi diem do nam trong vat
        // lay tam cua hhcn cac vat con lai va xet
        for(int i = 1 ; i < obj.objectsList.size() ; i++ ) {
            if( i == indexCurrentObj)
            {
                continue;
            }
            double ab = Math.round(distancePointAB(obj.objectsList.get(i).getPointA(), obj.objectsList.get(i).getPointB()));
            double ad = Math.round(distancePointAB(obj.objectsList.get(i).getPointA(), obj.objectsList.get(i).getPointD()));
            double ac = Math.round(distancePointAB(obj.objectsList.get(i).getPointA(), obj.objectsList.get(i).getPointC()));

            double xAB = obj.objectsList.get(i).getPointB().getX() - obj.objectsList.get(i).getPointA().getX();
            double yAB = obj.objectsList.get(i).getPointB().getY() - obj.objectsList.get(i).getPointA().getY();
            double zAB = obj.objectsList.get(i).getPointB().getZ() - obj.objectsList.get(i).getPointA().getZ();
            Cordination AB = new Cordination(xAB, yAB, zAB);
            Map pointAB = new Map(ab, AB.getX(), AB.getY(), AB.getZ());

            double xAD = obj.objectsList.get(i).getPointD().getX() - obj.objectsList.get(i).getPointA().getX();
            double yAD = obj.objectsList.get(i).getPointD().getY() - obj.objectsList.get(i).getPointA().getY();
            double zAD = obj.objectsList.get(i).getPointD().getZ() - obj.objectsList.get(i).getPointA().getZ();
            Cordination AD = new Cordination(xAD, yAD, zAD);
            Map pointAD = new Map(ad, AD.getX(), AD.getY(), AD.getZ());

            double xAC = obj.objectsList.get(i).getPointC().getX() - obj.objectsList.get(i).getPointA().getX();
            double yAC = obj.objectsList.get(i).getPointC().getY() - obj.objectsList.get(i).getPointA().getY();
            double zAC = obj.objectsList.get(i).getPointC().getZ() - obj.objectsList.get(i).getPointA().getZ();
            Cordination AC = new Cordination(xAC, yAC, zAC);
            Map pointAC = new Map(ac, AC.getX(), AC.getY(), AC.getZ());

            List<Map> listP = new ArrayList<>();
            listP.add(pointAB);
            listP.add(pointAC);
            listP.add(pointAD);
            sort(listP);

            // tinh chieu cao cua vat the
            double height = distancePointAB(obj.objectsList.get(i).getPointA(), obj.objectsList.get(i).getPointA1());

            // lay tam cua hhcn
            Cordination center = new Cordination();
            center.setX(listP.get(2).getX() / 2);
            center.setY(listP.get(2).getY() / 2);
            center.setZ(listP.get(2).getZ() / 2 + height / 2);

//            boolean check1 = checkRelativeObject(center, point, objectsList.get(i).getPointA(), objectsList.get(i).getPointB());
//            boolean check2 = checkRelativeObject(center, point, objectsList.get(i).getPointA(), objectsList.get(i).getPointD());
//            boolean check3 = checkRelativeObject(center, point, objectsList.get(i).getPointB(), objectsList.get(i).getPointC());
//            boolean check4 = checkRelativeObject(center, point, objectsList.get(i).getPointC(), objectsList.get(i).getPointD());
//            boolean check5 = checkRelativeObject(center, point, objectsList.get(i).getPointA(), objectsList.get(i).getPointA1());
//            boolean check6 = checkRelativeObject(center, point, objectsList.get(i).getPointB(), objectsList.get(i).getPointB1());
//            boolean check7 = checkRelativeObject(center, point, objectsList.get(i).getPointC(), objectsList.get(i).getPointC1());
//            boolean check8 = checkRelativeObject(center, point, objectsList.get(i).getPointD1(), objectsList.get(i).getPointD1());
//            boolean check9 = checkRelativeObject(center, point, objectsList.get(i).getPointA1(), objectsList.get(i).getPointB1());
//            boolean check10 = checkRelativeObject(center, point, objectsList.get(i).getPointA1(), objectsList.get(i).getPointD1());
//            boolean check11 = checkRelativeObject(center, point, objectsList.get(i).getPointB1(), objectsList.get(i).getPointC1());
//            boolean check12 = checkRelativeObject(center, point, objectsList.get(i).getPointC1(), objectsList.get(i).getPointD1());

        }

    }
    public int checkPointOnSurface(Cordination point, int indexCheckObj){
        // lay toa do trung diem cua duong cheo hinh hop chu nhat nam duoi
        List<Double> list = new ArrayList<>();
        double A1B1 = (double)Math.round(distancePointAB(objectsList.get(indexCheckObj).getPointA1(),objectsList.get(indexCheckObj).getPointB1())*100)/100;
        double A1C1 = (double)Math.round(distancePointAB(objectsList.get(indexCheckObj).getPointA1(),objectsList.get(indexCheckObj).getPointC1())*100)/100;
        double A1D1 = (double)Math.round(distancePointAB(objectsList.get(indexCheckObj).getPointA1(),objectsList.get(indexCheckObj).getPointD1())*100)/100;
        list.add(A1B1);
        list.add(A1C1);
        list.add(A1D1);
        Collections.sort(list);
        if(A1B1 == list.get(2)) {
            Cordination center = new Cordination();
            center.setX((objectsList.get(indexCheckObj).getPointA1().getX() + objectsList.get(indexCheckObj).getPointB1().getX()) / 2);
            center.setY((objectsList.get(indexCheckObj).getPointA1().getY() + objectsList.get(indexCheckObj).getPointB1().getY()) / 2);
            center.setZ(objectsList.get(indexCheckObj).getPointA1().getZ());
            boolean check1 = checkRelative(center, point, objectsList.get(indexCheckObj).getPointA1(), objectsList.get(indexCheckObj).getPointB1());
            boolean check2 = checkRelative(center, point, objectsList.get(indexCheckObj).getPointA1(), objectsList.get(indexCheckObj).getPointD1());
            boolean check3 = checkRelative(center, point, objectsList.get(indexCheckObj).getPointB1(), objectsList.get(indexCheckObj).getPointC1());
            boolean check4 = checkRelative(center, point, objectsList.get(indexCheckObj).getPointC1(), objectsList.get(indexCheckObj).getPointD1());
            if (check1 && check2 && check3 && check4) {
                return 1;
            }
            else {
                return -1;
            }
        }
        if(A1C1 == list.get(2)) {
            Cordination center = new Cordination();
            center.setX((objectsList.get(indexCheckObj).getPointA1().getX() + objectsList.get(indexCheckObj).getPointC1().getX()) / 2);
            center.setY((objectsList.get(indexCheckObj).getPointA1().getY() + objectsList.get(indexCheckObj).getPointC1().getY()) / 2);
            center.setZ(objectsList.get(indexCheckObj).getPointA1().getZ());
            boolean check1 = checkRelative(center, point, objectsList.get(indexCheckObj).getPointA1(), objectsList.get(indexCheckObj).getPointB1());
            boolean check2 = checkRelative(center, point, objectsList.get(indexCheckObj).getPointA1(), objectsList.get(indexCheckObj).getPointD1());
            boolean check3 = checkRelative(center, point, objectsList.get(indexCheckObj).getPointB1(), objectsList.get(indexCheckObj).getPointC1());
            boolean check4 = checkRelative(center, point, objectsList.get(indexCheckObj).getPointC1(), objectsList.get(indexCheckObj).getPointD1());
            if (check1 && check2 && check3 && check4) {
                return 1;
            }
            else {
                return -1;
            }
        }
        if(A1D1 == list.get(2)) {
            Cordination center = new Cordination();
            center.setX((objectsList.get(indexCheckObj).getPointA1().getX() + objectsList.get(indexCheckObj).getPointD1().getX()) / 2);
            center.setY((objectsList.get(indexCheckObj).getPointA1().getY() + objectsList.get(indexCheckObj).getPointD1().getY()) / 2);
            center.setZ(objectsList.get(indexCheckObj).getPointA1().getZ());
            boolean check1 = checkRelative(center, point, objectsList.get(indexCheckObj).getPointA1(), objectsList.get(indexCheckObj).getPointB1());
            boolean check2 = checkRelative(center, point, objectsList.get(indexCheckObj).getPointA1(), objectsList.get(indexCheckObj).getPointD1());
            boolean check3 = checkRelative(center, point, objectsList.get(indexCheckObj).getPointB1(), objectsList.get(indexCheckObj).getPointC1());
            boolean check4 = checkRelative(center, point, objectsList.get(indexCheckObj).getPointC1(), objectsList.get(indexCheckObj).getPointD1());
            if (check1 && check2 && check3 && check4) {
                return 1;
            }
            else {
                return -1;
            }
        }
        return -1;
    }
    // kiem tra xem vat co nam trong can phong khong?
    public int checkInRoom( Calculate object, int index)
    {
       // System.out.println(" size : "+object.objectsList.size());
            double sum1 = Math.round(sumOfVolume(object.objectsList.get(index).getPointA()));
            double sum2 = Math.round(sumOfVolume(object.objectsList.get(index).getPointB()));
            double sum3 = Math.round(sumOfVolume(object.objectsList.get(index).getPointC()));
            double sum4 = Math.round(sumOfVolume(object.objectsList.get(index).getPointD()));
            double sum5 = Math.round(sumOfVolume(object.objectsList.get(index).getPointA1()));
            double sum6 = Math.round(sumOfVolume(object.objectsList.get(index).getPointB1()));
            double sum7 = Math.round(sumOfVolume(object.objectsList.get(index).getPointC1()));
            double sum8 = Math.round(sumOfVolume(object.objectsList.get(index).getPointD1()));

            if (sum1 == volumeOfObject(object, 0) && sum2 == volumeOfObject(object, 0) && sum3 == volumeOfObject() && sum4 == volumeOfObject() && sum5 == volumeOfRoom()
                && sum6 == volumeOfObject() && sum7 == volumeOfObject() && sum8 == volumeOfObject()) {
                // xet vat nam tren san nha
               // System.out.println(" vao day lan 1");
                if(checkOnPlane(object.objectsList.get(index).getPointA()) == 0 && checkOnPlane(object.objectsList.get(index).getPointB()) == 0 &&
                        checkOnPlane(object.objectsList.get(index).getPointC()) == 0 && checkOnPlane(object.objectsList.get(index).getPointD()) == 0)
                {
                   // System.out.println(" check A1 :" + checkPointOnFloor(object.objectsList.get(i).getPointA()));
                    if(checkPointOnFloor(object.objectsList.get(index).getPointA()) == 1 && checkPointOnFloor(object.objectsList.get(index).getPointB()) == 1
                    && checkPointOnFloor(object.objectsList.get(index).getPointC()) == 1 && checkPointOnFloor(object.objectsList.get(index).getPointD()) == 1) {
                      //  System.out.println(" vao check vat nam tren san");
                        return 1;
                    }
//                    else {
//                       // System.out.println("chim cut 2");
//                        return -1;
//                    }
                }
                // xet vat co nam tren mat vat khac hay khong?
                else {
                  //  System.out.println(" vao day check vat nam tren mat vat khac");
                    for( int j = 1 ; j < object.objectsList.size() ; j++) {
                        if (index == j) {
                            continue;
                        }
                        // tao vectorN mat phang A1B1C1
                        // list de luu cac diem nam tren be mat vat khac
                        //ArrayList<Cordination> list = new ArrayList<>();
                        // tao vector phap tuyen cua mat phang
                        int count = 0;
//                        System.out.println(" co vao day khong");
//                        System.out.println(" J "+ object.objectsList.get(j).getPointA1().toString() +"  " +
//                                object.objectsList.get(j).getPointB1().toString() + " "+ object.objectsList.get(j).getPointC1().toString());
                        Cordination vectorN = makeVectorN(object.objectsList.get(j).getPointA1(), object.objectsList.get(j).getPointB1()
                                , object.objectsList.get(j).getPointC1());
//
//                        System.out.println(" vector N : " +vectorN.toString());
//                        System.out.println(" j point A1 "+ object.objectsList.get(j).getPointA1().toString());
//                        System.out.println(" distance 1 : "+ distance(object.objectsList.get(index).getPointA(), vectorN, object.objectsList.get(j).getPointA1()));
//                        System.out.println(" distance 2 : "+ distance(object.objectsList.get(index).getPointB(), vectorN, object.objectsList.get(j).getPointA1()));
//                        System.out.println("distance 3 : "+ distance(object.objectsList.get(index).getPointC(), vectorN, object.objectsList.get(j).getPointA1()));
                        if (distance(object.objectsList.get(index).getPointA(), vectorN, object.objectsList.get(j).getPointA1()) == 0 &&
                                distance(object.objectsList.get(index).getPointB(), vectorN, object.objectsList.get(j).getPointA1()) == 0 &&
                                distance(object.objectsList.get(index).getPointC(), vectorN, object.objectsList.get(j).getPointA1()) == 0) {
//                            System.out.println(" vao day check ");
//                            System.out.println(" point A : " + object.objectsList.get(index).getPointA().toString());
//                            System.out.println(" point B : " + object.objectsList.get(index).getPointB().toString());
//                            System.out.println(" point C: " + object.objectsList.get(index).getPointC().toString());
//                            System.out.println(" point D : " + object.objectsList.get(index).getPointD().toString());
                            if (checkPointOnSurface(object.objectsList.get(index).getPointA(), j) == 1) {
                                count++;
                                //System.out.println(" count 1 : "+ count);
                            }
                            if (checkPointOnSurface(object.objectsList.get(index).getPointB(), j) == 1) {
                                count++;
                              //  System.out.println(" count 2 : "+ count);
                            }
                            if (checkPointOnSurface(object.objectsList.get(index).getPointC(), j) == 1) {
                                count++;
                               // System.out.println(" count 3: "+ count);
                            }
                            if (checkPointOnSurface(object.objectsList.get(index).getPointD(), j) == 1) {
                                count++;
                               // System.out.println(" count 4 : "+ count);
                            }
                           // System.out.println(" count : "+ count);
                            if (count >= 1 && count <= 4) {
                                //System.out.println(" ok 10 lan");
                                return 2;

                            }
                        }
                    }
                   // return -1;
                    //System.out.println(" chim cut 1");
                }
            }
       // System.out.println(" chim cut 0");
        return -1;
    }
    private void sort(List<Map> listDistance) {
        for (int i = 0; i < listDistance.size() - 1; i++) {
            for (int j = i + 1; j < listDistance.size(); j++) {
                if (listDistance.get(i).getDistance() > listDistance.get(j).getDistance()) {
                    Map obj = new Map(listDistance.get(i).getDistance(), listDistance.get(i).getX(),
                            listDistance.get(i).getY(), listDistance.get(i).getZ());

                    listDistance.get(i).setDistance(listDistance.get(j).getDistance());
                    listDistance.get(i).setX(listDistance.get(j).getX());
                    listDistance.get(i).setY(listDistance.get(j).getY());
                    listDistance.get(i).setZ(listDistance.get(j).getZ());

                    listDistance.get(j).setDistance(obj.getDistance());
                    listDistance.get(j).setX(obj.getX());
                    listDistance.get(j).setY(obj.getY());
                    listDistance.get(j).setZ(obj.getZ());
                }
            }
        }
    }

    public boolean checkRoom(String s) {
        List<Double> list = new ArrayList<>();
        List<Cordination> listP = new ArrayList<>();
        int i = 0;
        while (i < s.length()) {
            int count = list.size();
            if (s.charAt(i) == '(') {
                int j = i;
                while ((j < s.length()) && (s.charAt(j) != ')')) {
                    if (s.charAt(j) == ',') {
                        String x = s.substring(i + 1, j);
                        try {
                            double so = Double.parseDouble(x);
                            if (so < 0) {
                                System.out.println("Error, the value must be positive .");
                                return false;
                            } else {
                                i = j + 1;
                                list.add(so);
                            }
                        } catch (Exception NumberFormatException) {
                            System.out.println("Error coordination! ");
                            return false;
                        }
                    } else if ((s.charAt(j + 1) == ')')) {
                        if (j + 2 >= s.length()) {
                            String x = s.substring(i + 1, j + 1);
                            try {
                                double so = Double.parseDouble(x);
                                if (so < 0) {
                                    System.out.println("Error, the value must be positive .");
                                    return false;
                                } else {
                                    i = s.length();
                                    list.add(so);
                                }

                            } catch (Exception NumberFormatException) {
                                System.out.println("Error coordination! ");
                                return false;
                            }
                        } else if (s.charAt(j + 2) == ' ') {
                            String x = s.substring(i + 1, j + 1);
                            try {
                                double so = Double.parseDouble(x);
                                if (so < 0) {
                                    System.out.println("Error, the value must be positive .");
                                    return false;
                                } else {
                                    i = j + 3;
                                    list.add(so);
                                }

                            } catch (Exception NumberFormatException) {
                                System.out.println("Error coordination! ");
                                return false;
                            }
                        }
                    }
                    j++;
                }
                if (list.size() - count == 3) {
                    Cordination po = new Cordination(list.get(count), list.get(count + 1), list.get(count + 2));
                    listP.add(po);
                    continue;
                } else {
                    System.out.println("error ! ");
                    return false;
                }
            }
            i++;
        }
        // check du 8 toa do dinh hay khong
        if (listP.size() != 8) {
            return false;
        }
        // kiem tra co ton tai mot diem la goc toa do hay khong
        int check = 0;
        for (Cordination x : listP) {
            if (x.getX() == 0 && x.getY() == 0 && x.getZ() == 0) {
                check++;
            }
        }
        if (check != 1) {
            return false;
        }
        Characteristics room = new Characteristics();
        for (Cordination cordination : listP) {
            {
                if (cordination.getX() == 0 && cordination.getY() == 0 && cordination.getZ() == 0) {
                    // quy uoc A la goc toa do
                    room.pointA.setX(cordination.getX());
                    room.pointA.setY(cordination.getY());
                    room.pointA.setZ(cordination.getZ());
                }
                if (room.getPointA().getX() == cordination.getX() && room.getPointA().getY() == cordination.getY() && room.getPointA().getZ() != cordination.getZ()) {
                    // quy uoc A1 khac A ve z
                    room.pointA1.setX(cordination.getX());
                    room.pointA1.setY(cordination.getY());
                    room.pointA1.setZ(cordination.getZ());
                }
                if (cordination.getX() == 0 && cordination.getY() != 0 && cordination.getZ() == 0) {
                    // quy uoc B nam tren truc OY
                    room.pointB.setX(cordination.getX());
                    room.pointB.setY(cordination.getY());
                    room.pointB.setZ(cordination.getZ());
                }
                if (room.getPointB().getX() == cordination.getX() && room.getPointB().getY() == cordination.getY() && room.getPointB().getZ() != cordination.getZ()) {
                    // quy uoc B1
                    room.pointB1.setX(cordination.getX());
                    room.pointB1.setY(cordination.getY());
                    room.pointB1.setZ(cordination.getZ());
                }
                if (cordination.getX() != 0 && cordination.getY() != 0 && cordination.getZ() == 0) {
                    // quy uoc C nam tren mat OXY
                    room.pointC.setX(cordination.getX());
                    room.pointC.setY(cordination.getY());
                    room.pointC.setZ(cordination.getZ());
                }
                if (cordination.getX() == room.getPointC().getX() && cordination.getY() == room.getPointC().getY() && room.getPointC().getZ() != cordination.getZ()) {
                    // quy uoc C1
                    room.pointC1.setX(cordination.getX());
                    room.pointC1.setY(cordination.getY());
                    room.pointC1.setZ(cordination.getZ());
                }
                if (cordination.getX() != 0 && cordination.getY() == 0 && cordination.getZ() == 0) {
                    // quy uoc D nam tren truc OX
                    room.pointD.setX(cordination.getX());
                    room.pointD.setY(cordination.getY());
                    room.pointD.setZ(cordination.getZ());
                }
                if (cordination.getX() == room.getPointD().getX() && cordination.getY() == room.getPointD().getY() && room.getPointD().getZ() != cordination.getZ()) {
                    // quy uoc D1
                    room.pointD1.setX(cordination.getX());
                    room.pointD1.setY(cordination.getY());
                    room.pointD1.setZ(cordination.getZ());
                }
            }
        }

        if (room.getPointA1().getZ() != room.getPointB1().getZ() || room.getPointA1().getZ() != room.getPointC1().getZ()
                || room.getPointA1().getZ() != room.getPointD1().getZ()) {
            return false;
        } else {
            Calculate calculate = new Calculate();
            if (calculate.checkRectangle(room.pointA, room.pointB, room.pointC, room.pointD)) {
                objectsList.add(room);
                return true;
            } else {
                return false;
            }
        }
    }

    public boolean checkObject(String s, Calculate obj) {
        List<Double> list = new ArrayList<>();
        List<Cordination> listP = new ArrayList<>();
        List<Map> listDistance = new ArrayList<>();
        int i = 0;
        while (i < s.length()) {
            int count = list.size();
            if (s.charAt(i) == '(') {
                int j = i;
                while ((j < s.length()) && (s.charAt(j) != ')')) {
                    if (s.charAt(j) == ',') {
                        String x = s.substring(i + 1, j);
                        try {
                            double so = Double.parseDouble(x);
                            if (so < 0) {
                                System.out.println("Error, the value must be positive .");
                                return false;

                            } else {
                                i = j + 1;
                                list.add(so);
                            }
                        } catch (Exception NumberFormatException) {
                            System.out.println("Error coordination! ");
                            return false;
                        }
                    } else if ((s.charAt(j + 1) == ')')) {
                        if (j + 2 >= s.length()) {
                            String x = s.substring(i + 1, j + 1);
                            try {
                                double so = Double.parseDouble(x);
                                if (so < 0) {
                                    System.out.println("Error, the value must be positive .");
                                    return false;
                                } else {
                                    i = s.length();
                                    list.add(so);
                                }

                            } catch (Exception NumberFormatException) {
                                System.out.println("Error coordination! ");
                                return false;
                            }
                        } else if (s.charAt(j + 2) == ' ') {
                            String x = s.substring(i + 1, j + 1);
                            try {
                                double so = Double.parseDouble(x);
                                if (so < 0) {
                                    System.out.println("Error, the value must be positive .");
                                    return false;
                                } else {
                                    i = j + 3;
                                    list.add(so);
                                }

                            } catch (Exception NumberFormatException) {
                                System.out.println("Error coordination! ");
                                return false;
                            }
                        }
                    }
                    j++;
                }
                if (list.size() - count == 3) {
                    Cordination po = new Cordination(list.get(count), list.get(count + 1), list.get(count + 2));
                    listP.add(po);
                    continue;
                } else {
                    System.out.println("error ! ");
                    return false;
                }
            }
            i++;
        }
        // check de tim ra 4 diem co khoang cach den mat san nho nhat de xac dinh day ABCD
        for (Cordination x : listP) {
            // tao vector phap tuyen cua mat OXY
            Cordination vectorN = makeVectorN(objectsList.get(0).getPointA(),
                    objectsList.get(0).getPointB(), objectsList.get(0).getPointC());
            // tinh khoang cach tu 8 dinh den mat san
            double distance = Math.round(distance(x, vectorN, objectsList.get(0).getPointA()));
            Map temp = new Map(distance, x.getX(), x.getY(), x.getZ());
            listDistance.add(temp); }
        sort(listDistance);
        // lay ra 4 diem co khoang cach nho nhat de xac dinh mat day ABCD cua vat ( thu tu 4 diem la bat ki)
        // diem A
        Characteristics object = new Characteristics();
        object.pointA.setX(listDistance.get(0).getX());
        object.pointA.setY(listDistance.get(0).getY());
        object.pointA.setZ(listDistance.get(0).getZ());
        // diem B
        object.pointB.setX(listDistance.get(1).getX());
        object.pointB.setY(listDistance.get(1).getY());
        object.pointB.setZ(listDistance.get(1).getZ());
        // diem C
        object.pointC.setX(listDistance.get(2).getX());
        object.pointC.setY(listDistance.get(2).getY());
        object.pointC.setZ(listDistance.get(2).getZ());
        // diem D
        object.pointD.setX(listDistance.get(3).getX());
        object.pointD.setY(listDistance.get(3).getY());
        object.pointD.setZ(listDistance.get(3).getZ());

        // lay ra cac diem A1 B1 C1 D1 thong qua cac diem A B C D
        for (i = 4; i < listP.size(); i++) {
            if (object.getPointA().getX() == listP.get(i).getX() && object.getPointA().getY() == listP.get(i).getY()
                    && object.getPointA().getZ() != listP.get(i).getZ()) {
                object.getPointA1().setX(listP.get(i).getX());
                object.getPointA1().setY(listP.get(i).getY());
                object.getPointA1().setZ(listP.get(i).getZ());
            }
            if (object.getPointB().getX() == listP.get(i).getX() && object.getPointB().getY() == listP.get(i).getY()
                    && object.getPointB().getZ() != listP.get(i).getZ()) {
                object.getPointB1().setX(listP.get(i).getX());
                object.getPointB1().setY(listP.get(i).getY());
                object.getPointB1().setZ(listP.get(i).getZ());
            }
            if (object.getPointC().getX() == listP.get(i).getX() && object.getPointC().getY() == listP.get(i).getY()
                    && object.getPointC().getZ() != listP.get(i).getZ()) {
                object.getPointC1().setX(listP.get(i).getX());
                object.getPointC1().setY(listP.get(i).getY());
                object.getPointC1().setZ(listP.get(i).getZ());
            }
            if (object.getPointD().getX() == listP.get(i).getX() && object.getPointD().getY() == listP.get(i).getY()
                    && object.getPointD().getZ() != listP.get(i).getZ()) {
                object.getPointD1().setX(listP.get(i).getX());
                object.getPointD1().setY(listP.get(i).getY());
                object.getPointD1().setZ(listP.get(i).getZ());
            }
        }

        if (object.getPointA1() == null || object.getPointB1() == null || object.getPointC1() == null || object.getPointD1() == null) {
            return false;
        } else {
            System.out.println(" da zo day thu lan 0");
            // check hcn
            if (checkRectangle(object.getPointA(), object.getPointB(), object.getPointC(), object.getPointD())) {
                System.out.println(" co zo day roi lan 1" );
                obj.objectsList.add(object);
                int index = obj.objectsList.indexOf(object);
                int check = checkInRoom(obj, index);
                if(check == 1) {
                    System.out.println(" vat nay dung that la nam tren san roi");
                    return true;
                }
                else if(check == 2)
                {
                    System.out.println(" vat nay nam tren vat khac");
                    return true;
                }
                else {
                    obj.objectsList.remove(object);
                    return false;
                }

            } else {
                System.out.println(" ko phai hcn");
                return false;
            }
        }
    }
}
