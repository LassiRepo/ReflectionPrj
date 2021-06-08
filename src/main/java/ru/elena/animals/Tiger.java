package ru.elena.animals;

import ru.elena.annotations.AfterSuite;
import ru.elena.annotations.BeforeSuite;
import ru.elena.annotations.Test;

public class Tiger {

    @BeforeSuite
    public void giveAnesthesia() {
        System.out.println("Тигру дали анастезию");
    }

    @Test(priority = 3)
    public void vaccination() {
        System.out.println("Поставлена прививка");
    }

    @Test
    public void measureTheTemperature() {
        System.out.println("Измерена температура");
    }

    @Test(priority = 2)
    public void checkTeeth() {
        System.out.println("Зубы осмотрены");
    }

    @AfterSuite
    public void outOfAnesthesia() {
        System.out.println("Тигр выведен из наркоза");
    }
}
