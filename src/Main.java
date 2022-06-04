import java.io.IOException;
import java.sql.*;

import Managers.*;
import Users.Auth;
import Users.AuthCheck;
import Users.Enter;
import Users.UserBDCreator;


public class Main {
    public static void main(String args[]) throws SQLException {
        Connector connector = new Connector();
        UserBDCreator userBDCreator= new UserBDCreator();
        Enter enter = new Enter();
        connector.createDbUserTable();
        userBDCreator.UserDBCreate();
        AuthCheck authCheck = new AuthCheck();
        authCheck.Check();
        CommandManager cm = new CommandManager();
        Commander c = new Commander(cm);
        cm.read();
        try {
            c.start();
        } catch (IOException e) {
            e.printStackTrace();


        }
    }
}








