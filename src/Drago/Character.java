package Drago;



public class Character {
    public DragonCharacter Character(String text) {
        DragonCharacter dragonCharacter;
        while (true) {
            String a = "CUNNING";
            String b = "EVIL";
            String c = "CHAOTIC";

            if (text.equals(a)) {
                dragonCharacter = DragonCharacter.CUNNING;
                break;
            } else if (text.equals(b)) {
                dragonCharacter = DragonCharacter.EVIL;
                break;
            } else if (text.equals(c)) {
                dragonCharacter = DragonCharacter.CHAOTIC;
                break;
            } else {
                System.out.println("Некорректный ввод, повторите попытку");
            }
        }
        return dragonCharacter;
    }
}
