package ca.ubc.cs304.ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ca.ubc.cs304.database.DatabaseConnectionHandler;
public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        DatabaseConnectionHandler dbHandler = new DatabaseConnectionHandler();
        boolean didConnect = dbHandler.login("ora_dorukes","a52316759");

        if (didConnect) {
            Parent root = FXMLLoader.load(getClass().getResource("main.fxml"));
            primaryStage.setTitle("test");
            primaryStage.setScene(new Scene(root, 600, 400));
            primaryStage.show();
        } else {
            System.out.println("You have exceeded your number of allowed attempts");
            System.exit(-1);
        }
    }


    public static void main(String[] args) {
        launch(args);
    }
}
