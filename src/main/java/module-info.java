module com.example.text_processor {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.text_processor to javafx.fxml;
    exports com.example.text_processor;
}