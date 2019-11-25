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
    Connection conn;
    String url;

    public DBDiaryDao() throws Exception {
        routes = new ArrayList<>();
        url = "jdbc:sqlite:test.db";
        String query = "CREATE TABLE IF NOT EXISTS route (id text PRIMARY KEY, name text NOT NULL, length integer NOT NULL);";

        try {
            conn = DriverManager.getConnection(url);
            System.out.println("Connection to SQLite has been established.");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        try {
            Statement stmt = conn.createStatement();
            stmt.execute(query);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        String getRoutes = "SELECT id, name, length FROM route;";
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(getRoutes);
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

    private void saveRoute(Route route) {
        String query = "INSERT INTO route (id, name, length) VALUES (?, ?, ?);";
        try (Connection conn = DriverManager.getConnection(url); Statement stmt = conn.createStatement()) {
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

    @Override
    public void close() throws Exception {
        try {
            conn.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}