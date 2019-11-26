package runningdiaryapp.ui;

import java.util.List;
import java.util.Scanner;
import runningdiaryapp.domain.AppService;
import runningdiaryapp.domain.Route;

public class TUI {
    private AppService service;
    String commands = "1=Add a new route to the database\n2=View routes in database\n3=Record new run\n4=Quit the program";

    public TUI() throws Exception {
        service = new AppService();
    }

    public void addNewRun() {
        System.out.println("Not yet implemented.");
    }

    public void addNewRoute(Scanner s) throws Exception {
        System.out.println("What is the route called?");
        String name = s.nextLine();
        System.out.println("How long is the route in meters?");
        String length = s.nextLine();
        int meters = 0;
        while (meters == 0) {
            try {
                meters = Integer.parseInt(length);
            } catch (NumberFormatException e) {
                System.out.println("Invalid number.\n\nHow long is the route in meters?");
                length = s.nextLine();
            }
        }
        System.out.println(name + " (" + meters + " meters) (yes/no)?");
        String selection = s.nextLine();

        if (!selection.toUpperCase().equals("YES")) {
            return;
        }

        service.createRoute(name, meters);
        System.out.println("New route called " + name + " succesfully created!");
    }

    public void viewRoutes() throws Exception {
        List<Route> routes = service.getRoutes();
        System.out.println("Routes currently registered:\n");
        if (routes.isEmpty()) {
            System.out.println("No routes in the database currently. Add one!");
            return;
        }
        for (Route route : routes) {
            System.out.println("Name: " + route.getName() + " Length: " + route.getLength());
        }
    }

    public void start() throws Exception {
        String answer = "";
        Scanner scanner = new Scanner(System.in);
        System.out.println("Hello runner!\n");
        while (!answer.equals("4")) {
            System.out.println(commands + "\n\nYour choice?");
            answer = scanner.nextLine();
            if (answer.equals("1")) {
                addNewRoute(scanner);
            } else if (answer.equals("2")) {
                viewRoutes();
            } else if (answer.equals("3")) {
                addNewRun();
            }
        }
        end(scanner);
    }

    public void end(Scanner scanner) throws Exception {
        scanner.close();
        service.closeDbConnection();
        System.out.println("See you next time! Have fun running.");
    }

    public static void main(String[] args) throws Exception {
        TUI ui = new TUI();
        ui.start();
    }
}