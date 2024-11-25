package agh.ics.oop.model;

import agh.ics.oop.Simulation;

import java.util.ArrayList;
import java.util.List;

public class SimulationEngine {
    List<Simulation> simulations;

    public SimulationEngine(List<Simulation> simulations) {
        this.simulations = new ArrayList<>(simulations);
    }

    public void runSync() {
        for (Simulation simulation : simulations) {
            simulation.run();
        }
    }
}
