package com.example.drawingappv2.helpers;

import com.example.drawingappv2.shapes.CustomShape;

import java.util.ArrayList;

public class AddedShapeHelper {

    public static ArrayList<CustomShape> addedShapes = new ArrayList<CustomShape>();

    static AddedShapeHelper INSTANCE;

    public static AddedShapeHelper getInstance() {

        if(INSTANCE == null) {
            INSTANCE = new AddedShapeHelper();
        }
        return INSTANCE;
    }

    public void addShape(CustomShape shape) {
        addedShapes.add(shape);
    }

    public void removeShape(CustomShape shape){
        addedShapes.remove(shape);
    }
}
