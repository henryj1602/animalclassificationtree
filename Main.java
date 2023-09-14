package p1;
//Author: Henry Jacobson
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        BinaryTree classification = new BinaryTree();
        classification.loadFromFile();
        Scanner scanner = new Scanner(System.in);
        String userInput = "";
        Boolean run = true;
        System.out.println("Do you have another animal to identity? (Y/N) >");
        userInput = scanner.next();


        //load
        while (run) {
            if (userInput.equalsIgnoreCase("Y")) {
                classification.identifyAnimal();
                System.out.println("Do you have another animal to identity? (Y/N) >");
                userInput = scanner.next();
            } else if (userInput.equalsIgnoreCase("N")){
                run = false;
                classification.writeToFile();
            } else {
                System.out.println("Do you have another animal to identity? (Y/N) >");
                userInput = scanner.next();
            }

        }
    }
}