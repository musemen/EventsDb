package ca.ubc.cs304.controller;

import ca.ubc.cs304.database.DatabaseConnectionHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;

import java.util.ArrayList;

public class manageUsers {
    public DatabaseConnectionHandler databaseConnectionHandler = new DatabaseConnectionHandler();

    @FXML
    TextField RIDUpdate;
    @FXML
    TextField VAL;
    @FXML
    TextField DESC;

    @FXML
    TextField EID;
    @FXML
    TextField STIME;
    @FXML
    TextField ETIME;
    @FXML
    TextField VID;
    @FXML
    TextField OID;
    @FXML
    TextField URL;

    @FXML
    TextField KEYWORD;
    @FXML
    CheckBox EIDCHECK;
    @FXML
    CheckBox VIDCHECK;
    @FXML
    CheckBox OIDCHECK;
    @FXML
    CheckBox STIMECHECK;
    @FXML
    CheckBox ETIMECHECK;
    @FXML
    CheckBox URLCHECK;
    @FXML
    CheckBox NAMECHECK;


    public void updateRating(ActionEvent actionEvent) {
        databaseConnectionHandler.updateRating(RIDUpdate.getText(), Integer.parseInt(VAL.getText()), DESC.getText());
    }

    public void SearchSelection(ActionEvent actionEvent) {
    }

    public void SearchProjection(ActionEvent actionEvent) {
        ArrayList<String> res = new ArrayList<>();

        if (EIDCHECK.isSelected()){
            res.add("EventId");
        }
        if (VIDCHECK.isSelected()){
            res.add("VenueId");
        }
        if (OIDCHECK.isSelected()){
            res.add("OrganizationId");
        }
        if (STIMECHECK.isSelected()){
            res.add("StartTime");
        }
        if (ETIMECHECK.isSelected()){
            res.add("EndTime");
        }
        if (URLCHECK.isSelected()){
            res.add("Url");
        }
        if (NAMECHECK.isSelected()){
            res.add("Name");
        }
        databaseConnectionHandler.searchEventsByKeyWord(res,KEYWORD.getText());
    }

    public void CalculateAverageRating(ActionEvent actionEvent) {
        System.out.println("TEST");
    }
}
