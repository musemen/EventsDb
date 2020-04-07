package ca.ubc.cs304.controller;

import ca.ubc.cs304.database.DatabaseConnectionHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.sql.Date;
import java.time.LocalDate;

import ca.ubc.cs304.model.event;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import javax.management.remote.SubjectDelegationPermission;

public class manageOrganizations extends DatabaseConnectionHandler{
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
        super.addEvent(newEvent);
    }


    public void deleteEvent(ActionEvent actionEvent) {
        String eventIdtoDelete = EIDDel.getText();
        super.removeEvent(eventIdtoDelete);

    }


    public void displayEvents(ActionEvent actionEvent) {

        TableView table = new TableView();
        Scene scene = new Scene(new Group());
        Stage stage = new Stage();
        stage.setTitle("Event");
        stage.setWidth(900);
        stage.setHeight(500);
        Label label = new Label("All Events");
        TableColumn vT = new TableColumn("Event Id");
        System.out.println("display all events");
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

            for (event object: super.geteventsAll()) {
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
    public void getAddressOfVenues(ActionEvent actionEvent) {
        super.getAddressOfVenues();
    }


    private Date getDateHelper(TextField time) {
        String text = time.getText();
        System.out.println(text);
        LocalDate localDate = LocalDate.parse(text);
        java.sql.Date sqlDate = java.sql.Date.valueOf(localDate);
        return sqlDate;

    }
}
