package com.example.drawingappv2.shapes;

import com.example.drawingappv2.interfaces.ICustomShape;
import com.example.drawingappv2.interfaces.IDragController;
import com.example.drawingappv2.interfaces.ISelectableShape;
import javafx.scene.Cursor;
import javafx.scene.shape.Shape;

public abstract class DraggableShape extends SelectableShape implements ISelectableShape, ICustomShape {

    IDragController dragController;
    boolean isDraggable;

    DraggableShape(Shape shape){
        super(shape);
        dragController = new DragController(this, false);
    }

    @Override
    public Shape getShape() {
        return this.shape;
    }

    public boolean isDraggable() {
        return isDraggable;
    }

    public void toggleDraggable() {
        System.out.println("Shape toggled draggable");
        this.setDraggable(!this.isDraggable);
        this.dragController.toggleDraggable();

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
