package object;

import customer.Camera;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;
import java.util.ArrayList;

public class Things {
    private final ArrayList<Camera> cameraList = new ArrayList<>();
    private final Calculate obj = new Calculate();

    public Things() {}

    //Read input file
    public boolean readInputFile(String fileName) {
        String[] inputLines = new String[100];
        int[] count = {0}; //count là bien đem xem da in toi dong nao
        try(Stream<String> stream = Files.lines(Paths.get(fileName))){
            stream.forEach(line ->{
//              line la tung dong trong file
                inputLines[count[0]++] = line;
//                System.out.println(inputLines[count[0]++]);
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        int numberOfObjects = Integer.parseInt(inputLines[1]);  //so luong vat the trong phong
        int numberOfCameras = Integer.parseInt(inputLines[numberOfObjects + 2]);  //so luong camera trong phong
        String tmp, tmp2;

        //Read the first line and set paramaters of room
        tmp = inputLines[0].replace('(', ',');
        tmp2 = tmp.replace(')', ',');
        String[] corTmp = tmp2.split(",");
        double corX;
        double corY;
        double corZ;    //cac toa do x,y,z

        Calculate room =new Calculate();
        //Point A
        corX = Double.parseDouble(corTmp[1]);
        corY = Double.parseDouble(corTmp[2].trim());
        corZ = Double.parseDouble(corTmp[3].trim());
        room.setPointA(new Cordination(corX, corY, corZ));
        //Point B
        corX = Double.parseDouble(corTmp[5]);
        corY = Double.parseDouble(corTmp[6].trim());
        corZ = Double.parseDouble(corTmp[7].trim());
        room.setPointB(new Cordination(corX, corY, corZ));
        //Point C

        corX = Double.parseDouble(corTmp[9]);
        corY = Double.parseDouble(corTmp[10].trim());
        corZ = Double.parseDouble(corTmp[11].trim());
        room.setPointC(new Cordination(corX, corY, corZ));
        //Point D
        corX = Double.parseDouble(corTmp[13]);
        corY = Double.parseDouble(corTmp[14].trim());
        corZ = Double.parseDouble(corTmp[15].trim());
        room.setPointD(new Cordination(corX, corY, corZ));
        //Point A1
        corX = Double.parseDouble(corTmp[17]);
        corY = Double.parseDouble(corTmp[18].trim());
        corZ = Double.parseDouble(corTmp[19].trim());
        room.setPointA1(new Cordination(corX, corY, corZ));
        //Point B1
        corX = Double.parseDouble(corTmp[21]);
        corY = Double.parseDouble(corTmp[22].trim());
        corZ = Double.parseDouble(corTmp[23].trim());
        room.setPointB1(new Cordination(corX, corY, corZ));
        //Point C1
        corX = Double.parseDouble(corTmp[25]);
        corY = Double.parseDouble(corTmp[26].trim());
        corZ = Double.parseDouble(corTmp[27].trim());
        room.setPointC1(new Cordination(corX, corY, corZ));
        //Point D1
        corX = Double.parseDouble(corTmp[29]);
        corY = Double.parseDouble(corTmp[30].trim());
        corZ = Double.parseDouble(corTmp[31].trim());
        room.setPointD1(new Cordination(corX, corY, corZ));
        //Set parameters
        // check room có hợp lệ hay không
        if (room.getPointA1().getZ() != room.getPointB1().getZ() || room.getPointA1().getZ() != room.getPointC1().getZ()
                || room.getPointA1().getZ() != room.getPointD1().getZ()) {
            System.out.println("Toa do can phong khong hop le");
            return false;
        } else {
            if (obj.checkRectangle(room.pointA, room.pointB, room.pointC, room.pointD)) {
                room.setParameter();
                obj.objectsList.add(room);
            } else {
                System.out.println("Can phong khong phai la hinh hop chu nhat");
                return false;
            }
        }
        //Read and add objects
        for (int i = 2; i<2+ numberOfObjects; i++) {
            tmp = inputLines[i].replace('(', ',');
            tmp2 = tmp.replace(')', ',');
            corTmp = tmp2.split(",");

            Calculate object = new Calculate();
            //Point A
            corX = Double.parseDouble(corTmp[1]);
            corY = Double.parseDouble(corTmp[2].trim());
            corZ = Double.parseDouble(corTmp[3].trim());
            object.setPointA(new Cordination(corX, corY, corZ));
            //Point B
            corX = Double.parseDouble(corTmp[5]);
            corY = Double.parseDouble(corTmp[6].trim());
            corZ = Double.parseDouble(corTmp[7].trim());
            object.setPointB(new Cordination(corX, corY, corZ));
            //Point C
            corX = Double.parseDouble(corTmp[9]);
            corY = Double.parseDouble(corTmp[10].trim());
            corZ = Double.parseDouble(corTmp[11].trim());
            object.setPointC(new Cordination(corX, corY, corZ));
            //Point D
            corX = Double.parseDouble(corTmp[13]);
            corY = Double.parseDouble(corTmp[14].trim());
            corZ = Double.parseDouble(corTmp[15].trim());
            object.setPointD(new Cordination(corX, corY, corZ));
            //Point A1
            corX = Double.parseDouble(corTmp[17]);
            corY = Double.parseDouble(corTmp[18].trim());
            corZ = Double.parseDouble(corTmp[19].trim());
            object.setPointA1(new Cordination(corX, corY, corZ));
            //Point B1
            corX = Double.parseDouble(corTmp[21]);
            corY = Double.parseDouble(corTmp[22].trim());
            corZ = Double.parseDouble(corTmp[23].trim());
            object.setPointB1(new Cordination(corX, corY, corZ));
            //Point C1
            corX = Double.parseDouble(corTmp[25]);
            corY = Double.parseDouble(corTmp[26].trim());
            corZ = Double.parseDouble(corTmp[27].trim());
            object.setPointC1(new Cordination(corX, corY, corZ));
            //Point D1
            corX = Double.parseDouble(corTmp[29]);
            corY = Double.parseDouble(corTmp[30].trim());
            corZ = Double.parseDouble(corTmp[31].trim());
            object.setPointD1(new Cordination(corX, corY, corZ));
            //Set parameters of object

            //Add to objects
            double Z = object.getPointA1().getZ();
            if ( Z <= 0 || object.getPointB1().getZ() != Z && object.getPointC1().getZ() != Z && object.getPointD1().getZ() != Z ) {
                System.out.println("Vat the #" + i + " khong hop le");
            } else {
                // check hcn
                if (obj.checkRectangle(object.getPointA(), object.getPointB(), object.getPointC(), object.getPointD())) {
                    object.setParameter();
                    obj.objectsList.add(object);
                    int index = obj.objectsList.indexOf(object);
                    int check = obj.checkInRoom(obj, index);
                    if(check == 1) {
                        System.out.println(" vat nay dung that la nam tren san roi");
                    }
                    else if(check == 2)
                    {
                        System.out.println(" vat nay nam tren vat khac");
                    }
                    else {
                        obj.objectsList.remove(object);
                        System.out.println("Vat the #" + i + " khong hop le");
                    }
                } else {
                    System.out.println("Vat the #" + i + " khong hop le, khong phai la hinh chu nhat");
                }
            }
        }
        //Read and add cameras
        for (int i = numberOfObjects +3; i< numberOfObjects + numberOfCameras +3; i++) {
            tmp = inputLines[i].replace('(', ' ');
            tmp2 = tmp.replace(')', ' ');
            String tmp3 = tmp2.replace(',',' ');
//            System.out.println(tmp3);
            corTmp = tmp3.split("\\s");
            corX = Double.parseDouble(corTmp[1]);
            corY = Double.parseDouble(corTmp[3]);
            corZ = Double.parseDouble(corTmp[5]);
            Double sightCorner;
            sightCorner = Double.parseDouble(corTmp[7]);
            //Add to cameras
            if ((corX == obj.objectsList.get(0).getLength())&& (corY <=  obj.objectsList.get(0).getWidth() &&
                    (corZ <= obj.objectsList.get(0).getHeight()))  || ((corY == obj.objectsList.get(0).getWidth())
                    && (corX<= obj.objectsList.get(0).getHeight()) && (corZ <= obj.objectsList.get(0).getHeight()))  ||
                    ((corX==0) && (corY <= obj.objectsList.get(0).getWidth()) && (corZ <= obj.objectsList.get(0).getHeight())) ||
                    ((corY == 0) && (corX <= obj.objectsList.get(0).getLength()) && (corZ <= obj.objectsList.get(0).getHeight())) ){
                cameraList.add(new Camera(corX, corY, corZ, sightCorner));
            } else {
                System.out.println("Camera #" + i + "khong hop le");
            }
        }
        return true;
    }

//    //Print parameters of things to check
//    public void printParameters() {
//        System.out.println("Room: ");
//        room.printParameters();
//        for (int i=2; i<2+numberOfObjects; i++) {
//            int id = i-2;
//            System.out.println("-----------------------------------------------------------------");
//            System.out.println("Object #" + id + ": ");
//            objectList.get(id).printParameters();
//        }
//        for (int i=numberOfObjects +3; i< numberOfObjects + numberOfCameras +3; i++) {
//            int id = i-numberOfObjects-3;
//            System.out.println("-----------------------------------------------------------------");
//            System.out.println("Camera #" + id + ": ");
//            cameraList.get(id).printParameter();
//        }
//    }

}
