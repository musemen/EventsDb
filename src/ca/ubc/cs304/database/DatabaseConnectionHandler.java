package ca.ubc.cs304.database;

import ca.ubc.cs304.model.BranchModel;

import java.awt.desktop.SystemEventListener;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;

import ca.ubc.cs304.model.*;
import javafx.util.Pair;

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

	public void deleteBranch(int branchId) {
		try {
			PreparedStatement ps = connection.prepareStatement("DELETE FROM branch WHERE branch_id = ?");
			ps.setInt(1, branchId);

			int rowCount = ps.executeUpdate();
			if (rowCount == 0) {
				System.out.println(WARNING_TAG + " Branch " + branchId + " does not exist!");
			}

			connection.commit();

			ps.close();
		} catch (SQLException e) {
			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
			rollbackConnection();
		}
	}

	public void insertBranch(BranchModel model) {
		try {
			PreparedStatement ps = connection.prepareStatement("INSERT INTO branch VALUES (?,?,?,?,?)");
			ps.setInt(1, model.getId());
			ps.setString(2, model.getName());
			ps.setString(3, model.getAddress());
			ps.setString(4, model.getCity());
			if (model.getPhoneNumber() == 0) {
				ps.setNull(5, java.sql.Types.INTEGER);
			} else {
				ps.setInt(5, model.getPhoneNumber());
			}

			ps.executeUpdate();
			connection.commit();

			ps.close();
		} catch (SQLException e) {
			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
			rollbackConnection();
		}
	}

	public BranchModel[] getBranchInfo() {
		ArrayList<BranchModel> result = new ArrayList<BranchModel>();

		try {
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM branch");

			while (rs.next()) {
				BranchModel model = new BranchModel(rs.getString("branch_addr"), rs.getString("branch_city"),
						rs.getInt("branch_id"), rs.getString("branch_name"), rs.getInt("branch_phone"));
				result.add(model);
			}

			rs.close();
			stmt.close();
		} catch (SQLException e) {
			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
		}

		return result.toArray(new BranchModel[result.size()]);
	}

	public void updateBranch(int id, String name) {
		try {
			PreparedStatement ps = connection.prepareStatement("UPDATE branch SET branch_name = ? WHERE branch_id = ?");
			ps.setString(1, name);
			ps.setInt(2, id);

			int rowCount = ps.executeUpdate();
			if (rowCount == 0) {
				System.out.println(WARNING_TAG + " Branch " + id + " does not exist!");
			}

			connection.commit();

			ps.close();
		} catch (SQLException e) {
			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
			rollbackConnection();
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

	public void addRating(Rating r) {
		try {
			PreparedStatement ps = connection.prepareStatement("INSERT INTO Rating VALUES (?,?,?)");
			ps.setString(1, r.getRatingID());
			ps.setInt(2, r.getValue());
			ps.setString(3, r.getDescription());
			ps.executeUpdate();
			connection.commit();
			ps.close();
		} catch (SQLException e) {
			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
			rollbackConnection();
		}
	}

	public void removeRating(String rid) {
		try {
			PreparedStatement ps = connection.prepareStatement("DELETE FROM Rating WHERE RatingID = ?");
			ps.setString(1, rid);
			int rowCount = ps.executeUpdate();
			if (rowCount == 0) {
				System.out.println(WARNING_TAG + " " + rid + " does not exist");
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

	public void addVolunteer(Volunteer v) {
		try {
			PreparedStatement ps = connection.prepareStatement("INSERT INTO Volunteer VALUES (?,?,?)");
			ps.setString(1, v.getUsername());
			ps.setInt(2, v.getTimeVolunteered());
			ps.setString(3, v.getPassword());
			ps.executeUpdate();
			connection.commit();
			ps.close();
		} catch (SQLException e) {
			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
			rollbackConnection();
		}
	}

	public void removeVolunteer(String u) {
		try {
			PreparedStatement ps = connection.prepareStatement("DELETE FROM Volunteer WHERE Username = ?");
			ps.setString(1, u);
			int rowCount = ps.executeUpdate();
			if (rowCount == 0) {
				System.out.println(WARNING_TAG + " " + u + " does not exist");
			}
			connection.commit();
			ps.close();

		} catch (SQLException e) {
			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
			rollbackConnection();
		}
	}

	public void addOrgnaization(Organization o) {
		try {
			PreparedStatement ps = connection.prepareStatement("INSERT INTO Organization VALUES (?,?)");
			ps.setString(1, o.getOrganizationID());
			ps.setString(2, o.getName());
			ps.executeUpdate();
			connection.commit();
			ps.close();
		} catch (SQLException e) {
			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
			rollbackConnection();
		}
	}

	public void removeOrganization(String oid) {
		try {
			PreparedStatement ps = connection.prepareStatement("DELETE FROM Organization WHERE OrganizationID = ?");
			ps.setString(1, oid);
			int rowCount = ps.executeUpdate();
			if (rowCount == 0) {
				System.out.println(WARNING_TAG + " " + oid + " does not exist");
			}
			connection.commit();
			ps.close();

		} catch (SQLException e) {
			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
			rollbackConnection();
		}
	}

	public void addTicket(Ticket t) {
		try {
			PreparedStatement ps = connection.prepareStatement("INSERT INTO Ticket VALUES (?,?,?,?,?,?)");
			ps.setString(1, t.getTicketID());
			ps.setString(2, t.getTicketType());
			ps.setDouble(3, t.getPrice());
			ps.setString(4, t.getEventID());
			ps.setString(6, t.getOrderNum());
			ps.executeUpdate();
			connection.commit();
			ps.close();
		} catch (SQLException e) {
			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
			rollbackConnection();
		}
	}

	public void removeTicket(String tid) {
		try {
			PreparedStatement ps = connection.prepareStatement("DELETE FROM Ticket WHERE TicketID = ?");
			ps.setString(1, tid);
			int rowCount = ps.executeUpdate();
			if (rowCount == 0) {
				System.out.println(WARNING_TAG + " " + tid + " does not exist");
			}
			connection.commit();
			ps.close();

		} catch (SQLException e) {
			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
			rollbackConnection();
		}
	}

	public void addVenue(Venue v) {
		try {
			PreparedStatement ps = connection.prepareStatement("INSERT INTO Venue VALUES (?,?,?,?)");
			ps.setString(1, v.getVenueID());
			ps.setInt(3, v.getAgeRestriction());
			ps.setString(4, v.getName());
			ps.setInt(6, v.getCapacity());
			ps.executeUpdate();
			connection.commit();
			ps.close();
		} catch (SQLException e) {
			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
			rollbackConnection();
		}
	}

	public void removeVenue(String vid) {
		try {
			PreparedStatement ps = connection.prepareStatement("DELETE FROM Venue WHERE VenueID = ?");
			ps.setString(1, vid);
			int rowCount = ps.executeUpdate();
			if (rowCount == 0) {
				System.out.println(WARNING_TAG + " " + vid + " does not exist");
			}
			connection.commit();
			ps.close();

		} catch (SQLException e) {
			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
			rollbackConnection();
		}
	}

	public void addAddress(Address a) {
		try {
			PreparedStatement ps = connection.prepareStatement("INSERT INTO Address VALUES (?,?,?,?,?)");
			ps.setString(1, a.getVenueID());
			ps.setString(2, a.getCity());
			ps.setString(3, a.getProvince());
			ps.setString(4, a.getZipCode());
			ps.setString(5, a.getStreet());
			ps.executeUpdate();
			connection.commit();
			ps.close();
		} catch (SQLException e) {
			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
			rollbackConnection();
		}
	}

	public void removeAddress(String vid, String zc, String s) {
		try {
			PreparedStatement ps = connection
					.prepareStatement("DELETE FROM Address WHERE VenueID = ? AND ZipCode = ? AND Street = ?");
			ps.setString(1, vid);
			ps.setString(2, zc);
			ps.setString(3, s);
			int rowCount = ps.executeUpdate();
			if (rowCount == 0) {
				System.out.println(WARNING_TAG + " " + vid + ", " + zc + " " + s + " does not exist");
			}
			connection.commit();
			ps.close();

		} catch (SQLException e) {
			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
			rollbackConnection();
		}
	}

	public void addAttendee(Attendee a) {
		try {
			PreparedStatement ps = connection.prepareStatement("INSERT INTO Attendee VALUES (?,?)");
			ps.setString(1, a.getUsername());
			ps.setString(2, a.getPassword());
			ps.executeUpdate();
			connection.commit();
			ps.close();
		} catch (SQLException e) {
			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
			rollbackConnection();
		}
	}

	public void removeAttendee(String u) {
		try {
			PreparedStatement ps = connection.prepareStatement("DELETE FROM Attendee WHERE Username = ?");
			ps.setString(1, u);
			int rowCount = ps.executeUpdate();
			if (rowCount == 0) {
				System.out.println(WARNING_TAG + " " + u + " does not exist");
			}
			connection.commit();
			ps.close();

		} catch (SQLException e) {
			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
			rollbackConnection();
		}
	}

	public void addPerformer(Performer p) {
		try {
			PreparedStatement ps = connection.prepareStatement("INSERT INTO Performer VALUES (?,?,?)");
			ps.setString(1, p.getPerformerID());
			ps.setString(2, p.getGenre());
			ps.setString(3, p.getName());
			ps.executeUpdate();
			connection.commit();
			ps.close();
		} catch (SQLException e) {
			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
			rollbackConnection();
		}
	}

	public void removePerformer(String pid) {
		try {
			PreparedStatement ps = connection.prepareStatement("DELETE FROM Performer WHERE PerformerID = ?");
			ps.setString(1, pid);
			int rowCount = ps.executeUpdate();
			if (rowCount == 0) {
				System.out.println(WARNING_TAG + " " + pid + " does not exist");
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

	public String getVolunteerStatus(String username) {
		String status = "";
		try {
			Statement s = connection.createStatement();
			ResultSet rs = s.executeQuery(
					"SELECT VolunteerType FROM Volunteer,VolunteerClassification WHERE Volunteer.Username = " + username
							+ " AND VolunteerClassification.TimeVolunteered = Volunteer.TimeVolunteered");
			while (rs.next()) {
				status = rs.getString("VolunteerType");
			}
		} catch (SQLException e) {
			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
			rollbackConnection();
		}
		return status;
	}

	// selection
	public void selectEvent(HashMap<String, Object> hm){

		System.out.println(hm);
//		for (int i = 0; i < attributes.size(); i++){
//			all.add(attributes.get(i));
//			all.add(condition.get(i));
//			all.add(values.get(i));
//		}
//		try {
//			Statement stmt = connection.createStatement();
//			String query = "SELECT * FROM Event" + String.join("AND ", all);
//			ResultSet rs = stmt.executeQuery(query);
//
//
//			while (rs.next()) {
//				System.out.println(rs.getString(1));
//			}
//
//			rs.close();
//			stmt.close();
//
//		} catch (SQLException e) {
//			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
//			rollbackConnection();
//		}
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

	public void databaseSetup() {
		dropBranchTableIfExists();

		try {
			Statement stmt = connection.createStatement();
			stmt.executeUpdate(
					"CREATE TABLE branch (branch_id integer PRIMARY KEY, branch_name varchar2(20) not null, branch_addr varchar2(50), branch_city varchar2(20) not null, branch_phone integer)");
			stmt.close();
		} catch (SQLException e) {
			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
		}

		BranchModel branch1 = new BranchModel("123 Charming Ave", "Vancouver", 1, "First Branch", 1234567);
		insertBranch(branch1);

		BranchModel branch2 = new BranchModel("123 Coco Ave", "Vancouver", 2, "Second Branch", 1234568);
		insertBranch(branch2);
	}

	private void dropBranchTableIfExists() {
		try {
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("select table_name from user_tables");

			while (rs.next()) {
				if (rs.getString(1).toLowerCase().equals("branch")) {
					stmt.execute("DROP TABLE branch");
					break;
				}
			}

			rs.close();
			stmt.close();
		} catch (SQLException e) {
			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
		}
	}

	public ArrayList<Pair<String,Integer>> countVolunteers() {
		ArrayList<Pair<String,Integer>> res =  new ArrayList<>();

		try {
			Statement s = connection.createStatement();
			ResultSet rs = s.executeQuery("SELECT Event.Name AS Eventname, Count(*) As VolunteerCount FROM Event, Volunteer, VolunteersAt WHERE Event.EventId = VolunteersAt.EventID AND VolunteersAt.Username = Volunteer.Username GROUP BY Event.Name");

			while (rs.next()){
				res.add(new Pair<String,Integer>(rs.getString("Eventname"),rs.getInt("VolunteerCount")));
			}
			rs.close();
			s.close();
		} catch (SQLException e) {
			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
			rollbackConnection();
		}
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
//		System.out.println(avg);
		return avg;
	}

	public ArrayList<String> getVolunteerAtEveryEvent() {
		ArrayList<String> volunteers = new ArrayList<String>();
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
				volunteers.add(rs.getString("Username"));
			}

			rs.close();
			stmt.close();
		} catch (SQLException e) {
			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
			rollbackConnection();
		}
		System.out.println(volunteers.size());
		return volunteers;
	}
}


	