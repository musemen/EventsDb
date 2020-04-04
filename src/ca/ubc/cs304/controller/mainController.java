package ca.ubc.cs304.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * This is the main controller class that will orchestrate everything.
 */
public class mainController {

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
			stage.setTitle("Organizations View");
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
			stage.setTitle("Organizations View");
			stage.setScene(scene);
			stage.show();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void listTables(ActionEvent actionEvent) {
//todo print out all tables

	}
}