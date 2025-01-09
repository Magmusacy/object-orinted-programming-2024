package agh.ics.oop.presenter;

import agh.ics.oop.OptionsParser;
import agh.ics.oop.Simulation;
import agh.ics.oop.SimulationEngine;
import agh.ics.oop.WorldElementBox;
import agh.ics.oop.model.*;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

import java.util.List;

public class SimulationPresenter implements MapChangeListener {
    private static final int CELL_WIDTH = 40;
    private static final int CELL_HEIGHT = 40;

    private WorldMap worldMap;

    @FXML
    private GridPane mapGrid;

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
        clearGrid();
        int lowerY = worldMap.getCurrentBounds().lowerLeft().getY();
        int upperY = worldMap.getCurrentBounds().upperRight().getY();
        int lowerX = worldMap.getCurrentBounds().lowerLeft().getX();
        int upperX = worldMap.getCurrentBounds().upperRight().getX();
        int mapWidth = upperX - lowerX + 1;
        int mapHeight = upperY - lowerY + 1;

        mapGrid.getColumnConstraints().add(new ColumnConstraints(CELL_WIDTH));
        mapGrid.getRowConstraints().add(new RowConstraints(CELL_HEIGHT));

        for(int i = 0; i < mapWidth; i++){
            Label label = new Label(String.format("%d", i + lowerX));
            GridPane.setHalignment(label, HPos.CENTER);
            mapGrid.getColumnConstraints().add(new ColumnConstraints(CELL_WIDTH));
            mapGrid.add(label, i + 1, 0);
        }

        for(int i = 0; i < mapHeight; i++){
            Label label = new Label(String.format("%d", upperY - i));
            GridPane.setHalignment(label, HPos.CENTER);
            mapGrid.getRowConstraints().add(new RowConstraints(CELL_HEIGHT));
            mapGrid.add(label, 0, i + 1);
        }

        Label yx = new Label("y\\x");
        mapGrid.add(yx, 0, 0);
        GridPane.setHalignment(yx, HPos.CENTER);
        mapGrid.setAlignment(Pos.CENTER);

        for (WorldElement element : worldMap.getElements()) {
            int x = element.getPosition().getX();
            int y = element.getPosition().getY();
//            Label label = new Label(String.format("%s", element));
            WorldElementBox worldElementBox = new WorldElementBox(element);
            mapGrid.add(worldElementBox.getBox(), x - lowerX + 1, upperY - y + 1);
            GridPane.setHalignment(worldElementBox.getBox(), HPos.CENTER);
        }
    }

    @Override
    public void mapChanged(WorldMap worldMap, String message) {
        Platform.runLater(() -> {
            currentMoveLabel.setText(message);
            drawMap();
        });
    }

    public void onSimulationStartClicked(ActionEvent actionEvent) {
        List<Vector2d> startPositions = List.of(new Vector2d(-2, -2), new Vector2d(3, 4));
        List<MoveDirection> moves = OptionsParser.parseOptions(movesInput.getText().split(" "));
        Simulation simulationRect = new Simulation(startPositions, moves, worldMap);
        SimulationEngine simulationEngine = new SimulationEngine(List.of(simulationRect));
        simulationEngine.runAsync();
    }

    private void clearGrid() {
        mapGrid.getChildren().retainAll(mapGrid.getChildren().get(0)); // hack to retain visible grid lines
        mapGrid.getColumnConstraints().clear();
        mapGrid.getRowConstraints().clear();
    }
}
