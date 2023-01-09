package com.example.drawingappv2;

import com.example.drawingappv2.actions.ShapeOperationExecutor;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class DrawingPaneKeyboardController {

    private DrawingPane pane;

    public DrawingPaneKeyboardController(DrawingPane pane){
        this.pane = pane;
        this.addKeyboardHandlers();
    }

    public void addKeyboardHandlers() {
        this.pane.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.isControlDown() && event.getCode() == KeyCode.Z) {
                    ShapeOperationExecutor.getInstance().undo();
                } else if (event.isControlDown() && event.getCode() == KeyCode.Y) {
                    ShapeOperationExecutor.getInstance().redo();
                }
            }
        });
    }
}
