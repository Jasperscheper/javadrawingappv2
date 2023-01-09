//package com.example.drawingappv2.actions;
//
//import com.jasperscheper.drawingapp.actions.interfaces.Command;
//import com.jasperscheper.drawingapp.shapegroups.ShapeGroup;
//import javafx.scene.input.MouseEvent;
//
//public class MoveGroupCommand implements Command {
//
//    ShapeGroup target;
//    MouseEvent event;
//
//    double originalX;
//    double originalY;
//
//    public MoveGroupCommand(ShapeGroup target, MouseEvent event){
//        this.target = target;
//        this.event = event;
//    }
//
//    @Override
//    public String execute() {
//        //commit changes to LayoutX and LayoutY
//        this.originalX = target.getNode().getLayoutX();
//        this.originalY = target.getNode().getLayoutY();
//
//        var children = target.children;
//
//        for(var child: children){
//            child.getNode().setLayoutX(event.getSceneX() - event.getX());
//            child.getNode().setLayoutY(event.getSceneY() - event.getY());
//        }
//
//        target.getNode().setLayoutX(event.getSceneX() - event.getX());
//        target.getNode().setLayoutY(event.getSceneY() - event.getY());
//
//        //clear changes from TranslateX and TranslateY
//        target.getNode().setTranslateX(0);
//        target.getNode().setTranslateY(0);
//
//        target.dragController.setDraggable(false);
//        return "";
//    }
//
//    @Override
//    public void undo() {
//        target.getNode().setLayoutX(this.originalX);
//        target.getNode().setLayoutY(this.originalY);
//    }
//
//    public void redo(){
//        this.execute();
//    }
//}
