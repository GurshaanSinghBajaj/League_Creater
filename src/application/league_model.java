package application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.*;

public class league_model {
	
	Connection connection;
	public league_model() {
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
	
	public static ObservableList<league_details> league_table (String user) throws SQLException, ClassNotFoundException {
		
		Connection connection=SqliteConnection.connector()
;		PreparedStatement prepared = null;
        ResultSet rsemps = null;
        String query = "SELECT League_Id, League_Name, League_organiser,Sponsor, Prize_money,Start_date FROM Leagues where username = ?";
        try {
        	prepared = connection.prepareStatement(query);
			prepared.setString(1, user);
            rsemps = prepared.executeQuery();
            ObservableList<league_details> League_List = getLeagueList(rsemps);
            return League_List;
        } catch (SQLException e) {
            System.out.println("SQL select operation has been failed: " + e);
            throw e;
        }
    }

	private static ObservableList<league_details> getLeagueList(ResultSet rsemps)throws SQLException, ClassNotFoundException {
        ObservableList<league_details> League_List = FXCollections.observableArrayList();
		
        while(rsemps.next()) {
        	league_details ls = new league_details();
        	ls.setLeague_id(rsemps.getString("League_Id"));
        	ls.setLeague_name(rsemps.getString("League_Name"));
        	ls.setOrganizer(rsemps.getString("League_organiser"));
        	ls.setPrize_money(rsemps.getInt("Prize_money"));
        	ls.setSponsor(rsemps.getString("Sponsor"));
        	ls.setStart_date(rsemps.getString("Start_date"));
        	League_List.add(ls);
        } 
        return League_List;
	}

	public static void insertleague(String id, String name, String organiser, 
			String sponsor , String start, int prize, String user)throws SQLException, ClassNotFoundException {
		Connection connection=SqliteConnection.connector();
		PreparedStatement prepared = null;
		String query = "Insert into Leagues values(?,?,?,?,?,?,?)";
		try {
			prepared = connection.prepareStatement(query);
			prepared.setString(1, user);
			prepared.setString(2, id); prepared.setString(3, name);
			prepared.setString(4, organiser); prepared.setString(5, sponsor);
			prepared.setInt(6, prize); prepared.setString(7, start);
			prepared.executeUpdate();
		}catch(SQLException e) {
			System.out.println("SQL select operation has been failed: " + e);
            throw e;
		}
		
	}

	public static void deleteleague(String id, String user) throws SQLException, ClassNotFoundException {
		Connection connection=SqliteConnection.connector();
		PreparedStatement prepared = null;
		String query = "Delete from Leagues where League_Id = ? and Username = ?";
		try {
			prepared = connection.prepareStatement(query);
			prepared.setString(1,id);
			prepared.setString(2, user);
			prepared.executeUpdate();
		} catch(SQLException e) {
			System.out.println("SQL select operation has been failed: " + e);
            throw e;
		}
	}
}
