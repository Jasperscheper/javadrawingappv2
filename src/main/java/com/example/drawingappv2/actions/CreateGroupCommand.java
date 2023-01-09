//package com.example.drawingappv2.actions;
//
//import com.jasperscheper.drawingapp.DrawingPane;
//import com.jasperscheper.drawingapp.actions.interfaces.Command;
//import com.jasperscheper.drawingapp.helpers.AddedShapeHelper;
//import com.jasperscheper.drawingapp.helpers.SelectedShapeHelper;
//import com.jasperscheper.drawingapp.shapegroups.ShapeGroup;
//import com.jasperscheper.drawingapp.shapes.CustomShape;
//import javafx.scene.Group;
//
//import java.util.ArrayList;
//
//public class CreateGroupCommand implements Command {
//
//    public ShapeGroup group;
//    public ArrayList<CustomShape> nodes = new ArrayList<>();
//    public CreateGroupCommand(ArrayList<CustomShape> nodes){
//        this.nodes = nodes;
//        this.group = new ShapeGroup(new Group(), (ArrayList<CustomShape>) nodes.clone());
//        this.group.children = this.nodes;
//    }
//
//    public CreateGroupCommand(ShapeGroup group){
//        this.group = group;
//        this.nodes = (ArrayList<CustomShape>) this.group.children.clone();
//    }
//
//    public void removeShapes(){
//        for (CustomShape s : nodes){
//            DrawingPane.getInstance().getChildren().remove(s.destroyShape());
//        }
//    }
//    @Override
//    public String execute()  {
//
//        // remove old shapes
//        removeShapes();
//
//        // add the group
//        DrawingPane.getInstance().getChildren().add(group.getNode());
//        AddedShapeHelper.getInstance().add(group);
//        SelectedShapeHelper.getInstance().unselectAll();
//        return null;
//    }
//
//    @Override
//    public void undo() {
//        DrawingPane.getInstance().getChildren().remove(this.group.getNode());
//        for (CustomShape s : this.nodes){
//           DrawingPane.getInstance().getChildren().add(s.getShape());
//        }
//    }
//
//    @Override
//    public void redo() {
//        removeShapes();
//        DrawingPane.getInstance().getChildren().add(this.group.getNode());
//    }
//}
