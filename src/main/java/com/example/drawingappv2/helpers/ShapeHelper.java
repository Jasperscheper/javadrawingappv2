package com.example.drawingappv2.helpers;

import com.example.drawingappv2.shapes.CustomCircle;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class ShapeHelper {

    private static Shape shape;

    public static String getInstructions(Node node){

        String shapeName = node.getClass().getSimpleName().toLowerCase();

        if (!shapeName.equals("shapegroup")){
            shape = (Shape) node;
        }

        Bounds b = shape.getBoundsInLocal();

        switch(shapeName){
            case "circle":
                shape = (Circle) node;
                return String.format("%s %s %s %s %s", shapeName, (int) b.getCenterX(), (int) b.getCenterY(), (int) b.getHeight(), (int) b.getWidth());
            default:
                return String.format("%s %s %s %s %s", shapeName, (int) b.getMinX(), (int) b.getMinY(), (int) b.getHeight(), (int) b.getWidth());

        }
    }
}
