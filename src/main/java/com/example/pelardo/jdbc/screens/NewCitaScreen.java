package com.example.pelardo.jdbc.screens;

import com.example.pelardo.jdbc.models.Usuario;
import com.example.pelardo.jdbc.navigation.Navigation;
import com.example.pelardo.jdbc.services.CitasService;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class NewCitaScreen {
    // Elementos de layout
    private VBox root = new VBox();
    private HBox fila1 = new HBox();
    private HBox fila2 = new HBox();
    private HBox fila3 = new HBox();
    private GridPane grid = new GridPane();
    // Componentes de la ventana
    private Label lblCliente = new Label("Cliente");
    private TextField txtCliente = new TextField();
    private Label lblFecha = new Label("Fecha");
    private DatePicker txtFecha = new DatePicker();
    private Label lblHora = new Label("Hora");
    private TextField txtHora = new TextField();
    private Label lblDescripcion = new Label("Descripcion");
    private TextField txtDescripcion = new TextField();
    private Button btnRegistar = new Button("Registar");
    private Button btnCancelar = new Button("Cancelar");

    // Añadimos los servicios de la ventana
    private CitasService citasService = new CitasService();

    // Constructor
    public NewCitaScreen(Usuario usuario) {
        // Configurar los elementos de layout
        root.setPadding(new Insets(10, 10, 10, 10));
        root.setSpacing(5);
        root.setAlignment(Pos.CENTER);
        fila1.setSpacing(5);
        fila2.setSpacing(5);
        fila3.setSpacing(5);
        fila1.setAlignment(Pos.CENTER);
        fila2.setAlignment(Pos.CENTER);
        fila3.setAlignment(Pos.CENTER);

        // Añadimos los componentes al layout correspondiente
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.add(lblCliente, 0, 0); // columna 0, fila 0
        grid.add(txtCliente, 0, 1); // columna 0, fila 1
        grid.add(lblFecha, 1, 0); // columna 1, fila 0
        grid.add(txtFecha, 1, 1); // columna 1, fila 1
        grid.add(lblHora, 2, 0); // columna 2, fila 0
        grid.add(txtHora, 2, 1); // columna 2, fila 1
        grid.add(lblDescripcion, 3, 0); // columna 3, fila 0
        grid.add(txtDescripcion, 3, 1); // columna 3, fila 1

        root.getChildren().addAll(grid, btnRegistar, btnCancelar);
        // Añadimos la interactividad de la ventana
        // Boton de cancelar (vuelve ala ventana anterior)
        btnCancelar.setOnAction(e -> {
            Navigation.navigate(Navigation.Screen.MAIN_SCREEN, usuario);
        });
        // Boton de registrar (registra la nueva cita)
        btnRegistar.setOnAction(e -> {
            try {
                citasService.insertCita(txtCliente.getText(), txtFecha.getValue().toString(), txtHora.getText(),
                        txtDescripcion.getText());
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Cita registrada");
                alert.setHeaderText(null);
                alert.setContentText("Cita registrada con éxito");
                alert.showAndWait();
                Navigation.navigate(Navigation.Screen.MAIN_SCREEN, usuario);
            } catch (Exception exception) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error al registrar cita");
                alert.setHeaderText(null);
                alert.setContentText(exception.getMessage());
                alert.showAndWait();
            }
        });
    }

    public VBox getRoot() {
        return root;
    }
}
