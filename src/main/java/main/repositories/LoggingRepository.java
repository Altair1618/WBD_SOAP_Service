package main.repositories;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import main.models.Logging;

public class LoggingRepository extends Repository {
    public LoggingRepository() {
        super("logging");
    }

        public void addLogging(Logging data) {
            String query = "INSERT INTO " + tableName + " (description, IP, endpoint) VALUES (?, ?, ?)";

            try {
                PreparedStatement stmt = this.conn.prepareStatement(query);
                stmt.setString(1, data.getDescription());
                stmt.setString(2, data.getIP());
                stmt.setString(3, data.getEndpoint());
                stmt.executeUpdate();
                this.conn.commit();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    public List<Logging> getAllLoggings() throws SQLException {
        String query = "SELECT * FROM " + tableName;
        
        try {
            PreparedStatement stmt = this.conn.prepareStatement(query);
            ResultSet res = stmt.executeQuery();
            
            if (!res.isBeforeFirst()) return null;

            List<Logging> loggings = new ArrayList<Logging>();

            while (res.next()) {
                Logging logging = new Logging(res.getInt("id"), res.getString("description"), res.getString("IP"),
                        res.getString("endpoint"), res.getTimestamp("requested_at"));
                loggings.add(logging);
            }

            return loggings;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
