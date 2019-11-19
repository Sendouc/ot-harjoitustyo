package runningdiaryapp.domain;

import runningdiaryapp.dao.DBRouteDao;
import runningdiaryapp.dao.RouteDao;

public class AppService {
    private RouteDao routeDao;

    public AppService() throws Exception {
        routeDao = new DBRouteDao();
    }

    /**
     * Uuden lenkkireitin lisääminen
     *
     * @param name   reitin nimi
     * @param length lenkin pituus metreissä
     */

    public boolean createRoute(String name, int length) {
        Route route = new Route(name, length);
        // TODO: Add to database
        return true;
    }
}