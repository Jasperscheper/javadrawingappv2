package com.example.drawingappv2.helpers;

import com.example.drawingappv2.shapes.CustomShape;

import java.util.ArrayList;

public interface IShapeHelper {

    void addShape(CustomShape shape);
    void removeShape(CustomShape shape);
    ArrayList<CustomShape> getShapes();
}
