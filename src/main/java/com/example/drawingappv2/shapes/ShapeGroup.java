package com.example.drawingappv2.shapes;

import com.example.drawingappv2.DrawingPane;
import com.example.drawingappv2.helpers.AddedShapeHelper;
import com.example.drawingappv2.helpers.SelectedShapeHelper;
import javafx.application.Platform;
import javafx.beans.binding.DoubleBinding;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.SelectionModel;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.HashMap;

public class ShapeGroup extends Group {

    private SimpleBooleanProperty isSelected = new SimpleBooleanProperty();

    public HashMap<Integer, Node> nodes = new HashMap<Integer, Node>();

    private Region region = new Region();

    public Circle handle;

    private double x;
    private double y;

    public ShapeGroup(ArrayList<Node> children) {
        this.toFront();

        for(Node child : children){
            this.addNode(child);
        }

        Platform.runLater(() -> {
            this.addBorder();
//            this.addResizeHandles();

            this.addClickHandler();
            this.addHandle();
            this.setDragListeners();

        });
    }

    private void addClickHandler() {
        this.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent event) -> {
            this.isSelected.set(!this.isSelected.get());
        });

        this.isSelected.addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                region.setBackground(Background.fill(Color.GREEN));
                region.setOpacity(0.5);
                SelectedShapeHelper.getInstance().addShape(this);
            } else {
                region.setBackground(Background.fill(Color.TRANSPARENT));
                region.setOpacity(1.0);
            }
        });
    }

    public void addNode(Node node) {
        nodes.put(node.hashCode(), node);
        DrawingPane.getInstance().getChildren().remove(node);
        AddedShapeHelper.getInstance().getShapes().remove(node);
        this.getChildren().add(node);
    }

    public void removeNode(Node node) {
        this.getChildren().remove(node);
        this.nodes.remove(node.hashCode());
        DrawingPane.getInstance().getChildren().add(nodes.get(node.hashCode()));
        AddedShapeHelper.getInstance().getShapes().add(node);
    }

    public void addBorder(){
        Bounds b = this.getBoundsInParent();

        Border border = new Border((new BorderStroke(Color.BLACK,
                BorderStrokeStyle.DASHED, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        region.setBorder(border);

        region.setTranslateX(b.getMinX());
        region.setMinWidth(b.getWidth());
        region.setMinHeight(b.getHeight());
        region.setTranslateY(b.getMinY());
        this.getChildren().add(region);
    }

    private void addHandle(){
        Bounds b = this.getBoundsInParent();
        handle = new Circle(30, Color.RED);
        handle.setCenterX(b.getMinX());
        handle.setCenterY(b.getMaxY());
        getChildren().add(handle);
    }

    private void setDragListeners(){
        handle.setOnMousePressed(event -> {
            // Store the initial position of the mouse when it is pressed
            x = event.getSceneX();
            y = event.getSceneY();
        });

        handle.setOnMouseDragged(event -> {
            // Calculate the new position of the group based on the mouse movement
            double offsetX = event.getSceneX() - x;
            double offsetY = event.getSceneY() - y;

            this.setTranslateX(this.getTranslateX() + offsetX);
            this.setTranslateY(this.getTranslateY() + offsetY);

            // Update the initial position of the mouse
            x = event.getSceneX();
            y = event.getSceneY();
        });
    }

    private void addResizeHandles() {
        Rectangle resizeHandleNW = new Rectangle(30, 30);
        resizeHandleNW.setFill(Color.BLUE);

        Bounds b = this.getBoundsInParent();
        resizeHandleNW.setX(b.getMinX());
        resizeHandleNW.setY(b.getMinY());

        Rectangle resizeHandleSE = new Rectangle(30, 30);

        resizeHandleSE.setX(b.getMaxX());
        resizeHandleSE.setY(b.getMaxY());

        resizeHandleNW.setCursor(Cursor.NW_RESIZE);
        resizeHandleSE.setCursor(Cursor.SE_RESIZE);



        this.getChildren().add(resizeHandleNW);
        this.getChildren().add(resizeHandleSE);

        resizeHandleNW.setOnMousePressed(event -> {
            x = event.getSceneX();
            y = event.getSceneY();
        });
    }


}
