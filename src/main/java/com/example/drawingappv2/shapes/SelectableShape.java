package com.example.drawingappv2.shapes;

import com.example.drawingappv2.helpers.SelectedShapeHelper;
import com.example.drawingappv2.interfaces.ISelectableShape;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;

public abstract class SelectableShape extends CustomShape implements ISelectableShape {

    public Boolean isSelected;
    public Color selectedColor = Color.RED;

    public SelectionHandler selectionHandler;

    public SelectableShape(Shape shape){
        super(shape);
        this.isSelected = false;
        this.selectionHandler = new SelectionHandler(this);
    }

    public Boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        this.isSelected = selected;
        this.setSelectedStyling();
    }

    public void toggleSelected(){
        this.setSelected(!this.isSelected());

        if(this.isSelected()){
            SelectedShapeHelper.getInstance().addShape(this.getShape());
            this.getShape().toFront();
        } else {
            SelectedShapeHelper.getInstance().removeShape(this.getShape());
        }
    }

    public void setSelectedStyling() {
        if(this.isSelected()){
            this.shape.setFill(this.selectedColor);
        } else {
            this.getShape().setFill(this.shapeColor);
        }
    }

    @Override
    public Shape getShape() {
        return this.shape;
    }
}
