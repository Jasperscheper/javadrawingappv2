package com.example.drawingappv2;

import com.example.drawingappv2.shapes.DraggableShape;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        ApplicationWindow window = new ApplicationWindow();
        window.setSize(700);
        window.setTitle("Grafische editor");

        Scene drawingScene = new Scene(DrawingPane.getInstance());
        drawingScene.getRoot().requestFocus();
        drawingScene.getRoot().setTranslateX(drawingScene.getWidth() / 2);
        drawingScene.getRoot().setTranslateY(drawingScene.getHeight() / 2);
        VBox vbox = new VBox();
        vbox.getChildren().add(new ApplicationMenu().getMenuBar());
        DrawingPane.getInstance().getChildren().add(vbox);
        window.setScene(drawingScene);
        DrawingPane.getInstance().test();

//        ShapeOperationExecutor.getInstance().executeOperation(new OpenTextFileOperation("src/instructions.txt"));
        window.show();
    }

    public static void main(String[] args) {
        launch();
    }
}