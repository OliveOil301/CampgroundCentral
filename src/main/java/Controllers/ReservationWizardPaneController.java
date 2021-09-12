package main.java.Controllers;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXToggleNode;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import main.java.App;
import main.java.Camping.Group;
import main.java.Camping.Site;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.function.Predicate;



public class ReservationWizardPaneController {

    @FXML
    ScrollPane availableSitesScrollPane;

    @FXML
    JFXDatePicker startDateBox;

    @FXML
    JFXDatePicker endDateBox;

    @FXML
    VBox resultsVBox;

    @FXML
    JFXToggleNode fiftyAmpToggle;

    @FXML
    JFXToggleNode thirtyAmpToggle;

    @FXML
    JFXToggleNode WEToggle;

    @FXML
    JFXToggleNode tentToggle;

    @FXML
    JFXToggleNode rentToggle;

    ArrayList<String> selectedTypes = new ArrayList<String>();




    public void initialize(){
        //See if we can gray out dates before today's date

    }

    @FXML
    public void findReservations(){
        LocalDate start = startDateBox.getValue();
        LocalDate end = endDateBox.getValue();
        setSiteTypeList();

        availableSitesScrollPane.setVvalue(0);
        resultsVBox.getChildren().clear();
        for (Group g: App.groupManager.getGroups()) {
            for (Site s:g.getSitesInGroup()) {
                if(isCorrectType(s) && s.isAvailable(start, end) ){
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

        //Below this is the HBox that holds the label and button showing the site and site type
        HBox holdingBox = new HBox();
        holdingBox.setAlignment(Pos.CENTER_LEFT);
        holdingBox.setPadding(new Insets(6));
        holdingBox.setSpacing(8);
        holdingBox.setBackground(new Background(new BackgroundFill(Color.web("#cbe5c1",1), new CornerRadii(2), new Insets(0))));
        holdingBox.setBorder(new Border(new BorderStroke(Color.web("#000000",1), BorderStrokeStyle.SOLID, new CornerRadii(2), new BorderWidths(1))));
        holdingBox.setOnMouseClicked(event -> {
            setNewReservationBoxes(start, end, s);//This sets the boxes on the newReservation screen
        });

        //Below this is the label for the site
        Label siteLabel = new Label();
        siteLabel.setStyle("-fx-font-size: 18; \n"+
                "-fx-font-weight: bold;");
        siteLabel.setText(s.getSiteName());

        //below this is the button for the site type (50 Amp, 30 AMp, W&E, Tent, Rental)
        Button siteType = new Button();
        siteType.setStyle("-fx-background-color: -secondary;\n" +
                "    -fx-text-fill: -primary;\n" +
                "    -fx-min-width: 50px;\n" +
                "    -fx-border-radius: 2px;");
        siteType.setText(s.getSiteType());
        siteType.setOnAction(event -> {
            setNewReservationBoxes(start, end, s);//This sets the boxes on the newReservation screen
        });

        //This is just putting everything inside where it needs to be
        holdingBox.getChildren().add(siteLabel);
        holdingBox.getChildren().add(siteType);

        return holdingBox;
    }



    private void setNewReservationBoxes(LocalDate start, LocalDate end, Site s){
        App.newReservationStart = start;
        App.newReservationEnd = end;
        App.newReservationSite = s.getSiteName();
        App.needToUpdate.setValue(!App.needToUpdate.getValue());
    }


    private void setSiteTypeList(){
        ArrayList<String> listOfTypes = new ArrayList<String>();
        if(fiftyAmpToggle.isSelected()){
            listOfTypes.add("Full-50");
        }
        if(thirtyAmpToggle.isSelected()){
            listOfTypes.add("Full-30");
        }
        if(WEToggle.isSelected()){
            listOfTypes.add("W & E");

        }
        if(tentToggle.isSelected()){
            listOfTypes.add("Tent");

        }
        if(rentToggle.isSelected()){
            listOfTypes.add("Rental");

        }
        selectedTypes = listOfTypes;
    }

    private boolean isCorrectType(Site s){
        for (String type:selectedTypes) {
            if(type.equals(s.getSiteType())){
                return true;
            }
        }
        return false;//NEED TO CHANGE THIS
    }

}
