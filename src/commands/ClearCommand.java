package commands;

import Managers.CommandManager;
import Users.Enter;

import java.io.IOException;
import java.sql.SQLException;

public class ClearCommand extends AbstractCommand {
    CommandManager cm;
    public ClearCommand(CommandManager cm) {
        super("clear", "очистить информацию о коллекции");
        this.cm = cm;
    }

    public void execute() throws IOException {

        try {
            cm.clear();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}


