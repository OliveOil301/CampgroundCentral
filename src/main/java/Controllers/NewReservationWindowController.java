package main.java.Controllers;


import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.RequiredFieldValidator;
import javafx.animation.ScaleTransition;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.Duration;
import main.java.App;
import main.java.Camping.Group;
import main.java.Camping.Reservation;
import main.java.Camping.Site;
import main.java.Exceptions.InvalidReservationIDException;
import main.java.Exceptions.InvalidSiteException;


import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class NewReservationWindowController {

    @FXML
    private HBox overallHBox;

    //The input fields start here:-------------------
    @FXML
    private JFXComboBox<String> siteComboBox;

    @FXML
    private JFXComboBox<String> stateComboBox;

    @FXML
    private JFXDatePicker startDateBox;

    @FXML
    private JFXDatePicker endDateBox;

    @FXML
    private JFXTextField vehicleMakeBox;

    @FXML
    private JFXTextField vehicleModelBox;

    @FXML
    private JFXTextField vehicleLicenseBox;

    @FXML
    private JFXTextField camperMakeBox;

    @FXML
    private JFXTextField camperModelBox;

    @FXML
    private JFXTextField camperLicenseBox;

    @FXML
    private JFXTextField firstNameBox;

    @FXML
    private JFXTextField lastNameBox;

    @FXML
    private JFXTextField streetBox;

    @FXML
    private JFXTextField cityBox;

    @FXML
    private JFXTextField zipCodeBox;

    @FXML
    private JFXTextField emailBox;

    @FXML
    private JFXTextField phoneNumberBox;




    //These are the separators used to show focus:
    @FXML
    private Separator ReservationSeparator;
    @FXML
    private Separator BillingSeparator;


    /* These SimpleIntegerProperties are used for focusing and unfocusing the two main sections of the New Reservation window
     *  A value of 0 corresponds to "not focused"
     *  A value of 1 corresponds to "unfocusing"
     *  A value of 2 corresponds to "focusing"
     *  A value of 3 corresponds to "focused"
     */
    private static SimpleIntegerProperty reservationInformationFocus = new SimpleIntegerProperty(0);
    private static SimpleIntegerProperty billingInformationFocus = new SimpleIntegerProperty(0);





    public void initialize() throws IOException {
        reservationInformationFocus = new SimpleIntegerProperty(0);
        billingInformationFocus = new SimpleIntegerProperty(0);


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
        setSites();

        //This fills up the state combobox with items
        setStates();

        //This sets the validators for all the required fields
        setValidators();

        //This sets the rests for the validators when you change the values
        setListeners();

        //This sets the date and site if an open day is clicked in the scheduling page
        setPreFilledData();

        //This add the reservationWizard pane to this window
        addReservationWizardPane();
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
        App.newReservationWindows = false;
        stage.close();

    }



    @FXML
    private void handleSaveAndExitButton() throws IOException, InvalidReservationIDException {
        if(goodForSubmission()){
            String customerName = firstNameBox.getText() + lastNameBox.getText();
            Reservation r = new Reservation(startDateBox.getValue(), endDateBox.getValue(), siteComboBox.getValue(), customerName, phoneNumberBox.getText(), vehicleMakeBox.getText(), vehicleModelBox.getText(), vehicleLicenseBox.getText(), camperMakeBox.getText(), camperModelBox.getText(), camperLicenseBox.getText() );
            r.setToNextID();
            App.groupManager.addReservation(r, true);
            System.out.println("We submitted??");
            //No point in retyping this code
            handleCancelButton();
        }

    }



    private boolean goodForSubmission(){
        boolean good = true;
        if(siteComboBox.getValue() == null){
            good = false;
            siteComboBox.validate();
        }
        if(startDateBox.getValue() == null || startDateBox.getValue().isBefore(LocalDate.now())){
            good = false;
            startDateBox.validate();
        }
        if(endDateBox.getValue() == null || endDateBox.getValue().isBefore(startDateBox.getValue()) || endDateBox.getValue().isBefore(LocalDate.now())){
            good = false;
            endDateBox.validate();
        }
        if(firstNameBox.getText().equals("")){
            good = false;
            firstNameBox.validate();
        }if(lastNameBox.getText().equals("")){
            good = false;
            lastNameBox.validate();
        }
        if(streetBox.getText().equals("")){
            good = false;
            streetBox.validate();
        }
        if(cityBox.getText().equals("")){
            good = false;
            cityBox.validate();
        }
        if(stateComboBox.getValue() == null){
            good = false;
            stateComboBox.validate();
        }
        if(zipCodeBox.getText().equals("") || zipCodeBox.getText().length() != 5){
            good = false;
            zipCodeBox.validate();
        }
        try {
            if(!siteIsOpenThatPeriod(siteComboBox.getValue(), startDateBox.getValue(), endDateBox.getValue())){
                startDateBox.validate();
                endDateBox.validate();
                good = false;
            }
        } catch (Exception e) {
            good = false;
            siteComboBox.validate();
        }

        return good;
    }


    /**
     * setStates fills up the state comboBox for the initialize method to make it a little more clean
     */
    private void setStates(){
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

    /**
     * setSites fills up the site comboBox for the initialize method
     */
    private void setSites(){
        ArrayList<String> comboList = new ArrayList<String>();
        ObservableList<String> ComboBoxItems = FXCollections.observableList(comboList);
        for (Group g:App.groupManager.getGroups()) {
            for (Site s:g.getSitesInGroup()) {
                ComboBoxItems.add(s.getSiteName());
            }
        }
        siteComboBox.setItems(ComboBoxItems);
    }


    /**
     * setValidators sets the validators for all inputs that are required or need error checking
     */
    private void setValidators(){
        //The validators start here:---------------------
        setValidator(siteComboBox, "Site Required");
        setValidator(startDateBox, "Start date required");
        setValidator(endDateBox, "End date required");
        setValidator(firstNameBox, "First name required");
        setValidator(lastNameBox, "Last name required");
        setValidator(streetBox, "Street required");
        setValidator(cityBox, "City Required");
        setValidator(stateComboBox, "State Required");
        setValidator(zipCodeBox, "Zip Code Required");
        setValidator(emailBox, "Email required");
    }

    /**
     * setValidator sets up a validator on a TextField, ComboBox, or DatePicker
     * @param t the specific control
     * @param message the message to display when the field is not filled
     */
    private void setValidator(JFXTextField t, String message){
        RequiredFieldValidator v = new RequiredFieldValidator();
        v.setMessage(message);
        t.setValidators(v);
    }
    private void setValidator(JFXComboBox<String> t, String message){
        RequiredFieldValidator v = new RequiredFieldValidator();
        v.setMessage(message);
        t.setValidators(v);
    }
    private void setValidator(JFXDatePicker t, String message){
        RequiredFieldValidator v = new RequiredFieldValidator();
        v.setMessage(message);
        t.setValidators(v);
    }


    private void setListener(JFXTextField t){
        t.setOnKeyTyped(
                new EventHandler<KeyEvent>() {
                    @Override
                    public void handle(KeyEvent event) {
                        t.resetValidation();
                    }
                }
        );
    }
    private void setListener(JFXComboBox<String> t){
        t.valueProperty().addListener(
                new ChangeListener<String>() {
                    @Override
                    public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                        t.resetValidation();
                    }
                }
        );
    }
    private void setListener(JFXDatePicker t){
        t.valueProperty().addListener(
                new ChangeListener<LocalDate>() {
                    @Override
                    public void changed(ObservableValue<? extends LocalDate> observable, LocalDate oldValue, LocalDate newValue) {
                        t.resetValidation();
                    }
                }
        );
    }

    private void setListeners(){
        setListener(siteComboBox);
        setListener(startDateBox);
        setListener(endDateBox);
        setListener(firstNameBox);
        setListener(lastNameBox);
        setListener(streetBox);
        setListener(cityBox);
        setListener(stateComboBox);
        setListener(zipCodeBox);
        setListener(emailBox);

        //This is the listener for the ReservationWizard
        App.needToUpdate.addListener((observable, oldValue, newValue) -> {
            setPreFilledData();
        });
    }

    private boolean siteIsOpenThatPeriod(String s, LocalDate start, LocalDate end) throws InvalidSiteException {
        Site site = App.groupManager.getSiteFromString(s);
        return site.isAvailable(start, end);

    }


    /**
     * setPreFilledData is called in the initialize of this window to pre-fill the date and site
     * if an open day is clicked on in the scheduling page.
     * The data is taken from App and is cleared immediately after
     */
    private void setPreFilledData(){
        if(App.newReservationSite != null && App.newReservationStart != null) {
            siteComboBox.setValue(App.newReservationSite);
            startDateBox.setValue(App.newReservationStart);
            App.newReservationSite = null;
            App.newReservationStart = null;
            if(App.newReservationEnd != null) {
                endDateBox.setValue(App.newReservationEnd);
                App.newReservationEnd = null;
            }
        }


    }


    private void addReservationWizardPane() throws IOException {
        AnchorPane root = FXMLLoader.load(getClass().getResource("/main/resources/views/ReservationWizardPane.fxml"));
        overallHBox.getChildren().add(root);
    }
}
