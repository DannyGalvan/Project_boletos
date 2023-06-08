module com.mycompany.ventatickets {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    requires javafx.graphics;
    requires javafx.media;
    requires java.sql;
    requires javafx.base;
    requires java.base;
    requires java.mail;
    requires org.jsoup;
    requires java.dotenv;
    opens com.mycompany.ventatickets to javafx.fxml;
    exports com.mycompany.ventatickets;
    exports com.mycompany.ventatickets.controllers.HomeController;
    exports com.mycompany.ventatickets.controllers.AuthController;
    exports com.mycompany.ventatickets.controllers.EventsController;
    exports com.mycompany.ventatickets.controllers.ResponsibleController;
    exports com.mycompany.ventatickets.controllers.UsersController;
    exports com.mycompany.ventatickets.controllers.SeatController;
    exports com.mycompany.ventatickets.controllers.PagosController;
    exports com.mycompany.ventatickets.models;
    exports com.mycompany.ventatickets.Conexion;
}
