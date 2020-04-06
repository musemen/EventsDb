package ca.ubc.cs304.controller;

import ca.ubc.cs304.database.DatabaseConnectionHandler;
import ca.ubc.cs304.model.event;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

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

            ArrayList<String> conditions = new ArrayList<>();
            ArrayList<String> values = new ArrayList<>();
            conditions.add(EVENTBOX.getValue() != null? EVENTBOX.getValue().toString(): "");
            conditions.add(VENUEBOX.getValue() != null? VENUEBOX.getValue().toString(): "");
            conditions.add(ORGBOX.getValue() != null? ORGBOX.getValue().toString(): "");
            conditions.add(NAMEBOX.getValue() != null? NAMEBOX.getValue().toString(): "");
            conditions.add(STARTTIMEBOX.getValue() != null? STARTTIMEBOX.getValue().toString(): "");
            conditions.add(ENDTIMEBOX.getValue() != null? ENDTIMEBOX.getValue().toString(): "");

            values.add(EID.getText());
            values.add(EID.getText());
            values.add(EID.getText());
            values.add(EID.getText());
            values.add(EID.getText());
            values.add(EID.getText());


            TableView table = new TableView();
            Scene scene = new Scene(new Group());
            Stage stage = new Stage();
            stage.setTitle("Event");
            stage.setWidth(900);
            stage.setHeight(500);
            Label label = new Label("Event");
            TableColumn vT = new TableColumn("Event Id");
            vT.setCellValueFactory(new PropertyValueFactory<>("eventID"));
            TableColumn vT1 = new TableColumn("Venue Id");
            vT1.setCellValueFactory(new PropertyValueFactory<>("venueID"));

            TableColumn vT2 = new TableColumn("Organization Id");
            vT2.setCellValueFactory(new PropertyValueFactory<>("OrganizationID"));

            TableColumn vT3 = new TableColumn("Name");
            vT3.setCellValueFactory(new PropertyValueFactory<>("Name"));

            TableColumn vT4 = new TableColumn("Start Time");
            vT4.setCellValueFactory(new PropertyValueFactory<>("StartTime"));

            TableColumn vT5 = new TableColumn("End Time");
            vT5.setCellValueFactory(new PropertyValueFactory<>("EndTime"));

            TableColumn vT6 = new TableColumn("URL");
            vT6.setCellValueFactory(new PropertyValueFactory<>("Url"));

            table.getColumns().removeAll();
            table.getColumns().addAll(vT,vT1,vT2,vT3,vT4,vT5,vT6);

            for (event object: super.selectEvent(conditions,values)) {
                table.getItems().add(object);
            }
            final VBox vbox = new VBox();
            vbox.setSpacing(7);
            vbox.setPadding(new Insets(10,10,10,10));
            vbox.getChildren().addAll(label, table);
            ((Group) scene.getRoot()).getChildren().addAll(vbox);
            stage.setScene(scene);
            stage.show();
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

        TableView table = new TableView();
        Scene scene = new Scene(new Group());
        Stage stage = new Stage();
        stage.setTitle("Event");
        stage.setWidth(900);
        stage.setHeight(500);
        Label label = new Label("Event");
        TableColumn vT = new TableColumn("Event Id");
        vT.setCellValueFactory(new PropertyValueFactory<>("eventID"));
        TableColumn vT1 = new TableColumn("Venue Id");
        vT1.setCellValueFactory(new PropertyValueFactory<>("venueID"));

        TableColumn vT2 = new TableColumn("Organization Id");
        vT2.setCellValueFactory(new PropertyValueFactory<>("OrganizationID"));

        TableColumn vT3 = new TableColumn("Name");
        vT3.setCellValueFactory(new PropertyValueFactory<>("Name"));

        TableColumn vT4 = new TableColumn("Start Time");
        vT4.setCellValueFactory(new PropertyValueFactory<>("StartTime"));

        TableColumn vT5 = new TableColumn("End Time");
        vT5.setCellValueFactory(new PropertyValueFactory<>("EndTime"));

        TableColumn vT6 = new TableColumn("URL");
        vT6.setCellValueFactory(new PropertyValueFactory<>("Url"));

        table.getColumns().removeAll();
        table.getColumns().addAll(vT,vT1,vT2,vT3,vT4,vT5,vT6);

        for (event object: super.getevent(res)) {
            table.getItems().add(object);
        }
        final VBox vbox = new VBox();
        vbox.setSpacing(7);
        vbox.setPadding(new Insets(10,10,10,10));
        vbox.getChildren().addAll(label, table);
        ((Group) scene.getRoot()).getChildren().addAll(vbox);
        stage.setScene(scene);
        stage.show();
    }


    public void getAverageRating(ActionEvent actionEvent) {
        super.getAverageRating(EIDVOLCHECK.getText());
    }
}
