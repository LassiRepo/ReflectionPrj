package ru.elena.animals;

import ru.elena.annotations.BeforeSuite;

public class Walrus {
    @BeforeSuite
    public void giveAnesthesia() {
        System.out.println("Моржу дали анастезию");
    }

    @BeforeSuite
    public void giveMoreAnesthesia() {
        System.out.println("Моржу дали больше анастезии");
    }
}
