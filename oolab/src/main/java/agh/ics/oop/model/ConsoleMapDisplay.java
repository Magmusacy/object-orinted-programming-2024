package agh.ics.oop.model;

public class ConsoleMapDisplay implements MapChangeListener {
    private int eventsRegistered = 0;

    @Override
    public void mapChanged(WorldMap worldMap, String message) {
        eventsRegistered++;
        System.out.printf("Map id: %d%n", worldMap.getId());
        System.out.println(message);
        System.out.printf("Events registered: %d%n", eventsRegistered);
        System.out.println(worldMap);
    }
}
