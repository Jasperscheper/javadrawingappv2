package com.example.drawingappv2.actions;
import com.example.drawingappv2.actions.interfaces.Command;
import com.example.drawingappv2.shapes.CustomShape;
import javafx.scene.input.MouseEvent;
public class MoveShapeCommand implements Command {

    CustomShape target;
    MouseEvent event;

    double originalX;
    double originalY;

    public MoveShapeCommand(CustomShape target, MouseEvent event){
        this.target = target;
        this.event = event;
    }

    @Override
    public String execute() {
        //commit changes to LayoutX and LayoutY
        this.originalX = target.getShape().getLayoutX();
        this.originalY = target.getShape().getLayoutY();
        target.getShape().setLayoutX(event.getSceneX() - event.getX());
        target.getShape().setLayoutY(event.getSceneY() - event.getY());

        //clear changes from TranslateX and TranslateY
        target.getShape().setTranslateX(0);
        target.getShape().setTranslateY(0);
        return "";
    }

    @Override
    public void undo() {
        target.getShape().setLayoutX(this.originalX);
        target.getShape().setLayoutY(this.originalY);
    }

    public void redo(){
        this.execute();
    }
}
