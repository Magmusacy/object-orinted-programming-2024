package agh.ics.oop;

import agh.ics.oop.model.*;
import agh.ics.oop.model.util.FileMapDisplay;
import agh.ics.oop.presenter.SimulationPresenter;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class SimulationApp extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.show();

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getClassLoader().getResource("simulation.fxml"));
        BorderPane viewRoot = loader.load();
        SimulationPresenter presenter = loader.getController();
        configureStage(primaryStage, viewRoot);
        primaryStage.show();

        WorldMap grassField = new GrassField(10);
        FileMapDisplay fileMapDisplay = new FileMapDisplay(grassField);
        presenter.setWorldMap(grassField);

        MapChangeListener mapChangeListener = new ConsoleMapDisplay();
        grassField.addObserver(mapChangeListener);
        grassField.addObserver((map, message) -> {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
            System.out.println(String.format("%s : %s", dtf.format(LocalDateTime.now()), message));
        });
        grassField.addObserver(presenter);
        grassField.addObserver(fileMapDisplay);
    }

    private void configureStage(Stage primaryStage, BorderPane viewRoot) {
        var scene = new Scene(viewRoot);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Simulation app");
        primaryStage.minWidthProperty().bind(viewRoot.minWidthProperty());
        primaryStage.minHeightProperty().bind(viewRoot.minHeightProperty());
    }
}
