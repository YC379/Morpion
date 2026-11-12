module morpion {
    requires javafx.controls;
    requires javafx.fxml;

    // Nécessaire pour que FXMLLoader puisse injecter les @FXML et charger les ressources
    opens application to javafx.fxml;
    exports application;
}
