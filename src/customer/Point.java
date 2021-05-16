//package customer;
//
//import object.Cordination;
//import object.Object;
//
//public class Point extends Cordination {
//
//    Object object = new Object();
//    public Cordination makePlaneOZX()
//    {
//        int indexRoom = object.checkExist("room");
//        Cordination vectorN = object.makeVectorN(object.objectsList.get(indexRoom).getPointA(), object.objectsList.get(indexRoom).getPointB(),
//                object.objectsList.get(indexRoom).getPointA1());
//        return vectorN;
//
//    }
//    // P :  diem dat camera nam tren mat phang OXZ (x, 0, z)
//    // M :  tam mat day (x, scope, z )
//    // a: canh hinh vuong , u = tan(anpha/2).scope, a = u.sqrt(2)
//    // N : (x- a/2, scope, z-a/2)
//    // K : (x + a/2, scope, z-a/2)
//    // Q : (x + a/2, scope, z + a/2)
//    // T : (x - a/2, scope, z + a/2)
//    // kiem tra dieu kien 2 diem nam cung phia so voi mat phang
//    public boolean checkSameSide(Cordination pointX, Cordination pointY, Cordination vectorN, Cordination camera)
//    {
//        // pt mat phang : X(x-x0) + Y (y-yo) +Z(z-zo) =0
//        double valueX = (vectorN.getX() * pointX.getX()) + (vectorN.getY() * pointX.getY()) + (vectorN.getZ() * pointX.getZ())
//                - (vectorN.getX() * camera.getX() + vectorN.getY() * camera.getY() + vectorN.getZ() * camera.getZ());
//        double valueY = (vectorN.getX() * pointY.getX()) + (vectorN.getY() * pointY.getY()) + (vectorN.getZ() * pointY.getZ())
//                - (vectorN.getX() * camera.getX() + vectorN.getY() * camera.getY() + vectorN.getZ() * camera.getZ());
//        if(valueX * valueY < 0){
//            return false;
//        }
//        else {
//            return true;
//        }
//    }
//    public int checkAllPlane(Camera camera)
//    {
//        // xet camera dat tren mat ben A B B1 A1
//        double edge = (Math.tan(camera.getSightCorner() / 2) * camera.getScope()) * Math.sqrt(2);
//        // toa do 4 dinh cua mat day
//        Cordination pointN = new Cordination(camera.getX() - edge / 2, camera.getScope(), camera.getZ() - edge / 2);
//        Cordination pointK = new Cordination(camera.getX() + edge / 2, camera.getScope(), camera.getZ() - edge / 2);
//        Cordination pointQ = new Cordination(camera.getX() + edge / 2, camera.getScope(), camera.getZ() + edge / 2);
//        Cordination pointT = new Cordination(camera.getX() - edge / 2, camera.getScope(), camera.getZ() + edge / 2);
//        //tọa độ điểm I nằm trong vùng nhìn thấy và gần P nhất, coi như độ chia nhỏ nhất là 0.05m
//        Cordination pointI = new Cordination(camera.getX(), 0.05, camera.getZ());
//        // vector phap tuyen của 5 mặt hình chóp
//        Cordination vectorPKQ = object.makeVectorN(camera, pointK, pointQ) ;
//        Cordination vectorPQT = object.makeVectorN(camera, pointQ, pointT) ;
//        Cordination vectorPTN = object.makeVectorN(camera, pointT, pointN) ;
//        Cordination vectorPNK = object.makeVectorN(camera, pointN, pointK) ;
//        Cordination vectorNKQT = object.makeVectorN(pointN, pointK, pointQ);
//        // W : diem bat ki can xet
//        Cordination pointW = new Cordination();
//        if(object.checkPointInRoom(pointW)) {
//            if (!checkSameSide(pointI, pointW, vectorPKQ, camera) || !checkSameSide(pointI, pointW, vectorPQT, camera)
//                || !checkSameSide(pointI, pointW, vectorPTN, camera) || !checkSameSide(pointI, pointW, vectorPNK, camera)
//                || !checkSameSide(pointI, pointW, vectorNKQT, pointN)) {
//            System.out.println("The point" + pointW.toString() + " is in the clear view");
//        }
//    }
//        return 0;
//    }
//}
