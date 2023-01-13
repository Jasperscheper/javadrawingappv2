package com.example.drawingappv2.actions;
import com.example.drawingappv2.actions.interfaces.Command;
import com.example.drawingappv2.shapes.CustomShape;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
public class MoveShapeCommand implements Command {

    Node target;
    MouseEvent event;

    double originalX;
    double originalY;

    public MoveShapeCommand(Node target, MouseEvent event){
        this.target = target;
        this.event = event;
    }

    @Override
    public String execute() {
        //commit changes to LayoutX and LayoutY
        this.originalX = target.getLayoutX();
        this.originalY = target.getLayoutY();
        target.setLayoutX(event.getSceneX() - event.getX());
        target.setLayoutY(event.getSceneY() - event.getY());

        //clear changes from TranslateX and TranslateY
        target.setTranslateX(0);
        target.setTranslateY(0);
        return "";
    }

    @Override
    public void undo() {
        target.setLayoutX(this.originalX);
        target.setLayoutY(this.originalY);
    }

    public void redo(){
        this.execute();
    }
}
