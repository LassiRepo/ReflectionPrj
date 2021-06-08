package ru.elena.animals;

import ru.elena.annotations.AfterSuite;
import ru.elena.annotations.Test;

public class Rabbit {

    @Test(priority = 2)
    public void cutTeeth() {
        System.out.println("Зубы подстрижены");
    }

    @Test(priority = 2)
    public void cutClaws() {
        System.out.println("Когти подстрижены");
    }

    @Test(priority = 2)
    public void groom() {
        System.out.println("Убраны колтуны");
    }

    @Test(priority = 3)
    public void vaccination() {
        System.out.println("Поставлена прививка");
    }

    @Test
    public void measureTheTemperature() {
        System.out.println("Измерена температура");
    }

    @AfterSuite
    public void calm() {
        System.out.println("Кролик успокоен");
    }
}
