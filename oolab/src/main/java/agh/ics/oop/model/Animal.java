package agh.ics.oop.model;

public class Animal implements WorldElement {
    private MapDirection orientation = MapDirection.NORTH;
    private Vector2d position;

    public Animal() {
        this(new Vector2d(2, 2));
    }

    public Animal(Vector2d position) {
        this.position = position;
    }

    public MapDirection getOrientation() {
        return orientation;
    }

    public Vector2d getPosition() {
        return position;
    }

    public String toString() {
        return switch (orientation) {
            case NORTH -> "↑";
            case EAST -> "→";
            case SOUTH -> "↓";
            case WEST -> "←";
        };
    }

    @Override
    public String getImageName() {
        return switch (orientation) {
            case NORTH -> "up.png";
            case EAST -> "left.png";
            case WEST -> "right.png";
            case SOUTH -> "down.png";
        };
    }

    public boolean isAt(Vector2d position) {
        return this.position.equals(position);
    }

    public void move(MoveValidator validator, MoveDirection direction) {

        switch (direction) {
            case RIGHT -> this.orientation = this.orientation.next();
            case LEFT -> this.orientation = this.orientation.previous();
            case FORWARD -> tryMove(validator, orientation.toUnitVector());
            case BACKWARD -> tryMove(validator, orientation.toUnitVector().opposite());
        }
    }

    private void tryMove(MoveValidator validator, Vector2d step) {
        Vector2d newPosition = position.add(step);

        if (validator.canMoveTo(newPosition)) {
            position = newPosition;
        }
    }
}
