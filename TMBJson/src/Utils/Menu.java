package Utils;

import DataModel.User;

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
    private boolean mainMenu;
    private Scanner scanner;

    public Menu() {
        scanner = new Scanner(System.in);
        option = -1;
        mainMenu = true;
    }

    public void printMenu(){
        System.out.println(option1);
        System.out.println(option2);
        System.out.println(option3);
        System.out.println(option4);
        System.out.println(exit);
    }

    public void askForOption(){
        String aux;

        System.out.println("");
        System.out.println(ask);
        aux = scanner.nextLine();
        try{
            option = Integer.parseInt(aux.toLowerCase());
        }
        catch (NumberFormatException e){
            option = -1;
        }
    }

    public boolean validOption() {
        if (!(option <= MAX && option >= MIN)){
            System.out.println("\nERROR: Invalid option.\n");
        }
        return option <= MAX && option >= MIN;
    }

    public boolean exit() {
        return option == MAX;
    }

    public User login() {
        String username;
        String email;
        int birthYear;

        System.out.println("Welcome to the TMBJson application! Please enter the requested information.\n");
        System.out.println("Username: ");
        username = scanner.nextLine();
        System.out.println("\nE-mail: ");
        email = scanner.nextLine();
        System.out.println("\nBirth Year: ");
        birthYear = scanner.nextInt();
        System.out.println("\nThe information has been successfully registered!");

        return new User(username, email, birthYear);
    }

}
