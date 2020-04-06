package ca.ubc.cs304.controller;

import ca.ubc.cs304.database.DatabaseConnectionHandler;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;

public class manageUsers extends DatabaseConnectionHandler{

    @FXML
    TextField RIDUpdate;
    @FXML
    TextField VAL;
    @FXML
    TextField DESC;


    //selection
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
    TextField Name;

    @FXML
    ChoiceBox VENUEBOX;
    @FXML
    ChoiceBox STARTTIMEBOX;
    @FXML
    ChoiceBox ENDTIMEBOX;
    @FXML
    ChoiceBox EVENTBOX;
    @FXML
    ChoiceBox NAMEBOX;
    @FXML
    ChoiceBox URLBOX;
    @FXML
    ChoiceBox ORGBOX;


    //projection
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

    @FXML
    TextField EIDVOLCHECK;

    public void UsersControlledMouseEnter(MouseEvent mouseEvent) {
        VENUEBOX.getItems().addAll("Equals", "Not Equals");
        ORGBOX.getItems().addAll("Equals", "Not Equals");
        NAMEBOX.getItems().addAll("Equals", "Not Equals");
        URLBOX.getItems().addAll("Equals", "Not Equals");
        EVENTBOX.getItems().addAll("Equals", "Not Equals");
        STARTTIMEBOX.getItems().addAll("Greater than", "Equal to", "Less Than");
        ENDTIMEBOX.getItems().addAll("Greater than", "Equal to", "Less Than");

//
//        VENUEBOX.setItems(FXCollections.observableArrayList(
//                "Equals", "Not Equals"));
//        ORGBOX.setItems(FXCollections.observableArrayList(
//                "Equals", "Not Equals"));
//        NAMEBOX.setItems(FXCollections.observableArrayList(
//                "Equals", "Not Equals"));
//        URLBOX.setItems(FXCollections.observableArrayList(
//                "Equals", "Not Equals"));
//        EVENTBOX.setItems(FXCollections.observableArrayList(
//                "Equals", "Not Equals"));
//        ENDTIMEBOX.setItems(FXCollections.observableArrayList(
//                "greater than", "less than", "equal to"));
//        STARTTIMEBOX.setItems(FXCollections.observableArrayList(
//                "greater than", "less than", "equal to"));
//        choicebox.getSelectionModel().selectedItemProperty().addListener(choiceboxSelectionChangeListener);

    }


    public void updateRating(ActionEvent actionEvent) {
        super.updateRating(RIDUpdate.getText(), Integer.parseInt(VAL.getText()), DESC.getText());
    }


    public void SearchSelection(ActionEvent actionEvent) {

        HashMap<String, Object> res = new HashMap<>();
        res.put(EID.getText(), EVENTBOX.getValue());
        res.put(VID.getText(), VENUEBOX.getValue());
        res.put(OID.getText(), ORGBOX.getValue());
        res.put(Name.getText(), NAMEBOX.getValue());
        res.put(URL.getText(), URLBOX.getValue());
        res.put(STIME.getText(), STARTTIMEBOX.getValue());
        res.put(ETIME.getText(), ENDTIMEBOX.getValue());

        super.selectEvent(res);
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
        super.getevent(res);
    }

    public void getAverageRating(ActionEvent actionEvent) {
        super.getAverageRating(EIDVOLCHECK.getText());
    }


}
