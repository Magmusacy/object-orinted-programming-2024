package agh.ics.oop.model;

import agh.ics.oop.model.util.RandomPositionGenerator;

import java.util.*;

public class GrassField extends AbstractWorldMap {
    private final Map<Vector2d, Grass> grassElements = new HashMap<>();
    private final Vector2d lowerLeft = new Vector2d(0, 0);
    private Vector2d upperRight = lowerLeft;

    public GrassField(int grassFieldsNumber) {
        int maxCoordinate = (int) Math.sqrt(10 * grassFieldsNumber);
        RandomPositionGenerator randomPositions = new RandomPositionGenerator(maxCoordinate, maxCoordinate, grassFieldsNumber);
        for (Vector2d grassPosition : randomPositions) {
            updateUpperRight(grassPosition);
            grassElements.put(grassPosition, new Grass(grassPosition));
        }
    }

    public Map<Vector2d, Grass> getGrassElements() {
        return Collections.unmodifiableMap(grassElements);
    }

    @Override
    public boolean place(Animal animal) {
        updateUpperRight(animal.getPosition());
        return super.place(animal);
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        return super.isOccupied(position) || grassElements.containsKey(position);
    }

    @Override
    public WorldElement objectAt(Vector2d position) {
        return super.objectAt(position) != null ? super.objectAt(position) : grassElements.get(position);
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        return !super.isOccupied(position) && lowerLeft.precedes(position);
    }

    @Override
    protected Vector2d getLowerLeft() {
        return lowerLeft;
    }

    @Override
    protected Vector2d getUpperRight() {
        return upperRight;
    }

    @Override
    public List<WorldElement> getElements() {
        List<WorldElement> combinedList = new ArrayList<>(super.getElements());
        combinedList.addAll(grassElements.values());
        return List.copyOf(combinedList);
    }

    private void updateUpperRight(Vector2d newPosition) {
        upperRight = newPosition.precedes(upperRight) ? upperRight : new Vector2d(Math.max(newPosition.getX(), upperRight.getX()), Math.max(newPosition.getY(), upperRight.getY()));
    }
}
