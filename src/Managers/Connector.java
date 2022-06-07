package Managers;

import Drago.DragonChecker;

import java.sql.*;

public class Connector<c> {
    public static Connection getDBConnection() {
        Connection dbConnection = null;
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        try {
            dbConnection = DriverManager.getConnection("jdbc:postgresql://pg:5432/studs", "s335035", "qgl703");
            return dbConnection;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return dbConnection;
    }

    public static void createDbUserTable() throws SQLException {
        Connection dbConnection = null;
        Statement statement = null;

        String createTableSQL = "CREATE TABLE DRAGON("
                + "LOGIN CHAR(100) NOT NULL, "
                + "ID INTEGER UNIQUE, "
                + "NAME CHAR(100) NOT NULL, "
                + "X INTEGER NOT NULL, "
                + "Y INTEGER NOT NULL, "
                + "DATE DATE NOT NULL, "
                + "DESCRIPTION CHAR(100) NOT NULL, "
                + "AGE INTEGER NOT NULL, "
                + "WEIGHT INTEGER NOT NULL, "
                + "DRAGONCHARACTER CHAR(100) NOT NULL, "
                + "DRAGONHEAD DOUBLE PRECISION NOT NULL"
                + ")";

        try {
            dbConnection = getDBConnection();
            statement = dbConnection.createStatement();
            statement.execute(createTableSQL);
            System.out.println("Table \"dragon\" is created!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            if (statement != null) {
                statement.close();
            }
            if (dbConnection != null) {
                dbConnection.close();
            }
        }

    }

    public void Delete() {
        String deleteTableSQL = "DELETE FROM DRAGON ";
        Connection dbConnection = null;
        Statement statement = null;
        try {
            dbConnection = getDBConnection();
            statement = dbConnection.createStatement();
            statement.execute(deleteTableSQL);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}