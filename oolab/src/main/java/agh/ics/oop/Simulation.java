package agh.ics.oop;

import agh.ics.oop.model.Animal;
import agh.ics.oop.model.MoveDirection;
import agh.ics.oop.model.Vector2d;

import java.util.ArrayList;
import java.util.List;

public class Simulation {
    private final List<Animal> animals;
    private final List<MoveDirection> directions;

    public Simulation(List<Vector2d> startingPositions, List<MoveDirection> directions) {
        this.animals = createAnimals(startingPositions);
        this.directions = directions;
    }

    public void run() {
        int iter = 0;
        for (MoveDirection direction : directions) {
            int currentAnimalIndex = iter++ % animals.size();
            Animal currentAnimal = animals.get(currentAnimalIndex);
            currentAnimal.move(direction);
            System.out.printf("Zwierzę %s : %s%n", currentAnimalIndex, currentAnimal);
        }
    }

    public List<Animal> getAnimals() {
        return animals;
    }

    private List<Animal> createAnimals(List<Vector2d> startingPositions) {
        List<Animal> animals = new ArrayList<>();

        for (Vector2d position : startingPositions) {
            animals.add(new Animal(position));
        }

        return animals;
    }
}
