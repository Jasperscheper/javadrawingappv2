package com.example.drawingappv2.interfaces;

public interface ISelectableShape {
    Boolean isSelected();
    void toggleSelected();
    void setSelected(boolean selected);

}
