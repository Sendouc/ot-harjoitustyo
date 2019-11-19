package runningdiaryapp.ui;

import java.util.Scanner;

public class TUI {
    static String COMMANDS = "1=Add a new route to the database\n2=Record new run\n3=Quit the program";

    private void addNewRun() {
        System.out.println("Not yet implemented.")
    }

    private void addNewRoute() {
        System.out.println("Not yet implemented.")
    }

    private void start() {
        int answer = 0;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Hello runner!\n" + COMMANDS);
        while (answer != 3) {
            System.out.print("Your choice=");
            int choice = scanner.nextInt();
            if (choice == 1)
                addNewRoute();
            else if (choice == 2)
                addNewRun();
        }
        end(scanner);
    }

    private void end(Scanner scanner) {
        scanner.close();
        System.out.println("See you next time! Have fun running.");
    }

    public static void main(String[] args) {
        TUI ui = new TUI();
        ui.start();
    }
}