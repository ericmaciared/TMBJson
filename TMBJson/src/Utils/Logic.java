package Utils;

import DataModel.DataModel;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import java.io.FileNotFoundException;
import java.io.FileReader;

public class Logic {
    final private String PATH = "TMBJson/data/localitzacions.json";

    Menu menu = new Menu();
    UserManagement userManagement = new UserManagement();
    private DataModel data = new DataModel();

    /**
     * Runs main loop with options shwon graphically in the Menu class
     * @see Menu
     * @see UserManagement
     */
    public void run(){
        do {
            do {
                menu.printMenu();
                menu.askForOption();
            } while (!menu.validOption());
            if (menu.getOption() == 1){
                do{
                    do {
                        userManagement.printMenu();
                        userManagement.askForOption();
                    } while (!userManagement.validOption());

                    doOption(userManagement.getOption());

                }while(!userManagement.exit());
            }
            else{
                doOption(menu.getOption());
            }

        } while(!menu.exit());
    }

    /**
     * The function imports a list of locations in the json file in the data folder to the datamodel class.
     * @throws FileNotFoundException
     */
    public void loadLocations() throws FileNotFoundException {
        Gson gson = new Gson();
        JsonReader reader;

        reader = new JsonReader(new FileReader(PATH));
        data = gson.fromJson(reader, DataModel.class);

        //System.out.println(data.toString());      //test for importation correctly
    }

    /**
     * Switches option to display/output
     * @param option Integer representing the option in the menu that the user chooses from main menu
     */
    public void doOption(int option){
        switch (option){
            case 2:
                System.out.println("option 2");
                break;
            case 3:
                System.out.println("option 3");
                break;
            case 4:
                System.out.println("option 4");
                break;
        }
    }

    /**
     * Switches option to display/output
     * @param option String representing option from user management menu that user chooses
     */
    public void doOption(String option){
        switch (option){
            case "a":
                System.out.println("a option");
                break;
            case "b":
                System.out.println("b option");
                break;
            case "c":
                System.out.println("c option");
                break;
            case "d":
                System.out.println("d option");
                break;
            case "e":
                System.out.println("e option");
                break;
        }
    }

    public void login(){
        data.setUser(menu.login());
        //System.out.println(data.getUser());
    }
}
