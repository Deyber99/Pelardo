package com.example.pelardo.jdbc.navigation;

import com.example.pelardo.jdbc.models.CitaTbl;
import com.example.pelardo.jdbc.models.Usuario;
import com.example.pelardo.jdbc.screens.*;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Navigation {
    public enum Screen {
        LOGIN_SCREEN,
        MAIN_SCREEN,
        REGISTER_SCREEN,
        NEWCITA_SCREEN,
        EDIT_CITA_SCREEN
    }
    public static Stage stage = new Stage();
    public static void navigate (Screen screen){
        switch (screen){
            case LOGIN_SCREEN -> {
                LoginScreen loginScreen = new LoginScreen();
                Scene loginScene = new Scene(loginScreen.getRoot(), 300, 300);
                stage.setTitle("Conexión");
                stage.setScene(loginScene);
                stage.show();
            }
            case REGISTER_SCREEN -> {
                RegisterScreen registerScreen = new RegisterScreen();
                Scene registerScene = new Scene(registerScreen.getRoot(), 300, 400);
                stage.setTitle("Registro");
                stage.setScene(registerScene);
                stage.show();
            }
        }
    }
    public  static void navigate (Screen screen, Usuario usuario){
        switch (screen){
            case MAIN_SCREEN -> {
                MainScreen mainScreen = new MainScreen(usuario);
                Scene mainScene = new Scene(mainScreen.getRoot(), 400, 400);
                stage.setTitle("Ventana Principal");
                stage.setScene(mainScene);
                stage.show();
            }
            case NEWCITA_SCREEN -> {
                NewCitaScreen newCitaScreen = new NewCitaScreen(usuario);
                Scene newCitaScene = new Scene(newCitaScreen.getRoot(), 400, 400);
                stage.setTitle("Crear Nueva Cita");
                stage.setScene(newCitaScene);
                stage.show();
            }

        }
    }
    public static void navigate(Screen screen,Usuario usuario  ,CitaTbl cita){
        switch (screen){
            case EDIT_CITA_SCREEN -> {
                EditCitaScreen editCitaScreen = new EditCitaScreen(usuario, cita);
                Scene editCitaScene = new Scene(editCitaScreen.getRoot(), 800, 600);
                stage.setTitle("Editar Cita");
                stage.setScene(editCitaScene);
                stage.show();
            }
        }
    }
    public static void navigate(String destination) {
        switch (destination) {
            case "HomeScreen" -> {
                LoginScreen loginScreen = new LoginScreen();
                Scene loginScene = new Scene(loginScreen.getRoot(), 550, 550);
                stage.setTitle("Home");
                stage.setScene(loginScene);
                stage.show();
            }
        }
    }
}
