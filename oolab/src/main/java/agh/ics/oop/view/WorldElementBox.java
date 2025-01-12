package agh.ics.oop.view;

import agh.ics.oop.model.WorldElement;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import java.util.HashMap;
import java.util.Map;


public class WorldElementBox extends VBox{
    private static final Map<String, Image> imageMemo = new HashMap<>();

    private static Image loadImage(String imageName) {
        if (imageMemo.containsKey(imageName)) {
            return imageMemo.get(imageName);
        }

        Image image = new Image(imageName);
        imageMemo.put(imageName, image);
        return image;
    }

    public WorldElementBox(WorldElement worldElement) {
        Image image = loadImage(worldElement.getImageName());

        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(20);
        imageView.setFitHeight(20);

        Label positionLabel = new Label(worldElement.getPosition().toString());
        this.getChildren().addAll(imageView, positionLabel);
        this.setAlignment(Pos.CENTER);
    }

    public VBox getBox() {
        return this;
    }
}
