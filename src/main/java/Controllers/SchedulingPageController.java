package main.java.Controllers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.*;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import main.java.App;
import main.java.Camping.Group;
import main.java.Camping.Reservation;
import main.java.Camping.Site;
import main.java.Exceptions.InvalidSiteException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import java.time.Duration;
import java.time.Month;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;


public class SchedulingPageController {

    @FXML
    private VBox LocationBox;

    @FXML
    private HBox DateRangeHBox;

    @FXML
    private VBox mainReservationVBox;

    @FXML
    private ScrollPane dateScrollPane;

    @FXML
    private ScrollPane reservationScrollPane;

    @FXML
    private ScrollPane siteScrollPane;

    @FXML
    private AnchorPane dateAnchorPane;

    @FXML
    private AnchorPane reservationAnchorPane;



    LocalDate date = LocalDate.now();

    private int daysToDisplay = 500;

    //Reading the .csv's:--------------------------
    public void initialize() throws IOException, InvalidSiteException {
        DateRangeHBox.setPadding(new Insets(0,0,0,8));

        //year = new Year(date.getYear(), date.getMonthValue(), date.getDayOfMonth());
        String prevArea = "";
        String line;
        String splitBy = ",";
        //parsing a CSV file into BufferedReader class constructor
        BufferedReader br = new BufferedReader(new FileReader("src/main/resources/storage/Sites.csv"));

        //This first while is for filling up the site list on the left. It makes the headers, sites, whether it is open, and the hookup type

        for (Group g:App.groupManager.getGroups()) {

            //This creates the title section for the new group
            Site site1 = g.getSitesInGroup().get(0);
            System.out.println(g.getGroupName() + " IS THE GROUP NAME");
            System.out.println(prevArea + "| - - |" + site1.getGroupName() + "|");
            Separator newSeparator = new Separator();//This is the separator for the label
            newSeparator.setId("New Separator " + site1.getGroupName());
            newSeparator.setPrefWidth(350);

            //This is the actual label for the new group
            Label newArea = new Label();
            newArea.setText(site1.getGroupName());
            newArea.setPrefHeight(40);
            newArea.setPadding(new Insets(5,0,-2,10));
            newArea.setId(site1.getGroupName() + " Title");
            newArea.setFont(Font.font("System", FontWeight.BOLD, 36));

            //This is adding the separator and label to the VBox that holds everything in this section
            LocationBox.getChildren().add(newSeparator);//These actually add the formatted label and separator
            LocationBox.getChildren().add(newArea);

            //This foreach loop adds all the sites to the VBox on the left
            for (Site s:g.getSitesInGroup()) {
                HBox box1 = new HBox();

                //This is the main label
                System.out.println(s.getSiteName() + "HELLO-------------------------");
                Label siteLabel = new Label();
                siteLabel.setText(s.getSiteName());
                siteLabel.setPrefHeight(28);
                siteLabel.setPrefWidth(145);
                siteLabel.setPadding(new Insets(2,0,0,10));
                siteLabel.setId(s.getSiteName());
                siteLabel.setFont(new Font("System", 26));

                //This is the site-type thing that may be able to be used to sort.
                Button typeLabel = new Button();
                typeLabel.setText(s.getSiteType());
                typeLabel.setFont(new Font("System", 22));
                typeLabel.setPadding(new Insets(0,5,0,5));
                typeLabel.setPrefWidth(80);
                switch (s.getSiteType()){
                    case "Full-50":
                        typeLabel.setBackground(new Background(new BackgroundFill(new Color(0.314,0.72,0.325, 1), new CornerRadii(3), new Insets(0,0,0,0))));
                        siteLabel.getWidth();
                        break;
                    case "Full-30":
                        typeLabel.setBackground(new Background(new BackgroundFill(new Color(0.1568,0.4509,0.169, 1), new CornerRadii(3), new Insets(0,0,0,0))));
                        break;
                    case "W & E":
                        typeLabel.setBackground(new Background(new BackgroundFill(new Color(0.427,0.91,0.92, 1), new CornerRadii(3), new Insets(0,0,0,0))));
                        break;
                    case "Tent":
                        typeLabel.setBackground(new Background(new BackgroundFill(new Color(0.878,0.243,0.243, 1), new CornerRadii(3), new Insets(0,0,0,0))));
                        break;
                    case "Rental":
                        typeLabel.setBackground(new Background(new BackgroundFill(new Color(0.651,0.651,0.651, 1), new CornerRadii(3), new Insets(0,0,0,0))));
                        break;
                }
                box1.setSpacing(100);
                box1.setAlignment(Pos.CENTER_LEFT);


                //This is the separator for the label and button
                Separator separator = new Separator();
                separator.setId("Separator " + s.getSiteName());
                separator.setPrefWidth(350);


                box1.getChildren().add(siteLabel);
                box1.getChildren().add(typeLabel);
                LocationBox.getChildren().add(separator);
                LocationBox.getChildren().add(box1);
            }
        }

        LocalDate endingDate = date.plusDays(daysToDisplay+1);

        //This is just a font I use multiple times
        Font textFont = Font.font("System", FontWeight.BOLD, null, 20);

        Month currentMonth = date.getMonth();//This is for the breaking up between months, a flag if you will for the creating of the date section..

        //Setting up a bunch of stuff so the first run through actually has items to work with. they will soon get written over.
        HBox dateBox = new HBox();
        VBox monthVBox = new VBox();
        HBox monthNameHBox = new HBox();
        Rectangle horizontalBoi = new Rectangle();
        Rectangle verticalBoi1 = new Rectangle();
        Label monthLabel = new Label();

        //This while loop is for filling up the dates on the top
        while(date.isBefore(endingDate)){
            if(currentMonth.compareTo(date.getMonth()) != 0 || date.plusDays(1).isEqual(endingDate)){
                monthVBox = new VBox();
                monthNameHBox = new HBox();
                monthLabel = new Label();

                //Setting up the monthLabel
                monthLabel.setText(String.valueOf(date.minusMonths(1).getMonth()));
                monthLabel.setFont(textFont);

                //These are just horizontal and vertical separators to make it look good.
                horizontalBoi = new Rectangle();
                horizontalBoi.setHeight(4);
                horizontalBoi.setWidth(10);
                horizontalBoi.setFill(new Color(0.8, 0.8, 0.8, 1));
                horizontalBoi.setWidth(dateBox.getChildren().size()*18);


                verticalBoi1 = new Rectangle();
                verticalBoi1.setHeight(30);
                verticalBoi1.setWidth(4);
                verticalBoi1.setFill(new Color(0.8, 0.8, 0.8, 1));


                //This is where everything actually gets added to the actual page after it gets put into the main month VBox
                monthNameHBox.getChildren().add(verticalBoi1);
                monthNameHBox.getChildren().add(monthLabel);
                monthNameHBox.setSpacing(3);
                monthVBox.getChildren().add(monthNameHBox);
                monthVBox.getChildren().add(horizontalBoi);
                monthVBox.getChildren().add(dateBox);

                //This addes the whole month to the main thing
                DateRangeHBox.getChildren().add(monthVBox);
                DateRangeHBox.setPadding(new Insets(0, 0, 0, -2));

                currentMonth = date.getMonth();
                dateBox = new HBox();
            }
            VBox dateDayBox = new VBox();
            Label dayOfWeek = new Label();
            Label dateOfMonth = new Label();

            //Making the calendar line here. The Day of the week is shortened to M,T,W,R,F,S,U for their respective days
            String day = " ";
            switch(date.getDayOfWeek()){
                case MONDAY:
                    day = "M";
                    break;
               case TUESDAY:
                    day = "T";
                    break;
               case WEDNESDAY:
                    day = "W";
                    break;
               case THURSDAY:
                    day = "R";
                    break;
               case FRIDAY:
                    day = "F";
                    break;
               case SATURDAY:
                    day = "S";
                    break;
               case SUNDAY:
                    day = "U";
                    break;
            }
            dayOfWeek.setText(day);
            dayOfWeek.setFont(textFont);
            dayOfWeek.setPrefWidth(80);
            dayOfWeek.setAlignment(Pos.TOP_CENTER);
            dateOfMonth.setText(String.valueOf(date.getDayOfMonth()));
            dateOfMonth.setFont(textFont);
            dateOfMonth.setPrefWidth(80);
            dateOfMonth.setAlignment(Pos.TOP_CENTER);
            dateDayBox.getChildren().add(dayOfWeek);
            dateDayBox.getChildren().add(dateOfMonth);
            dateDayBox.setAlignment(Pos.TOP_CENTER);
            dateDayBox.setMinWidth(25);
            dateDayBox.setSpacing(3);
            dateDayBox.setPadding(new Insets(0,1,0,0));

            Rectangle verticalBoi = new Rectangle();
            verticalBoi.setHeight(65);
            verticalBoi.setWidth(4);
            verticalBoi.setFill(new Color(0.8, 0.8, 0.8, 1));



            dateBox.getChildren().add(verticalBoi);
            dateBox.getChildren().add(dateDayBox);
            dateBox.setSpacing(3);

            date = date.plusDays(1);
        }



//        BufferedReader br2 = new BufferedReader(new FileReader("src/main/resources/storage/Sites.csv"));
//        String line2;
//
//        String prevArea1 = "";
//        //This while loop is for filling up the reservation loop.
//        while ((line2 = br2.readLine()) != null) {
//            String[] site = line2.split(splitBy);
//            if(site.length >3){
//                if(!prevArea1.equals(site[0])){//If this is the beginning of a new set of sites
//                    Separator newSeparator = new Separator();//This is the separator for the label
//                    newSeparator.setId("New Separator " + site[0]);
//                    newSeparator.setPrefWidth(350);
//
//                    Label newArea = new Label();
//                    newArea.setText(site[0]);
//                    newArea.setPrefHeight(40);
//                    newArea.setPadding(new Insets(5,0,-2,10));
//                    newArea.setId(site[0] + " Title");
//                    newArea.setFont(Font.font("System", FontWeight.BOLD, 36));
//
//                    mainReservationVBox.getChildren().add(newSeparator);
//                    mainReservationVBox.getChildren().add(newArea);
//
////                    LocationBox.getChildren().add(newSeparator);//These actually add the formatted label and separator
////                    LocationBox.getChildren().add(newArea);
//                }
//
//
//
//                HBox siteReservations = new HBox();
//                double totalReservationsLength = 0;
//                LocalDate lastDate = LocalDate.now();
//                double buttonHeight = 37;
//                for(int i = 3; i<=site.length; i++) {
//                    if(i != site.length){
//                        String split = "#";
//                        String[] reservation = site[i].split(split);
//
//
//                        //Date shit
//                        String[] startDate = reservation[1].split("-");
//                        String[] endDate = reservation[2].split("-");
//                        LocalDate reservationStart;
//                        LocalDate reservationEnd;
//                        System.out.println(site[i]);
//                        System.out.println(Arrays.toString(reservation));
//                        reservationStart = LocalDate.of(Integer.parseInt(startDate[2]), Integer.parseInt(startDate[0]), Integer.parseInt(startDate[1]));
//                        reservationEnd = LocalDate.of(Integer.parseInt(endDate[2]), Integer.parseInt(endDate[0]), Integer.parseInt(endDate[1]));
//
//                        LocalDate today = LocalDate.now();
//
//                        int reservationLength;
//                        if (reservationStart.isBefore(today)) {
//                            reservationLength = dateDifference(today, reservationEnd) + 1;
//                        } else {
//                            reservationLength = dateDifference(reservationStart, reservationEnd) + 1;
//                        }
//                        System.out.println("THE LENGTH OF THE RESERVATION IS " + reservationLength);
//
//
//                        //If there is a gap in between reservations--
//                        if (lastDate.isBefore(reservationStart) && dateDifference(lastDate, reservationStart) > 1) {
//                            HBox emptyReservationBox = new HBox();
//                            Button emptyReservationButton = new Button();
//                            int emptyReservationLength = dateDifference(lastDate, reservationStart) - 1;
//                            if (emptyReservationLength <= 1) {
//                                emptyReservationButton.setText("");
//                            } else {
//                                emptyReservationButton.setText("Open");
//                            }
//                            emptyReservationButton.setBackground(new Background(new BackgroundFill(new Color(0.314, 0.72, 0.325, 1), new CornerRadii(3), new Insets(0, 0, 0, 0))));
//                            double emptyReservationTotalLength = (emptyReservationLength * 37.35) - 10.0;
//                            emptyReservationButton.setPrefSize((emptyReservationTotalLength), buttonHeight);
//                            emptyReservationBox.setPadding(new Insets(3, 3, 3, 3));
//                            emptyReservationBox.getChildren().add(emptyReservationButton);
//                            siteReservations.getChildren().add(emptyReservationBox);
//                            System.out.println("THE LENGTH OF THE EMPTY **** RESERVATION IS " + emptyReservationLength);
//                            totalReservationsLength += emptyReservationTotalLength + 6;
//
//                        }
//
//                        //more reservation formatting---
//                        HBox reservationBox = new HBox();
//                        Button reservationButton = new Button();
//                        if (reservationLength == 1) {
//                            reservationButton.setText("");
//                        } else if (reservationLength <= 2) {
//                            reservationButton.setText(reservation[0]);
//                        } else if (reservationLength <= 3) {
//                            reservationButton.setText("Res #" + reservation[0]);
//                        }else {
//                            reservationButton.setText("Reservation #" + reservation[0]);
//                        }
//                        reservationButton.setBackground(new Background(new BackgroundFill(new Color(0.878, 0.243, 0.243, 1), new CornerRadii(3), new Insets(0, 0, 0, 0))));
//                        double totalLength = (reservationLength * 36.04)-7.2;
//                        reservationButton.setPrefSize(totalLength, buttonHeight);
//                        reservationBox.setPadding(new Insets(3, 3, 3, 3));
//                        reservationBox.getChildren().add(reservationButton);
//
//                        siteReservations.getChildren().add(reservationBox);
//                        lastDate = reservationEnd;
//                        totalReservationsLength += totalLength + 6;
//
//                    } else {//If we have reached the end of the reservations
//                        String split = "#";
//                        String[] reservation = site[i-1].split(split);
//
//
//                        //Date shit
//                        String[] startDate1 = reservation[1].split("-");
//                        String[] endDate1 = reservation[2].split("-");
//                        LocalDate lastReservationStart;
//                        LocalDate lastReservationEnd;
//                        System.out.println(site[i-1]);
//                        System.out.println(Arrays.toString(reservation));
//                        lastReservationStart = LocalDate.of(Integer.parseInt(startDate1[2]), Integer.parseInt(startDate1[0]), Integer.parseInt(startDate1[1]));
//                        lastReservationEnd = LocalDate.of(Integer.parseInt(endDate1[2]), Integer.parseInt(endDate1[0]), Integer.parseInt(endDate1[1]));
//                        if(lastReservationEnd.isBefore(endingDate)){
//                            HBox emptyReservationBox = new HBox();
//                            Button emptyReservationButton = new Button();
//                            int emptyReservationLength = dateDifference(lastReservationEnd, endingDate);
//                            if (emptyReservationLength <= 1) {
//                                emptyReservationButton.setText("");
//                            } else {
//                                emptyReservationButton.setText("Open");
//                            }
//                            emptyReservationButton.setBackground(new Background(new BackgroundFill(new Color(0.314, 0.72, 0.325, 1), new CornerRadii(3), new Insets(0, 0, 0, 0))));
//                            emptyReservationButton.setPrefSize(((daysToDisplay*36)-totalReservationsLength)-22, buttonHeight);
//                            emptyReservationBox.setPadding(new Insets(3, 3, 3, 3));
//                            emptyReservationBox.getChildren().add(emptyReservationButton);
//                            siteReservations.getChildren().add(emptyReservationBox);
//                            System.out.println("THE LENGTH OF THE EMPTY **** RESERVATION IS " + emptyReservationLength + " AND THE WIDTH IS supposed to be " + emptyReservationButton.getPrefWidth() + " But the actual width is " + emptyReservationButton.getWidth());
//                            reservationAnchorPane.setPrefWidth(daysToDisplay*36);
//                        }
//
//
//
//                    }
//                }
//                prevArea1 = site[0];//This is for the location titles above the specific types of sites
//                mainReservationVBox.getChildren().add(siteReservations);
//            }
//
//        }

        loadReservations(LocalDate.now().plusDays(daysToDisplay+1));

        //A BUNCH OF LISTENERS____________________________
        dateScrollPane.hvalueProperty().addListener(observable -> {
            handleDateScrolledH();
        });
        reservationScrollPane.hvalueProperty().addListener(observable -> {
            handleReservationScrolledH();
        });

        siteScrollPane.vvalueProperty().addListener(observable -> {
            handleSiteScrolledV();
        });
        reservationScrollPane.vvalueProperty().addListener(observable -> {
            handleReservationScrolledV();
        });

    }//End of initialize-----


    private int dateDifference(LocalDate start, LocalDate end){
        int i = 0;
        if(end.isBefore(start)||end.isEqual(start)){
            return i;
        }
        while(!start.isEqual(end)){
            start = start.plusDays(1);
            i = i + 1;
        }
        return i;
    }


    private void handleReservationScrolledH(){
        dateScrollPane.setHvalue(reservationScrollPane.getHvalue());
    }

    private void handleDateScrolledH(){
        reservationScrollPane.setHvalue(dateScrollPane.getHvalue());
    }

    private void handleReservationScrolledV(){
        siteScrollPane.setVvalue(reservationScrollPane.getVvalue());
    }

    private void handleSiteScrolledV(){
        reservationScrollPane.setVvalue(siteScrollPane.getVvalue());
    }


    @FXML
    private void handleNewReservationButton(){
        if(App.newReservationWindows == 0) {
            App.newReservationStage = new Stage();
            Parent root = null;
            try {
                root = FXMLLoader.load(getClass().getResource("/main/resources/views/NewReservationWindow.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            Scene scene = new Scene(root);
            App.newReservationStage.setScene(scene);
            App.newReservationStage.show();
            App.newReservationWindows += 1;
        }
    }


    private void loadReservations(LocalDate dateToStop) throws InvalidSiteException {

        for (Group g:App.groupManager.getGroups()) {
            //Make a new HBox with the correct size to space it right
            HBox groupNameSpaceBox = new HBox();
            groupNameSpaceBox.setPrefHeight(58);
            groupNameSpaceBox.setMinHeight(58);
            mainReservationVBox.getChildren().add(groupNameSpaceBox);

            Separator divider = new Separator();
            divider.setOrientation(Orientation.HORIZONTAL);
            divider.setPrefHeight(1);
            divider.setMinHeight(1);
            mainReservationVBox.getChildren().add(divider);

            for (Site s:g.getSitesInGroup()) {
                HBox reservationsBox = new HBox();
                reservationsBox.setSpacing(3);
                reservationsBox.setPadding(new Insets(2, 0, 5, 3));
                if(s.getListOfReservations().size() == 0){
                    reservationsBox.getChildren().add(makeOpenReservationButton(daysToDisplay));//Adding the open day button to the Hbox
                } else {
                    reservationsBox.getChildren().clear();
                    try {
                        reservationsBox.getChildren().setAll(showCompressedView(s).getChildren());
                    } catch (InvalidSiteException e) {
                        e.printStackTrace();
                    }
                }
                reservationsBox.setOnMouseEntered(
                        event -> {
                            reservationsBox.getChildren().clear();
                            try {
                                reservationsBox.getChildren().setAll(showExpandedView(s).getChildren());
                            } catch (InvalidSiteException e) {
                                e.printStackTrace();
                            }
                        });
                reservationsBox.setOnMouseExited(
                        event -> {
                            reservationsBox.getChildren().clear();
                            try {
                                reservationsBox.getChildren().setAll(showCompressedView(s).getChildren());
                            } catch (InvalidSiteException e) {
                                e.printStackTrace();
                            }
                        });
                mainReservationVBox.getChildren().add(reservationsBox);

                Separator divider1 = new Separator();
                divider1.setOrientation(Orientation.HORIZONTAL);
                divider1.setPrefHeight(1);
                divider1.setMinHeight(1);
                mainReservationVBox.getChildren().add(divider1);

            }
        }
        mainReservationVBox.getChildren().remove(mainReservationVBox.getChildren().size()-1);

    }



    private HBox showExpandedView(Site s) throws InvalidSiteException {
        ArrayList<Reservation> reservations = App.groupManager.getSiteFromString(s.getSiteName()).getListOfReservations();
        HBox endBox = new HBox();
        endBox.setSpacing(3);
        endBox.setPadding(new Insets(2, 0, 5, -1));
        if(reservations == null || reservations.size() == 0){
            LocalDate today = LocalDate.now();
            while(!today.isAfter(LocalDate.now().plusDays(daysToDisplay))){
                endBox.getChildren().add(makeOpenReservationButton(today));
                today = today.plusDays(1);
            }
            return endBox;
        }
        LocalDate today = LocalDate.now();
        for (Reservation r:reservations) {

            if(r.getStartDate().isEqual(today) || r.getStartDate().isAfter(today)){//If this reservation has not passed
                LocalDate startDate = r.getStartDate();
                if(today.isBefore(startDate)){
                    while(today.isBefore(r.getStartDate())){
                        endBox.getChildren().add(makeOpenReservationButton(today));
                        today = today.plusDays(1);
                    }
                }
                today = today.plusDays(r.getStartDate().compareTo(r.getEndDate()));
                endBox.getChildren().add(makeReservationButton(r));
            }
        }
        while(today.isBefore(LocalDate.now().plusDays(daysToDisplay))){
            endBox.getChildren().add(makeOpenReservationButton(today));
            today = today.plusDays(1);
        }
        return endBox;
    }

    private HBox showCompressedView(Site s) throws InvalidSiteException {
        ArrayList<Reservation> reservations = App.groupManager.getSiteFromString(s.getSiteName()).getListOfReservations();
        HBox endBox = new HBox();
        endBox.setSpacing(3);
        endBox.setPadding(new Insets(2, 0, 5, -1));
        if (reservations == null || reservations.size() == 0) {
            endBox.getChildren().add(makeOpenReservationButton(daysToDisplay));
            return endBox;
        }
        LocalDate today = LocalDate.now();
        for (Reservation r : reservations) {
            if (r.getStartDate().isEqual(today) || r.getStartDate().isAfter(today)) {//If this reservation has not passed
                LocalDate startDate = r.getStartDate();
                int days = (int) Duration.between(today.atTime(1, 1), startDate.atTime(1, 1)).toDays();
                if (today.isBefore(startDate)) {
                    endBox.getChildren().add(makeOpenReservationButton(days));
                }

                today = today.plusDays((int) Duration.between(r.getStartDate().atTime(1, 1), r.getEndDate().atTime(1, 1)).toDays()+days+1);
                endBox.getChildren().add(makeReservationButton(r));
            }
        }
        LocalDate endingDay = LocalDate.now().plusDays(daysToDisplay);
        long days = Duration.between(today.atTime(1, 1), endingDay.atTime(1, 1)).toDays();
        System.out.println("Days: " + days + " ---------------------------");
        endBox.getChildren().add(makeOpenReservationButton((int) days));
        return endBox;
    }

    private String getStringFromLocalDate(LocalDate date) {
        String day = Integer.toString(date.getDayOfMonth());
        String month = Integer.toString(date.getMonthValue());
        String year = Integer.toString(date.getYear());

        if (day.length() == 1) {
            day = "0" + day + "/";
        } else {
            day = day + "/";
        }

        if (month.length() == 1) {
            month = "0" + month + "/";
        } else {
            month = month + "/";
        }

        return day + month + year;

    }


    private LocalDate getDateFromString(String date) {
        // Date is in format dd/mm/yyyy with slashes
        return LocalDate.of(Integer.parseInt(date.substring(6, 9)), Integer.parseInt(date.substring(3, 4)), Integer.parseInt(date.substring(0, 1)));
    }


    private String getOpenDayID(Site s, LocalDate d){
        String site = s.getSiteName();
        String date = getStringFromLocalDate(d);
        return site + "_" + date;
    }

    private Site getSiteFromOpenDayID(String s) throws InvalidSiteException {
        return App.groupManager.getSiteFromString(s.substring(0,s.length()-12));
    }

    private LocalDate getDateFromOpenDayID(String s){
        return  getDateFromString(s.substring(s.length()-10));
    }




    private Button makeOpenReservationButton(int days){
        Button open = new Button();
        open.setStyle("-fx-background-color: -reservationOpen;\n" +
                "    -fx-text-fill: black;\n");
        open.setMinHeight(35);
        open.setPrefHeight(35);
        open.setMaxHeight(35);
        open.setMinWidth((days*33)+((days)*2));
        open.setPrefWidth((days*33)+((days)*2));
        open.setMaxWidth((days*33)+((days)*2));
        return open;
    }

    private Button makeOpenReservationButton(LocalDate startDate){
        Button open = new Button();
        open.setStyle("-fx-background-color: -reservationOpen;\n" +
                "    -fx-text-fill: black;\n");
        open.setMinHeight(35);
        open.setPrefHeight(35);
        open.setMaxHeight(35);
        open.setOnAction(
                event -> {
                    App.newReservationStart = startDate;
                    handleNewReservationButton();
                }
        );
        open.setMinWidth(33);
        open.setPrefWidth(33);
        open.setMaxWidth(33);

        return open;
    }


    private Button makeReservationButton(Reservation r){
        Button reserved = new Button();
        reserved.setStyle("-fx-background-color: -reservationTaken;\n" +
                "    -fx-text-fill: black;\n");
        reserved.setMinHeight(35);
        reserved.setPrefHeight(35);
        reserved.setMaxHeight(35);
        reserved.setText(r.getCustomerName()[0]);
        reserved.setId(r.getReservationID().getAlphanumericCode());
        reserved.setOnAction(
                event -> {
                    App.viewingReservation = r;
                    //TODO: Make the viewing Reservation stuff here
                }
        );
        int reservationDays = r.getEndDate().compareTo(r.getStartDate())+1;
        int buttonLength = (33*(reservationDays)+((reservationDays)*2));
        reserved.setMinWidth(buttonLength);
        reserved.setPrefWidth(buttonLength);
        reserved.setMaxWidth(buttonLength);


        return reserved;
    }

}
