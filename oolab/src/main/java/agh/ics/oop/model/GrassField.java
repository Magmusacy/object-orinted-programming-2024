package agh.ics.oop.model;

import java.util.*;

import static java.lang.Math.sqrt;

public class GrassField extends AbstractWorldMap {
    private final Map<Vector2d, Grass> grassElements = new HashMap<>();
    private final Vector2d lowerLeft = new Vector2d(0, 0);
    private Vector2d upperRight = lowerLeft;

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
            upperRight = upperRight.precedes(randomVector) ? randomVector : upperRight;
        }
    }

    public Map<Vector2d, Grass> getGrassElements() {
        return Collections.unmodifiableMap(grassElements);
    }

    @Override
    public boolean place(Animal animal) {
        Vector2d position = animal.getPosition();
        upperRight = upperRight.precedes(position) ? position : upperRight;
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
}
