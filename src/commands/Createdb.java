package commands;

import Managers.Connector;

import java.io.IOException;
import java.sql.SQLException;

public class Createdb extends AbstractCommand{


    public Createdb() {
        super("createdb", "создаёт таблицу");
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
