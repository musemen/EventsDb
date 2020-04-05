package ca.ubc.cs304.controller;

import ca.ubc.cs304.database.DatabaseConnectionHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

/**
 * This is the main controller class that will orchestrate everything.
 */
public class mainController {
	DatabaseConnectionHandler databaseConnectionHandler = new DatabaseConnectionHandler();
	public void ManageOrgButtonPress(ActionEvent actionEvent) {
			try {
				FXMLLoader fxmlLoader = new FXMLLoader();
				fxmlLoader.setLocation(getClass().getResource("../ui/organizations.fxml"));
				Scene scene = new Scene(fxmlLoader.load(), 800, 400);
				Stage stage = new Stage();
				stage.setTitle("Organizations View");
				stage.setScene(scene);
				stage.show();
			}
			catch (IOException e) {
				e.printStackTrace();
			}
		}

	public void ManageVolButtonPress(ActionEvent actionEvent) {
		try {
			FXMLLoader fxmlLoader = new FXMLLoader();
			fxmlLoader.setLocation(getClass().getResource("../ui/volunteers.fxml"));
			Scene scene = new Scene(fxmlLoader.load(), 800, 400);
			Stage stage = new Stage();
			stage.setTitle("Volunteer View");
			stage.setScene(scene);
			stage.show();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void ManageUserButtonPress(ActionEvent actionEvent) {
		try {
			FXMLLoader fxmlLoader = new FXMLLoader();
			fxmlLoader.setLocation(getClass().getResource("../ui/users.fxml"));
			Scene scene = new Scene(fxmlLoader.load(), 800, 400);
			Stage stage = new Stage();
			stage.setTitle("User View");
			stage.setScene(scene);
			stage.show();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void listTables(ActionEvent actionEvent) {
		ArrayList<String> tables = databaseConnectionHandler.getAllTables();
		String tablesName = "";

		for (String object: tables) {
			tablesName+= object+"\n";
		}

		TextArea l = new TextArea();
		l.setPrefWidth(300);
		l.setPrefHeight(400);
		l.setText(tablesName);
		Scene scene = new Scene(new Group());
		Stage stage = new Stage();
		stage.setTitle("Tables");
		stage.setWidth(350);
		stage.setHeight(500);
		Label label = new Label("Tables");
		TableColumn vT = new TableColumn("Table");

		VBox vbox = new VBox();
		vbox.setSpacing(5);
		vbox.setPadding(new Insets(10, 10, 10, 10));
		vbox.getChildren().addAll(label, l);
		((Group) scene.getRoot()).getChildren().addAll(vbox);
		stage.setScene(scene);
		stage.show();

	}
	}