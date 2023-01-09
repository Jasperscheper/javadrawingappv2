//package com.example.drawingappv2.actions;
//
//
//import com.example.drawingappv2.actions.interfaces.Command;
//import com.example.drawingappv2.shapes.CustomShape;
//
//import java.io.FileWriter;
//import java.io.IOException;
//import java.util.ArrayList;
//
//public class SaveToFileOperation implements Command {
//
//    public FileWriter fileWriter;
//
//    public SaveToFileOperation() throws IOException {
//        fileWriter = new FileWriter("drawing.txt");
//    }
//
//    @Override
//    public String execute() throws IOException {
//
////
////        System.out.println("Trying to save to file.");
////
////        var drawingFrameChildren = AddedShapeHelper.getInstance().addedShapes;
////
////        for(CustomShape node : drawingFrameChildren){
////            var shapeType = node.getClass().getSimpleName();
////
////            if(ApplicationSettings.applicationShapes.containsKey(shapeType)){
////
////                if(shapeType.equals("ShapeGroup")){
////                    traverseGroup(node, 0);
////                } else {
////                    fileWriter.write(String.format("%s\n", node.getInstructions()));
////                }
////            }
////        }
////
////        fileWriter.close();
////        return null;
//    }
//
//    @Override
//    public void undo() {
//
//    }
//
//    @Override
//    public void redo() {
//
//    }
//
////    void traverseGroup(CustomShape shape, Integer indent) throws IOException {
////
////        if(shape.getClass().getSimpleName().equals("ShapeGroup")){
////            // we still have children, let's recurse
////            ShapeGroup group = (ShapeGroup) shape;
////            var children = filterChildren(group);
////            var str = String.format( "group %s\n", children.size());
////            fileWriter.write(leftPadding(str, indent));
////
////            for(var child : children){
////                if(child.getClass().getSimpleName().equals("ShapeGroup")) {
////                    traverseGroup(child, indent + 1);
////                }
////                fileWriter.write(leftPadding(String.format("%s\n", child.getInstructions()), indent + 1));
////            }
////        }
//    }
//
////    ArrayList<CustomShape> filterChildren(ShapeGroup group){
////        ArrayList<CustomShape> result = new ArrayList<>();
////        for( CustomShape node : group.children){
////            if(!node.getClass().getSimpleName().equals("Region")){
////                result.add(node);
////            }
////        }
////        return result;
////    }
//
//    // Function to perform left padding
//    String leftPadding(String str, int indent){
//        StringBuilder builder = new StringBuilder();
//
//
//        for(int i = 0; i <= (indent * 4); i++){
//            builder.append(' ');
//        }
//        builder.append(str);
//
//        return builder.toString();
//    }
//}
