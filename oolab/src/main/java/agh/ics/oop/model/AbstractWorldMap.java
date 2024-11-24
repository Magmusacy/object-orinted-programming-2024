package agh.ics.oop.model;

import agh.ics.oop.model.util.MapVisualizer;

import java.util.*;

public abstract class AbstractWorldMap implements WorldMap {
    protected final Map<Vector2d, Animal> animals = new HashMap<>();
    protected final MapVisualizer map = new MapVisualizer(this);

    @Override
    public void place(Animal animal) throws IncorrectPositionException {
        Vector2d position = animal.getPosition();
        if (canMoveTo(position)) {
            animals.put(position, animal);
        } else {
            throw new IncorrectPositionException(position);
        }
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
            try {
                place(animal);
            } catch (IncorrectPositionException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        return animals.containsKey(position);
    }

    @Override
    public WorldElement objectAt(Vector2d position) {
        return animals.get(position);
    }

    @Override
    public String toString() {
        Boundry boundry = getCurrentBounds();
        return map.draw(boundry.lowerLeft(), boundry.upperRight());
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        return !isOccupied(position);
    }

    public List<WorldElement> getElements() {
        return new ArrayList<>(animals.values());
    }
}
