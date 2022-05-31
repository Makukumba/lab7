package commands;

import Managers.CommandManager;

import java.io.IOException;
import java.sql.SQLException;

public class UpdateCommand extends AbstractCommand {
    CommandManager cm;

    public UpdateCommand(CommandManager cm) {
        super("update_by_id", "обновить значение элемента коллекции, id которого равен заданному");
        this.cm = cm;
    }


    public void execute() throws IOException {
        try {
            cm.update();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}