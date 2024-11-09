package agh.ics.oop.model;

import agh.ics.oop.model.util.MapVisualizer;

import java.util.HashMap;
import java.util.Map;

public class RectangularMap implements WorldMap {
    private final Vector2d upperRight;
    private final Vector2d lowerLeft;
    private final Map<Vector2d, Animal> animals = new HashMap<>();

    public RectangularMap(int width, int height) {
        this.upperRight = new Vector2d(width, height);
        this.lowerLeft = new Vector2d(0, 0);
    }

    public boolean place(Animal animal) {
        Vector2d position = animal.getPosition();
        if (canMoveTo(position)) {
            animals.put(position, animal);
            return true;
        }
        return false;
    }

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

    public String toString() {
        MapVisualizer map = new MapVisualizer(this);
        return map.draw(lowerLeft, upperRight);
    }

    public boolean isOccupied(Vector2d position) {
        return animals.containsKey(position);
    }

    public Animal objectAt(Vector2d position) {
        return animals.get(position);
    }

    public boolean canMoveTo(Vector2d position) {
        return position.precedes(upperRight) && position.follows(lowerLeft) && !isOccupied(position);
    }
}
