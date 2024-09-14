package project1;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner keyboard = new Scanner(System.in);
        LinkedList list = new LinkedList();
        int choice = 0;

        while (choice != 9){
            System.out.println("1) Read from file and create data structures");
            System.out.println("2) Add new user using keyboard");
            System.out.println("3) Print average ratings for every product");
            System.out.println("4) Print average ratings for every product using only Turkish people's ratings");
            System.out.println("5) Print average ratings for every product using every country except Turkey");
            System.out.println("6) Print average ratings for every product using only Doctors ratings");
            System.out.println("7) Print customer data linked list");
            System.out.println("8) Print 2D array");
            System.out.println("9) Quit");
            choice = keyboard.nextInt();

            // We used println's for aesthetic
            switch (choice){
                case 1:
                    list.readingFromFile();
                    break;
                case 2:
                    list.enterFromKeyboard();
                    break;
                case 3:
                	System.out.println(); //
                    list.average(list);
                	System.out.println();
                    break;
                case 4:
                	System.out.println();
                    list.averageRatingsForTurkey(list);
                	System.out.println();
                    break;
                case 5:
                	System.out.println();
                    list.averageRatingsExceptTurkey(list);
                	System.out.println();
                    break;
                case 6:
                	System.out.println();
                    list.averageRatingsForDoctors(list);
                	System.out.println();
                    break;
                case 7:
                	System.out.println();
                    list.display();
                	System.out.println();
                    break;
                case 8:
                	System.out.println();
                    list.displayArray(list);
                	System.out.println();
                    break;
                case 9:
                    System.exit(0);
                    keyboard.close();
                    break;
            }
        }

    }
}