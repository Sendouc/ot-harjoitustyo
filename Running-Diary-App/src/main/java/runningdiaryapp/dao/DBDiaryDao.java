package runningdiaryapp.dao;

import java.util.ArrayList;
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
            Class.forName("org.sqlite.JDBC");
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
    }

    public DBDiaryDao(String db) throws Exception {
        routes = new ArrayList<>();
        url = "jdbc:sqlite:" + db;

        createTable("route (id text PRIMARY KEY, name text NOT NULL, length integer NOT NULL)");

        String getRoutes = "SELECT id, name, length FROM route;";
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
    public void addRoute(Route route) throws Exception {
        route.setId(generateId());
        routes.add(route);
        saveRoute(route);
    }
}