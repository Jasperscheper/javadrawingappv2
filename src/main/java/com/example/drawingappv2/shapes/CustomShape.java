package com.example.drawingappv2.shapes;

import com.example.drawingappv2.interfaces.ICustomShape;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;

public abstract class CustomShape implements ICustomShape {

    public Shape shape;
    public Color shapeColor;

    public CustomShape(Shape shape){
        this.shape = shape;
        this.shapeColor = Color.LIGHTBLUE;
        this.shape.setFill(this.shapeColor);
    }

    public abstract Shape getShape();

}
