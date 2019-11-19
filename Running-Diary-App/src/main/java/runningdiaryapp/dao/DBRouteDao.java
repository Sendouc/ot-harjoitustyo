package runningdiaryapp.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import runningdiaryapp.domain.Route;

public class DBRouteDao implements RouteDao {
    public List<Route> routes;

    public DBRouteDao() throws Exception {
        routes = new ArrayList<>();
        // TODO: get all routes from db and add them to routes
    }

    private String generateId() {
        return UUID.randomUUID().toString();
    }

    @Override
    public Route create(Route route) throws Exception {
        route.setId(generateId());
        routes.add(route);
        // TODO: Save the route to database
        return route;
    }
}