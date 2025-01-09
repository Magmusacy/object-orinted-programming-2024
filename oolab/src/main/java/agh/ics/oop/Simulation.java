package agh.ics.oop;

import agh.ics.oop.model.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Simulation implements Runnable {
    private final List<Animal> animals;
    private final List<MoveDirection> directions;
    private final WorldMap map;

    public Simulation(List<Vector2d> startingPositions, List<MoveDirection> directions, WorldMap map) {
        this.map = map;
        this.animals = createAnimals(startingPositions);
        this.directions = directions;
    }

    public void run() {
        int iter = 0;
        try {
            for (MoveDirection direction : directions) {
                int currentAnimalIndex = iter++ % animals.size();
                Animal currentAnimal = animals.get(currentAnimalIndex);
                map.move(currentAnimal, direction);
                Thread.sleep(500);
            }
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }

    public List<Animal> getAnimals() {
        return Collections.unmodifiableList(animals);
    }

    private List<Animal> createAnimals(List<Vector2d> startingPositions) {
        List<Animal> animals = new ArrayList<>();

        for (Vector2d position : startingPositions) {
            Animal newAnimal = new Animal(position);
            try {
                map.place(newAnimal);
                animals.add(newAnimal);
            } catch (IncorrectPositionException e) {
                System.out.println(e.getMessage());
            }
        }

        return animals;
    }
}
