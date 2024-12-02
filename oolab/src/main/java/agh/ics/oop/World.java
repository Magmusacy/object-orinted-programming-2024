package agh.ics.oop;

import agh.ics.oop.model.*;
import javafx.application.Application;

import java.util.LinkedList;
import java.util.List;

public class World {

    public static void main(String[] args) {
        System.out.println("System wystartował");
        Application.launch(SimulationApp.class, args);
        try {
            run(OptionsParser.parseOptions(args));
            WorldMap rectangularMap = new RectangularMap(10, 10);

            MapChangeListener mapChangeListener = new ConsoleMapDisplay();
            rectangularMap.addObserver(mapChangeListener);

            List<Vector2d> startPositions = List.of(new Vector2d(2, 2), new Vector2d(3, 4));
            Simulation simulationRect = new Simulation(startPositions, OptionsParser.parseOptions(args), rectangularMap);
            List<Simulation> simulations = new LinkedList<>(List.of(simulationRect));
            for (int i = 0; i < 1000; i++) {
                WorldMap grassField = new GrassField(10);
                grassField.addObserver(mapChangeListener);
                Simulation simulationGrass = new Simulation(startPositions, OptionsParser.parseOptions(args), grassField);
                simulations.add(simulationGrass);
            }

            SimulationEngine simulationEngine = new SimulationEngine(simulations);
            simulationEngine.runAsyncInThreadPool();
            try {
                simulationEngine.awaitSimulationEnd();
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
            System.out.println("System zakończył działanie");
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
