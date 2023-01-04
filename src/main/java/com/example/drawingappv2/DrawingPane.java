package com.example.drawingappv2;
import com.example.drawingappv2.shapes.CustomCircle;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;

import java.util.ArrayList;
import java.util.Arrays;

public class DrawingPane extends Pane {

    static DrawingPane INSTANCE;

    public DrawingPane(){
        this.addEventHandler(MouseEvent.MOUSE_PRESSED, handleMouseClick());
    }

    public static DrawingPane getInstance() {
        if(INSTANCE == null){
            return new DrawingPane();
        }
        return INSTANCE;
    }

    public void placeShape(Point2D location){

        if (this.canPlaceShape(location)) {
            CustomCircle circle = new CustomCircle(new Circle(location.getX(), location.getY(), 50));
            this.getChildren().add(circle.getShape());
        }

    }

    private Boolean canPlaceShape(Point2D location) {

        Boolean isHit = false;
        for (Node node : this.getChildren()) {
            if(!isHit){
                if (node.intersects(location.getX(), location.getY(), 1, 1)) {
                    isHit = true;

                }
            } else {
                break;
            }
        }
        return !isHit;
    }

    public EventHandler<MouseEvent> handleMouseClick(){
        return new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event){
                placeShape(new Point2D(event.getX(), event.getY()));
            }
        };
    }



}
