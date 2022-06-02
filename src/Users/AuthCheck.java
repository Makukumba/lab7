package Users;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static Managers.Connector.getDBConnection;

public class AuthCheck {
    public void Check() {
        Auth auth = new Auth();
        Connection dbConnection = null;
        Statement statement = null;
        boolean isUserExists = false;
        while (isUserExists == false) {
            String selectTableSQL = "SELECT LOGIN, PASSWORD FROM USERS";
            try {
                dbConnection = getDBConnection();
                statement = dbConnection.createStatement();

                ResultSet rs = statement.executeQuery(selectTableSQL);
                if (rs.next()) {
                    isUserExists = true;
                }
                if (!isUserExists) {
                    System.out.println("Необходима авторизация");
                    auth.Authorise();
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }
}
