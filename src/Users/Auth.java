package Users;

import Drago.DragonChecker;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import static Managers.Connector.getDBConnection;

public class Auth {
    public void Authorise() throws SQLException {
        int a = 1;
        while (a == 1) {
            Connection dbConnection = null;
            Statement statement = null;
            System.out.print("Введите логин: ");
            Scanner scanner = new Scanner(System.in);
            String login = scanner.nextLine();
            System.out.print("Введите пароль: ");
            Scanner scanner1 = new Scanner(System.in);
            String password = scanner1.nextLine();
            Hash hash= new Hash();

            String insertTableSQL = "INSERT INTO USERS"
                    + "(LOGIN, PASSWORD) " + "VALUES"
                    + "('" + login + "', '" + hash.hashPassword(password) + "')";
            try {
                dbConnection = getDBConnection();
                statement = dbConnection.createStatement();
                // выполнить SQL запрос
                statement.executeUpdate(insertTableSQL);
                System.out.println("Table \"users\" is updated!");
                a = 0;
            } catch (SQLException e) {
                System.out.println(e.getMessage());
                System.out.println("Повторите попытку ");
                a = 1;
            } finally {
                if (statement != null) {
                    statement.close();
                }
                if (dbConnection != null) {
                    dbConnection.close();
                }
            }
        }
    }
}
