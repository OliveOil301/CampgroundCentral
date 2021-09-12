package main.java.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import main.java.App;

import java.io.IOException;

public class ShoppingPageController {






    public void initialize(){
        //Will need to load all items from App.shoppingItemManager
    }

    @FXML
    private void handleNewReservationButton() throws IOException {
        if(!App.newReservationWindows) {
            App.newReservationStage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("/main/resources/views/NewReservationWindow.fxml"));
            Scene scene = new Scene(root);
            App.newReservationStage.setScene(scene);
            App.newReservationStage.show();
            App.newReservationWindows = true;
        }
    }
}
