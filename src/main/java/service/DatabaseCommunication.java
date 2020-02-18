package service;

import java.sql.*;

public class DatabaseCommunication {
    static final String DB_URL = "jdbc:postgresql://localhost:5432/training_epam?currentSchema=javacollectionsmaintask";
    static final String USER = "postgres";
    static final String PASS = "admin";
    static Connection connectionPostgresql;
    static Statement statement;
    static ResultSet resultSet;

    public static Statement initConnection() {
        statement = null;
        try {
            connectionPostgresql = DriverManager.getConnection(DB_URL, USER, PASS);
            try {
                statement = connectionPostgresql.createStatement();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return statement;
    }

    public static ResultSet getDataFromDatabase(String query) {
        resultSet = null;
        try  {
            resultSet = initConnection().executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }

    public static void closeConnection() {
        try {
            resultSet.close();
            statement.close();
            connectionPostgresql.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void insertData(String query) {
        try {
            DatabaseCommunication.initConnection().execute(query);
            DatabaseCommunication.closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
