package runningdiaryapp.ui;

import java.util.Scanner;
import runningdiaryapp.domain.AppService;

public class TUI {
    private AppService service;
    String COMMANDS = "1=Add a new route to the database\n2=Record new run\n3=Quit the program";

    public TUI() throws Exception {
        service = new AppService();
    }

    public void addNewRun() {
        System.out.println("Not yet implemented.");
    }

    public void addNewRoute(Scanner scanner) {
        System.out.println("What is the route called?");
        String name = scanner.nextLine();
        System.out.println("How long is the route in meters?");
        String length = scanner.nextLine();
        int meters = 0;
        while (meters == 0) {
            try {
                meters = Integer.parseInt(length);
            } catch (NumberFormatException e) {
                System.out.println("Invalid number.\n\nHow long is the route in meters?");
                length = scanner.nextLine();
            }
        }
        System.out.println(name + " (" + meters + " meters) (yes/no)?");
        String selection = scanner.nextLine();

        if (!selection.toUpperCase().equals("YES"))
            return;

        service.createRoute(name, meters);
        System.out.println("New route called " + name + " succesfully created!");
    }

    public void start() {
        String answer = "";
        Scanner scanner = new Scanner(System.in);
        System.out.println("Hello runner!\n");
        while (!answer.equals("3")) {
            System.out.println(COMMANDS + "\n\nYour choice?");
            answer = scanner.nextLine();
            if (answer.equals("1"))
                addNewRoute(scanner);
            else if (answer.equals("2"))
                addNewRun();
        }
        end(scanner);
    }

    public void end(Scanner scanner) {
        scanner.close();
        System.out.println("See you next time! Have fun running.");
    }

    public static void main(String[] args) throws Exception {
        TUI ui = new TUI();
        ui.start();
    }
}