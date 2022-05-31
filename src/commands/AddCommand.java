package commands;

import Managers.CommandManager;

import java.io.IOException;
import java.sql.SQLException;

public class AddCommand extends AbstractCommand {
    CommandManager cm;

    public AddCommand(CommandManager cm) {
        super("add", "добавить элемент в коллекцию");
        this.cm = cm;
    }

    public void execute()  {
        try {
            cm.add();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}

