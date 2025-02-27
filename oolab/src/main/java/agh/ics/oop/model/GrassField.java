package agh.ics.oop.model;

import agh.ics.oop.model.util.RandomPositionGenerator;

import java.util.*;

public class GrassField extends AbstractWorldMap {
    private final Map<Vector2d, Grass> grassElements = new HashMap<>();

    public GrassField(int grassFieldsNumber) {
//        super();
        int maxCoordinate = (int) Math.sqrt(10 * grassFieldsNumber);
        RandomPositionGenerator randomPositions = new RandomPositionGenerator(maxCoordinate, maxCoordinate, grassFieldsNumber);
        for (Vector2d grassPosition : randomPositions) {
            grassElements.put(grassPosition, new Grass(grassPosition));
        }
    }

    Map<Vector2d, Grass> getGrassElements() {
        return Collections.unmodifiableMap(grassElements);
    }
    Map<Vector2d, Animal> getAnimals() { return Collections.unmodifiableMap(animals); }

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
        return !super.isOccupied(position);

    }

    @Override
    public List<WorldElement> getElements() {
        List<WorldElement> combinedList = super.getElements();
        combinedList.addAll(grassElements.values());
        return combinedList;
    }

    @Override
    public Boundary getCurrentBounds() {
        Vector2d lowerLeft = getElements().getFirst().getPosition();
        Vector2d upperRight = lowerLeft;

        for (WorldElement element : getElements()) {
            Vector2d position = element.getPosition();
            lowerLeft = position.lowerLeft(lowerLeft);
            upperRight = position.upperRight(upperRight);
        }

        return new Boundary(lowerLeft, upperRight);

    }
}
