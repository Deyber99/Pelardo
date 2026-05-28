module com.example.pelardo.jdbc {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires javafx.graphics;
    requires org.postgresql.jdbc;



    opens com.example.pelardo.jdbc to javafx.fxml;
    exports com.example.pelardo.jdbc;
}