package main.repositories;

import java.sql.Connection;

import main.utils.db;

public class Repository {
    protected Connection conn;
    protected String tableName;

    public Repository(String tableName) {
        this.tableName = tableName;
        this.conn = db.getConnection();
    }
}
