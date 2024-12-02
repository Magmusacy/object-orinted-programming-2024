package agh.ics.oop.model;

import agh.ics.oop.model.util.MapVisualizer;

import java.util.*;

public abstract class AbstractWorldMap implements WorldMap {
    protected final Map<Vector2d, Animal> animals = new HashMap<>();
    protected final MapVisualizer map = new MapVisualizer(this);
    // Można by tutaj też zrobić hashmape zeby usuwac observerów w O(1) ale kosztem pamięci
    protected final List<MapChangeListener> observers = new LinkedList<>();
    private final UUID id;

    public AbstractWorldMap() {
        this.id = UUID.randomUUID();
    }

    @Override
    public void place(Animal animal) throws IncorrectPositionException {
        Vector2d position = animal.getPosition();
        if (canMoveTo(position)) {
            animals.put(position, animal);
            mapChanged(String.format("Postawiono zwierzaka na pozycji %s z orientacją %s", position, animal.getOrientation()));
        } else {
            throw new IncorrectPositionException(position);
        }
    }

    @Override
    public void move(Animal animal, MoveDirection direction) {
        Vector2d prevPosition = animal.getPosition();
        MapDirection prevDirection = animal.getOrientation();
        animal.move(this, direction);
        Vector2d newPosition = animal.getPosition();
        MapDirection newDirection = animal.getOrientation();

        if (!prevPosition.equals(newPosition)) {
            animals.remove(prevPosition);
            animals.put(newPosition, animal);
            mapChanged(String.format("Zwierzak zmienił pozycję z %s na %s", prevPosition, newPosition));
        } else {
            mapChanged(String.format("Zwierzak zmienił orientację z %s na %s", prevDirection, newDirection));
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
        Boundary boundary = getCurrentBounds();
        return map.draw(boundary.lowerLeft(), boundary.upperRight());
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        return !isOccupied(position);
    }

    public List<WorldElement> getElements() {
        return new ArrayList<>(animals.values());
    }

    public void addObserver(MapChangeListener observer) {
        observers.add(observer);
    }

    public void removeObserver(MapChangeListener observer) {
        observers.remove(observer);
    }

    public UUID getId() {
        return this.id;
    }

    private void mapChanged(String eventDescription) {
        for (MapChangeListener observer : observers) {
            observer.mapChanged(this, eventDescription);
        }
    }
}
