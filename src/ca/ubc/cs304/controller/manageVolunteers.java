package ca.ubc.cs304.controller;

import ca.ubc.cs304.database.DatabaseConnectionHandler;
import ca.ubc.cs304.model.event;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Pair;

public class manageVolunteers {
    DatabaseConnectionHandler databaseConnectionHandler = new DatabaseConnectionHandler();
    @FXML
    TextField USER;

    public void countVolunteers(ActionEvent actionEvent) {

        TableView table = new TableView();
        Scene scene = new Scene(new Group());
        Stage stage = new Stage();
        stage.setTitle("Volunteer Count per Event");
        stage.setWidth(900);
        stage.setHeight(500);
        Label label = new Label("Volunteer Count");
        TableColumn vT = new TableColumn("Event Name");
        vT.setCellValueFactory(new PropertyValueFactory<>("Name"));
        TableColumn vT1 = new TableColumn("Count");
        vT1.setCellValueFactory(new PropertyValueFactory<>("Count"));

        table.getColumns().removeAll();
        table.getColumns().addAll(vT,vT1);

        for (Pair<String,Integer> object: databaseConnectionHandler.countVolunteers()) {
            table.getItems().add(object);
        }
        final VBox vbox = new VBox();
        vbox.setSpacing(1);
        vbox.setPadding(new Insets(10,10,10,10));
        vbox.getChildren().addAll(label, table);
        ((Group) scene.getRoot()).getChildren().addAll(vbox);
        stage.setScene(scene);
        stage.show();
    }

    public void searchByName(ActionEvent actionEvent) {
        //todo

        String userID = USER.getText();
        System.out.println("SUM TimeVolunteered for this userID");
    }
}
