package com.example.drawingappv2.actions;

import com.example.drawingappv2.ApplicationSettings;
import com.example.drawingappv2.DrawingPane;
import com.example.drawingappv2.actions.interfaces.Command;
import com.example.drawingappv2.shapes.CustomShape;
import com.example.drawingappv2.shapes.SelectableShape;
import com.example.drawingappv2.shapes.ShapeFactory;
import com.example.drawingappv2.shapes.ShapeGroup;
import javafx.scene.Group;
import javafx.scene.Node;

import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Stream;

public class OpenTextFileOperation implements Command {

    private final String[] textFileLines;
    private ArrayList<Node> nodesToAdd = new ArrayList<>();

    private int index = 0;

    public OpenTextFileOperation(String filePath) throws IOException {
        List<String> stringList = Files.readAllLines(Path.of(filePath), Charset.defaultCharset());
        textFileLines = stringList.toArray(new String[]{});
    }

    @Override
    public String execute() throws IOException {

        for(index = 0; index <= textFileLines.length - 1; index++){
            parseLine(textFileLines[index]);
        }

        DrawingPane.getInstance().getChildren().addAll(nodesToAdd);
        return null;
    }

    private ShapeGroup parseGroup(String[] instructions){
        var totalGroupCount = Integer.parseInt(instructions[1]);
        ArrayList<Node> nodes = new ArrayList<Node>();

        for(int i = index + 1; i <= (index + totalGroupCount); i++){

            String[] inst = readInstructions(textFileLines[i]);

            if(inst[0].equals("group")){
                ShapeGroup g = parseGroup(inst);
                nodes.add(g);
            } else if (Arrays.asList(ApplicationSettings.validShapes).contains(inst[0])){
                nodes.add(parseShapeLine(inst).getShape());
            }
        }
        return new ShapeGroup(nodes);
    }

    private String[] readInstructions(String string) {
        return string.trim().split(" ");
    }

    private void parseLine(String string) {
        System.out.println(string);

        String[] instructions = this.readInstructions(string);

        var shapeType = instructions[0];
        try {
            if (instructions[0].equals("group")) {
                nodesToAdd.add(parseGroup(instructions));
                index += Integer.parseInt(instructions[1]);
            } else if (Arrays.asList(ApplicationSettings.validShapes).contains(shapeType.toLowerCase())) {
                CustomShape shape = this.parseShapeLine(instructions);
                nodesToAdd.add(shape.getShape());
            }
        } catch (Exception e){
            System.out.println(e);
        }
    }

    public CustomShape parseShapeLine(String[] instructions){

        var shapeInstruction = new HashMap<String, String>() {
            // todo error handling
            {
                var shapeType = instructions[0];
                var shapeHeight = ApplicationSettings.getInstance().getKey("shapeHeight");

                var x = instructions[1];
                if (x == null) x = "0";

                var y = instructions[2];
                if (y == null) y = "0";


                var shapeWidth = instructions[3];
                if (shapeWidth == null) shapeWidth = ApplicationSettings.getInstance().getKey("shapeWidth");

                shapeHeight = instructions[4];

                put("shapeType", String.valueOf(shapeType));
                put("x", x);
                put("y", y);
                put("shapeWidth", String.valueOf(shapeWidth));
                put("shapeHeight", String.valueOf(shapeHeight));
            }
        };

        return ShapeFactory.CreateShape(shapeInstruction);
    }

    @Override
    public void undo() {
        DrawingPane.getInstance().getChildren().removeAll(nodesToAdd);
    }

    @Override
    public void redo() {
        DrawingPane.getInstance().getChildren().addAll(nodesToAdd);
    }
}