package com.example.drawingappv2.shapes;
import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class CustomRectangle extends ResizableShape {

    public CustomRectangle(Shape shape) {
        super(shape);
    }

    @Override
    public Rectangle getShape() {
        return (Rectangle) this.shape;
    }

    @Override
    public double getHeight() {
        return this.getShape().getHeight();
    }

    @Override
    double getWidth() {
        return this.getShape().getWidth();
    }

    @Override
    public void setHeight(double height) {
        this.getShape().setHeight(height);
    }

    @Override
    public void setWidth(double width) {
        this.getShape().setWidth(width );
    }

    @Override
    public double calculateWidth(MouseEvent event, Point2D originalMouseLocation) {
        return this.getShape().getWidth() + event.getSceneX() - originalMouseLocation.getX();
    }

    @Override
    public double calculateHeight(MouseEvent event, Point2D originalMouseLocation) {
        return this.getShape().getHeight() + event.getSceneY() - originalMouseLocation.getY();
    }

}
