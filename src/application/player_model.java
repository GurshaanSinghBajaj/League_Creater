package application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.*;

public class player_model {
	
	Connection connection;
	public player_model() {
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
	
	public static ObservableList<player_details> player_table (String user) throws SQLException, ClassNotFoundException {
		
		Connection connection=SqliteConnection.connector()
;		PreparedStatement prepared = null;
        ResultSet rsemps = null;
        String query = "SELECT Player_Id, Player_name, Date_Of_Birth,City, Role FROM Players where username = ?";
        try {
        	prepared = connection.prepareStatement(query);
			prepared.setString(1, user);
            rsemps = prepared.executeQuery();
            ObservableList<player_details> Player_List = getPlayerList(rsemps);
            return Player_List;
        } catch (SQLException e) {
            System.out.println("SQL select operation has been failed: " + e);
            throw e;
        }
    }

	private static ObservableList<player_details> getPlayerList(ResultSet rsemps)throws SQLException, ClassNotFoundException {
        ObservableList<player_details> Player_List = FXCollections.observableArrayList();
		
        while(rsemps.next()) {
        	player_details ps = new player_details();
        	ps.setPlayer_id(rsemps.getString("Player_Id"));
        	ps.setPlayer_name(rsemps.getString("Player_name"));
        	ps.setDate_Of_Birth(rsemps.getString("Date_Of_Birth"));
        	ps.setCity(rsemps.getString("City"));
        	ps.setRole(rsemps.getString("Role"));
        	Player_List.add(ps);
        } 
        return Player_List;
	}

	public static void insertplayer(String playerid, String playername, String date_of_birth,
			String city,String role, String user, String teamid)throws SQLException, ClassNotFoundException {
		Connection connection=SqliteConnection.connector();
		PreparedStatement prepared = null;
		String query = "Insert into Players values(?,?,?,?,?,?)";
		try {
			prepared = connection.prepareStatement(query);
			prepared.setString(1, playerid);
			prepared.setString(2, playername); prepared.setString(3, date_of_birth);
			prepared.setString(4, city); prepared.setString(5, role);
			prepared.setString(6, user);
			prepared.executeUpdate();
		}catch(SQLException e) {
			System.out.println("SQL select operation has been failed: " + e);
            throw e;
		}
		prepared = null;
		String Query = "Insert into Player_team values(?,?)";
		try {
			prepared = connection.prepareStatement(Query);
			prepared.setString(1, playerid);
			prepared.setString(2, teamid);
			prepared.executeUpdate();
		} catch(SQLException e) {
			System.out.println("SQL select operation has been failed: " + e);
            throw e;
		}
	}

	public static void deleteplayer(String playerid, String user) throws SQLException, ClassNotFoundException {
		Connection connection=SqliteConnection.connector();
		PreparedStatement prepared = null;
		String query = "Delete from Players where Player_Id = ? and Username = ?";
		try {
			prepared = connection.prepareStatement(query);
			prepared.setString(1,playerid);
			prepared.setString(2, user);
			prepared.executeUpdate();
		} catch(SQLException e) {
			System.out.println("SQL select operation has been failed: " + e);
            throw e;
		}
	}
	public static ObservableList<player_details> search_player(String Team_id, String user) throws SQLException, ClassNotFoundException {
		Connection connection=SqliteConnection.connector();
		PreparedStatement prepared = null;
		ResultSet rsemps = null;
		System.out.println("Search Player");
		String query = "Select Players.Player_Id, Player_name,Date_Of_Birth,City,Role FROM Players Inner Join Player_team on Players.Player_Id = Player_team.Player_Id where Team_Id = ? and username = ?";
		try {
			prepared = connection.prepareStatement(query);
			prepared.setString(1,Team_id);
			prepared.setString(2,user);
			rsemps = prepared.executeQuery();
			ObservableList<player_details> Player_List = getPlayerList(rsemps);
            return Player_List;
		} catch(SQLException e) {
			System.out.println("SQL select operation has been failed: " + e);
            throw e;
		}
	}
	
}
