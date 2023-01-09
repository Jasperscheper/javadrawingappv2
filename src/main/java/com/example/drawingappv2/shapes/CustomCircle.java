package com.example.drawingappv2.shapes;
import javafx.geometry.Point2D;
import javafx.scene.input.MouseEvent;
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
    @Override
    public void setWidth(double width) {
        this.getShape().setRadius(width /2 );
    }

    @Override
    public double calculateWidth(MouseEvent event, Point2D originalLocation){
        return this.getShape().getRadius() + event.getSceneY() - originalLocation.getY();
    };

    @Override
    public double calculateHeight(MouseEvent event, Point2D originalLocation){
        return this.getShape().getRadius() + event.getSceneY() - originalLocation.getY();
    }
}
