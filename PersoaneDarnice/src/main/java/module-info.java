module com.example.persoanedarnice {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;

    opens com.example.persoanedarnice to javafx.fxml;
    opens com.example.persoanedarnice.controller to javafx.fxml;
    opens com.example.persoanedarnice.business to javafx.fxml;
    opens com.example.persoanedarnice.repository to javafx.fxml;
    opens com.example.persoanedarnice.domain to javafx.fxml;

    exports com.example.persoanedarnice;
    exports com.example.persoanedarnice.controller;
    exports com.example.persoanedarnice.business;
    exports com.example.persoanedarnice.repository;
    exports com.example.persoanedarnice.domain;
}