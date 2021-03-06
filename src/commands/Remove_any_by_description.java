package commands;

import Managers.CommandManager;

import java.io.IOException;
import java.sql.SQLException;

public class Remove_any_by_description extends AbstractCommand {
    CommandManager cm;

    public Remove_any_by_description(CommandManager cm) {
        super("remove_any_by_description", "удалить из коллекции один элемент, значение поля description которого эквивалентно заданному");
        this.cm = cm;
    }

    public void execute() throws IOException {
        try {
            cm.remove_by_d();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}