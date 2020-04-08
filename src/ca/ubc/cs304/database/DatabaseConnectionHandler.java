package ca.ubc.cs304.database;

import ca.ubc.cs304.model.BranchModel;

import java.awt.desktop.SystemEventListener;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import ca.ubc.cs304.model.*;
import ca.ubc.cs304.ui.TerminalTransactions;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Pair;
import org.w3c.dom.Text;
import javafx.scene.control.TextField;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 * This class handles all database related transactions
 */
public class DatabaseConnectionHandler {
	// Use this version of the ORACLE_URL if you are running the code off of the
	// server
	// private static final String ORACLE_URL =
	// "jdbc:oracle:thin:@dbhost.students.cs.ubc.ca:1522:stu";
	// Use this version of the ORACLE_URL if you are tunneling into the undergrad
	// servers
	private static final String ORACLE_URL = "jdbc:oracle:thin:@localhost:1522:stu";
	private static final String EXCEPTION_TAG = "[EXCEPTION]";
	private static final String WARNING_TAG = "[WARNING]";
	private String username = "ora_musashah";
	private String password = "a12748661";

	private static Connection connection;

	{
		try {
			if (connection == null){
				connection = DriverManager.getConnection(ORACLE_URL, username, password);
				System.out.println("connection created");

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	;

	public DatabaseConnectionHandler() {
		try {
			// Load the Oracle JDBC driver
			// Note that the path could change for new drivers
			DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
		} catch (SQLException e) {
			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
		}
	}

	public void close() {
		try {
			if (connection != null) {
				connection.close();
			}
		} catch (SQLException e) {
			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
		}
	}

	// insert
	public void addEvent(event ee) {
		try {
			PreparedStatement ps = connection.prepareStatement("INSERT INTO Event VALUES (?, ?, ?, ?, ?, ?, ?)");
			ps.setString(1, ee.getEventID());
			ps.setString(2, ee.getVenueID());
			ps.setString(3, ee.getOrganizationID());
			ps.setString(4, ee.getName());
			ps.setDate(5, ee.getStartTime());
			ps.setDate(6, ee.getEndTime());
			ps.setString(7, ee.getUrl());
			ps.executeUpdate();
			connection.commit();
			ps.close();
		} catch (SQLException e) {
			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
			rollbackConnection();
		}
	}

	// remove
	public void removeEvent(String eventID) {
		try {
			PreparedStatement ps = connection.prepareStatement("DELETE FROM Event WHERE EventId = ?");
			ps.setString(1, eventID);
			int rowCount = ps.executeUpdate();
			if (rowCount == 0) {
				System.out.println(WARNING_TAG + " " + eventID + " does not exist");
			}
			connection.commit();
			ps.close();

		} catch (SQLException e) {
			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
			rollbackConnection();
		}
	}

	// update
	public void updateRating(String rid, int val, String d) {
		try {
			PreparedStatement ps = connection.prepareStatement("UPDATE Rating SET Value = ?, Description = ? WHERE RatingID = ?");
			ps.setInt(1, val);
			ps.setString(2, d);
			ps.setString(3, rid);

			int rowCount = ps.executeUpdate();
			if (rowCount == 0) {
				System.out.println(WARNING_TAG + " Rating " + rid + " does not exist!");
			}

			connection.commit();
			ps.close();

		} catch (SQLException e) {
			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
			rollbackConnection();
		}
	}


	public ArrayList<event> geteventsAll(){
		ArrayList<event> list = new ArrayList<event>();
		try {
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM Event");

			while(rs.next()) {
				list.add(new event(rs.getString("EventId"), rs.getString("VenueId"), rs.getString("OrganizationID"),
						rs.getString("Name"), rs.getDate("StartTime"),
						rs.getDate("EndTime"), rs.getString("Url")));
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
			rollbackConnection();
		}
		return list;
	}

	// projection
	public ArrayList<event> getevent(ArrayList<String> attributes) {
		ArrayList<event> foundEvents = new ArrayList<event>();
		try {
			Statement stmt = connection.createStatement();
			String query = "SELECT " + String.join(",", attributes) + " FROM Event";
			ResultSet rs = stmt.executeQuery(query);

			while (rs.next()) {
				String eventid = (attributes.contains("EventID") ? rs.getString("EventId") : null);
				String venueid = (attributes.contains("VenueId") ? rs.getString("VenueId") : null);
				String orgid = (attributes.contains("OrganizationID") ? rs.getString("OrganizationID") : null);
				Date starttime = (attributes.contains("StartTime") ? rs.getDate("StartTime") : null);
				Date endtime = (attributes.contains("EndTime") ? rs.getDate("EndTime") : null);
				String url = (attributes.contains("Url") ? rs.getString("Url") : null);
				String name = (attributes.contains("Name") ? rs.getString("Name") : null);
				foundEvents.add(new event(eventid, venueid, orgid, name, starttime, endtime, url));
			}

			rs.close();
			stmt.close();

		} catch (SQLException e) {
			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
			rollbackConnection();
		}

		return foundEvents;
	}


	// selection
	public ArrayList<event> selectEvent(ArrayList<String> condition, ArrayList<String> values){
		String[] attributes = {"EventId","VenueId", "OrganizationID", "Name", "StartTime", "EndTime", "Url" };
		ArrayList<String> usedAttributes = new ArrayList<>();
		ArrayList<String> usedConditions = new ArrayList<>();
		ArrayList<String> usedValues = new ArrayList<>();
		ArrayList<event> events = new ArrayList<>();
		ArrayList<String> all = new ArrayList<>();

		for (int i = 0; i < values.size(); i++){
			if (!condition.get(i).equals("")) {
				all.add(attributes[i]);
				usedAttributes.add(attributes[i]);
				all.add(condition.get(i));
				all.add(values.get(i));
				all.add("AND");
			}
		}
		if (all.get(all.size() -1).equals("AND")) {
			all.remove(all.size() - 1);
		}
		try {
			Statement stmt = connection.createStatement();
			String query = "SELECT * FROM Event" + " WHERE " + String.join(" ", all);
			ResultSet rs = stmt.executeQuery(query);


			while (rs.next()) {
				String eventid = (usedAttributes.contains("EventID") ? rs.getString("EventId") : null);
				String venueid = (usedAttributes.contains("VenueId") ? rs.getString("VenueId") : null);
				String orgid = (usedAttributes.contains("OrganizationID") ? rs.getString("OrganizationID") : null);
				Date starttime = (usedAttributes.contains("StartTime") ? rs.getDate("StartTime") : null);
				Date endtime = (usedAttributes.contains("EndTime") ? rs.getDate("EndTime") : null);
				String url = (usedAttributes.contains("Url") ? rs.getString("Url") : null);
				String name = (usedAttributes.contains("Name") ? rs.getString("Name") : null);
				events.add(new event(eventid, venueid, orgid, name, starttime, endtime, url));
			}

			rs.close();
			stmt.close();

		} catch (SQLException e) {
			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
			rollbackConnection();
		}
		return events;
	}
	public boolean login(String username, String password) {
		try {
			connection.setAutoCommit(false);
			System.out.println("\nConnected to Oracle!");
			return true;
		} catch (SQLException e) {
			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
			return false;
		}
	}

	private void rollbackConnection() {
		try {
			connection.rollback();
		} catch (SQLException e) {
			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
		}
	}

	//join
	public HashMap<String,Address> getAddressOfVenues(){
		HashMap<String,Address> ret = new HashMap<String, Address>();
		try {
			Statement s = connection.createStatement();
			ResultSet rs = s.executeQuery("SELECT Venue.VenueID, Name, City, Province, ZipCode, Street FROM Venue, Address WHERE Venue.VenueID = Address.VenueID");

			while (rs.next()) {
				ret.put(rs.getString("VenueID"), new Address(rs.getString("VenueID"), rs.getString("City"), rs.getString("Province"), rs.getString("ZipCode"), rs.getString("Street")));
			}
			rs.close();
			s.close();
		} catch (SQLException e) {
			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
			rollbackConnection();
		}

		JTable t=new JTable(TerminalTransactions.buildTable(ret));
		JPanel p=new JPanel();
		p.add(t);
		JFrame f=new JFrame();
		f.add(p);
		f.setTitle("Adress of Venues Based on Venue Id's");
		f.setSize(400,400);
		f.setVisible(true);

		return ret;
	}
	public HashMap<String, Integer> countVolunteers() {
		HashMap<String, Integer> res =  new HashMap<>();

		try {
			Statement s = connection.createStatement();
			ResultSet rs = s.executeQuery("SELECT Event.Name AS Eventname, Count(*) As VolunteerCount FROM Event, Volunteer, VolunteersAt WHERE Event.EventId = VolunteersAt.EventID AND VolunteersAt.Username = Volunteer.Username GROUP BY Event.Name");

			while (rs.next()){
				res.put((rs.getString("Eventname")),rs.getInt("VolunteerCount"));
			}

			rs.close();
			s.close();
		} catch (SQLException e) {
			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
			rollbackConnection();
		}
		JTable t=new JTable(TerminalTransactions.toTableModel(res));
		JPanel p=new JPanel();
		p.add(t);
		JFrame f=new JFrame();
		f.add(p);
		f.setTitle("Volunteers per event");
		f.setSize(400,400);
		f.setVisible(true);

		return res;
	}

	public ArrayList<String> getAllTables() {
		ArrayList<String> list = new ArrayList<>();
		try {
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("select TNAME from tab");
			while(rs.next()) {
				list.add(rs.getString("TNAME"));
			}
			rs.close();
			stmt.close();
		}
		catch (SQLException e) {
			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
			rollbackConnection();
		}
		return list;
	}

	public int getAverageRating(String eventID) {
		int avg = 0;
		try {
			Statement s = connection.createStatement();
			ResultSet rs = s.executeQuery("SELECT\n" + "  AVG(Rating.Value) as AverageRating\n" + "FROM\n"
					+ " Event,\n" + " ReceiveRating,\n" + " Rating\n" + "WHERE\n"
					+ " Event.EventId = ReceiveRating.EventId\n" + " AND ReceiveRating.RatingID = Rating.RatingID\n"
					+ " AND Event.EventID = " + eventID);

			while (rs.next()){
				avg = rs.getInt("AverageRating");
			}

			connection.commit();
			rs.close();
			s.close();
		} catch (SQLException e) {
			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
			rollbackConnection();
		}


		Stage dialogStage = new Stage();
		dialogStage.initModality(Modality.WINDOW_MODAL);
		TextArea nameField = new TextArea("The Average Rating of the Event is: " + Integer.toString(avg)+"/5");
		nameField.setFont(Font.font("Verdana", FontWeight.BOLD, 15 ));

		VBox vbox = new VBox(nameField);
		vbox.setAlignment(Pos.CENTER);
		vbox.setPadding(new Insets(15));
		dialogStage.setScene(new Scene(vbox));
		dialogStage.show();
		dialogStage.setTitle("Average Rating");
		return avg;
	}

	public int getVolunteerAtEveryEvent() {
		int result = 0;
		try {
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(
					"SELECT Volunteer.Username FROM \n" +
							"Volunteer \n" +
							"WHERE NOT EXISTS\n" +
							"((SELECT Event.EventId FROM Event) MINUS\n" +
							"(SELECT VolunteersAt.EventID FROM VolunteersAt \n" +
							"WHERE VolunteersAt.Username = Volunteer.Username))");

			while (rs.next()) {
				result ++;
			}

			rs.close();
			stmt.close();
		} catch (SQLException e) {
			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
			rollbackConnection();
		}

		Stage dialogStage = new Stage();
		dialogStage.initModality(Modality.WINDOW_MODAL);
		TextArea nameField = new TextArea("Number of Volunteers who Volunteered at every event: " + Integer.toString(result));
		nameField.setFont(Font.font("Verdana", FontWeight.BOLD, 15 ));

		VBox vbox = new VBox(nameField);
		vbox.setAlignment(Pos.CENTER);
		vbox.setPadding(new Insets(15));
		dialogStage.setScene(new Scene(vbox));
		dialogStage.show();
		dialogStage.setTitle("Volunteer at Every Event");

		return result;
	}
}


	