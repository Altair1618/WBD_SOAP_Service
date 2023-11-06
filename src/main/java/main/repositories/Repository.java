package main.repositories;

import java.sql.Connection;

import main.utils.Database;

public class Repository {
    protected Connection conn;

    public Repository() {
        this.conn = Database.getConnection();
    }
}
