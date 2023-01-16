package com.example.drawingappv2.shapes;

import com.example.drawingappv2.interfaces.ICustomShape;
import com.example.drawingappv2.interfaces.IDragController;
import com.example.drawingappv2.interfaces.ISelectableShape;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.Cursor;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Shape;

public abstract class DraggableShape extends SelectableShape implements ISelectableShape, ICustomShape {

    IDragController dragController;
    SimpleBooleanProperty isDraggable = new SimpleBooleanProperty();

    DraggableShape(Shape shape){
        super(shape);
        dragController = new DragController(this.getShape());
        this.getShape().addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent event) -> this.toggleDraggable());
        this.dragController.setDraggable(false);
    }

    @Override
    public Shape getShape() {
        return this.shape;
    }

    public Boolean isDraggable() {
        return this.dragController.isDraggable();
    }

    public void toggleDraggable() {
        this.dragController.setDraggable(!this.isDraggable());

        if(this.isDraggable()){
            this.getShape().setCursor(Cursor.MOVE);
        } else {
            this.getShape().setCursor(Cursor.DEFAULT);
        }
    }

    public void freeze() {
        this.dragController.setDraggable(false);
    }
}
