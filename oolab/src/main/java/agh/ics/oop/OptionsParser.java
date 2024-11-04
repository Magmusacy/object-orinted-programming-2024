package agh.ics.oop;

import agh.ics.oop.model.MoveDirection;

import java.util.LinkedList;
import java.util.List;

public class OptionsParser {

    public static List<MoveDirection> parseOptions(String[] options) {

        List<MoveDirection> moves = new LinkedList<>();

        for (String option : options) {
            switch (option) {
                case "f" -> moves.add(MoveDirection.FORWARD);
                case "b" -> moves.add(MoveDirection.BACKWARD);
                case "r" -> moves.add(MoveDirection.RIGHT);
                case "l" -> moves.add(MoveDirection.LEFT);
            }
        }

        return moves;
    }
}
