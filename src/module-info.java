module JavaFX.Challenge {
    requires javafx.fxml;
    requires javafx.controls;
    requires lombok;
    requires java.xml;

    opens com.fad.lecture221;
    opens com.fad.lecture221.model;
}