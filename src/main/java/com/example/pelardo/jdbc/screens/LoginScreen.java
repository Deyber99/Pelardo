package com.example.pelardo.jdbc.screens;

import com.example.pelardo.jdbc.models.Usuario;
import com.example.pelardo.jdbc.navigation.Navigation;
import com.example.pelardo.jdbc.services.LoginServices;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;

public class LoginScreen {
    // Elementos de layout
    private VBox root = new  VBox();
    private HBox fila1 = new HBox();
    private HBox fila2 = new HBox();
    // Componentes de la ventana
    private Image logo = new Image("pelardoLogo.jpg");
    private ImageView logoView = new ImageView(logo);
    Rectangle rectanguloImage = new Rectangle(150,130);
    private Label lblUser = new Label("Usuario");
    private TextField txtUser = new TextField();
    private Label lblPassword = new Label("Contraseña");
    private PasswordField txtPassword = new PasswordField();
    private Button btnConectar = new Button("Conectar");
    private Label lblRegister = new Label("Registrarse");

    // Añadimos los servicios de la ventana
    private LoginServices loginServices = new LoginServices();

    // Constructor
    public LoginScreen() {
        // Configurar los elementos de layout
        root.setPadding(new Insets(10, 10, 10 ,10 ));
        root.setSpacing(5);
        root.setAlignment(Pos.CENTER);
        fila1.setSpacing(5);
        fila2.setSpacing(5);
        fila1.setAlignment(Pos.CENTER);
        fila2.setAlignment(Pos.CENTER);

        // Configuramos la imagen
        logoView.setFitWidth(150);
        logoView.setFitHeight(150);
        logoView.setPreserveRatio(true);
        rectanguloImage.setArcHeight(10);
        rectanguloImage.setArcWidth(10);
        logoView.setClip(rectanguloImage);
        // Añadimos los componentes al layout correspondiente
        fila1.getChildren().addAll(lblUser, txtUser);
        fila2.getChildren().addAll(lblPassword, txtPassword);
        root.getChildren().addAll(logoView, fila1, fila2, btnConectar, lblRegister);
        // Añadimos la interactividad de la ventana
        btnConectar.setOnAction(actionEvent -> {
            try {
                Usuario usuario = loginServices.login(txtUser.getText(), txtPassword.getText());
                Navigation.navigate(Navigation.Screen.MAIN_SCREEN, usuario);
            } catch (Exception exception) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Error de conexión");
                alert.setHeaderText(null);
                alert.setContentText(exception.getMessage());
                alert.showAndWait();
            }
        });

        lblRegister.setOnMouseClicked(actionEvent ->
                Navigation.navigate(Navigation.Screen.REGISTER_SCREEN));

    }
    public VBox getRoot() {
        return root;
    }
}
