package com.example.pelardo.jdbc.screens;

import com.example.pelardo.jdbc.navigation.Navigation;
import com.example.pelardo.jdbc.services.LoginServices;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;

public class RegisterScreen {
    // Elementos de layout
    private VBox root = new VBox();
    private HBox fila1 = new HBox();
    private HBox fila2 = new HBox();
    private HBox fila3 = new HBox();
    private GridPane grid = new GridPane();
    // Componentes de la ventana
    private Image logo = new Image("pelardoLogo.jpg");
    private ImageView logoView = new ImageView(logo);
    Rectangle rectanguloImage = new Rectangle(150, 130);
    private Label lblUser = new Label("Usuario");
    private TextField txtUser = new TextField();
    private Label lblPassword = new Label("Contraseña");
    private PasswordField txtPassword = new PasswordField();
    private Label lblPassword2 = new Label("Repite la contraseña");
    private PasswordField txtPassword2 = new PasswordField();
    private Button btnRegistar = new Button("Registar");

    // Añadimos los servicios de la ventana
    private LoginServices loginServices = new LoginServices();

    // Constructor
    public RegisterScreen() {
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

        // Configuramos la imagen
        logoView.setFitWidth(150);
        logoView.setFitHeight(150);
        logoView.setPreserveRatio(true);
        rectanguloImage.setArcHeight(10);
        rectanguloImage.setArcWidth(10);
        logoView.setClip(rectanguloImage);
        // Añadimos los componentes al layout correspondiente
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.add(lblUser, 0, 0); // columna 0, fila 0
        grid.add(txtUser, 0, 1); // columna 0, fila 1
        grid.add(lblPassword, 1, 0); // columna 1, fila 0
        grid.add(txtPassword, 1, 1); // columna 1, fila 1
        grid.add(lblPassword2, 2, 0); // columna 2, fila 0
        grid.add(txtPassword2, 2, 1); // columna 2, fila 1

        root.getChildren().addAll(logoView, grid, btnRegistar);
        // Añadimos la interactividad de la ventana
        btnRegistar.setOnAction(actionEvent -> {
            try {
                loginServices.register(txtUser.getText(), txtPassword.getText(), txtPassword2.getText());
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Usuario registrado");
                alert.setHeaderText(null);
                alert.setContentText("Usuario registrado con éxito");
                alert.showAndWait();
                Navigation.navigate(Navigation.Screen.LOGIN_SCREEN);
            } catch (Exception exception) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Error de conexión");
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
