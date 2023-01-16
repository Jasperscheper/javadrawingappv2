package com.example.drawingappv2;
import com.example.drawingappv2.actions.AddShapeCommand;
import com.example.drawingappv2.actions.CreateGroupCommand;
import com.example.drawingappv2.actions.ShapeOperationExecutor;
import com.example.drawingappv2.helpers.AddedShapeHelper;
import com.example.drawingappv2.helpers.SelectedShapeHelper;
import com.example.drawingappv2.shapes.*;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.HashMap;

public class DrawingPane extends Pane {

    static DrawingPane INSTANCE;
    static DrawingPaneKeyboardController controller;


    public DrawingPane(){
        this.addEventHandler(MouseEvent.MOUSE_PRESSED, handleMouseClick());
        this.addEventHandler(KeyEvent.KEY_PRESSED, handleGroupButton());
    }

    public static DrawingPane getInstance() {
        if(INSTANCE == null){
            INSTANCE = new DrawingPane();
            controller = new DrawingPaneKeyboardController(INSTANCE);
        }
        return INSTANCE;
    }

    public void placeShape(Point2D location) throws Exception {

        System.out.println("Place shape called");

        if (this.canPlaceShape(location)) {

            System.out.println("placing shape.");
            CustomShape shape = ShapeFactory.CreateShape(new HashMap<>(){
                {
                    put("shapeType", ApplicationSettings.getInstance().getKey("shapeType"));
                    put("x", String.valueOf(location.getX()));
                    put("y", String.valueOf(location.getY()));
                }});

            ShapeOperationExecutor.getInstance().executeOperation(new AddShapeCommand(shape));
        }
    }

    private Boolean canPlaceShape(Point2D location) {

        for (Node child : AddedShapeHelper.getInstance().getShapes()) {
            if (child.contains(location)) {
                return false;
            }
        }
        return true;
    }

    public EventHandler<MouseEvent> handleMouseClick(){
        return new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event){
                try {
                    if(event.getButton().equals(MouseButton.PRIMARY)) {
                        if (event.getClickCount() == 2) {
                            placeShape(new Point2D(event.getX(), event.getY()));
                        }
                    }
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        };
    }

    public EventHandler<KeyEvent> handleGroupButton() {
        return new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if(keyEvent.getCode() == KeyCode.G){
                    System.out.println("Creating group");

                    try {
                        ShapeOperationExecutor.getInstance().executeOperation(new CreateGroupCommand(SelectedShapeHelper.getInstance().getShapes()));
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        };
    }
}
