package agh.ics.oop;

import agh.ics.oop.model.*;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SimulationTest {

    @Test
    void simulationRunsCorrectlyWithTwoAnimals() {
        WorldMap map = new RectangularMap(5, 5);
        String[] moves = {"f", "b", "r", "l", "f", "f", "r", "r", "f", "f", "f", "f", "f", "f", "f", "f"};
        List<Vector2d> startingPositions = List.of(new Vector2d(2, 2), new Vector2d(3, 4));
        List<MoveDirection> parsedDirections = OptionsParser.parseOptions(moves);
        Simulation simulation = new Simulation(startingPositions, parsedDirections, map);

        System.out.println(map);
        simulation.run();

        Animal animal1 = simulation.getAnimals().get(0);
        Animal animal2 = simulation.getAnimals().get(1);
        assertTrue(animal1.isAt(new Vector2d(2, 0)));
        assertTrue(animal2.isAt(new Vector2d(3, 4)));
    }

    @Test
    void simulationRunsCorrectlyWithOneAnimal() {
        WorldMap map = new RectangularMap(5, 5);
        String[] moves = {"f", "f", "f", "r", "f", "f", "f", "f", "f", "f"};
        List<Vector2d> startingPositions = List.of(new Vector2d(1, 1));
        List<MoveDirection> parsedDirections = OptionsParser.parseOptions(moves);
        Simulation simulation = new Simulation(startingPositions, parsedDirections, map);

        simulation.run();

        Animal animal1 = simulation.getAnimals().getFirst();
        assertTrue(animal1.isAt(new Vector2d(4, 4)));
    }

    @Test
    void simulationRunsCorrectlyWithTwoAnimalsAndIllegalMoves() {
        WorldMap map = new RectangularMap(5, 5);
        String[] moves = {"f", "b", "r", "DOWN", "l", "f", "f", "UP", "r", "r", "f", "f", "f", "f", "f", "f", "f", "f"};
        List<Vector2d> startingPositions = List.of(new Vector2d(2, 2), new Vector2d(3, 4));
        List<MoveDirection> parsedDirections = OptionsParser.parseOptions(moves);
        Simulation simulation = new Simulation(startingPositions, parsedDirections, map);

        simulation.run();

        Animal animal1 = simulation.getAnimals().get(0);
        Animal animal2 = simulation.getAnimals().get(1);
        assertTrue(animal1.isAt(new Vector2d(2, 0)));
        assertTrue(animal2.isAt(new Vector2d(3, 4)));
    }

    @Test
    void simulationRunsCorrectlyDifferentMapSize() {
        WorldMap map = new RectangularMap(10, 13);
        String[] moves = {"f", "f", "f", "f", "f", "f", "f", "f", "f", "f", "r", "f", "f", "f", "f", "f", "f", "f", "f", "f", "f"};
        List<Vector2d> startingPositions = List.of(new Vector2d(2, 2));
        List<MoveDirection> parsedDirections = OptionsParser.parseOptions(moves);
        Simulation simulation = new Simulation(startingPositions, parsedDirections, map);

        simulation.run();

        Animal animal1 = simulation.getAnimals().get(0);
        assertTrue(animal1.isAt(new Vector2d(9, 12)));
    }
}