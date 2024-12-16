package agh.ics.oop.model;

public class ConsoleMapDisplay implements MapChangeListener {
    private int eventsRegistered = 0;

    @Override
    public void mapChanged(WorldMap worldMap, String message) {
        synchronized (System.out) {
            eventsRegistered++;
            System.out.println(worldMap.getId());
            System.out.println(message);
            System.out.printf("Events registered: %d%n", eventsRegistered);
            System.out.println(worldMap);
        }
    }
}
