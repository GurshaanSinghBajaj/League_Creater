package application;

import javafx.beans.property.*;

public class team_details {
	
	private StringProperty team_name;
	private StringProperty team_id;
	private StringProperty team_owner;
	private StringProperty coach_name;
	private StringProperty username;
	
	public team_details() {
		super();
		this.team_name = new SimpleStringProperty();
		this.team_id = new SimpleStringProperty();
		this.team_owner = new SimpleStringProperty();
		this.coach_name = new SimpleStringProperty();
		this.username = new SimpleStringProperty();
	}
	public String getTeam_name() {
		return team_name.get();
	}
	public void setTeam_name(String team_name) {
		this.team_name.set(team_name);
	}
	public String getTeam_id() {
		return team_id.get();
	}
	public void setTeam_id(String team_id) {
		this.team_id.set(team_id);
	}
	public String getTeam_owner() {
		return team_owner.get();
	}
	public void setTeam_owner(String team_owner) {
		this.team_owner.set(team_owner);;
	}
	public String getCoach_name() {
		return coach_name.get();
	}
	public void setCoach_name(String coach_name) {
		this.coach_name.set(coach_name);
	}
	public String getUsername() {
		return username.get();
	}
	public void setUsername(String username) {
		this.username.set(username);
	}
	public StringProperty team_nameProperty() {
		return team_name;
	}
	public StringProperty team_idProperty() {
		return team_id;
	}
	public StringProperty coach_nameProperty() {
		return coach_name;
	}
	public StringProperty usernameProperty() {
		return username;
	}
	public StringProperty team_ownerProperty() {
		return team_owner;
	}
}
