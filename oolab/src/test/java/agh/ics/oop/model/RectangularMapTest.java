package agh.ics.oop.model;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RectangularMapTest {

    @Test
    void placingAnimalOutOfBoundsDoesNotWork() {
        RectangularMap map = new RectangularMap(5, 5);
        Animal animal = new Animal(new Vector2d(5, 5));

        assertThrows(IncorrectPositionException.class, () -> {
            map.place(animal);
        });
    }

    @Test
    void placingAnimalInBoundsWorks() {
        RectangularMap map = new RectangularMap(5, 5);
        Vector2d animalPosition = new Vector2d(4, 4);
        Animal animal = new Animal(animalPosition);

        assertDoesNotThrow(() -> {
            map.place(animal);
        });
        assertTrue(map.isOccupied(animalPosition));
    }

    @Test
    void canNotPlaceAnimalOnOccupiedCell() {
        RectangularMap map = new RectangularMap(5, 5);
        Vector2d animalPosition = new Vector2d(4, 4);
        Animal animal1 = new Animal(animalPosition);
        Animal animal2 = new Animal(animalPosition);

        assertDoesNotThrow(() -> {
            map.place(animal1);
        });

        assertThrows(IncorrectPositionException.class, () -> {
            map.place(animal2);
        });
    }

    @Test
    void moveDoesNotPermitGoingOutOfBounds() {
        RectangularMap map = new RectangularMap(5, 5);
        Vector2d animalPosition1 = new Vector2d(4, 4);
        Vector2d animalPosition2 = new Vector2d(0, 0);
        Animal animal1 = new Animal(animalPosition1);
        Animal animal2 = new Animal(animalPosition2);

        assertDoesNotThrow(() -> {
            map.place(animal1);
            map.place(animal2);
        });
        map.move(animal1, MoveDirection.FORWARD);
        map.move(animal1, MoveDirection.RIGHT);
        map.move(animal1, MoveDirection.FORWARD);

        map.move(animal2, MoveDirection.LEFT);
        map.move(animal2, MoveDirection.FORWARD);
        map.move(animal2, MoveDirection.LEFT);
        map.move(animal2, MoveDirection.FORWARD);


        assertTrue(animal1.isAt(animalPosition1));
        assertTrue(animal2.isAt(animalPosition2));
    }

    @Test
    void moveDoesNotPermitMovingToOccupiedCell() {
        RectangularMap map = new RectangularMap(5, 5);
        Vector2d animalPosition1 = new Vector2d(4, 4);
        Vector2d animalPosition2 = new Vector2d(4, 3);
        Animal animal1 = new Animal(animalPosition1);
        Animal animal2 = new Animal(animalPosition2);

        assertDoesNotThrow(() -> {
            map.place(animal1);
            map.place(animal2);
        });
        map.move(animal1, MoveDirection.BACKWARD);

        assertTrue(animal1.isAt(animalPosition1));
    }

    @Test
    void isOccupiedBehavesCorrectly() {
        RectangularMap map = new RectangularMap(5, 5);
        Vector2d animalPosition = new Vector2d(4, 4);
        Vector2d emptyPosition = new Vector2d(4, 3);
        Animal animal = new Animal(animalPosition);

        assertDoesNotThrow(() -> {
            map.place(animal);
        });

        assertTrue(map.isOccupied(animalPosition));
        assertFalse(map.isOccupied(emptyPosition));
    }

    @Test
    void objectAtBehavesCorrectly() {
        RectangularMap map = new RectangularMap(5, 5);
        Vector2d animalPosition = new Vector2d(4, 4);
        Vector2d emptyPosition = new Vector2d(4, 3);
        Animal animal = new Animal(animalPosition);

        assertDoesNotThrow(() -> {
            map.place(animal);
        });

        assertEquals(animal, map.objectAt(animalPosition));
        assertNull(map.objectAt(emptyPosition));
    }

    @Test
    void canMoveToReturnsFalseWhenIncorrectPosition() {
        RectangularMap map = new RectangularMap(5, 5);
        Vector2d animalPosition = new Vector2d(4, 4);
        Animal animal = new Animal(animalPosition);

        assertDoesNotThrow(() -> {
            map.place(animal);
        });

        assertFalse(map.canMoveTo(animalPosition));
        assertFalse(map.canMoveTo(new Vector2d(5, 5)));
    }

    @Test
    void canMoveToReturnsTrueWhenCorrectPosition() {
        RectangularMap map = new RectangularMap(5, 5);
        Vector2d animalPosition = new Vector2d(4, 4);
        Animal animal = new Animal(animalPosition);

        assertDoesNotThrow(() -> {
            map.place(animal);
        });

        assertTrue(map.canMoveTo(new Vector2d(3, 3)));
    }

    @Test
    void getElementsReturnsOnlyAnimals() {
        RectangularMap map = new RectangularMap(5, 5);
        Animal animal1 = new Animal(new Vector2d(4, 4));
        Animal animal2 = new Animal(new Vector2d(3, 3));

        assertDoesNotThrow(() -> {
            map.place(animal1);
            map.place(animal2);
        });

        List<WorldElement> animals = map.getElements();
        assertFalse(animals.isEmpty());
        for (Object obj : animals) {
            assertSame(Animal.class, obj.getClass());
        }
        assertEquals(2, animals.size());
    }

    @Test
    void getOrderedAnimalsWorksCorrectly() {
        RectangularMap map = new RectangularMap(6, 6);
        Animal animal1 = new Animal(new Vector2d(4, 4));
        Animal animal2 = new Animal(new Vector2d(3, 5));
        Animal animal3 = new Animal(new Vector2d(2, 4));
        Animal animal4 = new Animal(new Vector2d(3, 3));
        List<Animal> expectedResult = List.of(animal3, animal4, animal2, animal1);

        assertDoesNotThrow(() -> {
            map.place(animal1);
            map.place(animal2);
            map.place(animal3);
            map.place(animal4);
        });


        assertEquals(expectedResult, map.getOrderedAnimals());
    }
}