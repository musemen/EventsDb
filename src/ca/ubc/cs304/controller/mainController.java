package ca.ubc.cs304.controller;

import ca.ubc.cs304.database.DatabaseConnectionHandler;
import ca.ubc.cs304.delegates.LoginWindowDelegate;
import ca.ubc.cs304.delegates.TerminalTransactionsDelegate;
import ca.ubc.cs304.model.BranchModel;
import ca.ubc.cs304.ui.LoginWindow;
import ca.ubc.cs304.ui.TerminalTransactions;
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
	private DatabaseConnectionHandler dbHandler = null;
	private LoginWindow loginWindow = null;

	public mainController() {
		dbHandler = new DatabaseConnectionHandler();
	}

	private void start() {

	}

	/**
	 * LoginWindowDelegate Implementation
	 * <p>
	 * connects to Oracle database with supplied username and password
	 */
	public void login(String username, String password) {
		boolean didConnect = dbHandler.login(username, password);

		if (didConnect) {
			// Once connected, remove login window and start text transaction flow
			loginWindow.dispose();

		} else {
			loginWindow.handleLoginFailed();

			if (loginWindow.hasReachedMaxLoginAttempts()) {
				loginWindow.dispose();
				System.out.println("You have exceeded your number of allowed attempts");
				System.exit(-1);
			}
		}
	}
	/**
	 * TerminalTransactionsDelegate Implementation
	 * <p>
	 * The TerminalTransaction instance tells us that the user is fine with dropping any existing table
	 * called branch and creating a new one for this project to use
	 */
	public void databaseSetup() {
		dbHandler.databaseSetup();
		;

	}

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