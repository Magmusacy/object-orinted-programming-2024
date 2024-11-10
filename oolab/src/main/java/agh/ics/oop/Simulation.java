package agh.ics.oop;

import agh.ics.oop.model.Animal;
import agh.ics.oop.model.MoveDirection;
import agh.ics.oop.model.Vector2d;
import agh.ics.oop.model.WorldMap;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Simulation<T, P> {
    private final List<T> mapObjects;
    private final List<MoveDirection> directions;
    private final WorldMap<T, P> map;

    // Zamiast przekazywania pozycji i tworzenia zwierzaków w simulation, będę tworzył je np. w main albo w jakims
    // parserze zwierząt i tu przekazywał unikalną listę zwierząt (nie ma dwoch na tej samej pozycji)
    public Simulation(List<T> mapObjects, List<MoveDirection> directions, WorldMap<T, P> map) {
        this.map = map;
        this.mapObjects = mapObjects;
        this.directions = directions;
        populateMap(mapObjects);
    }

    public void run() {
        int iter = 0;
        for (MoveDirection direction : directions) {
            int currentMapObjectIndex = iter++ % mapObjects.size();
            T currentMapObject = mapObjects.get(currentMapObjectIndex);
            map.move(currentMapObject, direction);
            System.out.println(map);
        }
    }

    public List<T> getMapObjects() {
        return Collections.unmodifiableList(mapObjects);
    }

    private void populateMap(List<T> mapObjects) {
        for (T mapObject : mapObjects) {
            map.place(mapObject);
        }
    }
}
