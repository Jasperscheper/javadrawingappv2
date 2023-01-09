package com.example.drawingappv2.actions;

import com.example.drawingappv2.DrawingPane;
import com.example.drawingappv2.actions.interfaces.Command;
import com.example.drawingappv2.helpers.AddedShapeHelper;
import com.example.drawingappv2.shapes.CustomShape;

public class AddShapeCommand implements Command {
    public CustomShape shape;

    public  AddShapeCommand(CustomShape shape){
        this.shape = shape;
    }

    @Override
    public void undo() {
        AddedShapeHelper.getInstance().removeShape(this.shape);
        DrawingPane.getInstance().getChildren().remove(this.shape.getShape());
    }

    public void redo() {
        this.execute();
    }

    @Override
    public String execute() {
        AddedShapeHelper.getInstance().addShape(shape);
        DrawingPane.getInstance().getChildren().add(shape.getShape());
        return null;
    }
}
