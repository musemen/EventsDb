package ca.ubc.cs304.controller;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.sql.Date;
import ca.ubc.cs304.model.event;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class manageOrganizations {

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
    TextField NAME;
    @FXML
    TextField EIDDel;

    public void addEvent(ActionEvent actionEvent) {

        String eventId = EID.getText();
        String venueId = VID.getText();
        String orgId = OID.getText();
        String name = NAME.getText();
        Date startTime = getDateHelper(STIME);
        Date endTime = getDateHelper(ETIME);
        String url = URL.getText();

        event newEvent = new event(eventId,venueId,orgId,name,startTime,endTime,url);

        //todo
        System.out.println("add this shit to fucking db lol");
    }


    public void deleteEvent(ActionEvent actionEvent) {
        String eventIdtoDelete = EIDDel.getText();
        //todo
        System.out.println("delete this event from db");
    }

    public void displayEvents(ActionEvent actionEvent) {
        //todo
        System.out.println("display all events");
    }


    private Date getDateHelper(TextField time) {
        String text = time.getText();
        java.time.format.DateTimeFormatter formatter = java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy");
        java.time.LocalDate textFieldAsDate = java.time.LocalDate.parse(text, formatter);
        java.sql.Date sqlDate = java.sql.Date.valueOf(textFieldAsDate);
        return sqlDate;
    }
}
