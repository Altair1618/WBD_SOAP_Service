package main.repositories;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import main.models.Logging;

public class LoggingRepository extends Repository {
    private static final String tableName = "logging";

    private List<Logging> readGetLoggingsResult(ResultSet res) throws SQLException {
        if (!res.isBeforeFirst()) return null;

        List<Logging> loggings = new ArrayList<>();
        while (res.next()) {
            Logging logging = new Logging(
                res.getInt("id"),
                res.getString("description"),
                res.getString("IP"),
                res.getString("endpoint"),
                res.getTimestamp("requested_at")
            );
            loggings.add(logging);
        }

        return loggings;
    }

    public void addLogging(String description, String IP, String endpoint) throws SQLException {
        String query = String.format(
            "INSERT INTO %s (description, IP, endpoint) VALUES (?, ?, ?)",
            tableName
        );

        try {
            PreparedStatement stmt = this.conn.prepareStatement(query);
            stmt.setString(1, description);
            stmt.setString(2, IP);
            stmt.setString(3, endpoint);
            stmt.executeUpdate();
            this.conn.commit();
        } catch (SQLException e) {
            this.conn.rollback();
            throw e;
        }
    }

    public List<Logging> getAllLoggings() throws SQLException {
        String query = String.format("SELECT * FROM %s", tableName);

        PreparedStatement stmt = this.conn.prepareStatement(query);
        ResultSet res = stmt.executeQuery();

        return this.readGetLoggingsResult(res);
    }
}
