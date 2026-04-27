package com.example.pelardo.jdbc.navigation;

import com.example.pelardo.jdbc.screens.LoginScreen;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AppNavigation {
    public static Stage stage = new Stage();

    public static void navigate(String destination) {
        switch (destination) {
            case "HomeScreen" -> {
                LoginScreen LoginScreen = new LoginScreen();
                Scene LoginScene = new Scene(LoginScreen.getRoot(), 550, 550);
                stage.setTitle("Home");
                stage.setScene(LoginScene);
                stage.show();
            }

        }
    }
}
