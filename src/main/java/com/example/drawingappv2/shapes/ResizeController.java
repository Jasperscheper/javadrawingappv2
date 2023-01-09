package com.example.drawingappv2.shapes;

import com.example.drawingappv2.actions.ResizeShapeCommand;
import com.example.drawingappv2.actions.ShapeOperationExecutor;
import javafx.scene.input.MouseEvent;

public class ResizeController {

    private ResizableShape shape;

    public ResizeController(ResizableShape shape){
        this(shape, false);
    }

    public ResizeController(ResizableShape shape, boolean isDraggable){
        this.shape = shape;
        this.addHandlers();
    }

    private void addHandlers() {

        this.shape.getShape().addEventHandler(MouseEvent.MOUSE_DRAGGED, (MouseEvent event) -> {
            if(this.shape.isResizable()){
                try {
                    ShapeOperationExecutor.getInstance().executeOperation(new ResizeShapeCommand(this.shape, event));
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

}