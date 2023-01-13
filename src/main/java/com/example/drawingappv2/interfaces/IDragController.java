package com.example.drawingappv2.interfaces;

import javafx.beans.property.SimpleBooleanProperty;

public interface IDragController {

    void addHandlers();
//    void toggleDraggable();
    SimpleBooleanProperty isDraggable = null;
    void setDraggable(Boolean value);

    Boolean isDraggable();
}
