package com.example.drawingappv2.actions;

import com.example.drawingappv2.DrawingPane;
import com.example.drawingappv2.actions.interfaces.Command;
import com.example.drawingappv2.shapes.CustomShape;

public class AddShapeCommand implements Command {
    public CustomShape shape;

    public  AddShapeCommand(CustomShape shape){
        this.shape = shape;
    }

    @Override
    public void undo() {
        DrawingPane.getInstance().getChildren().remove(this.shape.getShape());
    }

    public void redo() {
        DrawingPane.getInstance().getChildren().add(this.shape.getShape());
    }

    @Override
    public String execute() {
//        AddedShapeHelper.getInstance().add(shape);
        System.out.println("Placing shape");
        DrawingPane.getInstance().getChildren().add(shape.getShape());
        System.out.println(DrawingPane.getInstance().getChildren().size());
        return null;
    }
}
