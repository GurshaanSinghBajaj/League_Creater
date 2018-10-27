package application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.*;

public class team_model {
	
	Connection connection;
	public team_model() {
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
	
	public static ObservableList<team_details> team_table (String user) throws SQLException, ClassNotFoundException {
		
		Connection connection=SqliteConnection.connector()
;		PreparedStatement prepared = null;
        ResultSet rsemps = null;
        String query = "SELECT Team_Id, Team_name,Team_coach,Team_owner FROM Teams where username = ?";
        try {
        	prepared = connection.prepareStatement(query);
			prepared.setString(1, user);
            rsemps = prepared.executeQuery();
            ObservableList<team_details> Team_List = getTeamList(rsemps);
            return Team_List;
        } catch (SQLException e) {
            System.out.println("SQL select operation has been failed: " + e);
            throw e;
        }
    }

	private static ObservableList<team_details> getTeamList(ResultSet rsemps)throws SQLException, ClassNotFoundException {
        ObservableList<team_details> Team_List = FXCollections.observableArrayList();
		
        while(rsemps.next()) {
        	team_details ts = new team_details();
        	ts.setTeam_id(rsemps.getString("Team_Id"));
        	ts.setTeam_name(rsemps.getString("Team_name"));
        	ts.setCoach_name(rsemps.getString("Team_coach"));
        	ts.setTeam_owner(rsemps.getString("Team_owner"));
        	Team_List.add(ts);
        } 
        return Team_List;
	}

	public static void insertteam(String teamid, String teamname, String team_owner,
			String coachname, String user, String leagueid)throws SQLException, ClassNotFoundException {
		Connection connection=SqliteConnection.connector();
		PreparedStatement prepared = null;
		String query = "Insert into Teams values(?,?,?,?,?)";
		try {
			prepared = connection.prepareStatement(query);
			prepared.setString(1, teamid);
			prepared.setString(2, teamname); prepared.setString(3, team_owner);
			prepared.setString(4, coachname); prepared.setString(5, user);
			prepared.executeUpdate();
		}catch(SQLException e) {
			System.out.println("SQL select operation has been failed: " + e);
            throw e;
		}
		prepared = null;
		String Query = "Insert into Team_league values(?,?)";
		try {
			prepared = connection.prepareStatement(Query);
			prepared.setString(1, leagueid);
			prepared.setString(2, teamid);
			prepared.executeUpdate();
		} catch(SQLException e) {
			System.out.println("SQL select operation has been failed: " + e);
            throw e;
		}
	}

	public static void deleteteam(String teamid, String user) throws SQLException, ClassNotFoundException {
		Connection connection=SqliteConnection.connector();
		PreparedStatement prepared = null;
		String query = "Delete from Teams where Team_Id = ? and Username = ?";
		try {
			prepared = connection.prepareStatement(query);
			prepared.setString(1,teamid);
			prepared.setString(2, user);
			prepared.executeUpdate();
		} catch(SQLException e) {
			System.out.println("SQL select operation has been failed: " + e);
            throw e;
		}
	}
	
	public static ObservableList<team_details> search_team(String League_id, String user) throws SQLException, ClassNotFoundException {
		Connection connection=SqliteConnection.connector();
		PreparedStatement prepared = null;
		ResultSet rsemps = null;
		String query = "Select Team_Id, Team_name,Team_coach,Team_owner FROM Teams Natural Inner Join Team_league where League_Id = ? and username = ?";
		try {
			prepared = connection.prepareStatement(query);
			prepared.setString(1,League_id);
			prepared.setString(2,user);
			rsemps = prepared.executeQuery();
			ObservableList<team_details> Team_List = getTeamList(rsemps);
            return Team_List;
		} catch(SQLException e) {
			System.out.println("SQL select operation has been failed: " + e);
            throw e;
		}
	}
}
