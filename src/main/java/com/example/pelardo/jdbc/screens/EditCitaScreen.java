package com.example.pelardo.jdbc.screens;

import com.example.pelardo.jdbc.models.CitaTbl;
import com.example.pelardo.jdbc.navigation.Navigation;
import com.example.pelardo.jdbc.services.CitasService;
import com.example.pelardo.jdbc.models.Usuario;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.time.LocalDate;

public class EditCitaScreen {
    // elementos de layout
    private VBox root = new VBox();
    private HBox fila1 = new HBox();
    private GridPane grid = new GridPane();
    // componentes de la ventana
    private Label lblCliente = new Label("Cliente");
    private TextField txtCliente = new TextField();
    private Label lblFecha = new Label("Fecha");
    private DatePicker fecha = new DatePicker();
    private Label lblHora = new Label("Hora");
    private TextField txtHora = new TextField();
    private Label lblDescripcion = new Label("Descripcion");
    private TextField txtDescripcion = new TextField();
    private Button btnGuardar = new Button("Guardar");
    private Button btnCancelar = new Button("Cancelar");
    private Button btnBorrar = new Button("Borrar");
    // Servicios de la ventada
    private CitasService citasService = new CitasService();
    // Constructor de la ventana

    public EditCitaScreen(Usuario usuario, CitaTbl cita) {
        // Configurar los elementos de layout
        root.setPadding(new Insets(10, 10, 10, 10));
        root.setSpacing(5);
        root.setAlignment(Pos.CENTER);
        fila1.setSpacing(5);
        fila1.setAlignment(Pos.CENTER);

        // Añadimos los componentes al layout correspondiente
        fila1.getChildren().addAll(btnCancelar);
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.add(lblCliente, 0, 0); // columna 0, fila 0
        grid.add(txtCliente, 0, 1); // columna 0, fila 1
        grid.add(lblFecha, 1, 0); // columna 1, fila 0
        grid.add(fecha, 1, 1); // columna 1, fila 1
        grid.add(lblHora, 2, 0); // columna 2, fila 0
        grid.add(txtHora, 2, 1); // columna 2, fila 1
        grid.add(lblDescripcion, 3, 0); // columna 3, fila 0
        grid.add(txtDescripcion, 3, 1); // columna 3, fila 1

        root.getChildren().addAll(grid, btnGuardar, btnBorrar, btnCancelar);
        // asignamos a cada campo el valor inicial
        fecha.setValue(LocalDate.parse(cita.getFecha()));
        txtCliente.setText(cita.getCliente());
        txtHora.setText(cita.getHora());
        txtDescripcion.setText(cita.getDescripcion());
        // Añadimos la interactividad de la ventana
        // Boton de cancelar (vuelve ala ventana anterior)
        btnCancelar.setOnAction(e -> {
            Navigation.navigate(Navigation.Screen.MAIN_SCREEN, usuario);
        });
        btnBorrar.setOnAction(e -> {
            try {
                citasService.deleteCita(cita);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Cita borrada");
                alert.setHeaderText(null);
                alert.setContentText("Cita borrada con éxito");
                alert.showAndWait();
                Navigation.navigate(Navigation.Screen.MAIN_SCREEN, usuario);
            } catch (Exception exception) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error al borrar cita");
                alert.setHeaderText(null);
                alert.setContentText(exception.getMessage());
                alert.showAndWait();
            }
        });
        btnGuardar.setOnAction(e -> {
            try {
                citasService.updateCita(
                        new CitaTbl(
                                cita.getId_Cita(),
                                txtCliente.getText(),
                                String.valueOf(fecha.getValue()),
                                txtHora.getText(),
                                txtDescripcion.getText()));
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Cita actualizada");
                alert.setHeaderText(null);
                alert.setContentText("la cita ha sido actualizada correctamente");
                alert.showAndWait();
                Navigation.navigate(Navigation.Screen.MAIN_SCREEN, usuario);

            } catch (Exception exception) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Error al actualizar la cita");
                alert.setHeaderText(null);
                alert.setContentText("la cita no se ha actualizado. \n" + exception.getMessage());
                alert.showAndWait();
            }

        });
    }

    public VBox getRoot() {
        return root;
    }
}
