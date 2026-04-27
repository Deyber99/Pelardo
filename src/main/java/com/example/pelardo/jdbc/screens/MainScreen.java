package com.example.pelardo.jdbc.screens;

import com.example.pelardo.jdbc.models.CitaTbl;
import com.example.pelardo.jdbc.models.Usuario;
import com.example.pelardo.jdbc.navigation.Navigation;
import com.example.pelardo.jdbc.services.CitasService;
import javafx.beans.property.SimpleStringProperty;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.time.LocalDate;
import java.util.ArrayList;

public class MainScreen {
    // elementos de layout
    private VBox root = new VBox();
    private HBox fila1 = new HBox();

    // componentes de ventana
    private Label lblFecha = new Label();
    private DatePicker dpFecha = new DatePicker();
    private Button btnBuscar = new Button("Buscar");
    private Button btnCrear = new Button("Crear");
    // añadimos la tabla con las citas
    private TableView<CitaTbl> tblCitas = new TableView<>();
    // Añadimos las columnas de la tabla
    private TableColumn<CitaTbl, String> colFecha = new TableColumn<>("Fecha");
    private TableColumn<CitaTbl, String> colHora = new TableColumn<>("Hora");
    private TableColumn<CitaTbl, String> colCliente = new TableColumn<>("Cliente");
    private TableColumn<CitaTbl, String> colDescripcion = new TableColumn<>("Descripcion");
    private ArrayList<CitaTbl> listaCitas = new ArrayList<>();

    // servicio para consultas a DB
    private CitasService citasService = new CitasService();

    // borrar listaCitas luego es solo una prueba
    public VBox getRoot() {
        return root;
    }

    public MainScreen(Usuario usuario) {
        // Configuramos elementos de layout
        root.setPadding(new Insets(10, 10, 10, 10));
        root.setSpacing(15);
        fila1.setSpacing(5);
        // asignamos los componentes al layout correspondiente
        fila1.getChildren().addAll(lblFecha, dpFecha, btnBuscar, btnCrear);
        root.getChildren().addAll(fila1, tblCitas);
        // asignamos valores por defecto al datepicker
        dpFecha.setValue(LocalDate.now());
        dpFecha.setShowWeekNumbers(false);
        // asignamos las columnas a la tabla
        tblCitas.getColumns().addAll(colCliente, colFecha, colHora, colDescripcion);
        tblCitas.setPrefHeight(400);
        tblCitas.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        colCliente.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getCliente()));
        colFecha.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getFecha()));
        colHora.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getHora()));
        colDescripcion.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getDescripcion()));

        // Añadimos la accion al boton "Buscar"
        btnBuscar.setOnAction(event -> {
            // Obtenemos la fecha que ha escogido el usuario
            LocalDate fechaEscogida = dpFecha.getValue();
            if (fechaEscogida != null) {
                // Conseguir los resultados y actualizar la tabla directamente
                tblCitas.getItems().setAll(citasService.mostrarCita(fechaEscogida.toString()));
            }
        });

        // Cargar datos por defecto para la fecha actual
        btnBuscar.fire();

        // Añadimos la accion al boton "Crear"
        btnCrear.setOnAction(event -> {
            // Nos movemos a la ventana de crear una nueva cita
            Navigation.navigate(Navigation.Screen.NEWCITA_SCREEN, usuario);
        });

        tblCitas.setRowFactory(tv -> {
            TableRow<CitaTbl> rowSelected = new TableRow<>();
            rowSelected.setOnMouseClicked(mouseEvent -> {
                if (mouseEvent.getClickCount() == 2 && !rowSelected.isEmpty()) {
                    CitaTbl citaTbl = rowSelected.getItem();
                    Navigation.navigate(Navigation.Screen.EDIT_CITA_SCREEN, usuario, citaTbl);
                }
            });
            return rowSelected;
        });
    }
}