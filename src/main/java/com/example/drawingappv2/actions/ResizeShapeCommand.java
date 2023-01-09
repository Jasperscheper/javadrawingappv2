package com.example.drawingappv2.actions;

import com.example.drawingappv2.actions.interfaces.Command;
import com.example.drawingappv2.shapes.ResizableShape;
import javafx.scene.input.MouseEvent;

public class ResizeShapeCommand implements Command {

    ResizableShape target;
    MouseEvent event;

    double originalX;
    double originalY;

    double originalHeight;

    public ResizeShapeCommand(ResizableShape target, MouseEvent event){
        this.target = target;
        this.event = event;
    }

    @Override
    public String execute() {
        //commit changes to LayoutX and LayoutY
        this.originalX = target.getShape().getLayoutX();
        this.originalY = target.getShape().getLayoutY();

        this.originalHeight = target.getHeight();

        double dx = event.getX() - originalX;
        double dy = event.getY() - originalY;
        double newRadius = Math.sqrt(dx * dx + dy * dy) + target.getHeight();
        newRadius = Math.max(newRadius, 0.0);
        this.target.setHeight(newRadius);

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
