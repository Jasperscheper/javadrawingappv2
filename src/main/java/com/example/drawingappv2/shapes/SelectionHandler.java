package com.example.drawingappv2.shapes;

import javafx.scene.input.MouseEvent;

public class SelectionHandler {

    private SelectableShape shape;

    public SelectionHandler(SelectableShape shape){
        this.shape = shape;
        this.addClickHandler();
    }

    private void addClickHandler(){
        this.shape.getShape().addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent event) -> {
            this.shape.toggleSelected();
        });
    }
}
