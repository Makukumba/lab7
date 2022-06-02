package commands;

import Users.Auth;

import java.io.IOException;
import java.sql.SQLException;

public class Authorization extends AbstractCommand{

    public Authorization() {
        super("Authorization", "Авторизация пользователя");
    }

    public void execute() throws IOException {
        Auth auth =new Auth();
        try {
            auth.Authorise();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
