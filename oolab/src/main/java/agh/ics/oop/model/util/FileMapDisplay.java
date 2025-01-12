package agh.ics.oop.model.util;

import agh.ics.oop.model.MapChangeListener;
import agh.ics.oop.model.WorldMap;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOError;
import java.io.IOException;
import java.util.UUID;

public class FileMapDisplay implements MapChangeListener {
    private final UUID map_id;

    public FileMapDisplay(WorldMap map) {
        map_id = map.getId();
    }

    @Override
    public void mapChanged(WorldMap worldMap, String message) {
        String fileName = String.format("map_%s.log", map_id);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            writer.write(message + "\n");
            writer.write(worldMap + "\n");
        } catch (IOException e) {
            System.out.println("Błąd zapisu pliku.");
        }
    }
}
