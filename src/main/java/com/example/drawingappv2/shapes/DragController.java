package com.example.drawingappv2.shapes;

import com.example.drawingappv2.actions.MoveShapeCommand;
import com.example.drawingappv2.actions.ShapeOperationExecutor;
import com.example.drawingappv2.interfaces.IDragController;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.input.MouseEvent;

public class DragController implements IDragController {

    final DraggableShape target;


    double anchorX;
    double anchorY;
    final int ACTIVE = 1;
    final int INACTIVE = 0;
    int cycleStatus = INACTIVE;

    EventHandler<MouseEvent> setAnchor;
    EventHandler<MouseEvent> updatePositionOnDrag;
    EventHandler<MouseEvent> commitPositionOnRelease;



    public boolean isDraggable() {
        return this.target.isDraggable.get();
    }

    public DragController(DraggableShape target) {
        this.target = target;
        addHandlers();
        this.createDraggableProperty();

    }

//    public void toggleDraggable() {
//        this.setDraggable(!this.isDraggable());
//
//        if(this.isDraggable()){
//            this.target.getShape().setCursor(Cursor.MOVE);
//        } else {
//            this.target.getShape().setCursor(Cursor.DEFAULT);
//        }
//    }

    public void addHandlers() {

        this.target.getShape().addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent event) -> {
            // add shape click draggable
            this.target.toggleDraggable();
        });

        setAnchor = event -> {
            if (event.isPrimaryButtonDown()) {
                System.out.println("Clicked shape");
                cycleStatus = ACTIVE;
                anchorX = event.getSceneX();
                anchorY = event.getSceneY();
            }

            if (event.isSecondaryButtonDown()) {
                cycleStatus = INACTIVE;
                target.getShape().setTranslateX(0);
                target.getShape().setTranslateY(0);
            }
        };

        updatePositionOnDrag = event -> {
            if (cycleStatus != INACTIVE) {
                target.getShape().setTranslateX(event.getSceneX() - anchorX);
                target.getShape().setTranslateY(event.getSceneY() - anchorY);
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
        this.target.isDraggable.addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                target.getShape().addEventFilter(MouseEvent.MOUSE_PRESSED, setAnchor);
                target.getShape().addEventFilter(MouseEvent.MOUSE_DRAGGED, updatePositionOnDrag);
                target.getShape().addEventFilter(MouseEvent.MOUSE_RELEASED, commitPositionOnRelease);
            } else {
                target.getShape().removeEventFilter(MouseEvent.MOUSE_PRESSED, setAnchor);
                target.getShape().removeEventFilter(MouseEvent.MOUSE_DRAGGED, updatePositionOnDrag);
                target.getShape().removeEventFilter(MouseEvent.MOUSE_RELEASED, commitPositionOnRelease);
            }
        });
    }

    public void setDraggable(Boolean val){
        this.target.isDraggable.set(val);
    }

}