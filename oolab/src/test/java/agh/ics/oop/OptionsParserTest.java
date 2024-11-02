package agh.ics.oop;

import agh.ics.oop.model.MoveDirection;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OptionsParserTest {

    @Test
    void convertsStringArrayWithCorrectOptionsToMoveDirectionArray() {
        // given
        String[] options = {"l", "r", "f", "b"};

        // when
        List<MoveDirection> result = OptionsParser.parseOptions(options);

        // then
        List<MoveDirection> correct = List.of(
            MoveDirection.LEFT,
            MoveDirection.RIGHT,
            MoveDirection.FORWARD,
            MoveDirection.BACKWARD
        );
        assertEquals(correct, result);
    }

    @Test
    void omitsIllegalOptionFromTheInput() {
        String[] options = {"l", "r", ";(", "b"};
        List<MoveDirection> result = OptionsParser.parseOptions(options);
        List<MoveDirection> correct = List.of(
                MoveDirection.LEFT,
                MoveDirection.RIGHT,
                MoveDirection.BACKWARD
        );
        assertEquals(correct, result);
    }
}