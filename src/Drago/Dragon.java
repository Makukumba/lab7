package Drago;


import java.time.LocalDate;

public class Dragon implements Comparable<Dragon> {
    private Long id;
    private String name;
    private Coordinates coordinates;
    private LocalDate creationDate;
    private String description;
    private int age;
    private Integer weight;
    private DragonCharacter character;
    private DragonHead head;
    private String login;

    public Dragon(Long id, String name, Coordinates coordinates,LocalDate creationDate, String description, int age, int weight, DragonCharacter character, DragonHead head, String login) {
        this.id = id;
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = creationDate;
        this.description = description;
        this.age = age;
        this.weight = weight;
        this.character = character;
        this.head = head;
        this.login = login;


    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public LocalDate getCreationDate() { return creationDate; }

    public String getDescription() {
        return description;
    }

    public Integer getAge() {
        return age;
    }

    public int getWeight() {
        return weight;
    }

    public DragonCharacter getCharacter() {
        return character;
    }

    public DragonHead getHead() {
        return head;
    }

    public String getLogin() { return login; }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public void setCreationDate(LocalDate creationDate){
        this.creationDate = creationDate;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public void setCharacter(DragonCharacter dragonCharacter) {
        this.character = dragonCharacter;
    }

    public void setHead(DragonHead head) {
        this.head = head;
    }

    public int compareTo(Dragon dragon) {

        if (this.id == dragon.getId()) {
            return 0;
        } else if (this.id < dragon.getId()) {
            return -1;
        } else {
            return 1;
        }
    }

    public String toString() {
        return " Id: " + id + " Имя: " + name + " Координаты: " + "(" + coordinates + ")" + " Время создания: "+creationDate+ " Возраст: " + age + " Описание: " + description + " Вес: " + weight + " Характер: " + character + " Количество глаз: " + head + " Создатель: " +login;
    }

}


