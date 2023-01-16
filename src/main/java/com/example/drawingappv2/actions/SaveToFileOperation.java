package com.example.drawingappv2.actions;


import com.example.drawingappv2.ApplicationSettings;
import com.example.drawingappv2.actions.interfaces.Command;
import com.example.drawingappv2.helpers.AddedShapeHelper;
import com.example.drawingappv2.helpers.ShapeHelper;
import com.example.drawingappv2.shapes.CustomShape;
import com.example.drawingappv2.shapes.ShapeGroup;
import javafx.scene.Node;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class SaveToFileOperation implements Command {

    public FileWriter fileWriter;

    public SaveToFileOperation() throws IOException {
        fileWriter = new FileWriter("drawing.txt");
    }

    @Override
    public String execute() throws IOException {

//
        System.out.println("Trying to save to file.");

        var drawingFrameChildren = AddedShapeHelper.getInstance().getShapes();

        for(Node node : drawingFrameChildren){
            var shapeType = node.getClass().getSimpleName();

            if(ApplicationSettings.applicationShapes.containsKey(shapeType)){

                if(shapeType.equals("ShapeGroup")){
                    traverseGroup(node, 0);
                } else {
                    fileWriter.write(String.format("%s\n", ShapeHelper.getInstructions(node)));
                }
            }
        }

        fileWriter.close();
        return null;
    }

    @Override
    public void undo() {

    }

    @Override
    public void redo() {

    }

    void traverseGroup(Node shape, Integer indent) throws IOException {

        if(shape.getClass().getSimpleName().equals("ShapeGroup")){
            // we still have children, let's recurse
            ShapeGroup group = (ShapeGroup) shape;
            var children = filterChildren(group);
            var str = String.format( "group %s\n", children.size());
            fileWriter.write(leftPadding(str, indent));

            for(var child : children){
                if(child.getClass().getSimpleName().equals("ShapeGroup")) {
                    traverseGroup(child, indent + 1);
                }
                fileWriter.write(leftPadding(String.format("%s\n", ShapeHelper.getInstructions(child)), indent + 1));
            }
        }
    }

    ArrayList<Node> filterChildren(ShapeGroup group){
        ArrayList<Node> result = new ArrayList<>();
        for( Node node : group.getChildren()){
            if(!node.getClass().getSimpleName().equals("Region")){
                result.add(node);
            }
        }
        return result;
    }

    // Function to perform left padding
    String leftPadding(String str, int indent){
        StringBuilder builder = new StringBuilder();


        for(int i = 0; i <= (indent * 4); i++){
            builder.append(' ');
        }
        builder.append(str);

        return builder.toString();
    }
}
