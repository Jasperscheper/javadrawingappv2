package com.example.drawingappv2.shapes;

import com.example.drawingappv2.interfaces.ICustomShape;
import com.example.drawingappv2.interfaces.IDragController;
import com.example.drawingappv2.interfaces.ISelectableShape;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.Cursor;
import javafx.scene.shape.Shape;

public abstract class DraggableShape extends SelectableShape implements ISelectableShape, ICustomShape {

    IDragController dragController;
    SimpleBooleanProperty isDraggable = new SimpleBooleanProperty();

    DraggableShape(Shape shape){
        super(shape);
        dragController = new DragController(this);
        this.isDraggable.set(false);
    }

    @Override
    public Shape getShape() {
        return this.shape;
    }

    public boolean isDraggable() {
        return this.isDraggable.get();
    }

    public void toggleDraggable() {
        System.out.println("Shape toggled draggable");
        this.setDraggable(!this.isDraggable.get());

        if(this.isDraggable()){
            this.getShape().setCursor(Cursor.MOVE);
        } else {
            this.getShape().setCursor(Cursor.DEFAULT);
        }
    }

    public void setDraggable(boolean draggable) {
        isDraggable.set(draggable);
    }
}
