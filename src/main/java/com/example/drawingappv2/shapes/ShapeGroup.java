package com.example.drawingappv2.shapes;

import com.example.drawingappv2.DrawingPane;
import javafx.scene.Group;
import javafx.scene.Node;

import java.util.ArrayList;
import java.util.HashMap;

public class ShapeGroup extends Group {

    private HashMap<Integer, Node> nodes = new HashMap<Integer, Node>();
    DragController dragController;

    public ShapeGroup(ArrayList<Node> children) {
       this.dragController = new DragController(this, true);

       for(Node child : children){
           this.addNode(child);
       }
    }

    public void addNode(Node node) {
        nodes.put(node.hashCode(), node);
        this.getChildren().add(node);
        DrawingPane.getInstance().getChildren().remove(node);
    }

    public void removeNode(Node node) {
        this.getChildren().remove(node);
        this.nodes.remove(node.hashCode());
        DrawingPane.getInstance().getChildren().add(nodes.get(node.hashCode()));
    }
}
