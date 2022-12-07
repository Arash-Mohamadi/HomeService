package menu.main_menu;

import java.util.Objects;
import java.util.Scanner;

public class MainMenu {

    private final Scanner scanner = new Scanner(System.in);

    public void showMainMenu() {

        while (true) {
            System.out.println("... Welcome to HomeService ... ");
            System.out.println("... Please enter desired item ! ... ");
            System.out.println("1- Log in");
            System.out.println("2- Sign up");
            System.out.println("3- Exit");

            String command = scanner.next();
            if (Objects.equals(command, "1")) {
                // invoke login method
            } else if (Objects.equals(command, "2")) {
                // invoke sign up method
            } else if (Objects.equals(command, "3")) {
                System.exit(0);
            }else {
                System.out.println("... Number is WRONG ...");
            }


        }
    }
}
