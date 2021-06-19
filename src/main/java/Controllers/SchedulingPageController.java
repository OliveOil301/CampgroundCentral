package main.java.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import main.java.App;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import java.time.Month;
import java.util.Arrays;
import java.time.LocalDate;


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
    private AnchorPane dateAnchorPane;

    @FXML
    private AnchorPane reservationAnchorPane;



    LocalDate date = LocalDate.now();

    private int daysToDisplay = 500;

    //Reading the .csv's:--------------------------
    public void initialize() throws IOException{
        DateRangeHBox.setPadding(new Insets(0,0,0,8));

        //year = new Year(date.getYear(), date.getMonthValue(), date.getDayOfMonth());
        String prevArea = "";
        String line;
        String splitBy = ",";
        //parsing a CSV file into BufferedReader class constructor
        BufferedReader br = new BufferedReader(new FileReader("src/main/resources/storage/Sites.csv"));

        //This first while is for filling up the site list on the left. It makes the headers, sites, whether it is open, and the hookup type
        while ((line = br.readLine()) != null)
        {
            String[] site = line.split(splitBy);    // use comma as separator and split area name and area number

            /* This is for the headers
             *  This makes the header and the additional spacer so it looks nicer
             */
            if(!prevArea.equals(site[0])){//If this is the beginning of a new set of sites
                System.out.println(prevArea + "| - - |" + site[0] + "|");
                Separator newSeparator = new Separator();//This is the separator for the label
                newSeparator.setId("New Separator " + site[0]);
                newSeparator.setPrefWidth(350);

                Label newArea = new Label();
                newArea.setText(site[0]);
                newArea.setPrefHeight(40);
                newArea.setPadding(new Insets(5,0,-2,10));
                newArea.setId(site[0] + " Title");
                newArea.setFont(Font.font("System", FontWeight.BOLD, 36));

                LocationBox.getChildren().add(newSeparator);//These actually add the formatted label and separator
                LocationBox.getChildren().add(newArea);
            }

            HBox box1 = new HBox();

            //This is the main label
            Label siteLabel = new Label();
            siteLabel.setText(site[0] + " " + site[1]);
            siteLabel.setPrefHeight(28);
            siteLabel.setPrefWidth(145);
            siteLabel.setPadding(new Insets(2,0,0,10));
            siteLabel.setId(site[0] + " " + site[1]);
            siteLabel.setFont(new Font("System", 26));

            //This is the site-type thing that may be able to be used to sort.
            Button typeLabel = new Button();
            typeLabel.setText(site[2]);
            typeLabel.setFont(new Font("System", 22));
            typeLabel.setPadding(new Insets(0,5,0,5));
            typeLabel.setPrefWidth(80);
            switch (site[2]){
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
            separator.setId("Separator " + site[0] + " " + site[1]);
            separator.setPrefWidth(350);


            box1.getChildren().add(siteLabel);
            box1.getChildren().add(typeLabel);
            LocationBox.getChildren().add(separator);
            LocationBox.getChildren().add(box1);
            prevArea = site[0];//This is for the location titles above the specific types of sites

            //End of Left-Hand side with the sites and their type----------------------------------------------------------------


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



        BufferedReader br2 = new BufferedReader(new FileReader("src/main/resources/storage/Sites.csv"));
        String line2;

        String prevArea1 = "";
        //This while loop is for filling up the reservation loop.
        while ((line2 = br2.readLine()) != null) {
            String[] site = line2.split(splitBy);
            if(site.length >3){
                if(!prevArea1.equals(site[0])){//If this is the beginning of a new set of sites
                    Separator newSeparator = new Separator();//This is the separator for the label
                    newSeparator.setId("New Separator " + site[0]);
                    newSeparator.setPrefWidth(350);

                    Label newArea = new Label();
                    newArea.setText(site[0]);
                    newArea.setPrefHeight(40);
                    newArea.setPadding(new Insets(5,0,-2,10));
                    newArea.setId(site[0] + " Title");
                    newArea.setFont(Font.font("System", FontWeight.BOLD, 36));

                    mainReservationVBox.getChildren().add(newSeparator);
                    mainReservationVBox.getChildren().add(newArea);

//                    LocationBox.getChildren().add(newSeparator);//These actually add the formatted label and separator
//                    LocationBox.getChildren().add(newArea);
                }



                HBox siteReservations = new HBox();
                double totalReservationsLength = 0;
                LocalDate lastDate = LocalDate.now();
                double buttonHeight = 37;
                for(int i = 3; i<=site.length; i++) {
                    if(i != site.length){
                        String split = "#";
                        String[] reservation = site[i].split(split);


                        //Date shit
                        String[] startDate = reservation[1].split("-");
                        String[] endDate = reservation[2].split("-");
                        LocalDate reservationStart;
                        LocalDate reservationEnd;
                        System.out.println(site[i]);
                        System.out.println(Arrays.toString(reservation));
                        reservationStart = LocalDate.of(Integer.parseInt(startDate[2]), Integer.parseInt(startDate[0]), Integer.parseInt(startDate[1]));
                        reservationEnd = LocalDate.of(Integer.parseInt(endDate[2]), Integer.parseInt(endDate[0]), Integer.parseInt(endDate[1]));

                        LocalDate today = LocalDate.now();

                        int reservationLength;
                        if (reservationStart.isBefore(today)) {
                            reservationLength = dateDifference(today, reservationEnd) + 1;
                        } else {
                            reservationLength = dateDifference(reservationStart, reservationEnd) + 1;
                        }
                        System.out.println("THE LENGTH OF THE RESERVATION IS " + reservationLength);


                        //If there is a gap in between reservations--
                        if (lastDate.isBefore(reservationStart) && dateDifference(lastDate, reservationStart) > 1) {
                            HBox emptyReservationBox = new HBox();
                            Button emptyReservationButton = new Button();
                            int emptyReservationLength = dateDifference(lastDate, reservationStart) - 1;
                            if (emptyReservationLength <= 1) {
                                emptyReservationButton.setText("");
                            } else {
                                emptyReservationButton.setText("Open");
                            }
                            emptyReservationButton.setBackground(new Background(new BackgroundFill(new Color(0.314, 0.72, 0.325, 1), new CornerRadii(3), new Insets(0, 0, 0, 0))));
                            double emptyReservationTotalLength = (emptyReservationLength * 37.35) - 10.0;
                            emptyReservationButton.setPrefSize((emptyReservationTotalLength), buttonHeight);
                            emptyReservationBox.setPadding(new Insets(3, 3, 3, 3));
                            emptyReservationBox.getChildren().add(emptyReservationButton);
                            siteReservations.getChildren().add(emptyReservationBox);
                            System.out.println("THE LENGTH OF THE EMPTY **** RESERVATION IS " + emptyReservationLength);
                            totalReservationsLength += emptyReservationTotalLength + 6;

                        }

                        //more reservation formatting---
                        HBox reservationBox = new HBox();
                        Button reservationButton = new Button();
                        if (reservationLength == 1) {
                            reservationButton.setText("");
                        } else if (reservationLength <= 2) {
                            reservationButton.setText(reservation[0]);
                        } else if (reservationLength <= 3) {
                            reservationButton.setText("Res #" + reservation[0]);
                        }else {
                            reservationButton.setText("Reservation #" + reservation[0]);
                        }
                        reservationButton.setBackground(new Background(new BackgroundFill(new Color(0.878, 0.243, 0.243, 1), new CornerRadii(3), new Insets(0, 0, 0, 0))));
                        double totalLength = (reservationLength * 36.04)-7.2;
                        reservationButton.setPrefSize(totalLength, buttonHeight);
                        reservationBox.setPadding(new Insets(3, 3, 3, 3));
                        reservationBox.getChildren().add(reservationButton);

                        siteReservations.getChildren().add(reservationBox);
                        lastDate = reservationEnd;
                        totalReservationsLength += totalLength + 6;

                    } else {//If we have reached the end of the reservations
                        String split = "#";
                        String[] reservation = site[i-1].split(split);


                        //Date shit
                        String[] startDate1 = reservation[1].split("-");
                        String[] endDate1 = reservation[2].split("-");
                        LocalDate lastReservationStart;
                        LocalDate lastReservationEnd;
                        System.out.println(site[i-1]);
                        System.out.println(Arrays.toString(reservation));
                        lastReservationStart = LocalDate.of(Integer.parseInt(startDate1[2]), Integer.parseInt(startDate1[0]), Integer.parseInt(startDate1[1]));
                        lastReservationEnd = LocalDate.of(Integer.parseInt(endDate1[2]), Integer.parseInt(endDate1[0]), Integer.parseInt(endDate1[1]));
                        if(lastReservationEnd.isBefore(endingDate)){
                            HBox emptyReservationBox = new HBox();
                            Button emptyReservationButton = new Button();
                            int emptyReservationLength = dateDifference(lastReservationEnd, endingDate);
                            if (emptyReservationLength <= 1) {
                                emptyReservationButton.setText("");
                            } else {
                                emptyReservationButton.setText("Open");
                            }
                            emptyReservationButton.setBackground(new Background(new BackgroundFill(new Color(0.314, 0.72, 0.325, 1), new CornerRadii(3), new Insets(0, 0, 0, 0))));
                            emptyReservationButton.setPrefSize(((daysToDisplay*36)-totalReservationsLength)-22, buttonHeight);
                            emptyReservationBox.setPadding(new Insets(3, 3, 3, 3));
                            emptyReservationBox.getChildren().add(emptyReservationButton);
                            siteReservations.getChildren().add(emptyReservationBox);
                            System.out.println("THE LENGTH OF THE EMPTY **** RESERVATION IS " + emptyReservationLength + " AND THE WIDTH IS supposed to be " + emptyReservationButton.getPrefWidth() + " But the actual width is " + emptyReservationButton.getWidth());
                            reservationAnchorPane.setPrefWidth(daysToDisplay*36);
                        }



                    }
                }
                prevArea1 = site[0];//This is for the location titles above the specific types of sites
                mainReservationVBox.getChildren().add(siteReservations);
            }

        }


        //A BUNCH OF LISTENERS____________________________
        dateScrollPane.hvalueProperty().addListener(observable -> {
            handleDateScrolled();
        });
        reservationScrollPane.hvalueProperty().addListener(observable -> {
            handleReservationScrolled();
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


    private void handleReservationScrolled(){
        dateScrollPane.setHvalue(reservationScrollPane.getHvalue());
    }


    private void handleDateScrolled(){
        reservationScrollPane.setHvalue(dateScrollPane.getHvalue());
    }


    @FXML
    private void handleNewReservationButton() throws IOException {
        if(App.newReservationWindows == 0) {
            App.newReservationStage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("/main/resources/views/NewReservationWindow.fxml"));
            Scene scene = new Scene(root);
            App.newReservationStage.setScene(scene);
            App.newReservationStage.show();
            App.newReservationWindows += 1;
        }
    }




}
