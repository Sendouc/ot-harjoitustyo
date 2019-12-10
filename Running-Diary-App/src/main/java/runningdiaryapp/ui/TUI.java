package runningdiaryapp.ui;

import java.util.List;
import java.util.Scanner;
import runningdiaryapp.domain.AppService;
import runningdiaryapp.domain.Route;
import runningdiaryapp.domain.Run;

/**
 * Tekstikäyttöliittymästä vastaava luokka
 */

public class TUI {
    private AppService service;
    final String commands = "-----\n1 = Add a new route to the database\n2 = View routes in database\n"
            + "3 = Search for a route by name\n4 = Record new run\n5 = View runs\n6 = Quit the program";

    public TUI() throws Exception {
        service = new AppService();
    }

    private void addNewRun(Scanner s) throws Exception {
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

        String routeNumberInput = s.nextLine();
        int routeNumber = 0;
        while (routeNumber == 0) {
            try {
                routeNumber = Integer.parseInt(routeNumberInput);
            } catch (NumberFormatException e) {
                System.out.println("Invalid number.\n");
                routeNumberInput = s.nextLine();
            }
        }

        if (routeNumber < 1) {
            return;
        }

        if (routeNumber > routes.size()) {
            System.out.println("Invalid route number. Only " + routes.size() + " routes available.");
            return;
        }

        service.createRun(routes.get(routeNumber - 1).getLength());
        System.out.println("New run successfully created!");
    }

    private void addNewRoute(Scanner s) throws Exception {
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
        System.out.println("New route called " + name + " successfully created!");
    }

    private void viewRoutes() throws Exception {
        List<Route> routes = service.getRoutes();
        System.out.println("Routes currently registered:\n");
        if (routes.isEmpty()) {
            System.out.println("No routes in the database currently. Add one!");
            return;
        }
        for (Route route : routes) {
            System.out.println("Name: " + route.getName() + " Length: " + route.getLength() + " meters");
        }
    }

    private void viewRuns() throws Exception {
        List<Run> runs = service.getRuns();
        if (runs.isEmpty()) {
            System.out.println("No runs in the database currently. Add one!");
            return;
        }
        for (Run run : runs) {
            System.out.println("Date: " + run.getDate() + " Length: " + run.getLength() + " meters");
        }
    }

    private void searchRouteByName(Scanner s) throws Exception {
        System.out.println("What route do you want to search for?");
        String name = s.nextLine();
        List<Route> routes = service.getRoutesByName(name);

        if (routes.isEmpty()) {
            System.out.println("No routes found matching the search criteria.");
        } else {
            System.out.println("Following routes match the name given:");
            for (Route route : routes) {
                System.out.println("Name: " + route.getName() + " Length: " + route.getLength() + " meters");
            }
        }
    }

    /**
     * Tekstikäyttöliittymän aloittava metodi
     * 
     * @throws Exception
     */
    public void start() throws Exception {
        String answer = "";
        Scanner scanner = new Scanner(System.in);
        System.out.println("Hello runner!\n");
        while (!answer.equals("6")) {
            System.out.println(commands + "\n\nYour choice?");
            answer = scanner.nextLine();
            if (answer.equals("1")) {
                addNewRoute(scanner);
            } else if (answer.equals("2")) {
                viewRoutes();
            } else if (answer.equals("3")) {
                searchRouteByName(scanner);
            } else if (answer.equals("4")) {
                addNewRun(scanner);
            } else if (answer.equals("5")) {
                viewRuns();
            }
        }
        end(scanner);
    }

    /**
     * Tekstikäyttöliittymästä poistuttaessa suoritettava metodi
     * 
     * @param scanner
     * @throws Exception
     */
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