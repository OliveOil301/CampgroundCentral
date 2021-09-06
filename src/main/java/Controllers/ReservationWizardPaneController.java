package main.java.Controllers;

import com.jfoenix.controls.JFXDatePicker;
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;


public class ReservationWizardPaneController {

    @FXML
    JFXDatePicker startDateBox;

    @FXML
    JFXDatePicker endDateBox;

    @FXML
    VBox resultsVBox;


    public void initialize(){
        //See if we can gray out dates before today's date

    }


}
