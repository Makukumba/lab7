package Users;

import java.sql.*;
import java.util.Scanner;

import static Managers.Connector.getDBConnection;

public class Enter {
    private String login;
    private String password;

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public void Entering() throws SQLException {
        boolean isUserExists = false;
        int a = 0;
        while (isUserExists == false) {
            Connection dbConnection = null;
            Statement statement = null;
            System.out.print("Введите логин: ");
            Scanner scanner = new Scanner(System.in);
            login = scanner.nextLine();
            System.out.print("Введите пароль: ");
            Scanner scanner1 = new Scanner(System.in);
            password = scanner1.nextLine();
            Hash hash = new Hash();
            String selectTableSQL = "SELECT from USERS WHERE LOGIN = '" + login + "' AND PASSWORD = '" + hash.hashPassword(password) + "'";
            try {
                dbConnection = getDBConnection();
                statement = dbConnection.createStatement();


                ResultSet rs = statement.executeQuery(selectTableSQL);
                if (rs.next()) {
                    isUserExists = true;
                }
                if (isUserExists) {
                    System.out.println("Выполнен вход под логином " + login);
                } else {
                    System.out.println("Неправильный логин или пароль!");
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }
}


