package com.example.drawingappv2;

import com.example.drawingappv2.actions.SaveToFileOperation;
import com.example.drawingappv2.actions.ShapeOperationExecutor;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;

public class ApplicationMenu {


    public MenuBar menuBar = new MenuBar();
    public ApplicationMenu() {
       this.addMenus();
        System.out.println("Menu created");
    }

    public MenuBar getMenuBar() {
        return this.menuBar;
    }

    public void addMenus() {
        Menu shapes = new Menu("Shapes");

        MenuItem circle = new MenuItem("Circle");
        MenuItem rectangle = new MenuItem("Rectangle");
        MenuItem ellipse = new MenuItem("Ellipse");
        shapes.getItems().add(circle);
        shapes.getItems().add(rectangle);
        shapes.getItems().add(ellipse);
        this.menuBar.getMenus().add(shapes);

        EventHandler<ActionEvent> shapeEvent = e -> {
            System.out.println(((MenuItem)e.getSource()).getText());
            ApplicationSettings.getInstance().setKey("shapeType", ((MenuItem) e.getSource()).getText());
        };

        // add event
        circle.setOnAction(shapeEvent);
        rectangle.setOnAction(shapeEvent);
        ellipse.setOnAction(shapeEvent);

        Menu fileOperations = new Menu("File IO");
        MenuItem openFile = new MenuItem("Open file");
        MenuItem saveFile = new MenuItem("Save to file");

        fileOperations.getItems().add(openFile);
        fileOperations.getItems().add(saveFile);

        EventHandler<ActionEvent> saveEvent = e -> {
            try {

                ShapeOperationExecutor.getInstance().executeOperation(new SaveToFileOperation());
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        };

        saveFile.setOnAction(saveEvent);

        this.menuBar.getMenus().add(fileOperations);
    }
}
