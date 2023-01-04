module com.example.drawingappv2 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.drawingappv2 to javafx.fxml;
    exports com.example.drawingappv2;
}