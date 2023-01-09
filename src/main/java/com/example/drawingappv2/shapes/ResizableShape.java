package com.example.drawingappv2.shapes;

import com.example.drawingappv2.interfaces.IResizableShape;
import javafx.scene.Cursor;
import javafx.scene.shape.Shape;

public abstract class ResizableShape extends DraggableShape implements IResizableShape {

    ResizeController resizeController;

    ResizableShape(Shape shape) {
        super(shape);
        resizeController = new ResizeController(this);
    }

    @Override
    public Shape getShape() {
        return this.shape;
    }

    public boolean isResizable() {
        return !this.isSelected();
    }

    public abstract double getHeight();
    abstract double getWidth();
    public abstract void setHeight(double height);

}
