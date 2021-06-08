package ru.elena;

import ru.elena.animals.Tiger;
import ru.elena.animals.Walrus;

public class ReflectionPrjApp {
    public static void main(String[] args) {
        System.out.println("******** ТИГР ********");
        Veterinarian.start(Tiger.class);
        System.out.println("******** КРОЛИК ********");
        Veterinarian.start("ru.elena.animals.Rabbit");
        System.out.println("******** МОРЖ ********");
        Veterinarian.start(Walrus.class);
    }
}
