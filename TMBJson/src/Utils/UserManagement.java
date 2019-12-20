package Utils;

import java.util.Scanner;

public class UserManagement {
    final private String optionA = "a)My locations";
    final private String optionB = "b)Location History";
    final private String optionC = "c)My routes";
    final private String optionD = "d)Favorite stops and stations";
    final private String optionE = "e)Stations inaugurated in birth year";
    final private String exit = "f)Back to the principal menu";
    final private String ask = "Select an option: ";

    private String option;
    private Scanner scanner;

    public UserManagement() {
        scanner = new Scanner(System.in);
    }

    public void printMenu(){
        System.out.println(optionA);
        System.out.println(optionB);
        System.out.println(optionC);
        System.out.println(optionD);
        System.out.println(optionE);
        System.out.println(exit);
    }

    public void askForOption(){
        String aux;

        System.out.println("");
        System.out.println(ask);
        option = scanner.nextLine();
    }

    public boolean validOption() {
        if(!(option.equals("a") || option.equals("b") || option.equals("c") || option.equals("d") ||
                option.equals("e") || option.equals("f"))){
            System.err.println("ERROR: Invalid option.");
        }
        return option.equals("a") || option.equals("b") || option.equals("c") || option.equals("d") ||
                option.equals("e") || option.equals("f");
    }

    public boolean exit() {
        return option.equals("f");
    }

    public String getOption() {
        return this.option;
    }
}
