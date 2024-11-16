package agh.ics.oop.model;

import agh.ics.oop.model.util.MapVisualizer;

import java.util.*;

import static java.lang.Math.sqrt;

public class GrassField implements WorldMap {
    private final Map<Vector2d, Animal> animals = new HashMap<>();
    private final Map<Vector2d, Grass> grassElements = new HashMap<>();
    private final static Vector2d LOWER_LEFT = new Vector2d(0, 0);
    private final MapVisualizer map = new MapVisualizer(this);

    public GrassField(int grassFieldsNumber) {
        int maxRange = (int) sqrt(10*grassFieldsNumber);
        Random random = new Random();
        Set<Vector2d> uniqNumbers = new HashSet<>();
        while (uniqNumbers.size() < grassFieldsNumber) {
            int randomX = random.nextInt(maxRange);
            int randomY = random.nextInt(maxRange);
            Vector2d randomVector = new Vector2d(randomX, randomY);
            uniqNumbers.add(randomVector);
            grassElements.put(randomVector, new Grass(randomVector));
        }
        System.out.println(grassElements);
    }

    @Override
    public boolean place(Animal animal) {
        Vector2d position = animal.getPosition();
        if (canMoveTo(position)) {
            animals.put(position, animal);
            return true;
        }
        return false;
    }

    @Override
    public void move(Animal animal, MoveDirection direction) {
        if (!animals.containsValue(animal)) {
            return;
        }
        Vector2d prevPosition = animal.getPosition();
        animal.move(this, direction);
        Vector2d newPosition = animal.getPosition();

        if (!prevPosition.equals(newPosition)) {
            animals.remove(prevPosition);
            place(animal);
        }
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        return isOccupiedByAnimal(position) || grassElements.containsKey(position);
    }

    @Override
    public WorldElement objectAt(Vector2d position) {
        return animals.get(position) != null ? animals.get(position) : grassElements.get(position);
    }


    @Override
    public boolean canMoveTo(Vector2d position) {
        return !isOccupiedByAnimal(position) && LOWER_LEFT.precedes(position);
    }

    @Override
    public String toString() {
        Vector2d upperRight = new Vector2d(0, 0);
        for (Vector2d vector : animals.keySet()) {
            upperRight = upperRight.precedes(vector) ? vector : upperRight;
        }

        for (Vector2d vector : grassElements.keySet()) {
            upperRight = upperRight.precedes(vector) ? vector : upperRight;
        }

        return map.draw(LOWER_LEFT, upperRight);
    }

    private boolean isOccupiedByAnimal(Vector2d position) {
        return animals.containsKey(position);
    }
}
