package Utils;

import java.util.Scanner;

public class Menu {
    final private String option1 = "1. User Management";
    final private String option2 = "2. Search Locations";
    final private String option3 = "3. Plan a Route";
    final private String option4 = "4. Bus Wait Time";
    final private String exit = "5. Exit";
    final private String ask = "Select an option: ";
    final private int MAX = 5;
    final private int MIN = 1;

    private int option;
    private Scanner scanner;

    public Menu() {
        scanner = new Scanner(System.in);
        option = -1;
    }

    public void printMainMenu(){
        System.out.println(option1);
        System.out.println(option2);
        System.out.println(option3);
        System.out.println(option4);
        System.out.println(exit);
    }

    public void askForOption(){
        System.out.println("");
        System.out.println(ask);
        option = scanner.nextInt();
    }

    public boolean validOption() {
        return option <= MAX && option >= MIN;
    }

    public boolean exit() {
        return option == MAX;
    }
}
