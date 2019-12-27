import Utils.Logic;

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
        Logic logic = new Logic();

        //First we import the localizations
        try{
            logic.loadLocations();
        } catch (FileNotFoundException e) {
            System.err.println("\nERROR: There was a problem when reading the Locations file.");
            return;
        }

        logic.login();  //Set login user to active user in Data Model

        //Main logic-graphic loop
        logic.run();
    }
}