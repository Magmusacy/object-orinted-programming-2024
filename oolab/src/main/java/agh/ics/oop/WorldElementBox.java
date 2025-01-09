package agh.ics.oop;

import agh.ics.oop.model.WorldElement;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;


public class WorldElementBox {
    private VBox box;

    public WorldElementBox(WorldElement worldElement) {
        Image image = new Image(worldElement.getImageName());

        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(20);
        imageView.setFitHeight(20);

        Label positionLabel = new Label(worldElement.getPosition().toString());
        box = new VBox();
        box.getChildren().addAll(imageView, positionLabel);
        box.setAlignment(Pos.CENTER);
    }

    public VBox getBox() {
        return box;
    }
}
