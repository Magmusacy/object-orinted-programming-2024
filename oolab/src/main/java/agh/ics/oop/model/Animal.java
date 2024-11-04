package agh.ics.oop.model;

public class Animal {
    private static final Vector2d upperRight = new Vector2d(4, 4);
    private static final Vector2d lowerLeft = new Vector2d(0, 0);
    private MapDirection orientation = MapDirection.NORTH;
    private Vector2d position;

    public Animal() {
        this.position = new Vector2d(2, 2);
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
        return String.format("%s - %s", position, orientation);
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
