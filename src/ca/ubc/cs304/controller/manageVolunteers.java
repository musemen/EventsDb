package ca.ubc.cs304.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class manageVolunteers {
    @FXML
    TextField USER;



    public void countVolunteers(ActionEvent actionEvent) {
        //todo

        System.out.println("count of all volunteers that helped out at each event ");
    }

    public void searchByName(ActionEvent actionEvent) {
        //todo

        String userID = USER.getText();
        System.out.println("SUM TimeVolunteered for this userID");
    }
}
