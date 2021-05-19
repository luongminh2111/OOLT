package customer;
import object.Calculate;
import object.Things;

import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        //Cho nay nho thay duong dan nhe :v
        String fileName = "D:\\13.txt";
        Things thing = new Things();
        thing.readInputFile(fileName);
    }
}

//        Calculate object = new Calculate();
//        String s;
//        Camera camera =new Camera();
//        Scanner input = new Scanner(System.in);
//        System.out.println("Enter the size of room : ");
//        s = input.nextLine();
//        if (object.checkRoom(s)) {
//            System.out.println("Enter number of object : ");
//            Scanner scanner = new Scanner(System.in);
//            int numberObj = scanner.nextInt();
//            if (numberObj < 0) {
//                System.out.println(" the value must be positive");
//            } else {
//                scanner.nextLine();
//                for (int i = 0; i < numberObj; i++) {
//                    System.out.println("Enter coordination of object " + (i + 1));
//                    String s1 = scanner.nextLine();
//                    if(!object.checkObject(s1, object))
//                    {
//                        System.out.println(" Invalid !");
//                    }
//                    else {
//                        // kiem tra tinh hop le cua vat the
//                        System.out.println("ok ok ok");
//                    }
//                }
//            }
//            System.out.println("Enter Cam");
//            String cam= input.nextLine();
//            if(camera.checkCamare(object.objectsList.get(0), cam))
//            {
//                System.out.println(" vao check diem");
//                int check = camera.checkVisibleArea(camera, object);
//                System.out.println("check " +check);
//            }
//
//        }
//        else {
//            System.out.println("Invalid! Try again.");
//        }
//    }
