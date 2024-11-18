package agh.ics.oop.model;

import agh.ics.oop.model.util.RandomPositionGenerator;

import java.util.*;

public class GrassField extends AbstractWorldMap {
    private final Map<Vector2d, Grass> grassElements = new HashMap<>();
    private static final Vector2d LOWER_LEFT = new Vector2d(0, 0);

    public GrassField(int grassFieldsNumber) {
        int maxCoordinate = (int) Math.sqrt(10 * grassFieldsNumber);
        RandomPositionGenerator randomPositions = new RandomPositionGenerator(maxCoordinate, maxCoordinate, grassFieldsNumber);
        for (Vector2d grassPosition : randomPositions) {
            grassElements.put(grassPosition, new Grass(grassPosition));
        }
    }

    public Map<Vector2d, Grass> getGrassElements() {
        return Collections.unmodifiableMap(grassElements);
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
        return !super.isOccupied(position);
    }

    @Override
    protected Vector2d getLowerLeft() {
        return LOWER_LEFT;
    }

    @Override
    protected Vector2d getUpperRight() {
        Vector2d upperRight = LOWER_LEFT;
        for (WorldElement element : getElements()) {
            Vector2d position = element.getPosition();
            upperRight = upperRight.upperRight(position);
        }
        return upperRight;
    }

    @Override
    public List<WorldElement> getElements() {
        List<WorldElement> combinedList = super.getElements();
        combinedList.addAll(grassElements.values());
        return combinedList;
    }
}
