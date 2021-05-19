package object;

import customer.Camera;

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
    public double triangle(Cordination pointX, Cordination pointY, Cordination pointZ)
    {
        double XY = distancePointAB(pointX, pointY);
        double YZ = distancePointAB(pointY, pointZ);
        double ZX = distancePointAB(pointZ, pointX);
        double p = (XY + YZ + ZX )/2;

        return Math.round(Math.sqrt(p*(p-XY)*(p-YZ)*(p-ZX))*100) / 100.0;
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
    public double volumeOfObject( Calculate obj, int indexObj)
    {
        double width = distancePointAB(obj.objectsList.get(indexObj).getPointA(), obj.objectsList.get(indexObj).getPointB());
        double length = distancePointAB(obj.objectsList.get(indexObj).getPointA(), obj.objectsList.get(indexObj).getPointD());
        double height = distancePointAB(obj.objectsList.get(indexObj).getPointA(), obj.objectsList.get(indexObj).getPointA1());
        return  width * length * height;
    }
    // tinh tong the tich 6 hinh chop
    public double sumOfVolume( Calculate obj, int index, Cordination pointM)
    {
        double volumeM1 = pyramidVolume(obj.objectsList.get(index).getPointA(), obj.objectsList.get(index).getPointB(), obj.objectsList.get(index).getPointC(), pointM);
        double volumeM2 = pyramidVolume(obj.objectsList.get(index).getPointA(), obj.objectsList.get(index).getPointB(), obj.objectsList.get(index).getPointB1(), pointM);
        double volumeM3 = pyramidVolume(obj.objectsList.get(index).getPointA(), obj.objectsList.get(index).getPointD(), obj.objectsList.get(index).getPointD1(), pointM);
        double volumeM4 = pyramidVolume(obj.objectsList.get(index).getPointB1(), obj.objectsList.get(index).getPointB(), obj.objectsList.get(index).getPointC(), pointM);
        double volumeM5 = pyramidVolume(obj.objectsList.get(index).getPointA1(), obj.objectsList.get(index).getPointB1(), obj.objectsList.get(index).getPointC1(), pointM);
        double volumeM6 = pyramidVolume(obj.objectsList.get(index).getPointC(), obj.objectsList.get(index).getPointD(), obj.objectsList.get(index).getPointD1(), pointM);
        return volumeM1 + volumeM2 + volumeM3 + volumeM4 + volumeM5 + volumeM6;
    }
    // kiem tra xem vat co ton tai trong danh sach khong

    // kiem tra xem vat co nam o tren san nha khong?
    // quy uoc OXY la mat phang san nha
    public double checkOnPlane(Calculate obj, int index, Cordination pointM)
    {
        Cordination vectorN = makeVectorN(obj.objectsList.get(index).getPointA(), obj.objectsList.get(index).getPointB(), obj.objectsList.get(index).getPointC());
        return distance(pointM, vectorN, obj.objectsList.get(index).getPointA());
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
//    public boolean checkInObject(Calculate obj, int indexCurrentObj)
//    {
//        // y tuong la xet vi tri tuong doi cua diem can xet den tam cua hhcn, neu dong thoi cung phia voi 12 canh cua hhcn thi diem do nam trong vat
//        // lay tam cua hhcn cac vat con lai va xet
////        for(int i = 1 ; i < obj.objectsList.size() ; i++ ) {
////            if( i == indexCurrentObj)
////            {
////                continue;
////            }
////            double ab = Math.round(distancePointAB(obj.objectsList.get(i).getPointA(), obj.objectsList.get(i).getPointB()));
////            double ad = Math.round(distancePointAB(obj.objectsList.get(i).getPointA(), obj.objectsList.get(i).getPointD()));
////            double ac = Math.round(distancePointAB(obj.objectsList.get(i).getPointA(), obj.objectsList.get(i).getPointC()));
////
////            double xAB = obj.objectsList.get(i).getPointB().getX() - obj.objectsList.get(i).getPointA().getX();
////            double yAB = obj.objectsList.get(i).getPointB().getY() - obj.objectsList.get(i).getPointA().getY();
////            double zAB = obj.objectsList.get(i).getPointB().getZ() - obj.objectsList.get(i).getPointA().getZ();
////            Cordination AB = new Cordination(xAB, yAB, zAB);
////            Map pointAB = new Map(ab, AB.getX(), AB.getY(), AB.getZ());
////
////            double xAD = obj.objectsList.get(i).getPointD().getX() - obj.objectsList.get(i).getPointA().getX();
////            double yAD = obj.objectsList.get(i).getPointD().getY() - obj.objectsList.get(i).getPointA().getY();
////            double zAD = obj.objectsList.get(i).getPointD().getZ() - obj.objectsList.get(i).getPointA().getZ();
////            Cordination AD = new Cordination(xAD, yAD, zAD);
////            Map pointAD = new Map(ad, AD.getX(), AD.getY(), AD.getZ());
////
////            double xAC = obj.objectsList.get(i).getPointC().getX() - obj.objectsList.get(i).getPointA().getX();
////            double yAC = obj.objectsList.get(i).getPointC().getY() - obj.objectsList.get(i).getPointA().getY();
////            double zAC = obj.objectsList.get(i).getPointC().getZ() - obj.objectsList.get(i).getPointA().getZ();
////            Cordination AC = new Cordination(xAC, yAC, zAC);
////            Map pointAC = new Map(ac, AC.getX(), AC.getY(), AC.getZ());
////
////            List<Map> listP = new ArrayList<>();
////            listP.add(pointAB);
////            listP.add(pointAC);
////            listP.add(pointAD);
////            sort(listP);
////
////            // tinh chieu cao cua vat the
////            double height = distancePointAB(obj.objectsList.get(i).getPointA(), obj.objectsList.get(i).getPointA1());
////
////            // lay tam cua hhcn
////            Cordination center = new Cordination();
////            center.setX(listP.get(2).getX() / 2);
////            center.setY(listP.get(2).getY() / 2);
////            center.setZ(listP.get(2).getZ() / 2 + height / 2);
////        }
//        for( int i = 1 ; i< obj.objectsList.size() ; i++)
//        {
//            if( i == indexCurrentObj)
//            {
//                continue;
//            }
//            double sum1 = Math.round(sumOfVolume(obj, i, obj.objectsList.get(indexCurrentObj).getPointA()));
//            double sum2 = Math.round(sumOfVolume(obj, i, obj.objectsList.get(indexCurrentObj).getPointB()));
//            double sum3 = Math.round(sumOfVolume(obj, i, obj.objectsList.get(indexCurrentObj).getPointC()));
//            double sum4 = Math.round(sumOfVolume(obj, i, obj.objectsList.get(indexCurrentObj).getPointD()));
//            double sum5 = Math.round(sumOfVolume(obj, i, obj.objectsList.get(indexCurrentObj).getPointA1()));
//            double sum6 = Math.round(sumOfVolume(obj, i, obj.objectsList.get(indexCurrentObj).getPointB1()));
//            double sum7 = Math.round(sumOfVolume(obj, i, obj.objectsList.get(indexCurrentObj).getPointC1()));
//            double sum8 = Math.round(sumOfVolume(obj, i ,obj.objectsList.get(indexCurrentObj).getPointD1()));
//
//            // y tuong la tinh tong the tich cac phan tu mot diem den casi dinh bang the tich vua vat va khoang cach tu diem do den motj trong cac majt toi da 4 diem la 0
//            Cordination vectorAA1B = obj.makeVectorN(obj.objectsList.get(i).getPointA(), obj.objectsList.get(i).getPointA1(), obj.objectsList.get(i).getPointB());
//            Cordination vectorAA1D = obj.makeVectorN(obj.objectsList.get(i).getPointA(), obj.objectsList.get(i).getPointA1(), obj.objectsList.get(i).getPointD());
//            Cordination vectorA1B1C1 = obj.makeVectorN(obj.objectsList.get(i).getPointA1(), obj.objectsList.get(i).getPointB1(), obj.objectsList.get(i).getPointC1());
//            Cordination vectorDD1C = obj.makeVectorN(obj.objectsList.get(i).getPointD(), obj.objectsList.get(i).getPointD1(), obj.objectsList.get(i).getPointC());
//            Cordination vectorBB1C = obj.makeVectorN(obj.objectsList.get(i).getPointB(), obj.objectsList.get(i).getPointB1(), obj.objectsList.get(i).getPointC());
//
//            int count = 0 ;
//            if(sum1 == volumeOfObject(obj, i)) {
//                if (distance(obj.objectsList.get(indexCurrentObj).getPointA(), vectorAA1B, obj.objectsList.get(i).getPointA()) == 0
//                        || distance(obj.objectsList.get(indexCurrentObj).getPointA(), vectorAA1D, obj.objectsList.get(i).getPointA()) == 0
//                        || distance(obj.objectsList.get(indexCurrentObj).getPointA(), vectorA1B1C1, obj.objectsList.get(i).getPointA()) == 0
//                        || distance(obj.objectsList.get(indexCurrentObj).getPointA(), vectorDD1C, obj.objectsList.get(i).getPointA()) == 0
//                        || distance(obj.objectsList.get(indexCurrentObj).getPointA(), vectorBB1C, obj.objectsList.get(i).getPointA()) == 0) {
//                    count ++ ;
//                }
//            }
//            if(sum2 == volumeOfObject(obj, i)) {
//                if (distance(obj.objectsList.get(indexCurrentObj).getPointB(), vectorAA1B, obj.objectsList.get(i).getPointA()) == 0
//                        || distance(obj.objectsList.get(indexCurrentObj).getPointB(), vectorAA1D, obj.objectsList.get(i).getPointA()) == 0
//                        || distance(obj.objectsList.get(indexCurrentObj).getPointB(), vectorA1B1C1, obj.objectsList.get(i).getPointA()) == 0
//                        || distance(obj.objectsList.get(indexCurrentObj).getPointB(), vectorDD1C, obj.objectsList.get(i).getPointA()) == 0
//                        || distance(obj.objectsList.get(indexCurrentObj).getPointB(), vectorBB1C, obj.objectsList.get(i).getPointA()) == 0) {
//                    count ++ ;
//                }
//            }
//            if(sum3 == volumeOfObject(obj, i)) {
//                if (distance(obj.objectsList.get(indexCurrentObj).getPointC(), vectorAA1B, obj.objectsList.get(i).getPointA()) == 0
//                        || distance(obj.objectsList.get(indexCurrentObj).getPointC(), vectorAA1D, obj.objectsList.get(i).getPointA()) == 0
//                        || distance(obj.objectsList.get(indexCurrentObj).getPointC(), vectorA1B1C1, obj.objectsList.get(i).getPointA()) == 0
//                        || distance(obj.objectsList.get(indexCurrentObj).getPointC(), vectorDD1C, obj.objectsList.get(i).getPointA()) == 0
//                        || distance(obj.objectsList.get(indexCurrentObj).getPointC(), vectorBB1C, obj.objectsList.get(i).getPointA()) == 0) {
//                    count ++ ;
//                }
//            }
//            if(sum4 == volumeOfObject(obj, i)) {
//                if (distance(obj.objectsList.get(indexCurrentObj).getPointD(), vectorAA1B, obj.objectsList.get(i).getPointA()) == 0
//                        || distance(obj.objectsList.get(indexCurrentObj).getPointD(), vectorAA1D, obj.objectsList.get(i).getPointA()) == 0
//                        || distance(obj.objectsList.get(indexCurrentObj).getPointD(), vectorA1B1C1, obj.objectsList.get(i).getPointA()) == 0
//                        || distance(obj.objectsList.get(indexCurrentObj).getPointD(), vectorDD1C, obj.objectsList.get(i).getPointA()) == 0
//                        || distance(obj.objectsList.get(indexCurrentObj).getPointD(), vectorBB1C, obj.objectsList.get(i).getPointA()) == 0) {
//                    count ++ ;
//                }
//            }
//            if(sum5 == volumeOfObject(obj, i)) {
//                if (distance(obj.objectsList.get(indexCurrentObj).getPointA1(), vectorAA1B, obj.objectsList.get(i).getPointA()) == 0
//                        || distance(obj.objectsList.get(indexCurrentObj).getPointA1(), vectorAA1D, obj.objectsList.get(i).getPointA()) == 0
//                        || distance(obj.objectsList.get(indexCurrentObj).getPointA1(), vectorA1B1C1, obj.objectsList.get(i).getPointA()) == 0
//                        || distance(obj.objectsList.get(indexCurrentObj).getPointA1(), vectorDD1C, obj.objectsList.get(i).getPointA()) == 0
//                        || distance(obj.objectsList.get(indexCurrentObj).getPointA1(), vectorBB1C, obj.objectsList.get(i).getPointA()) == 0) {
//                    count ++ ;
//                }
//            }
//            if(sum6 == volumeOfObject(obj, i)) {
//                if (distance(obj.objectsList.get(indexCurrentObj).getPointB1(), vectorAA1B, obj.objectsList.get(i).getPointA()) == 0
//                        || distance(obj.objectsList.get(indexCurrentObj).getPointB1(), vectorAA1D, obj.objectsList.get(i).getPointA()) == 0
//                        || distance(obj.objectsList.get(indexCurrentObj).getPointB1(), vectorA1B1C1, obj.objectsList.get(i).getPointA()) == 0
//                        || distance(obj.objectsList.get(indexCurrentObj).getPointB1(), vectorDD1C, obj.objectsList.get(i).getPointA()) == 0
//                        || distance(obj.objectsList.get(indexCurrentObj).getPointB1(), vectorBB1C, obj.objectsList.get(i).getPointA()) == 0) {
//                    count ++ ;
//                }
//            }
//            if(sum7 == volumeOfObject(obj, i)) {
//                if (distance(obj.objectsList.get(indexCurrentObj).getPointC1(), vectorAA1B, obj.objectsList.get(i).getPointA()) == 0
//                        || distance(obj.objectsList.get(indexCurrentObj).getPointC1(), vectorAA1D, obj.objectsList.get(i).getPointA()) == 0
//                        || distance(obj.objectsList.get(indexCurrentObj).getPointC1(), vectorA1B1C1, obj.objectsList.get(i).getPointA()) == 0
//                        || distance(obj.objectsList.get(indexCurrentObj).getPointC1(), vectorDD1C, obj.objectsList.get(i).getPointA()) == 0
//                        || distance(obj.objectsList.get(indexCurrentObj).getPointC1(), vectorBB1C, obj.objectsList.get(i).getPointA()) == 0) {
//                    count ++ ;
//                }
//            }
//            if(sum8 == volumeOfObject(obj, i)) {
//                if (distance(obj.objectsList.get(indexCurrentObj).getPointD1(), vectorAA1B, obj.objectsList.get(i).getPointA()) == 0
//                        || distance(obj.objectsList.get(indexCurrentObj).getPointD1(), vectorAA1D, obj.objectsList.get(i).getPointA()) == 0
//                        || distance(obj.objectsList.get(indexCurrentObj).getPointD1(), vectorA1B1C1, obj.objectsList.get(i).getPointA()) == 0
//                        || distance(obj.objectsList.get(indexCurrentObj).getPointD1(), vectorDD1C, obj.objectsList.get(i).getPointA()) == 0
//                        || distance(obj.objectsList.get(indexCurrentObj).getPointD1(), vectorBB1C, obj.objectsList.get(i).getPointA()) == 0) {
//                    count ++ ;
//                }
//            }
//            if( count <= 4)
//            {
//                System.out.println("co test vao day ");
//                return true;
//            }
//            else {
//                return false;
//            }
//        }
//        return true;
//    }
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
        double sum1 = Math.round(sumOfVolume(object, 0, object.objectsList.get(index).getPointA()));
        double sum2 = Math.round(sumOfVolume(object, 0, object.objectsList.get(index).getPointB()));
        double sum3 = Math.round(sumOfVolume(object, 0, object.objectsList.get(index).getPointC()));
        double sum4 = Math.round(sumOfVolume(object, 0, object.objectsList.get(index).getPointD()));
        double sum5 = Math.round(sumOfVolume(object, 0, object.objectsList.get(index).getPointA1()));
        double sum6 = Math.round(sumOfVolume(object, 0, object.objectsList.get(index).getPointB1()));
        double sum7 = Math.round(sumOfVolume(object, 0, object.objectsList.get(index).getPointC1()));
        double sum8 = Math.round(sumOfVolume(object, 0 , object.objectsList.get(index).getPointD1()));

        if (sum1 == volumeOfObject(object, 0) && sum2 == volumeOfObject(object, 0) && sum3 == volumeOfObject(object, 0)
                && sum4 == volumeOfObject(object, 0) && sum5 == volumeOfObject(object, 0)
                && sum6 == volumeOfObject(object, 0) && sum7 == volumeOfObject(object, 0) && sum8 == volumeOfObject(object, 0)) {
            // xet vat nam tren san nha
            // System.out.println(" vao day lan 1");
            if(checkOnPlane(object,0, object.objectsList.get(index).getPointA()) == 0
                    && checkOnPlane(object, 0, object.objectsList.get(index).getPointB()) == 0 &&
                    checkOnPlane(object, 0, object.objectsList.get(index).getPointC()) == 0
                    && checkOnPlane(object, 0, object.objectsList.get(index).getPointD()) == 0)
            {
                // System.out.println(" check A1 :" + checkPointOnFloor(object.objectsList.get(i).getPointA()));
                if(checkPointOnFloor(object.objectsList.get(index).getPointA()) == 1 && checkPointOnFloor(object.objectsList.get(index).getPointB()) == 1
                        && checkPointOnFloor(object.objectsList.get(index).getPointC()) == 1 && checkPointOnFloor(object.objectsList.get(index).getPointD()) == 1) {
                    //  System.out.println(" vao check vat nam tren san");
                    return 1;
                }
            }
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
                    Cordination vectorN = makeVectorN(object.objectsList.get(j).getPointA1(), object.objectsList.get(j).getPointB1()
                            , object.objectsList.get(j).getPointC1());
                    if (distance(object.objectsList.get(index).getPointA(), vectorN, object.objectsList.get(j).getPointA1()) == 0 &&
                            distance(object.objectsList.get(index).getPointB(), vectorN, object.objectsList.get(j).getPointA1()) == 0 &&
                            distance(object.objectsList.get(index).getPointC(), vectorN, object.objectsList.get(j).getPointA1()) == 0) {
                        if (checkPointOnSurface(object.objectsList.get(index).getPointA(), j) == 1) {
                            count++; }
                        if (checkPointOnSurface(object.objectsList.get(index).getPointB(), j) == 1) {
                            count++;
                        }
                        if (checkPointOnSurface(object.objectsList.get(index).getPointC(), j) == 1) {
                            count++;
                        }
                        if (checkPointOnSurface(object.objectsList.get(index).getPointD(), j) == 1) {
                            count++;
                        }
                        if (count >= 1 && count <= 4) {
                            return 2;
                        }
                    }
                }
            }
        }

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

//    public boolean checkRoom(String s) {
//        List<Double> list = new ArrayList<>();
//        List<Cordination> listP = new ArrayList<>();
//        int i = 0;
//        while (i < s.length()) {
//            int count = list.size();
//            if (s.charAt(i) == '(') {
//                int j = i;
//                while ((j < s.length()) && (s.charAt(j) != ')')) {
//                    if (s.charAt(j) == ',') {
//                        String x = s.substring(i + 1, j);
//                        try {
//                            double so = Double.parseDouble(x);
//                            if (so < 0) {
//                                System.out.println("Error, the value must be positive .");
//                                return false;
//                            } else {
//                                i = j + 1;
//                                list.add(so);
//                            }
//                        } catch (Exception NumberFormatException) {
//                            System.out.println("Error coordination! ");
//                            return false;
//                        }
//                    } else if ((s.charAt(j + 1) == ')')) {
//                        if (j + 2 >= s.length()) {
//                            String x = s.substring(i + 1, j + 1);
//                            try {
//                                double so = Double.parseDouble(x);
//                                if (so < 0) {
//                                    System.out.println("Error, the value must be positive .");
//                                    return false;
//                                } else {
//                                    i = s.length();
//                                    list.add(so);
//                                }
//
//                            } catch (Exception NumberFormatException) {
//                                System.out.println("Error coordination! ");
//                                return false;
//                            }
//                        } else if (s.charAt(j + 2) == ' ') {
//                            String x = s.substring(i + 1, j + 1);
//                            try {
//                                double so = Double.parseDouble(x);
//                                if (so < 0) {
//                                    System.out.println("Error, the value must be positive .");
//                                    return false;
//                                } else {
//                                    i = j + 3;
//                                    list.add(so);
//                                }
//
//                            } catch (Exception NumberFormatException) {
//                                System.out.println("Error coordination! ");
//                                return false;
//                            }
//                        }
//                    }
//                    j++;
//                }
//                if (list.size() - count == 3) {
//                    Cordination po = new Cordination(list.get(count), list.get(count + 1), list.get(count + 2));
//                    listP.add(po);
//                    continue;
//                } else {
//                    System.out.println("error ! ");
//                    return false;
//                }
//            }
//            i++;
//        }
//        // check du 8 toa do dinh hay khong
//        if (listP.size() != 8) {
//            return false;
//        }
//        // kiem tra co ton tai mot diem la goc toa do hay khong
//        int check = 0;
//        for (Cordination x : listP) {
//            if (x.getX() == 0 && x.getY() == 0 && x.getZ() == 0) {
//                check++;
//            }
//        }
//        if (check != 1) {
//            return false;
//        }
//        Characteristics room = new Characteristics();
//        for (Cordination cordination : listP) {
//            {
//                if (cordination.getX() == 0 && cordination.getY() == 0 && cordination.getZ() == 0) {
//                    // quy uoc A la goc toa do
//                    room.pointA.setX(cordination.getX());
//                    room.pointA.setY(cordination.getY());
//                    room.pointA.setZ(cordination.getZ());
//                }
//                if (room.getPointA().getX() == cordination.getX() && room.getPointA().getY() == cordination.getY() && room.getPointA().getZ() != cordination.getZ()) {
//                    // quy uoc A1 khac A ve z
//                    room.pointA1.setX(cordination.getX());
//                    room.pointA1.setY(cordination.getY());
//                    room.pointA1.setZ(cordination.getZ());
//                }
//                if (cordination.getX() == 0 && cordination.getY() != 0 && cordination.getZ() == 0) {
//                    // quy uoc B nam tren truc OY
//                    room.pointB.setX(cordination.getX());
//                    room.pointB.setY(cordination.getY());
//                    room.pointB.setZ(cordination.getZ());
//                }
//                if (room.getPointB().getX() == cordination.getX() && room.getPointB().getY() == cordination.getY() && room.getPointB().getZ() != cordination.getZ()) {
//                    // quy uoc B1
//                    room.pointB1.setX(cordination.getX());
//                    room.pointB1.setY(cordination.getY());
//                    room.pointB1.setZ(cordination.getZ());
//                }
//                if (cordination.getX() != 0 && cordination.getY() != 0 && cordination.getZ() == 0) {
//                    // quy uoc C nam tren mat OXY
//                    room.pointC.setX(cordination.getX());
//                    room.pointC.setY(cordination.getY());
//                    room.pointC.setZ(cordination.getZ());
//                }
//                if (cordination.getX() == room.getPointC().getX() && cordination.getY() == room.getPointC().getY() && room.getPointC().getZ() != cordination.getZ()) {
//                    // quy uoc C1
//                    room.pointC1.setX(cordination.getX());
//                    room.pointC1.setY(cordination.getY());
//                    room.pointC1.setZ(cordination.getZ());
//                }
//                if (cordination.getX() != 0 && cordination.getY() == 0 && cordination.getZ() == 0) {
//                    // quy uoc D nam tren truc OX
//                    room.pointD.setX(cordination.getX());
//                    room.pointD.setY(cordination.getY());
//                    room.pointD.setZ(cordination.getZ());
//                }
//                if (cordination.getX() == room.getPointD().getX() && cordination.getY() == room.getPointD().getY() && room.getPointD().getZ() != cordination.getZ()) {
//                    // quy uoc D1
//                    room.pointD1.setX(cordination.getX());
//                    room.pointD1.setY(cordination.getY());
//                    room.pointD1.setZ(cordination.getZ());
//                }
//            }
//        }
//
//        if (room.getPointA1().getZ() != room.getPointB1().getZ() || room.getPointA1().getZ() != room.getPointC1().getZ()
//                || room.getPointA1().getZ() != room.getPointD1().getZ()) {
//            return false;
//        } else {
//            Calculate calculate = new Calculate();
//            if (calculate.checkRectangle(room.pointA, room.pointB, room.pointC, room.pointD)) {
//                objectsList.add(room);
//                return true;
//            } else {
//                return false;
//            }
//        }
//    }

//    public boolean checkObject(String s, Calculate obj) {
//        List<Double> list = new ArrayList<>();
//        List<Cordination> listP = new ArrayList<>();
//        List<Map> listDistance = new ArrayList<>();
//        int i = 0;
//        while (i < s.length()) {
//            int count = list.size();
//            if (s.charAt(i) == '(') {
//                int j = i;
//                while ((j < s.length()) && (s.charAt(j) != ')')) {
//                    if (s.charAt(j) == ',') {
//                        String x = s.substring(i + 1, j);
//                        try {
//                            double so = Double.parseDouble(x);
//                            if (so < 0) {
//                                System.out.println("Error, the value must be positive .");
//                                return false;
//
//                            } else {
//                                i = j + 1;
//                                list.add(so);
//                            }
//                        } catch (Exception NumberFormatException) {
//                            System.out.println("Error coordination! ");
//                            return false;
//                        }
//                    } else if ((s.charAt(j + 1) == ')')) {
//                        if (j + 2 >= s.length()) {
//                            String x = s.substring(i + 1, j + 1);
//                            try {
//                                double so = Double.parseDouble(x);
//                                if (so < 0) {
//                                    System.out.println("Error, the value must be positive .");
//                                    return false;
//                                } else {
//                                    i = s.length();
//                                    list.add(so);
//                                }
//
//                            } catch (Exception NumberFormatException) {
//                                System.out.println("Error coordination! ");
//                                return false;
//                            }
//                        } else if (s.charAt(j + 2) == ' ') {
//                            String x = s.substring(i + 1, j + 1);
//                            try {
//                                double so = Double.parseDouble(x);
//                                if (so < 0) {
//                                    System.out.println("Error, the value must be positive .");
//                                    return false;
//                                } else {
//                                    i = j + 3;
//                                    list.add(so);
//                                }
//
//                            } catch (Exception NumberFormatException) {
//                                System.out.println("Error coordination! ");
//                                return false;
//                            }
//                        }
//                    }
//                    j++;
//                }
//                if (list.size() - count == 3) {
//                    Cordination po = new Cordination(list.get(count), list.get(count + 1), list.get(count + 2));
//                    listP.add(po);
//                    continue;
//                } else {
//                    System.out.println("error ! ");
//                    return false;
//                }
//            }
//            i++;
//        }
//        // check de tim ra 4 diem co khoang cach den mat san nho nhat de xac dinh day ABCD

//        double Z = object.getPointA().getZ();
//        if ( Z <= 0 || object.getPointB1().getZ() != Z && object.getPointC1().getZ() != Z && object.getPointD1().getZ() != Z ) {
//            System.out.println( " vao day hay khong noi mot loi");
//            return false;
//        } else {
//            System.out.println(" da zo day thu lan 0");
//            // check hcn
//            if (checkRectangle(object.getPointA(), object.getPointB(), object.getPointC(), object.getPointD())) {
//                System.out.println(" co zo day roi lan 1" );
//                obj.objectsList.add(object);
//                int index = obj.objectsList.indexOf(object);
//                int check = checkInRoom(obj, index);
//                if(check == 1) {
//                    System.out.println(" vat nay dung that la nam tren san roi");
//                    return true;
//                }
//                else if(check == 2)
//                {
//                    System.out.println(" vat nay nam tren vat khac");
//                    return true;
//                }
//                else {
//                    obj.objectsList.remove(object);
//                    return false;
//                }
//
//            } else {
//                System.out.println(" ko phai hcn");
//                return false;
//            }
//        }
//    }
    public boolean checkPointInObject(Cordination point, Calculate obj){
        Calculate calculate = new Calculate();
        Cordination vectorABCD = calculate.makeVectorN(obj.getPointA(),obj.getPointB(),obj.getPointC());
        Cordination vectorABB1A1 = calculate.makeVectorN(obj.getPointA(),obj.getPointB(),obj.getPointA1());
        Cordination vectorA1B1C1D1 = calculate.makeVectorN(obj.getPointA1(),obj.getPointB1(),obj.getPointC1());
        Cordination vectorC1D1DC = calculate.makeVectorN(obj.getPointC1(),obj.getPointD1(),obj.getPointC());
        Cordination vectorADD1A1 = calculate.makeVectorN(obj.getPointA(),obj.getPointD1(),obj.getPointA1());
        Cordination vectorBCC1B1 = calculate.makeVectorN(obj.getPointC1(),obj.getPointB(),obj.getPointC());

        double distancePToABCD = calculate.distance(point,vectorABCD,obj.getPointA());
        double distancePToABB1A1 = calculate.distance(point,vectorABB1A1,obj.getPointA());
        double distancePToA1B1C1D1 = calculate.distance(point,vectorA1B1C1D1,obj.getPointA1());
        double distancePToC1D1DC = calculate.distance(point,vectorC1D1DC,obj.getPointC());
        double distancePToADD1A1 = calculate.distance(point,vectorADD1A1,obj.getPointA());
        double distancePToBCC1B1 = calculate.distance(point,vectorBCC1B1,obj.getPointB());

        double volumePABCD = distancePToABCD*calculate.square(obj.getPointA(), obj.getPointB(), obj.getPointC());
        double volumePA1B1C1D1 = distancePToA1B1C1D1*calculate.square(obj.getPointA1(), obj.getPointB1(), obj.getPointC1());
        double volumePABB1A1 = distancePToABB1A1*calculate.square(obj.getPointA(), obj.getPointB(), obj.getPointB1());
        double volumePCDD1C1 = distancePToC1D1DC*calculate.square(obj.getPointC(), obj.getPointD(), obj.getPointC1());
        double volumePADD1A1 = distancePToADD1A1*calculate.square(obj.getPointA(), obj.getPointA1(), obj.getPointD());
        double volumePBCC1B1 = distancePToBCC1B1*calculate.square(obj.getPointB(), obj.getPointC(), obj.getPointC1());

        double height = calculate.distancePointAB(obj.getPointA(),obj.getPointA1());
        double volumeObj = height*square(obj.getPointA(),obj.getPointB(),obj.getPointC());
        if (volumeObj == volumePA1B1C1D1 + volumePABCD +volumePABB1A1 +volumePADD1A1 +volumePBCC1B1 +volumePCDD1C1){
            return true;
        }else {
            return  false;
        }
    }
    public boolean checkPointInCamera(Cordination point , Camera camera )
    {
        Calculate calculate = new Calculate();
        if (calculate.checkPointInObject(point, (Calculate) calculate.objectsList.get(0))){
            double a = Math.round((Math.tan(camera.getSightCorner()/2/180*Math.PI)*camera.getScope())*100) /100.0;
            if (camera.getX() == 0){

//            System.out.println(a + " " + camera.getY());
                Cordination po = new Cordination();
                po.setX(camera.getX()+camera.getScope());
                po.setY(camera.getY() - a);
//            System.out.println(camera.getY()-a + " " + po.getY());
                po.setZ(camera.getZ() + a);
                camera.setPointA(po);
//            System.out.println(po.toString());
//
                po.setZ(camera.getZ()-a);
                camera.setPointB(po);
//            System.out.println(camera.getPointB().toString());

                po.setY(camera.getY()+a);
                camera.setPointC(po);
//            System.out.println(camera.getPointC().toString());
//
                po.setZ(camera.getZ()+a);
                camera.setPointD(po);
//
            }
            if (camera.getY() == 0){
//            System.out.println(a + " " + camera.getY());
                Cordination po = new Cordination();
                po.setX(camera.getX()+a);
                po.setY(camera.getY() +camera.getScope());
//            System.out.println(camera.getY()-a + " " + po.getY());
                po.setZ(camera.getZ() + a);
                camera.setPointA(po);
//            System.out.println(po.toString());
//
                po.setZ(camera.getZ()-a);
                camera.setPointB(po);
//            System.out.println(camera.getPointB().toString());

                po.setX(camera.getX()-a);
                camera.setPointC(po);
//            System.out.println(camera.getPointC().toString());
//
                po.setZ(camera.getZ()+a);
                camera.setPointD(po);
            }

            Cordination vectorCamAB = calculate.makeVectorN(camera,camera.getPointA(),camera.getPointB());
            Cordination vectorCamBC = calculate.makeVectorN(camera,camera.getPointB() ,camera.getPointC());
            Cordination vectorCamCD = calculate.makeVectorN(camera,camera.getPointC(),camera.getPointD());
            Cordination vectorCamDA = calculate.makeVectorN(camera,camera.getPointD(),camera.getPointA());
            Cordination vectorABCD = calculate.makeVectorN(camera.getPointA(),camera.getPointB(),camera.getPointC());

            double distancePToCamAB = calculate.distance(point,vectorCamAB,camera);
            double distancePToCamBC = calculate.distance(point,vectorCamBC,camera);
            double distancePToCamCD = calculate.distance(point,vectorCamCD,camera);
            double distancePToCamDA = calculate.distance(point,vectorCamDA,camera);
            double distancePToABCD = calculate.distance(point,vectorABCD,camera);

            double volumeCamAB = distancePToCamAB*calculate.triangle(camera,camera.getPointA(),camera.getPointB());
            double volumeCamBC = distancePToCamBC*calculate.triangle(camera,camera.getPointB(),camera.getPointC());
            double volumeCamCD = distancePToCamCD*calculate.triangle(camera,camera.getPointC(),camera.getPointD());
            double volumeCamDA = distancePToCamDA*calculate.triangle(camera,camera.getPointD(),camera.getPointA());
            double volumePABCD = distancePToABCD*calculate.square(camera.getPointA(),camera.getPointB(),camera.getPointC());
            double volumeCamABCD = camera.getScope() * square(camera.getPointA(),camera.getPointB(),camera.getPointC());
            if (volumeCamAB + volumeCamBC + volumeCamCD + volumeCamDA + volumePABCD == volumeCamABCD){
                return true;
            }
            return false;
        }else return false;
    }
    public void setParameter() {
        setLength(distancePointAB(pointA, pointB));
        setWidth(distancePointAB(pointA, pointD));
        setHeight(distancePointAB(pointA, pointA1));
    }
    public void printParameters() {
        System.out.println("Point A: (" + this.getPointA().getX() + ", " + this.getPointA().getY() + ", " + this.getPointA().getZ() + ")");
        System.out.println("Point B: (" + this.getPointB().getX() + ", " + this.getPointB().getY() + ", " + this.getPointB().getZ() + ")");
        System.out.println("Point C: (" + this.getPointC().getX() + ", " + this.getPointC().getY() + ", " + this.getPointC().getZ() + ")");
        System.out.println("Point D: (" + this.getPointD().getX() + ", " + this.getPointD().getY() + ", " + this.getPointD().getZ() + ")");
        System.out.println("Point A1: (" + this.getPointA1().getX() + ", " + this.getPointA1().getY() + ", " + this.getPointA1().getZ() + ")");
        System.out.println("Point B1: (" + this.getPointB1().getX() + ", " + this.getPointB1().getY() + ", " + this.getPointB1().getZ() + ")");
        System.out.println("Point C1: (" + this.getPointC1().getX() + ", " + this.getPointC1().getY() + ", " + this.getPointC1().getZ() + ")");
        System.out.println("Point D1: (" + this.getPointD1().getX() + ", " + this.getPointD1().getY() + ", " + this.getPointD1().getZ() + ")");
        System.out.println("Length: " + getLength());
        System.out.println("Width: " + getWidth());
        System.out.println("Height: " + getHeight());
    }

}
