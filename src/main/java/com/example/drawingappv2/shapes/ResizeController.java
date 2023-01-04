package com.example.drawingappv2.shapes;

import javafx.scene.input.MouseEvent;

public class ResizeController {

    private ResizableShape shape;
    private boolean isResizable;

    private double initialMouseX;
    private double initialMouseY;

    public ResizeController(ResizableShape shape){
        this(shape, false);
    }

    public ResizeController(ResizableShape shape, boolean isDraggable){
        this.shape = shape;
        this.addHandlers();
    }

    private void addHandlers() {

        this.shape.getShape().addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent event) -> {
            System.out.println("Clicked");
            this.initialMouseX = event.getSceneX();
            this.initialMouseY = event.getSceneY();
        });

        this.shape.getShape().addEventHandler(MouseEvent.MOUSE_DRAGGED, (MouseEvent event) -> {
            if(this.shape.isResizable()){
                double dx = event.getX() - initialMouseX;
                double dy = event.getY() - initialMouseY;
                double newRadius = Math.sqrt(dx * dx + dy * dy) + shape.getHeight();
                newRadius = Math.max(newRadius, 0.0);
                this.shape.setHeight(newRadius);
            }
        });
    }

}
