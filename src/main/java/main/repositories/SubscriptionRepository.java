package main.repositories;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import main.models.Subscription;
import main.models.Subscription.Status;

public class SubscriptionRepository extends Repository {
    private static final String tableName = "subscription";
    private static final Long subscriptionDuration = 1000L * 60 * 60 * 24 * 30;  // 30 days
    // private static final Long subscriptionDuration = 1000L * 60;  // 60 seconds for testing

    private List<Subscription> readGetSubscriptionsResult(ResultSet res) throws SQLException {
        if (!res.isBeforeFirst()) return null;

        List<Subscription> subscriptions = new ArrayList<>();
        while (res.next()) {
            Subscription subscription = new Subscription(
                res.getInt("id"),
                res.getInt("user_id"),
                Subscription.Status.valueOf(res.getString("status")),
                new Date(res.getTimestamp("expired_at").getTime()),
                new Date(res.getTimestamp("created_at").getTime())
            );
            subscriptions.add(subscription);
        }

        return subscriptions;
    }

    public void createSubscriptionRequest(Integer user_id) throws SQLException {
        String query = String.format(
            "INSERT INTO %s (user_id) VALUES (?)",
            tableName
        );

        try {
            PreparedStatement stmt = this.conn.prepareStatement(query);
            stmt.setInt(1, user_id);
            stmt.executeUpdate();
            this.conn.commit();
        } catch (SQLException e) {
            this.conn.rollback();
            throw e;
        }
    }

    public List<Subscription> getAllSubscriptionRequests() throws SQLException {
        String query = String.format(
            "SELECT * FROM %s WHERE status = 'PENDING'",
            tableName
        );

        PreparedStatement stmt = this.conn.prepareStatement(query);
        ResultSet res = stmt.executeQuery();

        return this.readGetSubscriptionsResult(res);
    }

    public List<Subscription> getSubscriptionByUserId(Integer userId) throws SQLException {
        String query = String.format(
            "SELECT * FROM %s WHERE user_id = ? ORDER BY created_at DESC LIMIT 1",
            tableName
        );

        PreparedStatement stmt = this.conn.prepareStatement(query);
        stmt.setInt(1, userId);
        ResultSet res = stmt.executeQuery();

        return this.readGetSubscriptionsResult(res);
    }

    public void updateSubscriptionStatus(Integer id, Status newStatus) throws SQLException {
        String query = String.format(
            "UPDATE %s SET status = ?, expired_at = ? WHERE user_id = ?",
            tableName
        );

        Timestamp expiredAt;
        if (newStatus == Status.ACCEPTED) {
            expiredAt = new Timestamp(new Date().getTime() + subscriptionDuration);
        } else {
            expiredAt = null;
        }

        try {
            PreparedStatement stmt = this.conn.prepareStatement(query);
            stmt.setString(1, newStatus.toString());
            stmt.setTimestamp(2, expiredAt);
            stmt.setInt(3, id);
            stmt.executeUpdate();
            this.conn.commit();
        } catch (SQLException e) {
            this.conn.rollback();
            throw e;
        }
    }
}
