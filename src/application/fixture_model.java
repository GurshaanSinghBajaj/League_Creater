package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class fixture_model {

	Connection connection;
	public fixture_model() {
		connection=SqliteConnection.connector();
		if(connection==null) System.exit(1);
	}
	
	public boolean isDbConnected() {
		try {
			return !connection.isClosed();
		} catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public static ObservableList<fixture_detail> fixture_table (String user) throws SQLException, ClassNotFoundException {
		Connection connection=SqliteConnection.connector();
		PreparedStatement prepared = null;
        ResultSet rsemps = null;
        String query = "SELECT Fixture_Id, Home_Team,Away_Team,Date,Time,Venue FROM Fixtures where username = ?";
        try {
        	prepared = connection.prepareStatement(query);
			prepared.setString(1, user);
            rsemps = prepared.executeQuery();
            ObservableList<fixture_detail> Fixture_List = getFixtureList(rsemps);
            return Fixture_List;
        } catch (SQLException e) {
            System.out.println("SQL select operation has been failed: " + e);
            throw e;
        }
    }
	
	private static ObservableList<fixture_detail> getFixtureList(ResultSet rsemps)throws SQLException, ClassNotFoundException {
        ObservableList<fixture_detail> Fixture_List = FXCollections.observableArrayList();
		
        while(rsemps.next()) {
        	fixture_detail ts = new fixture_detail();
        	ts.setfixture_id(rsemps.getString("Fixture_Id"));
        	ts.sethome_team(rsemps.getString("Home_Team"));
        	ts.setaway_team(rsemps.getString("Away_Team"));
        	ts.setdate(rsemps.getString("Date"));
        	ts.settime(rsemps.getString("Time"));
        	ts.setvenue(rsemps.getString("Venue"));
        	Fixture_List.add(ts);
        } 
        return Fixture_List;
	}
	
	public static void insertfixture(String fixtureid, String hometeam, String awayteam,
			String date, String time, String venue,String leagueid, String user)throws SQLException, ClassNotFoundException {
		Connection connection=SqliteConnection.connector();
		PreparedStatement prepared = null;
		String query = "Insert into Fixtures values(?,?,?,?,?,?,?)";
		try {
			prepared = connection.prepareStatement(query);
			prepared.setString(1, fixtureid);
			prepared.setString(2, hometeam); prepared.setString(3, awayteam);
			prepared.setString(4, date); prepared.setString(5, time);
			prepared.setString(6, venue); prepared.setString(7, user);
			prepared.executeUpdate();
		}catch(SQLException e) {
			System.out.println("SQL select operation has been failed: " + e);
            throw e;
		}
		prepared = null;
		String Query = "Insert into Fixture_league values(?,?)";
		try {
			prepared = connection.prepareStatement(Query);
			prepared.setString(1, fixtureid);
			prepared.setString(2, leagueid);
			prepared.executeUpdate();
		} catch(SQLException e) {
			System.out.println("SQL select operation has been failed: " + e);
            throw e;
		}
	}
	
	public static void deletefixture(String fixtureid, String user) throws SQLException, ClassNotFoundException {
		Connection connection=SqliteConnection.connector();
		PreparedStatement prepared = null;
		String query = "Delete from Fixtures where Fixture_Id = ? and Username = ?";
		try {
			prepared = connection.prepareStatement(query);
			prepared.setString(1,fixtureid);
			prepared.setString(2, user);
			prepared.executeUpdate();
		} catch(SQLException e) {
			System.out.println("SQL select operation has been failed: " + e);
            throw e;
		}
		String QUERY = "Delete from Fixture_league where Fixture_Id = ?";
		try {
			System.out.println("delete");
			prepared = connection.prepareStatement(QUERY);
			prepared.setString(1,fixtureid);
			prepared.executeUpdate();
		} catch(SQLException e) {
			System.out.println("SQL select operation has been failed: " + e);
            throw e;
		}
	}
	
	public static ObservableList<fixture_detail> search_fixture(String League_id, String user) throws SQLException, ClassNotFoundException {
		Connection connection=SqliteConnection.connector();
		PreparedStatement prepared = null;
		ResultSet rsemps = null;
		System.out.println("Search Fixture");
		String query = "Select Fixtures.Fixture_Id, Home_Team,Away_Team, Date, Time, Venue FROM Fixtures Inner Join Fixture_league on Fixtures.Fixture_Id = Fixture_league.Fixture_Id where League_Id = ? and username = ?";
		try {
			prepared = connection.prepareStatement(query);
			prepared.setString(1,League_id);
			prepared.setString(2,user);
			rsemps = prepared.executeQuery();
			ObservableList<fixture_detail> Fixture_List = getFixtureList(rsemps);
            return Fixture_List;
		} catch(SQLException e) {
			System.out.println("SQL select operation has been failed: " + e);
            throw e;
		}
	}

}
