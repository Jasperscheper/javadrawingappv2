package com.example.drawingappv2.shapes;

import com.example.drawingappv2.ApplicationSettings;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Rectangle;

import java.util.HashMap;

public final class ShapeFactory{

    public static double parseX(HashMap<String, String> description){
        return Double.parseDouble(description.get("x"));
    }

    public static double parseY(HashMap<String, String> description){
        return Double.parseDouble(description.get("y"));
    }

    public static String parseType(HashMap<String, String> description){
        return description.get("shapeType").toLowerCase();
    }

    public static double parseWidth(HashMap<String,String> description){
        var width = description.get("shapeWidth");
        return width != null ? parseDouble(width) : parseDouble(ApplicationSettings.getInstance().getKey("shapeWidth"));
    }

    public static double parseHeight(HashMap<String,String> description){
        var width = description.get("shapeHeight");
        return width != null ? parseDouble(width) : parseDouble(ApplicationSettings.getInstance().getKey("shapeHeight"));
    }

    public static double parseDouble(String value){
        return Double.parseDouble(value);
    }

    public static CustomShape CreateShape(HashMap<String, String> shapeDescription){
        return switch (parseType(shapeDescription)) {
            case "rectangle" -> new CustomRectangle(
                    new Rectangle(
                            parseX(shapeDescription),
                            parseY(shapeDescription),
                            parseWidth(shapeDescription),
                            parseHeight(shapeDescription)
                    ));
            case "circle" -> new CustomCircle(
                    new Circle(
                            parseX(shapeDescription),
                            parseY(shapeDescription),
                            parseWidth(shapeDescription) / 2
                    ));
//            case "ellipse" -> new ResizableEllipse(
//                    new Ellipse(
//                            parseX(shapeDescription),
//                            parseY(shapeDescription),
//                            parseDouble(ApplicationSettings.getInstance().getKey("ellipseRadius")),
//                            parseDouble(ApplicationSettings.getInstance().getKey("ellipseRadius"))
//                    ));
            default -> throw new IllegalStateException("Unexpected value");
        };
    }

}