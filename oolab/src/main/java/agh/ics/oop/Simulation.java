package agh.ics.oop;

import agh.ics.oop.model.Animal;
import agh.ics.oop.model.MoveDirection;
import agh.ics.oop.model.Vector2d;
import agh.ics.oop.model.WorldMap;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Simulation {
    private final List<Animal> animals;
    private final List<MoveDirection> directions;
    private final WorldMap map;

    public Simulation(List<Vector2d> startingPositions, List<MoveDirection> directions, WorldMap map) {
        this.map = map;
        this.animals = createAnimals(startingPositions);
        this.directions = directions;
        populateMap(animals);
    }

    public void run() {
        int iter = 0;
        for (MoveDirection direction : directions) {
            int currentAnimalIndex = iter++ % animals.size();
            Animal currentAnimal = animals.get(currentAnimalIndex);
            map.move(currentAnimal, direction);
            System.out.println(map);
        }
    }

    public List<Animal> getAnimals() {
        return Collections.unmodifiableList(animals);
    }

    private List<Animal> createAnimals(List<Vector2d> startingPositions) {
        List<Animal> animals = new ArrayList<>();

        for (Vector2d position : startingPositions) {
            Animal newAnimal = new Animal(position);
            if (map.place(newAnimal)) {
                animals.add(newAnimal);
            }
        }

        return animals;
    }

    private void populateMap(List<Animal> animals) {
        for (Animal animal : animals) {
            map.place(animal);
        }
    }
}
