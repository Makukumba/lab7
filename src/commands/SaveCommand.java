package commands;

import Managers.CommandManager;

import java.io.IOException;
import java.sql.SQLException;

public class SaveCommand extends AbstractCommand{
    CommandManager cm;
    public SaveCommand(CommandManager cm) {
        super("save", "Cохранить коллекцию в базу данных");
        this.cm = cm;
    }

    public void execute() throws IOException {
        try {
            cm.save();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
