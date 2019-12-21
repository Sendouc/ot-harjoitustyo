package runningdiaryapp.dao;

import java.util.List;

import runningdiaryapp.domain.Route;
import runningdiaryapp.domain.Run;

public interface DiaryDao {

    List<Route> getRoutes() throws Exception;

    List<Route> getRoutesByName(String name) throws Exception;

    List<Run> getRuns() throws Exception;

    void addRoute(Route route) throws Exception;

    void addRun(Run run) throws Exception;

    int getDistanceRan() throws Exception;

    void closeConn() throws Exception;
}