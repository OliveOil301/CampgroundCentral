package main.java.Controllers;


import javafx.animation.ScaleTransition;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Separator;
import javafx.stage.Stage;
import javafx.util.Duration;
import main.java.App;
import com.opencsv.CSVWriter;


import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NewReservationWindowController {

    /* These SimpleIntegerProperties are used for focusing and unfocusing the two main sections of the New Reservation window
    *  A value of 0 corresponds to "not focused"
    *  A value of 1 corresponds to "unfocusing"
    *  A value of 2 corresponds to "focusing"
    *  A value of 3 corresponds to "focused"
     */
    private static SimpleIntegerProperty reservationInformationFocus = new SimpleIntegerProperty(0);
    private static SimpleIntegerProperty billingInformationFocus = new SimpleIntegerProperty(0);;

    @FXML
    private Separator ReservationSeparator;
    @FXML
    private Separator BillingSeparator;

    @FXML
    private ComboBox<String> siteComboBox;

    @FXML
    private ComboBox<String> stateComboBox;
    public void initialize() throws IOException {

        //These are the listeners for the focus animations.
        reservationInformationFocus.addListener((observable, oldVal, newVal) ->{

            if(reservationInformationFocus.getValue() == 2){//If we should be focusing
                ScaleTransition focus = new ScaleTransition();
                focus.setFromX(ReservationSeparator.getScaleX());
                focus.setToX(3);
                focus.setDuration(Duration.millis(300));
                focus.setNode(ReservationSeparator);
                focus.play();
                reservationInformationFocus.setValue(3);

            } else if(reservationInformationFocus.getValue() == 1){//If we should be unfocusing
                ScaleTransition unfocus = new ScaleTransition();
                unfocus.setFromX(ReservationSeparator.getScaleX());
                unfocus.setToX(1);
                unfocus.setDuration(Duration.millis(300));
                unfocus.setNode(ReservationSeparator);
                unfocus.play();
                reservationInformationFocus.setValue(0);
            }
        });
        billingInformationFocus.addListener((observable, oldVal, newVal) ->{

            if(billingInformationFocus.getValue() == 2){//If we should be focusing
                ScaleTransition focus = new ScaleTransition();
                focus.setFromX(BillingSeparator.getScaleX());
                focus.setToX(3);
                focus.setDuration(Duration.millis(300));
                focus.setNode(BillingSeparator);
                focus.play();
                billingInformationFocus.setValue(3);

            } else if(billingInformationFocus.getValue() == 1){//If we should be unfocusing
                ScaleTransition unfocus = new ScaleTransition();
                unfocus.setFromX(BillingSeparator.getScaleX());
                unfocus.setToX(1);
                unfocus.setDuration(Duration.millis(300));
                unfocus.setNode(BillingSeparator);
                unfocus.play();
                billingInformationFocus.setValue(0);
            }
        });


        // Filling up the combo box with the sites.
        BufferedReader br2 = null;
        try {
            br2 = new BufferedReader(new FileReader("src/main/resources/storage/Sites.csv"));
        } catch (FileNotFoundException e) {
            System.out.println("The file doesn't Exist. Please don't mess with this shit. ");
            //Make sure to throw an error message.
        }
        String line2;
        String splitBy = ",";

        ArrayList<String> comboList = new ArrayList<String>();
        ObservableList<String> ComboBoxItems = FXCollections.observableList(comboList);
        while ((line2 = br2.readLine()) != null) {
            String[] site = line2.split(splitBy);
            ComboBoxItems.add(site[0] + site[1]);
        }
        siteComboBox.setItems(ComboBoxItems);

        ArrayList<String> states = new ArrayList<String>();
        states.add("Alabama");
        states.add("Alaska");
        //states.add("Alberta");
        //states.add("American Samoa");
        states.add("Arizona");
        states.add("Arkansas");
        //states.add("Armed Forces (AE)");
        //states.add("Armed Forces Americas");
        //states.add("Armed Forces Pacific");
        //states.add("British Columbia");
        states.add("California");
        states.add("Colorado");
        states.add("Connecticut");
        states.add("Delaware");
        states.add("District Of Columbia");
        states.add("Florida");
        states.add("Georgia");
        states.add("Guam");
        states.add("Hawaii");
        states.add("Idaho");
        states.add("Illinois");
        states.add("Indiana");
        states.add("Iowa");
        states.add("Kansas");
        states.add("Kentucky");
        states.add("Louisiana");
        states.add("Maine");
        //states.add("Manitoba");
        states.add("Maryland");
        states.add("Massachusetts");
        states.add("Michigan");
        states.add("Minnesota");
        states.add("Mississippi");
        states.add("Missouri");
        states.add("Montana");
        states.add("Nebraska");
        states.add("Nevada");
        //states.add("New Brunswick");
        states.add("New Hampshire");
        states.add("New Jersey");
        states.add("New Mexico");
        states.add("New York");
        //states.add("Newfoundland");
        states.add("North Carolina");
        states.add("North Dakota");
        //states.add("Northwest Territories");
        //states.add("Nova Scotia");
        //states.add("Nunavut");
        states.add("Ohio");
        states.add("Oklahoma");
        //states.add("Ontario");
        states.add("Oregon");
        states.add("Pennsylvania");
        //states.add("Prince Edward Island");
        states.add("Puerto Rico");
        states.add("Quebec");
        states.add("Rhode Island");
        //states.add("Saskatchewan");
        states.add("South Carolina");
        states.add("South Dakota");
        states.add("Tennessee");
        states.add("Texas");
        states.add("Utah");
        states.add("Vermont");
        //states.add("Virgin Islands");
        states.add("Virginia");
        states.add("Washington");
        states.add("West Virginia");
        states.add("Wisconsin");
        states.add("Wyoming");
        //states.add("Yukon Territory");

        ObservableList<String> stateComboBoxItems = FXCollections.observableList(states);
        stateComboBox.setItems(stateComboBoxItems);
    }


    @FXML
    private void handleFocusReservationInformation()
    {
            reservationInformationFocus.setValue(2);
    }
    @FXML
    private void handleUnfocusReservationInformation()
    {
            reservationInformationFocus.setValue(1);
    }


    @FXML
    private void handleFocusBillingInformation()
    {
            billingInformationFocus.setValue(2);
    }
    @FXML
    private void handleUnfocusBillingInformation()
    {
            billingInformationFocus.setValue(1);
    }


    @FXML
    private void handleCancelButton(){
        Stage stage = (Stage) App.newReservationStage.getScene().getWindow();
        App.newReservationWindows -= 1;
        stage.close();
    }




    private void handleSaveAndExitButton() throws IOException {
        BufferedReader br2 = new BufferedReader(new FileReader("src/main/resources/storage/Sites.csv"));
        String line2;
        String splitBy = ",";
        CSVWriter writer = new CSVWriter(new FileWriter("src/main/resources/storage/Sites.csv"));
        //This while loop is for filling up the reservation loop.
        String selectedSite = siteComboBox.getValue();
        while ((line2 = br2.readLine()) != null) {
            String[] site = line2.split(splitBy);
            if ((site[0] + site[1]).equals(selectedSite)){//If we've reached the site that we want to make a reservation at


            }


            //We want to check if we are on the right line
            //this should compare to the combobox that was loaded with the sites
            //if it isnt the same then we need to just add that line to the new writer

            //once we find the right site, we need to add a new reservation at the end of the list
            //then add it to the new writer.


        }

        //after all of this is done, save the csv?
        //need to figure ot if the relative paths work. it should?



    }

}
