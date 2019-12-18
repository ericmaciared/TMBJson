import DataModel.User;
import Utils.Logic;
import Utils.Menu;

import java.io.FileNotFoundException;

/**
 * Main class.
 *
 * @author Guillermo Sabaté - guille.sabate@students.salle.url.edu
 * @author Eric Macià - eric.macia@students.salle.url.edu
 * @version 1.0
 */

public class Main {

    public static void main(String[] args) {
        User user;
        Logic logic = new Logic();
        Menu menu = new Menu();

        //First we import the localizations
        try{
            logic.loadLocations();
        } catch (FileNotFoundException e) {
            System.out.println("\nERROR: There was a problem when reading the Locations file.");
            return;
        }

        //user = menu.login();

        do {
            do {
                menu.printMenu();
                menu.askForOption();
            } while (!menu.validOption());

        } while(!menu.exit());
    }
}
