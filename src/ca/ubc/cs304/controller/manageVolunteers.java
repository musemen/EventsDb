package ca.ubc.cs304.controller;

import ca.ubc.cs304.database.DatabaseConnectionHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class manageVolunteers {
    @FXML
    TextField USER;

    public DatabaseConnectionHandler databaseConnectionHandler = new DatabaseConnectionHandler();

    public void topVolunteer(ActionEvent actionEvent) {
        int maxVal = databaseConnectionHandler.getMaxVolunteerHours();
        String name = databaseConnectionHandler.getMaxVolunteerHourName(maxVal);
        System.out.println("top volunteer and hours volunteered");
    }

    public void searchByName(ActionEvent actionEvent) {
        //todo

        String userID = USER.getText();
        System.out.println("SUM TimeVolunteered for this userID");
    }
}
