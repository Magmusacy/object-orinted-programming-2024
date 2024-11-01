package agh.ics.oop;

import agh.ics.oop.model.MoveDirection;

public class OptionsParser {

    public static MoveDirection[] parseOptions(String[] options) {

        MoveDirection[] moveDirectionArray = new MoveDirection[getCorrectOptionsLength(options)];
        int correctOptionsLength = 0;

        for (int i = 0; i < options.length; i++) {
            String option = options[i];
            switch (option) {
                case "f" -> moveDirectionArray[correctOptionsLength++] = MoveDirection.FORWARD;
                case "b" -> moveDirectionArray[correctOptionsLength++] = MoveDirection.BACKWARD;
                case "r" -> moveDirectionArray[correctOptionsLength++] = MoveDirection.RIGHT;
                case "l" -> moveDirectionArray[correctOptionsLength++] = MoveDirection.LEFT;
            }
        }

        return moveDirectionArray;
    }

    private static int getCorrectOptionsLength(String[] options) {

        int correctLength = 0;
        for (String option : options) {
            switch (option) {
                case "f", "b", "r", "l" -> correctLength++;
            }
        }
        return correctLength;
    }
}
