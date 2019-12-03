package runningdiaryapp.dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import runningdiaryapp.domain.Route;

public class DBDiaryDao implements DiaryDao {
    public List<Route> routes;
    String url;
    Connection conn;

    private Connection getConn(String url) throws Exception {
        if (conn == null) {
            conn = DriverManager.getConnection(url);
        }

        return conn;
    }

    public void closeConn() throws Exception {
        conn.close();
    }

    public void createTable(String queryPart) throws Exception {
        String query = "CREATE TABLE IF NOT EXISTS " + queryPart + ";";
        try (Statement stmt = getConn(url).createStatement()) {
            stmt.execute(query);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        routes.clear();
    }

    public DBDiaryDao(String db) throws Exception {
        routes = new ArrayList<>();
        url = "jdbc:h2:" + db;

        createTable("route (id varchar PRIMARY KEY, name varchar NOT NULL, length int NOT NULL)");

        String getRoutes = "SELECT id, name, length FROM route ORDER BY length DESC;";
        try (Statement stmt = getConn(url).createStatement(); ResultSet rs = stmt.executeQuery(getRoutes);) {
            while (rs.next()) {
                routes.add(new Route(rs.getString("id"), rs.getString("name"), rs.getInt("length")));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private String generateId() {
        return UUID.randomUUID().toString();
    }

    private void saveRoute(Route route) throws Exception {
        String query = "INSERT INTO route (id, name, length) VALUES (?, ?, ?);";
        try (Statement stmt = getConn(url).createStatement()) {
            PreparedStatement prepared = conn.prepareStatement(query);
            prepared.setString(1, route.getId());
            prepared.setString(2, route.getName());
            prepared.setInt(3, route.getLength());
            prepared.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public List<Route> getRoutes() throws Exception {
        return routes;
    }

    @Override
    public List<Route> getRoutesByName(String name) throws Exception {
        List<Route> toReturn = new ArrayList<>();
        for (Route route : routes) {
            if (route.getName().toLowerCase().contains(name.toLowerCase())) {
                toReturn.add(route);
            }
        }

        return toReturn;
    }

    @Override
    public void addRoute(Route route) throws Exception {
        route.setId(generateId());
        routes.add(route);
        Collections.sort(routes);
        saveRoute(route);
    }
}