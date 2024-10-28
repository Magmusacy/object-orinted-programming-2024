package agh.ics.oop.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Vector2dTest {

    @Test
    void toStringReturnsCorrectString() {
        // given
        Vector2d vec = new Vector2d(6, 9);

        // when
        String result = vec.toString();

        // then
        assertEquals("(6, 9)", result);
    }

    @Test
    void equalsReturnsFalseWhenDifferentVectorValues() {
        // given
        Vector2d vec1 = new Vector2d(6, 9);
        Vector2d vec2 = new Vector2d(9, 6);

        // when
        boolean result = vec1.equals(vec2);

        // then
        assertFalse(result);
    }

    @Test
    void equalsReturnsTrueWhenSameVectorValues() {
        // given
        Vector2d vec1 = new Vector2d(6, 9);
        Vector2d vec2 = new Vector2d(6, 9);

        // when
        boolean result = vec1.equals(vec2);

        // then
        assertTrue(result);
    }

    @Test
    void precedesReturnsTrueWhenVec1PrecedesVec2() {
        // given
        Vector2d vec1 = new Vector2d(6, 9);
        Vector2d vec2 = new Vector2d(7, 10);

        // when
        boolean result = vec1.precedes(vec2);

        // then
        assertTrue(result);
    }

    @Test
    void precedesReturnsTrueWhenVec1SameAsVec2() {
        // given
        Vector2d vec1 = new Vector2d(6, 9);
        Vector2d vec2 = new Vector2d(6, 9);

        // when
        boolean result = vec1.precedes(vec2);

        // then
        assertTrue(result);
    }

    @Test
    void precedesReturnsFalseWhenVec1DoesNotPrecedeVec2() {
        // given
        Vector2d vec1 = new Vector2d(6, 9);
        Vector2d vec2 = new Vector2d(5, 9);

        // when
        boolean result = vec1.precedes(vec2);

        // then
        assertFalse(result);
    }

    @Test
    void followsReturnsTrueWhenVec1FollowsVec2() {
        // given
        Vector2d vec1 = new Vector2d(7, 10);
        Vector2d vec2 = new Vector2d(6, 9);

        // when
        boolean result = vec1.follows(vec2);

        // then
        assertTrue(result);
    }

    @Test
    void followsReturnsTrueWhenVec1SameAsVec2() {
        // given
        Vector2d vec1 = new Vector2d(7, 10);
        Vector2d vec2 = new Vector2d(7, 10);

        // when
        boolean result = vec1.follows(vec2);

        // then
        assertTrue(result);
    }

    @Test
    void followsReturnsFalseWhenVec1DoesNotFollowVec2() {
        // given
        Vector2d vec1 = new Vector2d(5, 10);
        Vector2d vec2 = new Vector2d(6, 9);

        // when
        boolean result = vec1.follows(vec2);

        // then
        assertFalse(result);
    }

    @Test
    void upperRightReturnsRightCorner() {
        // given
        Vector2d vec1 = new Vector2d(7, 10);
        Vector2d vec2 = new Vector2d(6, 96);

        // when
        Vector2d result = vec1.upperRight(vec2);
        Vector2d correct = new Vector2d(7, 96);

        // then
        assertEquals(result, correct);
    }

    @Test
    void lowerLeftReturnsLeftCorner() {
        // given
        Vector2d vec1 = new Vector2d(7, 10);
        Vector2d vec2 = new Vector2d(6, 96);

        // when
        Vector2d result = vec1.lowerLeft(vec2);
        Vector2d correct = new Vector2d(6, 10);

        // then
        assertEquals(result, correct);
    }

    @Test
    void addReturnsCorrectNewVector() {
        // given
        Vector2d vec1 = new Vector2d(9, 21);
        Vector2d vec2 = new Vector2d(10, 23);

        // when
        Vector2d result = vec1.add(vec2);
        Vector2d correct = new Vector2d(19, 44);

        // then
        assertEquals(result, correct);
    }

    @Test
    void substractReturnsCorrectNewVector() {
        // given
        Vector2d vec1 = new Vector2d(9, 21);
        Vector2d vec2 = new Vector2d(10, 23);

        // when
        Vector2d result = vec1.substract(vec2);
        Vector2d correct = new Vector2d(-1, -2);

        // then
        assertEquals(result, correct);
    }

    @Test
    void oppositeReturnsOppositeVector() {
        // given
        Vector2d vec = new Vector2d(9, 21);

        // when
        Vector2d result = vec.opposite();
        Vector2d correct = new Vector2d(-9, -21);

        // then
        assertEquals(result, correct);
    }
}