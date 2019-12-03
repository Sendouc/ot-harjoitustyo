package runningdiaryapp.ui;

import java.util.List;
import java.util.Scanner;
import runningdiaryapp.domain.AppService;
import runningdiaryapp.domain.Route;

public class TUI {
    private AppService service;
    String commands = "-----\n1=Add a new route to the database\n2=View routes in database\n"
            + "3=Search for a route by name\n4=Record new run\n5=Quit the program";

    public TUI() throws Exception {
        service = new AppService();
    }

    public void addNewRun() throws Exception {
        List<Route> routes = service.getRoutes();
        if (routes.isEmpty()) {
            System.out.println("No routes in the database currently. Add one before adding a run!");
            return;
        }

        for (int i = 0; i < routes.size(); i++) {
            Route route = routes.get(i);
            System.out.println("[" + (i + 1) + "] " + route.getName() + "(" + route.getLength() + " meters)");
        }

        System.out.println("Which route you ran? (-1 to quit)");

        // TODO: Rest of the method
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

    public void searchRouteByName(Scanner s) throws Exception {
        System.out.println("What route do you want to search for?");
        String name = s.nextLine();
        List<Route> routes = service.getRoutesByName(name);

        if (routes.isEmpty()) {
            System.out.println("No routes found matching the search criteria.");
        } else {
            System.out.println("Following routes match the name given:");
            for (Route route : routes) {
                System.out.println("Name: " + route.getName() + " Length: " + route.getLength());
            }
        }
    }

    public void start() throws Exception {
        String answer = "";
        Scanner scanner = new Scanner(System.in);
        System.out.println("Hello runner!\n");
        while (!answer.equals("5")) {
            System.out.println(commands + "\n\nYour choice?");
            answer = scanner.nextLine();
            if (answer.equals("1")) {
                addNewRoute(scanner);
            } else if (answer.equals("2")) {
                viewRoutes();
            } else if (answer.equals("3")) {
                searchRouteByName(scanner);
            } else if (answer.equals("4")) {
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