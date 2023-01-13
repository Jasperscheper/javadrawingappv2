package com.example.drawingappv2.shapes;

import com.example.drawingappv2.actions.MoveShapeCommand;
import com.example.drawingappv2.actions.ShapeOperationExecutor;
import com.example.drawingappv2.interfaces.IDragController;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;

public class DragController implements IDragController {

    final Node target;

    SimpleBooleanProperty isDraggable = new SimpleBooleanProperty();

    double anchorX;
    double anchorY;
    final int ACTIVE = 1;
    final int INACTIVE = 0;
    int cycleStatus = INACTIVE;

    EventHandler<MouseEvent> setAnchor;
    EventHandler<MouseEvent> updatePositionOnDrag;
    EventHandler<MouseEvent> commitPositionOnRelease;


    public DragController(Node target) {
        this.target = target;
        addHandlers();
        this.createDraggableProperty();
    }

    public DragController(Node target, Boolean isDraggable){
        this(target);
        this.setDraggable(true);
    }


    public void addHandlers() {

        setAnchor = event -> {
            if (event.isPrimaryButtonDown()) {
                System.out.println("Clicked shape");
                cycleStatus = ACTIVE;
                anchorX = event.getSceneX();
                anchorY = event.getSceneY();
            }

            if (event.isSecondaryButtonDown()) {
                cycleStatus = INACTIVE;
                target.setTranslateX(0);
                target.setTranslateY(0);
            }
        };

        updatePositionOnDrag = event -> {
            if (cycleStatus != INACTIVE) {
                target.setTranslateX(event.getSceneX() - anchorX);
                target.setTranslateY(event.getSceneY() - anchorY);
            }
        };

        commitPositionOnRelease = event -> {
            if (cycleStatus != INACTIVE) {
                try {
                    ShapeOperationExecutor.getInstance().executeOperation(new MoveShapeCommand(target, event));
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        };
    }

    public void createDraggableProperty() {
        this.isDraggable.addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                target.addEventFilter(MouseEvent.MOUSE_PRESSED, setAnchor);
                target.addEventFilter(MouseEvent.MOUSE_DRAGGED, updatePositionOnDrag);
                target.addEventFilter(MouseEvent.MOUSE_RELEASED, commitPositionOnRelease);
            } else {
                target.removeEventFilter(MouseEvent.MOUSE_PRESSED, setAnchor);
                target.removeEventFilter(MouseEvent.MOUSE_DRAGGED, updatePositionOnDrag);
                target.removeEventFilter(MouseEvent.MOUSE_RELEASED, commitPositionOnRelease);
            }
        });
    }

    public void setDraggable(Boolean val){
        this.isDraggable.set(val);
    }
    public Boolean isDraggable(){
        System.out.println("setting isDraggable to" + this.isDraggable.get());
        return this.isDraggable.get();
    }

}