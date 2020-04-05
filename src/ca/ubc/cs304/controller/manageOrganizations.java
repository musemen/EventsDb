package ca.ubc.cs304.controller;

import ca.ubc.cs304.database.DatabaseConnectionHandler;
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

import java.text.SimpleDateFormat;
import java.util.Date;

import ca.ubc.cs304.model.event;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class manageOrganizations {
    public DatabaseConnectionHandler databaseConnectionHandler = new DatabaseConnectionHandler();

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
        databaseConnectionHandler.addEvent(newEvent);
    }

    public void deleteEvent(ActionEvent actionEvent) {
        String eventIdtoDelete = EIDDel.getText();
        databaseConnectionHandler.removeEvent(eventIdtoDelete);

    }

    private Date getDateHelper(TextField time) {
        String text = time.getText();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-DD"); 
        return (Date)format.parse(text);
    }
}
