package main.java.Controllers;

import com.jfoenix.controls.JFXDatePicker;
import javafx.fxml.FXML;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import main.java.App;
import main.java.Camping.Group;
import main.java.Camping.Site;

import java.time.LocalDate;


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

    public void findReservations(LocalDate start, LocalDate end, String siteType){

        for (Group g: App.groupManager.getGroups()) {
            for (Site s:g.getSitesInGroup()) {
                if(s.getSiteType().equals(siteType) && s.isAvailable(start, end) ){
                    resultsVBox.getChildren().add(makeAvailableReservation(start, end, s));
                }
            }
        }
        if(resultsVBox.getChildren().size() == 0){
            //Make somethin in there that says there are no reservation that could be matched.
        }
    }

    /**
     * makes the HBox that is added to the VBox of suggested reservations
     * @param start the start date of the potential reservation
     * @param end the end date of the potential reservation
     * @param s the site of the potential reservation
     * @return the HBox that should be added to the final results VBox
     */
    private HBox makeAvailableReservation(LocalDate start, LocalDate end, Site s){


        return new HBox();
    }

}
