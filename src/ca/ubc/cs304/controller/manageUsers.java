package ca.ubc.cs304.controller;

import ca.ubc.cs304.database.DatabaseConnectionHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;

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
    }
}
