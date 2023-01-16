package com.example.drawingappv2.actions;

import com.example.drawingappv2.DrawingPane;
import com.example.drawingappv2.actions.interfaces.Command;
import com.example.drawingappv2.helpers.AddedShapeHelper;
import com.example.drawingappv2.shapes.ShapeGroup;
import javafx.scene.Group;
import javafx.scene.Node;

import java.util.ArrayList;

public class CreateGroupCommand implements Command {

    public ShapeGroup group;
    public ArrayList<Node> nodes;

    public CreateGroupCommand(ArrayList<Node> nodes){
        this.nodes = nodes;
    }
    @Override
    public String execute()  {
        this.group = new ShapeGroup(nodes);
        DrawingPane.getInstance().getChildren().add(this.group);
        AddedShapeHelper.getInstance().getShapes().add(this.group);
        return "";
    }

    @Override
    public void undo() {

        for( Node node : this.group.getChildren()){
            group.removeNode(node);
        }

        DrawingPane.getInstance().getChildren().remove(group);
    }

    @Override
    public void redo() {
    }
}
