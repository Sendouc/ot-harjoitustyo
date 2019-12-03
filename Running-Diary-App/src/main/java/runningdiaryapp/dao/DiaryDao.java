package runningdiaryapp.dao;

import java.util.List;

import runningdiaryapp.domain.Route;

public interface DiaryDao {

    List<Route> getRoutes() throws Exception;

    List<Route> getRoutesByName(String name) throws Exception;

    void addRoute(Route route) throws Exception;

    void closeConn() throws Exception;
}