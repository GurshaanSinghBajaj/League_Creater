package application;

import javafx.beans.property.*;

public class player_details {
	
	private StringProperty player_name;
	private StringProperty player_id;
	private StringProperty date_of_birth;
	private StringProperty city;
	private StringProperty role;
	private StringProperty username;
	
	public player_details() {
		super();
		this.player_name = new SimpleStringProperty();
		this.player_id = new SimpleStringProperty();
		this.date_of_birth = new SimpleStringProperty();
		this.city = new SimpleStringProperty();
		this.role = new SimpleStringProperty();
		this.username = new SimpleStringProperty();
	}
	
	public String getPlayer_name() {
		return player_name.get();
	}
	public void setPlayer_name(String player_name) {
		this.player_name.set(player_name);
	}
	public String getPlayer_id() {
		return player_id.get();
	}
	public void setPlayer_id(String player_id) {
		this.player_id.set(player_id);
	}
	public String getDate_Of_Birth() {
		return date_of_birth.get();
	}
	public void setDate_Of_Birth(String date_of_birth) {
		this.date_of_birth.set(date_of_birth);
	}
	public String getCity() {
		return city.get();
	}
	public void setCity(String city) {
		this.city.set(city);
	}
	public String getRole() {
		return role.get();
	}
	public void setRole(String role) {
		this.role.set(role);
	}
	public String getUsername() {
		return username.get();
	}
	public void setUsername(String username) {
		this.username.set(username);
	}
	public StringProperty player_idProperty() {
		return player_id;
	}
	public StringProperty player_nameProperty() {
		return player_name;
	}
	public StringProperty date_of_birthProperty() {
		return date_of_birth;
	}
	public StringProperty cityProperty() {
		return city;
	}
	public StringProperty roleProperty() {
		return role;
	}
}

