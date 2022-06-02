package commands;

import Managers.CommandManager;
import Users.Enter;

import java.io.IOException;
import java.sql.SQLException;

public class ClearCommand extends AbstractCommand {
    CommandManager cm;
    Enter enter = new Enter();
    public ClearCommand(CommandManager cm) {
        super("clear", "очистить информацию о коллекции");
        this.cm = cm;
    }

    public void execute() throws IOException {
        try {
            enter.Entering();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        cm.clear();

    }
}


