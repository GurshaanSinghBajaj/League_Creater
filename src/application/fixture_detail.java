package application;

import javafx.beans.property.*;

public class fixture_detail {
	
	private StringProperty fixture_id;
	private StringProperty home_team;
	private StringProperty away_team;
	private StringProperty date;
	private StringProperty time;
	private StringProperty venue;
	private StringProperty username;
	
	public fixture_detail() {
		super();
		this.fixture_id = new SimpleStringProperty();
		this.home_team = new SimpleStringProperty();
		this.away_team = new SimpleStringProperty();
		this.date = new SimpleStringProperty();
		this.time = new SimpleStringProperty();
		this.venue = new SimpleStringProperty();
		this.username = new SimpleStringProperty();
	}
	
	public String getfixture_id() {
		return fixture_id.get();
	}
	public void setfixture_id(String fixture_id) {
		this.fixture_id.set(fixture_id);
	}
	public String gethome_team() {
		return home_team.get();
	}
	public void sethome_team(String home_team) {
		this.home_team.set(home_team);
	}
	public String getaway_team() {
		return away_team.get();
	}
	public void setaway_team(String away_team) {
		this.away_team.set(away_team);
	}
	public String getdate() {
		return date.get();
	}
	public void setdate(String date) {
		this.date.set(date);
	}
	public String gettime() {
		return time.get();
	}
	public void settime(String time) {
		this.time.set(time);
	}
	public String getvenue() {
		return venue.get();
	}
	public void setvenue(String venue) {
		this.venue.set(venue);
	}
	public String getUsername() {
		return username.get();
	}
	public void setUsername(String username) {
		this.username.set(username);
	}
	public StringProperty fixture_idProperty() {
		return fixture_id;
	}
	public StringProperty home_teamProperty() {
		return home_team;
	}
	public StringProperty dateProperty() {
		return date;
	}
	public StringProperty usernameProperty() {
		return username;
	}
	public StringProperty away_teamProperty() {
		return away_team;
	}
	public StringProperty timeProperty() {
		return time;
	}
	public StringProperty venueProperty() {
		return venue;
	}
}
