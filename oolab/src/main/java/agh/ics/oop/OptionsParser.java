package agh.ics.oop;

import agh.ics.oop.model.MoveDirection;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;

public class OptionsParser {

    public static List<MoveDirection> parseOptions(String[] options) {
        return Stream.of(options)
                .map(option -> switch (option) {
                    case "f" -> MoveDirection.FORWARD;
                    case "b" -> MoveDirection.BACKWARD;
                    case "r" -> MoveDirection.RIGHT;
                    case "l" -> MoveDirection.LEFT;
                    default -> throw new IllegalArgumentException(option + " is not legal move specification");
                })
                .toList();
    }
}
