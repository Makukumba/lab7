package Managers;

import Drago.DragonChecker;

import java.sql.*;

public class Connector<pubilc> {
    public static Connection getDBConnection() {
        Connection dbConnection = null;
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        try {
            dbConnection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/test", "postgres", "admin");
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
                + "ID INTEGER UNIQUE, "
                + "NAME CHAR(10) NOT NULL, "
                + "X INTEGER NOT NULL, "
                + "Y INTEGER NOT NULL, "
                + "DATE DATE NOT NULL, "
                + "DESCRIPTION CHAR(10) NOT NULL, "
                + "AGE INTEGER NOT NULL, "
                + "WEIGHT INTEGER NOT NULL, "
                + "DRAGONCHARACTER CHAR(10) NOT NULL, "
                + "DRAGONHEAD DOUBLE PRECISION NOT NULL"
                + ")";

        try {
            dbConnection = getDBConnection();
            statement = dbConnection.createStatement();
            // выполнить SQL запрос
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

    public void Insert() throws SQLException {
        Connection dbConnection = null;
        Statement statement = null;
        DragonChecker d = new DragonChecker();
        String aGE = Integer.toString(d.AGE());
        String  insertTableSQL = "INSERT INTO DRAGON"
                + "(ID, NAME, DESCRIPTION, AGE) " + "VALUES"
                + "(5, 'Makumba','lox'," +aGE+ ")";
        try {
            dbConnection = getDBConnection();
            statement = dbConnection.createStatement();
            // выполнить SQL запрос
            statement.executeUpdate(insertTableSQL);
            System.out.println("Table \"dragon\" is updated!");
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

    public void Show() {
        String selectTableSQL = "SELECT ID, NAME, DESCRIPTION, AGE from DRAGON";
        Connection dbConnection = null;
        Statement statement = null;
        try {
            dbConnection = getDBConnection();
            statement = dbConnection.createStatement();

            // выбираем данные с БД
            ResultSet rs = statement.executeQuery(selectTableSQL);

            // И если что то было получено то цикл while сработает
            while (rs.next()) {
                String id = rs.getString("ID");
                String name = rs.getString("NAME");
                String description = rs.getString("DESCRIPTION");
                String age = rs.getString("AGE");
                System.out.println("id : " + id);
                System.out.println("name : " + name);
                System.out.println("description : " + description);
                System.out.println("age : " + age);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void Delete() {
        String deleteTableSQL = "DELETE FROM dragon WHERE ID = 2";
        Connection dbConnection = null;
        Statement statement = null;
        try {
            dbConnection = getDBConnection();
            statement = dbConnection.createStatement();
            // выполняем запрос delete SQL
            statement.execute(deleteTableSQL);
            System.out.println("Record is deleted from DRAGON table!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}