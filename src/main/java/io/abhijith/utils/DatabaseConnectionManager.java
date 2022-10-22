package io.abhijith.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseConnectionManager {

    private final String url;
    private final Properties properties;

    public DatabaseConnectionManager(String host, String databaseName, Properties properties) {
        this.url = "jdbc:postgresql://" + host + "/" + databaseName;
        this.properties = properties;
    }

    /**
     * Create a connection for Database
     * @return Connection
     * @throws SQLException
     */
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(this.url, this.properties);
    }

}
