package com.example.drawingappv2.interfaces;

import javafx.geometry.Point2D;
import javafx.scene.input.MouseEvent;

public interface IResizableShape {

    boolean isResizable();
    void setHeight(double height);
    void setWidth(double width);
    double calculateHeight(MouseEvent event, Point2D originalLocation);
    double calculateWidth(MouseEvent event, Point2D originalLocation);
}
