module assignment2.healthapp {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;
    requires java.desktop;

    opens assignment2.healthapp to javafx.fxml;
    exports assignment2.healthapp;
}