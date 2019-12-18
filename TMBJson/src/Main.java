import Utils.Logic;
import Utils.Menu;

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
        Menu menu = new Menu();

        do {
            menu.printMainMenu();
            do {
                menu.askForOption();
            } while (!menu.validOption());
        } while(!menu.exit());

    }
}
