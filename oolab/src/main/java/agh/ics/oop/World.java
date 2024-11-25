package agh.ics.oop;

import agh.ics.oop.model.*;

import java.util.List;

public class World {

    public static void main (String[] args) {
        System.out.println("System wystartował");
        try {
            run(OptionsParser.parseOptions(args));
            System.out.println("System zakończył działanie");
            //        WorldMap grassField = new GrassField(10);
            WorldMap rectangularMap = new RectangularMap(10, 10);
            MapChangeListener mapChangeListener = new ConsoleMapDisplay();
            //        grassField.addObserver(mapChangeListener);
            rectangularMap.addObserver(mapChangeListener);
            List<Vector2d> startPositions = List.of(new Vector2d(2, 2), new Vector2d(3, 4));
            //        Simulation simulation = new Simulation(startPositions, OptionsParser.parseOptions(args), grassField);
            Simulation simulation = new Simulation(startPositions, OptionsParser.parseOptions(args), rectangularMap);
            simulation.run();
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
        }
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
