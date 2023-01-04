package com.example.drawingappv2.shapes;

import javafx.scene.Cursor;
import javafx.scene.shape.Shape;

public class DraggableShape extends SelectableShape {

    DragController dragController;
    boolean isDraggable;

    DraggableShape(Shape shape){
        super(shape);
        dragController = new DragController(this);
    }

    @Override
    Shape getShape() {
        return this.shape;
    }

    public boolean isDraggable() {
        return isDraggable;
    }

    public void toggleDraggable() {
        this.setDraggable(!this.isDraggable);

        if(this.isDraggable()){
            this.getShape().setCursor(Cursor.MOVE);
        } else {
            this.getShape().setCursor(Cursor.DEFAULT);
        }
    }

    public void setDraggable(boolean draggable) {
        isDraggable = draggable;
    }
}
