package com.example.drawingappv2.shapes;

import com.example.drawingappv2.interfaces.IResizableShape;
import javafx.geometry.Point2D;
import javafx.scene.Cursor;
import javafx.scene.input.MouseEvent;
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

    public abstract void setWidth(double width);

    public abstract double calculateHeight(MouseEvent location, Point2D originalLocation);

    public abstract double calculateWidth(MouseEvent location, Point2D originalLocation);
}
