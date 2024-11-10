package agh.ics.oop.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TextMap implements WorldMap<String, Integer> {
    private final List<String> map = new ArrayList<>();

    @Override
    public boolean place(String text) {
        map.addLast(text);
        return true;
    }

    @Override
    public void move(String text, MoveDirection direction) {
        int animalPosition = map.indexOf(text);
        if (animalPosition == -1) {return;}

        switch (direction) {
            case FORWARD, RIGHT -> moveRight(animalPosition);
            case BACKWARD, LEFT -> moveLeft(animalPosition);
        }
    }

    @Override
    public boolean isOccupied(Integer position) {
        return position > -1 && position < map.size();
    }

    @Override
    public String objectAt(Integer position) {
        return position > -1 && position < map.size() ? map.get(position) : null;
    }

    @Override
    public boolean canMoveTo(Integer position) {
        return position > -1 && position < map.size();
    }

    @Override
    public String toString() {
        return map.toString();
    }

    private void moveRight(int initialPosition) {
        if (initialPosition + 1 < map.size()) {
            Collections.swap(map, initialPosition, initialPosition + 1);
        }
    }

    private void moveLeft(int initialPosition) {
        if (initialPosition > 0) {
            Collections.swap(map, initialPosition, initialPosition - 1);
        }
    }
}
