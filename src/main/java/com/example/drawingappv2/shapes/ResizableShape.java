package com.example.drawingappv2.shapes;

import javafx.scene.Cursor;
import javafx.scene.shape.Shape;

public abstract class ResizableShape extends DraggableShape {

    ResizeController resizeController;
    boolean isDraggable;

    ResizableShape(Shape shape) {
        super(shape);
        resizeController = new ResizeController(this);
    }

    @Override
    Shape getShape() {
        return this.shape;
    }

    public boolean isResizable() {
        return !this.isSelected();
    }

    abstract double getHeight();
    abstract double getWidth();
    abstract void setHeight(double height);
}

