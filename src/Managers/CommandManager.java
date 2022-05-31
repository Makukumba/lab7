package Managers;

import Drago.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.sql.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.sql.Date;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

import static Managers.Connector.getDBConnection;

public class CommandManager {
    DragonChecker dragonChecker = new DragonChecker();
    TreeSet<Dragon> ts = new TreeSet();

    public TreeSet<Dragon> read() {

        String selectTableSQL = "SELECT ID, NAME, X, Y, DATE, DESCRIPTION, AGE, WEIGHT, DRAGONCHARACTER, DRAGONHEAD from DRAGON";
        Connection dbConnection = null;
        Statement statement = null;
        try {
            dbConnection = getDBConnection();
            statement = dbConnection.createStatement();
            ResultSet rs = statement.executeQuery(selectTableSQL);
            while (rs.next()) {
                String id = rs.getString("ID");
                Long id1 = Long.valueOf(id);
                String name = rs.getString("NAME");
                String name1 = String.valueOf(name).trim();
                //Coordinates coordinates = new Coordinates(2,3);
                String x = rs.getString("X");
                String y = rs.getString("Y");
                int x1 = Integer.valueOf(x);
                long y1 = Long.valueOf(y);
                String date = rs.getString("DATE");
                LocalDate creationDate = LocalDate.parse(date);
                Coordinates coordinates = new Coordinates(x1, y1);
                String description = rs.getString("DESCRIPTION");
                String description1 = String.valueOf(description).trim();
                String age = rs.getString("AGE");
                Integer age1 = Integer.valueOf(age);
                String weight = rs.getString("WEIGHT");
                Integer weight1 = Integer.valueOf(weight);
                String dragoncharacter = rs.getString("DRAGONCHARACTER");
                DragonCharacter dragonCharacter1 = DragonCharacter.EVIL;
                if (dragoncharacter.equals("CUNNING")) {
                    dragonCharacter1 = DragonCharacter.CUNNING;
                } else if (dragoncharacter.equals("EVIL")) {
                    dragonCharacter1 = DragonCharacter.EVIL;
                } else if (dragoncharacter.equals("chaotic")) {
                    dragonCharacter1 = DragonCharacter.CHAOTIC;
                }
                String dragonhead = rs.getString("DRAGONHEAD");
                Double eyescount = Double.valueOf(dragonhead);
                DragonHead dragonHead1 = new DragonHead(eyescount);
                Dragon dragon = new Dragon(id1, name1, coordinates, creationDate, description1, age1, weight1, dragonCharacter1, dragonHead1);
                ts.add(dragon);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return ts;
    }


    public void info() {
        LocalDate date = LocalDate.now();
        if (ts.isEmpty()) {
            System.out.println("Ошибка, Сначала добавьте элементы в коллекцию");
        } else {
            System.out.println("Коллекция типа " + ts.getClass().getName());
            System.out.println("Количество элементов в коллекции " + ts.size());
            System.out.println("Дата создания коллекции: " + date);
        }
    }


    public void exit() {
        System.out.println("завершение работы");
        System.exit(0);
    }


    public TreeSet<Dragon> add() throws SQLException {
        Connection dbConnection = null;
        Statement statement = null;
        DragonChecker dragonChecker = new DragonChecker();
        Dragon dragon = new Dragon(dragonChecker.ID(ts), dragonChecker.NAME(), dragonChecker.COORDINATES(), dragonChecker.CREATIONDATE(), dragonChecker.DESCRIPTION(), dragonChecker.AGE(), dragonChecker.WEIGHT(), dragonChecker.CHAR(), dragonChecker.dragonHead());
        String insertTableSQL = "INSERT INTO DRAGON"
                + "(ID, NAME, X, Y, DATE, DESCRIPTION, AGE, WEIGHT, DRAGONCHARACTER, DRAGONHEAD)" + "VALUES"
                + "(" + dragon.getId() + ",'" + dragon.getName() + "', " + dragon.getCoordinates().getX() + ", " + dragon.getCoordinates().getY() + ", '" + dragon.getCreationDate() + "'," + dragon.getDescription() + ", " + dragon.getAge() + ", " + dragon.getWeight() + ",'" + dragon.getCharacter() + "', " + dragon.getHead().getEyesCount() + ")";

        try {
            dbConnection = getDBConnection();
            statement = dbConnection.createStatement();
            statement.executeUpdate(insertTableSQL);
            System.out.println("Table \"dragon\" is updated!");
            ts.add(dragon);
            System.out.println("Дракон успешно добавлен в коллекцию");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            if (statement != null) {
                statement.close();
            }
            if (dbConnection != null) {
                dbConnection.close();
            }
        }
        return ts;
    }

    public void show() {
        System.out.println("Выводим информация о коллекции");
       /* for (Dragon dragon : ts) {
            System.out.println(dragon);
        }*/
        ts.stream().forEach(dragon -> System.out.println(dragon)); // Stream API с лямбдой
        if (ts.isEmpty()) {
            System.out.println("Коллекция пуста");
        }
    }

    public TreeSet<Dragon> clear() {
        ts.clear();
        System.out.println("Коллекция очищена");
        String deleteTableSQL = "DELETE FROM DRAGON";
        Connection dbConnection = null;
        Statement statement = null;
        try {
            dbConnection = getDBConnection();
            statement = dbConnection.createStatement();
            statement.execute(deleteTableSQL);
            System.out.println("Таблица DRAGON очищена!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return ts;
    }

    public TreeSet<Dragon> add_if_max() throws SQLException {
        while (true) {
            try {
                Scanner scanner = new Scanner(System.in);
                System.out.print("Введите id: ");
                long iD = scanner.nextLong();
                if (iD > ts.last().getId()) {
                    Dragon dragon = new Dragon(iD, dragonChecker.NAME(), dragonChecker.COORDINATES(), dragonChecker.CREATIONDATE(), dragonChecker.DESCRIPTION(), dragonChecker.AGE(), dragonChecker.WEIGHT(), dragonChecker.CHAR(), dragonChecker.dragonHead());
                    System.out.println("Дракон успешно добавлен в коллекцию");
                    Connection dbConnection = null;
                    Statement statement = null;
                    String insertTableSQL = "INSERT INTO DRAGON"
                            + "(ID, NAME, X, Y, DATE, DESCRIPTION, AGE, WEIGHT, DRAGONCHARACTER, DRAGONHEAD)" + "VALUES"
                            + "(" + dragon.getId() + ",'" + dragon.getName() + "', " + dragon.getCoordinates().getX() + ", " + dragon.getCoordinates().getY() + ", '" + dragon.getCreationDate() + "', " + dragon.getDescription() + ", " + dragon.getAge() + ", " + dragon.getWeight() + ",'" + dragon.getCharacter() + "', " + dragon.getHead().getEyesCount() + ")";

                    try {
                        dbConnection = getDBConnection();
                        statement = dbConnection.createStatement();
                        statement.executeUpdate(insertTableSQL);
                        System.out.println("Table \"dragon\" is updated!");
                        ts.add(dragon);
                        System.out.println("Дракон успешно добавлен в коллекцию");

                    } catch (SQLException e) {
                        System.out.println(e.getMessage());
                    } finally {
                        if (statement != null) {
                            statement.close();
                        }
                        if (dbConnection != null) {
                            dbConnection.close();
                        }
                    }
                    break;
                } else {
                    System.out.println("id должен быть больше, чем " + ts.last().getId() + ". Попробуйте снова");
                }

            } catch (InputMismatchException exception) {
                System.out.println("Значение id должно быть представлено числом");
            } catch (NullPointerException exception) {
                System.out.println("Поле не может быть null");
            } catch (NoSuchElementException exception) {
                System.out.println("Коллекция пуста, сначала добавьте элементы");
                break;
            }
        }

        return ts;
    }


    public TreeSet<Dragon> add_if_min() throws SQLException {
        while (true) {
            try {
                Scanner scanner = new Scanner(System.in);
                System.out.print("Введите id: ");
                long iD = scanner.nextLong();
                if (iD <= 0) {
                    System.out.println("id должен быть больше 0");
                } else if (iD < ts.first().getId()) {
                    Dragon dragon = new Dragon(iD, dragonChecker.NAME(), dragonChecker.COORDINATES(), dragonChecker.CREATIONDATE(), dragonChecker.DESCRIPTION(), dragonChecker.AGE(), dragonChecker.WEIGHT(), dragonChecker.CHAR(), dragonChecker.dragonHead());
                    System.out.println("Дракон успешно добавлен в коллекцию");
                    Connection dbConnection = null;
                    Statement statement = null;
                    String insertTableSQL = "INSERT INTO DRAGON"
                            + "(ID, NAME, X, Y, DATE, DESCRIPTION, AGE, WEIGHT, DRAGONCHARACTER, DRAGONHEAD)" + "VALUES"
                            + "(" + dragon.getId() + ",'" + dragon.getName() + "', " + dragon.getCoordinates().getX() + ", " + dragon.getCoordinates().getY() + ", '" + dragon.getCreationDate() + "'," + dragon.getDescription() + ", " + dragon.getAge() + ", " + dragon.getWeight() + ",'" + dragon.getCharacter() + "', " + dragon.getHead().getEyesCount() + ")";

                    try {
                        dbConnection = getDBConnection();
                        statement = dbConnection.createStatement();
                        statement.executeUpdate(insertTableSQL);
                        System.out.println("Table \"dragon\" is updated!");
                        ts.add(dragon);
                        System.out.println("Дракон успешно добавлен в коллекцию");

                    } catch (SQLException e) {
                        System.out.println(e.getMessage());
                    } finally {
                        if (statement != null) {
                            statement.close();
                        }
                        if (dbConnection != null) {
                            dbConnection.close();
                        }
                    }
                    break;
                } else {
                    System.out.println("id должен быть меньше, чем " + ts.first().getId() + ". Попробуйте снова");
                }

            } catch (InputMismatchException exception) {
                System.out.println("Значение id должно быть представлено числом");
            } catch (NullPointerException exception) {
                System.out.println("Поле не может быть null");
            } catch (NoSuchElementException exception) {
                System.out.println("Коллекция пуста, сначала добавьте элементы");
                break;
            }
        }


        return ts;
    }


    public TreeSet<Dragon> remove_by_id() {
        int a = 0;
        while (a == 0) {
            if (ts.isEmpty()) {
                System.out.println("Коллекция пуста, сначала добавьте драконов!");
                break;
            } else {
                System.out.print("Введите id: ");
                try {
                    Scanner scanner = new Scanner(System.in);
                    long s = scanner.nextLong();
                    ts.removeIf(dragon -> dragon.getId() == s);
                    System.out.println("Дракон с id " + s + " удален");
                    String deleteTableSQL = "DELETE FROM dragon WHERE ID = " + s;
                    Connection dbConnection = null;
                    Statement statement = null;
                    try {
                        dbConnection = getDBConnection();
                        statement = dbConnection.createStatement();
                        statement.execute(deleteTableSQL);
                        System.out.println("Record is deleted from DRAGON table!");
                    } catch (SQLException e) {
                        System.out.println(e.getMessage());
                    }
                    a = 1;
                    break;


                } catch (InputMismatchException exception) {
                    System.out.println("Значение id должно быть представлено числом");
                } catch (NullPointerException exception) {
                    System.out.println("Поле не может быть null");
                }
                if (a == 0) {
                    System.out.println("Дракона с таким id не существует");
                }
            }
        }
        return ts;
    }


    public TreeSet<Dragon> remove_by_d() {

        int a = 0;
        while (a == 0) {
            if (ts.isEmpty()) {
                System.out.println("Коллекция пуста, сначала добавьте драконов!");
                break;
            } else {
                System.out.print("Введите описание: ");
                Scanner scanner = new Scanner(System.in);
                String s = scanner.nextLine();
                for (Dragon dragon : ts) {
                    if (dragon.getDescription().equals(s)) {
                        ts.remove(dragon);
                        System.out.println("Дракон с описанием " + s + " удален");
                        String deleteTableSQL = "DELETE FROM dragon WHERE DESCRIPTION = " + s;
                        Connection dbConnection = null;
                        Statement statement = null;
                        try {
                            dbConnection = getDBConnection();
                            statement = dbConnection.createStatement();
                            statement.execute(deleteTableSQL);
                            System.out.println("Record is deleted from DRAGON table!");
                        } catch (SQLException e) {
                            System.out.println(e.getMessage());
                        }
                        a = 1;
                        break;
                    }
                }
                if (a == 0) {
                    System.out.println("Дракона с таким описанием не существует, поробуйте снова");
                }
            }
        }

        return ts;
    }


    public TreeSet<Dragon> update() throws SQLException {
        int a = 0;
        int x = 0;
        while (a == 0 || a == 2) {
            if (ts.isEmpty()) {
                System.out.println("Коллекция пуста, сначала добавьте драконов");
            } else {

                try {
                    Scanner scanner = new Scanner(System.in);
                    System.out.print("Введите id дракона: ");
                    long s = scanner.nextLong();
                    for (Dragon dragon : ts) {
                        if (dragon.getId() == s) {
                            DragonChecker dragonChecker = new DragonChecker();
                            dragon.setName(dragonChecker.NAME());
                            dragon.setCoordinates(dragonChecker.COORDINATES());
                            dragon.setCreationDate(dragonChecker.CREATIONDATE());
                            dragon.setAge(dragonChecker.AGE());
                            dragon.setDescription(dragonChecker.DESCRIPTION());
                            dragon.setWeight(dragonChecker.WEIGHT());
                            dragon.setCharacter(dragonChecker.CHAR());
                            dragon.setHead(dragonChecker.dragonHead());
                            a = 1;
                            Connection dbConnection = null;
                            Statement statement = null;
                            String updateTableSQL = "UPDATE DRAGON SET NAME = '" + dragon.getName() + "', X = " + dragon.getCoordinates().getX() + ",Y = " + dragon.getCoordinates().getY() + ", DESCRIPTION = '" + dragon.getDescription() + "', DATE = '" + dragon.getCreationDate() + "', AGE = " + dragon.getAge() + ", WEIGHT = " + dragon.getWeight() + ", DRAGONCHARACTER = '" + dragon.getCharacter() + "', DRAGONHEAD = " + dragon.getHead().getEyesCount() + " WHERE ID =" + s;
                            try {
                                dbConnection = getDBConnection();
                                statement = dbConnection.createStatement();
                                // выполнить SQL запрос
                                statement.executeUpdate(updateTableSQL);
                                System.out.println("Table \"dragon\" is updated!");
                            } catch (SQLException e) {
                                System.out.println(e.getMessage());
                            } finally {
                                if (statement != null) {
                                    statement.close();
                                }
                                if (dbConnection != null) {
                                    dbConnection.close();
                                }
                            }
                            System.out.println("Значения дракона успешно обновлены");
                        }
                    }
                    if (a == 0) {
                        System.out.println("Дракона с таким id не существует, попробуйте снова");
                    }
                } catch (InputMismatchException exception) {
                    System.out.println("Значение id должно быть представлено числом, попробуйте снова");
                } catch (NullPointerException exception) {
                    System.out.println("Поле не может быть null, попробуйте снова");
                }
            }
        }

        return ts;
    }


    public TreeSet<Dragon> remove_lower() {
        int a = 0;
        while (a == 0) {
            if (ts.isEmpty()) {
                System.out.println("Коллекция пуста, сначала добавьте элементы!");
            } else {
                System.out.print("Введите id дракона: ");
                Scanner scanner = new Scanner(System.in);
                long s = scanner.nextLong();
                try {

                    ts.removeIf(dragon -> dragon.getId() < s);
                    System.out.println("Драконы с id меньше, чем " + s + " удалены");
                    String deleteTableSQL = "DELETE FROM dragon WHERE ID < " + s;
                    Connection dbConnection = null;
                    Statement statement = null;
                    try {
                        dbConnection = getDBConnection();
                        statement = dbConnection.createStatement();
                        // выполняем запрос delete SQL
                        statement.execute(deleteTableSQL);
                        System.out.println("Record is deleted from DRAGON table!");
                    } catch (SQLException e) {
                        System.out.println(e.getMessage());
                    }
                    a = 1;
                    break;


                } catch (InputMismatchException exception) {
                    System.out.println("Значение id должно быть представлено числом, поробуйте снова");
                } catch (NullPointerException exception) {
                    System.out.println("Поле не может быть null");
                }
                if (a == 0) {
                    System.out.println("Драконов с id, меньше, чем " + s + " не существует, попробуйте снова");
                }
            }
        }
        return ts;
    }


    public void print_field_descending_head() {
        if (ts.isEmpty()) {
            System.out.println("Коллекция пуста, сначала добавьте драконов!");
        } else {
            Set set = new TreeSet(new HeadComparatop());
            ts.stream().forEach(dragon -> set.add(dragon.getHead())); // Stream API с лямбдой
            set.stream().forEach(o -> System.out.println(o));  // Stream API с лямбдой
        }
    }
}














