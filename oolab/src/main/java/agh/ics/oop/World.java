package agh.ics.oop;

import agh.ics.oop.model.MoveDirection;

import java.util.List;

public class World {

    public static void main (String[] args) {
        System.out.println("System wystartował");
        run(OptionsParser.parseOptions(args));
        System.out.println("System zakończył działanie");
    }

    private static void run(List<MoveDirection> instructions) {

        for (MoveDirection instruction : instructions) {
            switch (instruction) {
                case FORWARD -> System.out.println("Zwierzak idzie do przodu");
                case BACKWARD -> System.out.println("Zwierzak idzie do tyłu");
                case RIGHT -> System.out.println("Zwierzak skręca w prawo");
                case LEFT -> System.out.println("Zwierzak skręca w lewo");
            }
        }
    }
}
