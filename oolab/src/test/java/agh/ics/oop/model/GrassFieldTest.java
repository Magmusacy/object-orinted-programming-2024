package agh.ics.oop.model;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class GrassFieldTest {

    @Test
    void grassFieldInitializesCorrectAmountOfGrassFields() {
        int correctAmount = 69;

        GrassField grassField = new GrassField(correctAmount);
        int amountOfGrassFields = grassField.getGrassElements().size();

        assertEquals(correctAmount, amountOfGrassFields);
    }

    @Test
    void grassFieldCanBeInitializedWithNoGrass() {
        int correctAmount = 0;

        GrassField grassField = new GrassField(correctAmount);
        int amountOfGrassFields = grassField.getGrassElements().size();

        assertEquals(correctAmount, amountOfGrassFields);
    }

    @Test
    void placingAnimalWorksOnTheWholeMap() {
        GrassField grassField = new GrassField(10);

        Animal animal1 = new Animal(new Vector2d(-1, 2));
        Animal animal2 = new Animal(new Vector2d(1, -2));
        Animal animal3 = new Animal(new Vector2d(-1, -2));
        Animal animal4 = new Animal(new Vector2d(1, 2));
        System.out.println(grassField.canMoveTo(new Vector2d(-1, 2)));
        assertDoesNotThrow(() -> {
            grassField.place(animal1);
            grassField.place(animal2);
            grassField.place(animal3);
            grassField.place(animal4);
        });
        Map<Vector2d, Animal> grassFieldAnimals = grassField.getAnimals();

        assertTrue(grassFieldAnimals.containsKey(animal1.getPosition()));
        assertTrue(grassFieldAnimals.containsKey(animal2.getPosition()));
        assertTrue(grassFieldAnimals.containsKey(animal3.getPosition()));
        assertTrue(grassFieldAnimals.containsKey(animal4.getPosition()));
    }

    @Test
    void canNotPlaceAnimalOnOccupiedCell() {
        GrassField grassField = new GrassField(10);
        Vector2d animalPosition = new Vector2d(4, 4);
        Animal animal1 = new Animal(animalPosition);
        Animal animal2 = new Animal(animalPosition);

        assertDoesNotThrow(() -> {
            grassField.place(animal1);
        });
        assertThrows(IncorrectPositionException.class, () -> {
            grassField.place(animal2);
        });
    }

    @Test
    void canPlaceAnimalOnGrass() {
        GrassField grassField = new GrassField(10);
        Vector2d grassPosition = grassField.getGrassElements().keySet().iterator().next();
        Animal animal1 = new Animal(grassPosition);

        assertDoesNotThrow(() -> {
            grassField.place(animal1);
        });
    }

    @Test
    void moveDoesNotPermitMovingToOccupiedCell() {
        GrassField grassField = new GrassField(10);
        Vector2d animalPosition1 = new Vector2d(4, 4);
        Vector2d animalPosition2 = new Vector2d(4, 3);
        Animal animal1 = new Animal(animalPosition1);
        Animal animal2 = new Animal(animalPosition2);

        assertDoesNotThrow(() -> {
            grassField.place(animal1);
            grassField.place(animal2);
        });
        grassField.move(animal1, MoveDirection.BACKWARD);

        assertTrue(animal1.isAt(animalPosition1));
    }


    @Test
    void isOccupiedBehavesCorrectly() {
        GrassField grassField = new GrassField(10);
        Vector2d animalPosition = new Vector2d(4, 4);
        Animal animal = new Animal(animalPosition);

        assertDoesNotThrow(() -> {
            grassField.place(animal);
        });

        assertTrue(grassField.isOccupied(animalPosition));
    }

    @Test
    void objectAtBehavesCorrectly() {
        GrassField grassField = new GrassField(10);
        Vector2d animalPosition = new Vector2d(4, 4);
        Animal animal = new Animal(animalPosition);

        assertDoesNotThrow(() -> {
            grassField.place(animal);
        });

        assertEquals(animal, grassField.objectAt(animalPosition));
    }

    @Test
    void canMoveToReturnsFalseWhenIncorrectPosition() {
        GrassField grassField = new GrassField(10);
        Vector2d animalPosition = new Vector2d(4, 4);
        Animal animal = new Animal(animalPosition);

        assertDoesNotThrow(() -> {
            grassField.place(animal);
        });

        assertFalse(grassField.canMoveTo(animalPosition));
    }

    @Test
    void canMoveToReturnsTrueWhenCorrectPosition() {
        GrassField grassField = new GrassField(10);
        Vector2d animalPosition = new Vector2d(4, 4);
        Animal animal = new Animal(animalPosition);

        assertDoesNotThrow(() -> {
            grassField.place(animal);
        });

        assertTrue(grassField.canMoveTo(new Vector2d(3, 3)));
    }

    @Test
    void getElementsReturnsCorrectAnimalsAndGrassElements() {
        int numOfAnimals = 2;
        int numOfGrassFields = 10;
        GrassField grassField = new GrassField(numOfGrassFields);
        Animal animal1 = new Animal(new Vector2d(4, 4));
        Animal animal2 = new Animal(new Vector2d(3, 3));

        assertDoesNotThrow(() -> {
            grassField.place(animal1);
            grassField.place(animal2);
        });

        List<WorldElement> worldElements = grassField.getElements();
        assertFalse(worldElements.isEmpty());
        int animalCounter = 0;
        int grassFieldsCounter = 0;
        for (WorldElement element : worldElements) {
            if (element instanceof Animal) {
                animalCounter++;
            } else if (element instanceof Grass) {
                grassFieldsCounter++;
            }
        }
        assertEquals(numOfAnimals + numOfGrassFields, worldElements.size());
        assertEquals(numOfAnimals, animalCounter);
        assertEquals(numOfGrassFields, grassFieldsCounter);
    }
}