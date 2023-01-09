package com.example.drawingappv2.shapes;

import com.example.drawingappv2.actions.ResizeShapeCommand;
import com.example.drawingappv2.actions.ShapeOperationExecutor;
import javafx.geometry.Point2D;
import javafx.scene.input.MouseEvent;

public class ResizeController {

    private ResizableShape shape;
    private Point2D originalLocation = new Point2D(0,0);

    public ResizeController(ResizableShape shape){
        this(shape, false);
    }

    public ResizeController(ResizableShape shape, boolean isDraggable){
        this.shape = shape;
        this.addHandlers();
    }

    private void addHandlers() {

        this.shape.getShape().setOnMouseClicked(event -> {
            // Get the mouse coordinates when the mouse button is pressed
            System.out.println("clicked resizable shape");
            originalLocation = new Point2D(event.getSceneX(), event.getSceneY());
            this.shape.getShape().toFront();
        });

        this.shape.getShape().addEventHandler(MouseEvent.MOUSE_DRAGGED, (MouseEvent event) -> {
            if(this.shape.isResizable()){
                try {

                    double newHeight = shape.calculateHeight(event, originalLocation);
                    double newWidth = shape.calculateHeight(event, originalLocation);

                    ShapeOperationExecutor.getInstance().executeOperation(new ResizeShapeCommand(this.shape, newHeight, newWidth));

                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

}