package agh.ics.oop;

public class World {
    public static void main (String[] args) {
        System.out.println("System wystartował");
        run(args);
        System.out.println("System zakończył działanie");
    }

    public static void run(String[] instructions) {
        System.out.println("Zwierzak idzie do przodu");

//        for (int i = 0; i < instructions.length; i++) {
//            if (i < instructions.length - 1) {
//                System.out.println(instructions[i] + ',');
//            } else {
//                System.out.println(instructions[i]);
//            }
//        }

        for (String instruction : instructions) {
            switch (instruction) {
                case "f" -> System.out.println("Zwierzak idzie do przodu");
                case "b" -> System.out.println("Zwierzak idzie do tyłu");
                case "r" -> System.out.println("Zwierzak skręca w prawo");
                case "l" -> System.out.println("Zwierzak skręca w lewo");
            }
        }
    }
}
