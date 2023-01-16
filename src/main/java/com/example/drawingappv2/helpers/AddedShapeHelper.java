package com.example.drawingappv2.helpers;

import com.example.drawingappv2.shapes.CustomShape;
import javafx.scene.Node;

import java.util.ArrayList;

public class AddedShapeHelper {

     private ArrayList<Node> addedShapes = new ArrayList<Node>();

     static AddedShapeHelper INSTANCE;

    public static AddedShapeHelper getInstance() {

        if(INSTANCE == null) {
            INSTANCE = new AddedShapeHelper();
        }
        return INSTANCE;
    }

    public void addShape(Node shape) {
        addedShapes.add(shape);
    }

    public void removeShape(Node shape){
        addedShapes.remove(shape);
    }

    public ArrayList<Node> getShapes(){
        return this.addedShapes;
    }
}
