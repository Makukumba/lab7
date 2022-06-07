package commands;

import Managers.Connector;

import java.io.IOException;
import java.sql.SQLException;

public class Create_table extends AbstractCommand{


    public Create_table() {
        super("create_table", "создаёт таблицу Драконов");
    }

    public void execute() throws IOException {
        Connector c = new Connector();
        try {
            c.createDbUserTable();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
