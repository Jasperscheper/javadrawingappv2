package com.example.drawingappv2.actions;

import com.example.drawingappv2.actions.interfaces.Command;
import com.example.drawingappv2.shapes.ResizableShape;
import javafx.geometry.Point2D;

public class ResizeShapeCommand implements Command {

    ResizableShape target;
    double newHeight;
    double newWidth;

    double originalX;
    double originalY;

    double originalHeight;

    public ResizeShapeCommand(ResizableShape target, double height, double width){
        this.target = target;
        this.newHeight = height;
        this.newWidth = width;
    }

    @Override
    public String execute() {
        //commit changes to LayoutX and LayoutY
        this.originalX = target.getShape().getLayoutX();
        this.originalY = target.getShape().getLayoutY();
        this.originalHeight = target.getHeight();

        this.target.setHeight(newHeight);
        this.target.setWidth(newWidth);

        return "";
    }

    @Override
    public void undo() {
        target.setHeight(this.originalHeight);
    }

    public void redo(){
        this.execute();
    }
}
