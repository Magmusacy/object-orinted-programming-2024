package agh.ics.oop;

import agh.ics.oop.model.MoveDirection;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OptionsParserTest {

    @Test
    void convertsStringArrayWithCorrectOptionsToMoveDirectionArray() {
        // given
        String[] options = {"l", "r", "f", "b"};

        // when
        MoveDirection[] result = OptionsParser.parseOptions(options);

        // then
        MoveDirection[] correct = {
                MoveDirection.LEFT,
                MoveDirection.RIGHT,
                MoveDirection.FORWARD,
                MoveDirection.BACKWARD
        };
        assertArrayEquals(correct, result);
    }

    @Test
    void omitsIllegalOptionFromTheInput() {
        String[] options = {"l", "r", ";(", "b"};
        MoveDirection[] result = OptionsParser.parseOptions(options);
        MoveDirection[] correct = {
                MoveDirection.LEFT,
                MoveDirection.RIGHT,
                MoveDirection.BACKWARD,
        };
        assertArrayEquals(correct, result);
    }
}