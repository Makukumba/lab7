package Users;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import static Managers.Connector.getDBConnection;
public class UserBDCreator {
    public static void UserDBCreate() throws SQLException{
        Connection dbConnection = null;
        Statement statement = null;

        String createTableSQL = "CREATE TABLE USERS("
                + "LOGIN CHAR(100) UNIQUE, "
                + "PASSWORD CHAR(10000) NOT NULL"
                + ")";

        try {
            dbConnection = getDBConnection();
            statement = dbConnection.createStatement();
            // выполнить SQL запрос
            statement.execute(createTableSQL);
            System.out.println("Table \"users\" is created!");
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

}
