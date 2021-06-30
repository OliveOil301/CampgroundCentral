package main.java;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import main.java.Camping.GroupManager;
import main.java.Camping.ReservationID;
import main.java.Database.Database;
import main.java.Database.SiteData;

import java.io.IOException;


public class App extends Application {

    //public static SimpleBooleanProperty useCache = new SimpleBooleanProperty(true);  I think this is used to ensure there is only one instance of the application running at a time
    public static Stage primaryStage;//This is the main application stage

    public static Stage newReservationStage;//Thi sis the stage for the new reservation window that may pop up
    public static Integer newReservationWindows = 0;

    public static App app_instance = null;


    //Public things:
    public static Database database = new Database();
    public static SiteData siteData = new SiteData();
    public static GroupManager groupManager;

    public static ReservationID reservationID = new ReservationID("000000");


    static {
        try {
            groupManager = siteData.getAllSites();
            //Make a function in GroupManager that loads the next ReservationID and assigns it to the ID above
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static App getInstance(){
        if(app_instance == null){
            app_instance = new App();
        }
        return app_instance;
    }

    @Override
    public void start(Stage stage) throws Exception {
        siteData.loadSitesFromCSV();


        System.out.println("App start");
        App.primaryStage = stage; // stage is the window given to us
        Parent root = FXMLLoader.load(getClass().getResource("/main/resources/views/LoginScreen.fxml"));

        //mapService.loadCSVFile("src/main/resources/edu/wpi/u/MapUAllNodes.csv", "Nodes");
        //mapService.loadCSVFile("src/main/resources/edu/wpi/u/MapUAllEdges.csv", "Edges");

        Scene scene = new Scene(root);
        App.primaryStage.setScene(scene);
        //App.primaryStage.getScene().getStylesheets().add(getClass().getResource("/edu/wpi/u/views/css/BaseStyle.css").toExternalForm());
        //App.primaryStage.setFullScreen(true);
        //App.primaryStage.setFullScreenExitHint("");
        //App.primaryStage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
        App.primaryStage.show();

        App.getPrimaryStage().getScene().setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.Q && e.isControlDown()) {
                App.getInstance().exitApp();
            }
        });
    }





    public static Stage getPrimaryStage() {
        return primaryStage;
    }
    public void exitApp() {
        //App.isLoggedIn.set(false); IF we have some log in procedure
        System.out.println("Shutting Down");
        //Database.getDB().stop(); !Need to do this for the database
        Stage stage = (Stage) App.primaryStage.getScene().getWindow();
        stage.close();
    }
}
