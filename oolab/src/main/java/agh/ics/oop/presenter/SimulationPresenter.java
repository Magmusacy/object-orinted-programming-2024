package agh.ics.oop.presenter;

import agh.ics.oop.OptionsParser;
import agh.ics.oop.Simulation;
import agh.ics.oop.SimulationEngine;
import agh.ics.oop.model.MapChangeListener;
import agh.ics.oop.model.MoveDirection;
import agh.ics.oop.model.Vector2d;
import agh.ics.oop.model.WorldMap;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import javax.swing.text.html.Option;
import java.util.List;

public class SimulationPresenter implements MapChangeListener {

    private WorldMap worldMap;

    @FXML
    private Label currentMoveLabel;

    @FXML
    private TextField movesInput;

    @FXML
    private Label infoLabel;

    public void setWorldMap(WorldMap worldMap) {
        this.worldMap = worldMap;
    }

    public void drawMap() {
        infoLabel.setText(worldMap.toString());
    }

    @Override
    public void mapChanged(WorldMap worldMap, String message) {
        Platform.runLater(() -> {
            currentMoveLabel.setText(message);
            drawMap();
        });
    }

    public void onSimulationStartClicked(ActionEvent actionEvent) {
        List<Vector2d> startPositions = List.of(new Vector2d(2, 2), new Vector2d(3, 4));
        List<MoveDirection> moves = OptionsParser.parseOptions(movesInput.getText().split(" "));
        Simulation simulationRect = new Simulation(startPositions, moves, worldMap);
        SimulationEngine simulationEngine = new SimulationEngine(List.of(simulationRect));
        simulationEngine.runAsync();
    }
}
