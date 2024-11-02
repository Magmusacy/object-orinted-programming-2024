package agh.ics.oop;

import agh.ics.oop.model.Animal;
import agh.ics.oop.model.MoveDirection;
import agh.ics.oop.model.Vector2d;
import org.junit.jupiter.api.Test;

import javax.swing.text.html.Option;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SimulationIntegrationTest {
    @Test
    void simulationRunsCorrectlyWithTwoAnimals() {
        String[] moves = {"f", "b", "r", "l", "f", "f", "r", "r", "f", "f", "f", "f", "f", "f", "f", "f"};
        List<Vector2d> startingPositions = List.of(new Vector2d(2, 2), new Vector2d(3, 4));
        List<MoveDirection> parsedDirections = OptionsParser.parseOptions(moves);
        Simulation simulation = new Simulation(startingPositions, parsedDirections);

        simulation.run();

        Animal animal1 = simulation.getAnimals().get(0);
        Animal animal2 = simulation.getAnimals().get(1);
        assertTrue(animal1.isAt(new Vector2d(3, 0)));
        assertTrue(animal2.isAt(new Vector2d(2, 4)));
    }
}