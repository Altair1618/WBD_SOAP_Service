package main.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    private static Connection conn = null;

    public static Connection getConnection() {
        if (conn == null) {
            try {
                String url = "jdbc:mysql://host.docker.internal:3308/wbd_soap";
                String user = "root";
                String password = System.getenv("MYSQL_ROOT_PASSWORD");

                conn = DriverManager.getConnection(url, user, password);
                conn.setAutoCommit(false);

                System.out.println("Successfully connected to database");
            } catch (SQLException e) {
                System.out.println("Failed to connect to database");
                System.out.println(e.getMessage());
                System.exit(1);
            }
        }

        return conn;
    }
}
