package agh.ics.oop;

import agh.ics.oop.model.MoveDirection;

public class OptionsParser {

    public static MoveDirection[] parseOptions(String[] options) {

        MoveDirection[] moveDirectionArray = new MoveDirection[getCorrectLength(options)];

        for (int i = 0; i < options.length; i++) {
            String option = options[i];
            switch (option) {
                case "f" -> moveDirectionArray[i] = MoveDirection.FORWARD;
                case "b" -> moveDirectionArray[i] = MoveDirection.BACKWARD;
                case "r" -> moveDirectionArray[i] = MoveDirection.RIGHT;
                case "l" -> moveDirectionArray[i] = MoveDirection.LEFT;
                default -> throw new IllegalArgumentException("Invalid option: " + option);
            }
        }

        return moveDirectionArray;
    }

    private static int getCorrectLength(String[] options) {

        int correctLength = 0;
        for (String option : options) {
            switch (option) {
                case "f", "b", "r", "l" -> correctLength++;
            }
        }
        return correctLength;
    }
}
