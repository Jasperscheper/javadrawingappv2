package com.example.drawingappv2.shapes;

import com.example.drawingappv2.interfaces.ICustomShape;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;

public class CustomCircle extends ResizableShape {

    public CustomCircle(Shape shape) {
        super(shape);
    }

    @Override
    public Circle getShape() {
        return (Circle) this.shape;
    }

    @Override
    public double getHeight() {
        return this.getShape().getRadius();
    }

    @Override
    double getWidth() {
        return this.getShape().getRadius();
    }

    @Override
    public void setHeight(double height) {
        this.getShape().setRadius(height /2 );
    }
}
