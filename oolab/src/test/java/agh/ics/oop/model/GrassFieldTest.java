package agh.ics.oop.model;

import org.junit.jupiter.api.Test;

import java.util.List;

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
    void placingAnimalWorksOnlyInFirstQuadrant() {
        GrassField grassField = new GrassField(10);

        assertFalse(grassField.place(new Animal(new Vector2d(-1, 2))));
        assertFalse(grassField.place(new Animal(new Vector2d(1, -2))));
        assertFalse(grassField.place(new Animal(new Vector2d(-1, -2))));
        assertTrue(grassField.place(new Animal(new Vector2d(1, 2))));
    }

    @Test
    void canNotPlaceAnimalOnOccupiedCell() {
        GrassField grassField = new GrassField(10);
        Vector2d animalPosition = new Vector2d(4, 4);
        Animal animal1 = new Animal(animalPosition);
        Animal animal2 = new Animal(animalPosition);

        grassField.place(animal1);

        assertFalse(grassField.place(animal2));
    }

    @Test
    void canPlaceAnimalOnGrass() {
        GrassField grassField = new GrassField(10);
        Vector2d grassPosition = grassField.getGrassElements().keySet().iterator().next();
        Animal animal1 = new Animal(grassPosition);

        assertTrue(grassField.place(animal1));
    }

    @Test
    void moveDoesNotPermitGoingOutOfFirstQuadrant() {
        GrassField grassField = new GrassField(10);
        Vector2d animalPosition1 = new Vector2d(0, 0);
        Vector2d animalPosition2 = new Vector2d(1200, 690);

        Animal animal1 = new Animal(animalPosition1);
        Animal animal2 = new Animal(animalPosition2);

        grassField.place(animal1);
        grassField.place(animal2);

        grassField.move(animal1, MoveDirection.LEFT);
        grassField.move(animal1, MoveDirection.FORWARD);
        grassField.move(animal1, MoveDirection.LEFT);
        grassField.move(animal1, MoveDirection.FORWARD);

        assertTrue(animal1.isAt(animalPosition1));
        assertTrue(animal2.isAt(animalPosition2));
    }

    @Test
    void moveDoesNotPermitMovingToOccupiedCell() {
        GrassField grassField = new GrassField(10);
        Vector2d animalPosition1 = new Vector2d(4, 4);
        Vector2d animalPosition2 = new Vector2d(4, 3);
        Animal animal1 = new Animal(animalPosition1);
        Animal animal2 = new Animal(animalPosition2);

        grassField.place(animal1);
        grassField.place(animal2);
        grassField.move(animal1, MoveDirection.BACKWARD);

        assertTrue(animal1.isAt(animalPosition1));
    }


    @Test
    void isOccupiedBehavesCorrectly() {
        GrassField grassField = new GrassField(10);
        Vector2d animalPosition = new Vector2d(4, 4);
        Animal animal = new Animal(animalPosition);

        grassField.place(animal);

        assertTrue(grassField.isOccupied(animalPosition));
    }

    @Test
    void objectAtBehavesCorrectly() {
        GrassField grassField = new GrassField(10);
        Vector2d animalPosition = new Vector2d(4, 4);
        Animal animal = new Animal(animalPosition);

        grassField.place(animal);

        assertEquals(animal, grassField.objectAt(animalPosition));
    }

    @Test
    void canMoveToReturnsFalseWhenIncorrectPosition() {
        GrassField grassField = new GrassField(10);
        Vector2d animalPosition = new Vector2d(4, 4);
        Animal animal = new Animal(animalPosition);

        grassField.place(animal);

        assertFalse(grassField.canMoveTo(animalPosition));
        assertFalse(grassField.canMoveTo(new Vector2d(-1, -1)));
    }

    @Test
    void canMoveToReturnsTrueWhenCorrectPosition() {
        GrassField grassField = new GrassField(10);
        Vector2d animalPosition = new Vector2d(4, 4);
        Animal animal = new Animal(animalPosition);

        grassField.place(animal);

        assertTrue(grassField.canMoveTo(new Vector2d(3, 3)));
    }

    @Test
    void getElementsReturnsCorrectAnimalsAndGrassElements() {
        int numOfAnimals = 2;
        int numOfGrassFields = 10;
        GrassField grassField = new GrassField(numOfGrassFields);
        Animal animal1 = new Animal(new Vector2d(4, 4));
        Animal animal2 = new Animal(new Vector2d(3, 3));

        grassField.place(animal1);
        grassField.place(animal2);

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