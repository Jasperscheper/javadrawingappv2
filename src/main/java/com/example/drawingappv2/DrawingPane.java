package com.example.drawingappv2;
import com.example.drawingappv2.actions.AddShapeCommand;
import com.example.drawingappv2.actions.ShapeOperationExecutor;
import com.example.drawingappv2.shapes.CustomCircle;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;

public class DrawingPane extends Pane {

    static DrawingPane INSTANCE;
    static DrawingPaneKeyboardController controller;


    public DrawingPane(){
        this.addEventHandler(MouseEvent.MOUSE_PRESSED, handleMouseClick());
    }

    public static DrawingPane getInstance() {
        if(INSTANCE == null){
            INSTANCE = new DrawingPane();
            controller = new DrawingPaneKeyboardController(INSTANCE);
        }
        return INSTANCE;
    }

    public void placeShape(Point2D location) throws Exception {

        if (this.canPlaceShape(location)) {
            ShapeOperationExecutor.getInstance().executeOperation(new AddShapeCommand(new CustomCircle(new Circle(location.getX(), location.getY(), 50))));
        }

    }

    private Boolean canPlaceShape(Point2D location) {

        Boolean isHit = false;
        for (Node node : this.getChildren()) {
            if(!isHit){
                if (node.intersects(location.getX(), location.getY(), 1, 1)) {
                    isHit = true;

                }
            } else {
                break;
            }
        }
        return !isHit;
    }

    public EventHandler<MouseEvent> handleMouseClick(){
        return new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event){
                try {
                    placeShape(new Point2D(event.getX(), event.getY()));
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        };
    }



}
