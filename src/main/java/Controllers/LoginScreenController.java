package main.java.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import main.java.App;

import java.io.IOException;

public class LoginScreenController {
    private FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/LoginScreen.fxml"));


    @FXML
    private void handleLoginButton() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/main/resources/views/SchedulingPage.fxml"));
        App.primaryStage.getScene().setRoot(root);
        //System.out.println("YOOOOOOO");
    }
//    public void initialize() throws IOException {
//        fxmlLoader.load();
//    }
}
