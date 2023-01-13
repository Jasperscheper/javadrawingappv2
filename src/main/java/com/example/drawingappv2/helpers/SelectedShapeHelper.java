package com.example.drawingappv2.helpers;

import com.example.drawingappv2.shapes.CustomShape;

import java.util.ArrayList;

public class SelectedShapeHelper implements IShapeHelper{

    ArrayList<CustomShape> selectedShapes = new ArrayList<CustomShape>();

    public static AddedShapeHelper INSTANCE;

    public static AddedShapeHelper getInstance() {

        if(INSTANCE == null) {
            INSTANCE = new AddedShapeHelper();
        }
        return INSTANCE;
    }

    public void addShape(CustomShape shape) {
        selectedShapes.add(shape);
    }

    public void removeShape(CustomShape shape){
        selectedShapes.remove(shape);
    }

    public ArrayList<CustomShape> getShapes(){
        return this.selectedShapes;
    }
}
