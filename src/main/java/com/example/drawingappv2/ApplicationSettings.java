package com.example.drawingappv2;

import com.example.drawingappv2.shapes.CustomShape;

import java.util.HashMap;

public final class ApplicationSettings {

    public static String[] validShapes = {"circle", "rectangle"};
    private static ApplicationSettings INSTANCE;
    public static HashMap<String, String> settings = new HashMap<String, String>() {{
        put("shapeType", "circle");
        put("rectangleSize", "50");
        put("circleRadius", "25");
        put("ellipseRadius", "25");
        put("shapeHeight", "25");
        put("shapeWidth", "25");
        put("circleSizeIncrement", "5");
        put("rectangleSizeIncrement", "5");
        put("ellipseSizeIncrement", "5");
    }};

    public static HashMap<String, String> applicationShapes = new HashMap<String, String>() {{
        put("ShapeGroup", "group");
        put("Circle", "circle");
        put("Rectangle", "rectangle");
    }};

    public static CustomShape selectedShape;

    public static ApplicationSettings getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new ApplicationSettings();
        }

        return INSTANCE;
    }

    public void setKey(String key, String value){
        settings.put(key, value);
    }

    public String getKey(String key){
        return settings.get(key);
    }

}
