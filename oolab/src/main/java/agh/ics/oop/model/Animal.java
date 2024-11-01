package agh.ics.oop.model;

public class Animal {
    private final Vector2d upperRight = new Vector2d(4, 4);
    private final Vector2d lowerLeft = new Vector2d(0, 0);
    private MapDirection orientation;
    private Vector2d position;

    public Animal() {
        this.orientation = MapDirection.NORTH;
        this.position = new Vector2d(2, 2);
    }

    public Animal(Vector2d position) {
        this.orientation = MapDirection.NORTH;
        this.position = position;
    }

    public String toString() {
        return String.format("(%d, %d) - %s", position.getX(), position.getY(), orientation);
    }

    public boolean isAt(Vector2d position) {
        return this.position.equals(position);
    }

    public void move(MoveDirection direction) {

        switch (direction) {
            case MoveDirection.RIGHT -> this.orientation = this.orientation.next();
            case MoveDirection.LEFT -> this.orientation = this.orientation.previous();
            case MoveDirection.FORWARD -> tryMove(orientation.toUnitVector());
            case MoveDirection.BACKWARD -> tryMove(orientation.toUnitVector().opposite());
        }
    }

    private void tryMove(Vector2d step) {
        Vector2d newVector = position.add(step);

        if (newVector.precedes(upperRight) && newVector.follows(lowerLeft)) {
            position = newVector;
        }
    }
}
