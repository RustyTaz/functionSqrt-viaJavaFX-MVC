module com.example.functionviamvc {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.functionviamvc to javafx.fxml;
    exports com.example.functionviamvc;
}