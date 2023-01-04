package com.example.drawingappv2.shapes;

import javafx.scene.input.MouseEvent;

public class DragController {

    private DraggableShape shape;
    private boolean isDraggable;

    private double initialMouseX;
    private double initialMouseY;

    public DragController(DraggableShape shape){
        this(shape, false);
    }

    public DragController(DraggableShape shape, boolean isDraggable){
        this.shape = shape;
        this.isDraggable = isDraggable;
        this.addHandlers();
    }

    private void addHandlers() {

        this.shape.getShape().addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent event) -> {
            System.out.println("Clicked");
            this.shape.toggleDraggable();
            this.initialMouseX = event.getSceneX();
            this.initialMouseY = event.getSceneY();
        });

        this.shape.getShape().addEventHandler(MouseEvent.MOUSE_DRAGGED, (MouseEvent event) -> {
            System.out.println("Clicked");
            if(this.shape.isDraggable()){
                System.out.println("Item is draggable");

                double newSceneX = event.getSceneX() - initialMouseX;
                double newSceneY = event.getSceneY() - initialMouseY;

                // set the new scene position
                this.shape.getShape().setLayoutX(newSceneX);
                this.shape.getShape().setLayoutY(newSceneY);
            }
        });
    }

}
