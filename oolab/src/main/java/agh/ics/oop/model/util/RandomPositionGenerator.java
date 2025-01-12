package agh.ics.oop.model.util;

import agh.ics.oop.model.Vector2d;

import java.util.*;

public class RandomPositionGenerator implements Iterable<Vector2d>, Iterator<Vector2d> {
    private final List<Vector2d> availablePositions = new ArrayList<>();
    private final Random random = new Random();
    private int grassLeft;

    public RandomPositionGenerator(int maxWidth, int maxHeight, int n) {
        grassLeft = n;

        for (int x = 0; x < maxWidth; x++) {
            for (int y = 0; y < maxHeight; y++) {
                availablePositions.add(new Vector2d(x, y));
            }
        }
    }

    @Override
    public Iterator<Vector2d> iterator() {
        return this;
    }

    @Override
    public boolean hasNext() {
        return grassLeft > 0;
    }

    @Override
    public Vector2d next() {
        int index = random.nextInt(availablePositions.size());
        Vector2d chosenPosition = availablePositions.get(index);
        removePosition(index);
        return chosenPosition;
    }

    private void removePosition(int index) {
        int lastIndex = availablePositions.size() - 1;
        Collections.swap(availablePositions, index, lastIndex);
        availablePositions.remove(lastIndex);
        grassLeft--;
    }
}
