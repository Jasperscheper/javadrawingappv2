//package com.example.drawingappv2.actions;
//
//import com.example.drawingappv2.actions.interfaces.Command;
//import com.example.drawingappv2.shapes.CustomShape;
//import javafx.scene.Group;
//import javafx.scene.shape.Shape;
//
//import java.io.IOException;
//import java.nio.file.Files;
//import java.nio.file.Paths;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.HashMap;
//import java.util.stream.Stream;
//
//public class OpenTextFileOperation implements Command {
//
//    private ArrayList<CustomShape> addedShapes = new ArrayList<>();
//    private final TextFile textFile;
//
//    private Integer groupIndex = null;
//    private Integer totalGroupCount = null;
//    public ShapeGroup tmpGroup;
//
//    public OpenTextFileOperation(String filePath) {
//        this.textFile = new TextFile(filePath);
//    }
//
//    @Override
//    public String execute() {
//        try (Stream<String> stream = Files.lines(Paths.get(this.textFile.getPath()))) {
//            stream.forEach(this::parseLine);
//        } catch(IOException e){
//            throw new RuntimeException(e);
//        }
//
//        return null;
//    }
//
//    private void removeShape(Shape shape){
//        DrawingPane.getInstance().getChildren().remove(shape);
//    }
//
//    @Override
//    public void undo() {
//        addedShapes.forEach(e -> this.removeShape(e.destroyShape()));
//    }
//
//    @Override
//    public void redo() {
//        this.execute();
//    }
//
//    private void parseLine(String s) {
//
//        String[] instructions = s.trim().split(" ");
//
//        if (instructions.length == 0) {
//            return;
//        }
//
//        try {
//
//            if(groupIndex != null && totalGroupCount != null){
//                // we are in a group
//                CustomShape shape = parseShapeLine(instructions);
//                tmpGroup.getNode().getChildren().add(shape.getNode());
//                tmpGroup.children.add(shape);
//
//                if(groupIndex.equals(totalGroupCount)){
//                    // add the group if we are at the end of our list
//                    System.out.println("Added group");
//                    ShapeOperationExecutor.getInstance().executeOperation(new CreateGroupCommand(tmpGroup));
//                    groupIndex = null;
//                    totalGroupCount = null;
//                } else {
//                    groupIndex++;
//                }
//            }
//
//            else if(instructions[0].equals("group")){
//                // if we find a group instruction
//                // create a group and set the index pointers
//                tmpGroup = new ShapeGroup(new Group(), new ArrayList<>());
//                totalGroupCount = Integer.parseInt(instructions[1]);
//                groupIndex = 1;
//            } else if (Arrays.asList(ApplicationSettings.validShapes).contains(instructions[0])){
//                // else just create a shape
//                CustomShape shape = parseShapeLine(instructions);
//                System.out.println("Added shape");
//                ShapeOperationExecutor.getInstance().executeOperation(new AddShapeCommand(shape));
//            }
//
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }
//    }
//    public CustomShape parseShapeLine(String[] instructions){
//
//
//        var shapeInstruction = new HashMap<String, String>() {
//            // todo error handling
//            {
//                var shapeType = instructions[0];
//                var shapeHeight = ApplicationSettings.getInstance().getKey("shapeHeight");
//
//                var x = instructions[1];
//                if (x == null) x = "0";
//
//                var y = instructions[2];
//                if (y == null) y = "0";
//
//
//                var shapeWidth = instructions[3];
//                if (shapeWidth == null) shapeWidth = ApplicationSettings.getInstance().getKey("shapeWidth");
//
//                shapeHeight = instructions[4];
//
//
//                put("shapeType", String.valueOf(shapeType));
//                put("x", x);
//                put("y", y);
//                put("shapeWidth", String.valueOf(shapeWidth));
//                put("shapeHeight", String.valueOf(shapeHeight));
//            }
//        };
//
//        return ShapeFactory.CreateShape(shapeInstruction);
//    }
//}