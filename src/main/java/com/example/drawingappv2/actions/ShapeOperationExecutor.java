package com.example.drawingappv2.actions;

import com.example.drawingappv2.actions.interfaces.Command;

import java.util.ArrayList;

public final class ShapeOperationExecutor {

    private static ShapeOperationExecutor INSTANCE;

    public static ShapeOperationExecutor getInstance() {
        if(INSTANCE == null){
            INSTANCE = new ShapeOperationExecutor();
        }

        return INSTANCE;
    }

    private int index;

    private final ArrayList<Command> redoStack = new ArrayList<>();
    private final ArrayList<Command> undoStack = new ArrayList<>();



    public void executeOperation(Command command) throws Exception {

        redoStack.clear();
        undoStack.add(command);

        try{
            command.execute();
        } catch (Exception e){
            System.out.println(e.getMessage());
            throw(e);
        }

    }
    public void undo() {

        if(this.undoStack.size() > 0){
             var index = this.undoStack.size() -1;
             var item = this.undoStack.get(index);
             this.undoStack.remove(item);
             this.redoStack.add(item);
             item.undo();
        }

    }
    public void redo() {
        if(this.redoStack.size() > 0){
            var index = this.redoStack.size() -1;
            var item = this.redoStack.get(index);
            item.redo();
            this.undoStack.add(item);

        }
    }
}
