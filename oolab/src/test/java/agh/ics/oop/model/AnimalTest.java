package agh.ics.oop.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AnimalTest {
    @Test
    void initializesDefaultAnimalWithNorthOrientationAndCorrectPosition() {
        MapDirection defaultOrientation = MapDirection.NORTH;
        Vector2d defaultPosition = new Vector2d(2, 2);

        Animal animal = new Animal();

        assertEquals(defaultPosition, animal.getPosition());
        assertEquals(defaultOrientation, animal.getOrientation());
    }

    @Test
    void canIntializeAnimalWithDifferentPosition() {
        MapDirection defaultOrientation = MapDirection.NORTH;
        Vector2d defaultPosition = new Vector2d(3, 2);

        Animal animal = new Animal(new Vector2d(3, 2));

        assertEquals(defaultPosition, animal.getPosition());
        assertEquals(defaultOrientation, animal.getOrientation());
    }

    @Test
    void toStringReturnsCorrectStringWithDefaultOrientation() {
        Animal animal = new Animal(new Vector2d(3, 2));
        String correct = "(3, 2) - Północ";

        assertEquals(correct, animal.toString());
    }

    @Test
    void isAtBehavesCorrectly() {
        Animal animal = new Animal(new Vector2d(5, 5));
        Vector2d correctPosition = new Vector2d(5, 5);
        Vector2d incorrectPosition = new Vector2d(5, 4);

        assertTrue(animal.isAt(correctPosition));
        assertFalse(animal.isAt(incorrectPosition));
    }

    @Test
    void moveChangesOrientationToRightCorrectly() {
        Animal animal = new Animal(new Vector2d(5, 5));

        animal.move(MoveDirection.RIGHT);
        assertEquals(MapDirection.EAST, animal.getOrientation());

        animal.move(MoveDirection.RIGHT);
        assertEquals(MapDirection.SOUTH, animal.getOrientation());

        animal.move(MoveDirection.RIGHT);
        assertEquals(MapDirection.WEST, animal.getOrientation());

        animal.move(MoveDirection.RIGHT);
        assertEquals(MapDirection.NORTH, animal.getOrientation());
    }

    @Test
    void moveChangesOrientationToLeftCorrectly() {
        Animal animal = new Animal(new Vector2d(5, 5));

        animal.move(MoveDirection.LEFT);
        assertEquals(MapDirection.WEST, animal.getOrientation());

        animal.move(MoveDirection.LEFT);
        assertEquals(MapDirection.SOUTH, animal.getOrientation());

        animal.move(MoveDirection.LEFT);
        assertEquals(MapDirection.EAST, animal.getOrientation());

        animal.move(MoveDirection.LEFT);
        assertEquals(MapDirection.NORTH, animal.getOrientation());
    }

    @Test
    void movingNorthForwardWorksCorrectly() {
        Animal animal = new Animal();

        animal.move(MoveDirection.FORWARD);

        assertEquals(new Vector2d(2, 3), animal.getPosition());
    }

    @Test
    void canNotGetOutOfBoundsNorthForward() {
        Animal animal = new Animal(new Vector2d(4, 4));

        animal.move(MoveDirection.FORWARD);

        assertEquals(new Vector2d(4, 4), animal.getPosition());
    }

    @Test
    void movingNorthBackwardWorksCorrectly() {
        Animal animal = new Animal();

        animal.move(MoveDirection.BACKWARD);

        assertEquals(new Vector2d(2, 1), animal.getPosition());
    }

    @Test
    void canNotGetOutOfBoundsNorthBackward() {
        Animal animal = new Animal(new Vector2d(0, 2));
        animal.move(MoveDirection.RIGHT);

        animal.move(MoveDirection.BACKWARD);

        assertEquals(new Vector2d(0, 2), animal.getPosition());
    }

    @Test
    void movingEastForwardWorksCorrectly() {
        Animal animal = new Animal();
        animal.move(MoveDirection.RIGHT);

        animal.move(MoveDirection.FORWARD);

        assertEquals(new Vector2d(3, 2), animal.getPosition());
    }

    @Test
    void canNotGetOutOfBoundsEastForward() {
        Animal animal = new Animal(new Vector2d(4, 2));
        animal.move(MoveDirection.RIGHT);

        animal.move(MoveDirection.FORWARD);

        assertEquals(new Vector2d(4, 2), animal.getPosition());
    }

    @Test
    void movingEastBackwardWorksCorrectly() {
        Animal animal = new Animal();
        animal.move(MoveDirection.RIGHT);

        animal.move(MoveDirection.BACKWARD);

        assertEquals(new Vector2d(1, 2), animal.getPosition());
    }

    @Test
    void canNotGetOutOfBoundsEastBackward() {
        Animal animal = new Animal(new Vector2d(0, 2));
        animal.move(MoveDirection.RIGHT);

        animal.move(MoveDirection.BACKWARD);

        assertEquals(new Vector2d(0, 2), animal.getPosition());
    }

    @Test
    void movingSouthForwardWorksCorrectly() {
        Animal animal = new Animal();
        animal.move(MoveDirection.RIGHT);
        animal.move(MoveDirection.RIGHT);

        animal.move(MoveDirection.FORWARD);

        assertEquals(new Vector2d(2, 1), animal.getPosition());
    }

    @Test
    void canNotGetOutOfBoundsSouthForward() {
        Animal animal = new Animal(new Vector2d(2, 0));
        animal.move(MoveDirection.RIGHT);
        animal.move(MoveDirection.RIGHT);

        animal.move(MoveDirection.FORWARD);

        assertEquals(new Vector2d(2, 0), animal.getPosition());
    }

    @Test
    void movingSouthBackwardWorksCorrectly() {
        Animal animal = new Animal();
        animal.move(MoveDirection.RIGHT);
        animal.move(MoveDirection.RIGHT);

        animal.move(MoveDirection.BACKWARD);

        assertEquals(new Vector2d(2, 3), animal.getPosition());
    }

    @Test
    void canNotGetOutOfBoundsSouthBackward() {
        Animal animal = new Animal(new Vector2d(2, 4));
        animal.move(MoveDirection.RIGHT);
        animal.move(MoveDirection.RIGHT);

        animal.move(MoveDirection.BACKWARD);

        assertEquals(new Vector2d(2, 4), animal.getPosition());
    }

    @Test
    void movingWestForwardWorksCorrectly() {
        Animal animal = new Animal();
        animal.move(MoveDirection.LEFT);

        animal.move(MoveDirection.FORWARD);

        assertEquals(new Vector2d(1, 2), animal.getPosition());
    }

    @Test
    void canNotGetOutOfBoundsWestForward() {
        Animal animal = new Animal(new Vector2d(0, 2));
        animal.move(MoveDirection.LEFT);

        animal.move(MoveDirection.FORWARD);

        assertEquals(new Vector2d(0, 2), animal.getPosition());
    }

    @Test
    void movingWestBackwardWorksCorrectly() {
        Animal animal = new Animal();
        animal.move(MoveDirection.LEFT);

        animal.move(MoveDirection.BACKWARD);

        assertEquals(new Vector2d(3, 2), animal.getPosition());
    }

    @Test
    void canNotGetOutOfBoundsWestBackward() {
        Animal animal = new Animal(new Vector2d(4, 2));
        animal.move(MoveDirection.LEFT);

        animal.move(MoveDirection.BACKWARD);

        assertEquals(new Vector2d(4, 2), animal.getPosition());
    }
}