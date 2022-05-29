import java.io.IOException;
import java.sql.*;

import Managers.*;


public class Main {
    public static void main(String args[]) throws SQLException {
        Connector connector = new Connector();
        //connector.getDBConnection();
        //connector.createDbUserTable();
        connector.Insert();
        connector.Show();
         //connector.Delete();

       /* CommandManager cm = new CommandManager();
        Commander c = new Commander(cm);
        try {
            c.start();
        } catch (IOException e) {
            e.printStackTrace();
*/


    }
}








