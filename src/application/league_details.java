package application;

import javafx.beans.property.*;

public class league_details {
	
	private StringProperty league_name;
	private StringProperty league_id;
	private StringProperty organizer;
	private StringProperty sponsor;
	private StringProperty start_date;
	private IntegerProperty prize_money;
	private StringProperty username;
	
	public league_details() {
		super();
		this.league_name = new SimpleStringProperty();
		this.league_id = new SimpleStringProperty();
		this.organizer = new SimpleStringProperty();
		this.sponsor = new SimpleStringProperty();
		this.start_date = new SimpleStringProperty();
		this.prize_money = new SimpleIntegerProperty();
	}
	
	public String getLeague_name() {
		return league_name.get();
	}
	public void setLeague_name(String league_name) {
		this.league_name.set(league_name);
	}
	public String getLeague_id() {
		return league_id.get();
	}
	public void setLeague_id(String league_id) {
		this.league_id.set(league_id);
	}
	public String getOrganizer() {
		return organizer.get();
	}
	public void setOrganizer(String organizer) {
		this.organizer.set(organizer);
	}
	public String getSponsor() {
		return sponsor.get();
	}
	public void setSponsor(String sponsor) {
		this.sponsor.set(sponsor);
	}
	public String getStart_date() {
		return start_date.get();
	}
	public void setStart_date(String start_date) {
		this.start_date.set(start_date);
	}
	public int getPrize_money() {
		return prize_money.get();
	}
	public void setPrize_money(int prize_money) {
		this.prize_money.set(prize_money);
	}
	public String getUsername() {
		return username.get();
	}
	public void setUsername(String username) {
		this.username.set(username);
	}
	public StringProperty league_idProperty() {
		return league_id;
	}
	public StringProperty league_nameProperty() {
		return league_name;
	}
	public StringProperty organizerProperty() {
		return organizer;
	}
	public StringProperty sponsorProperty() {
		return sponsor;
	}
	public StringProperty start_dateProperty() {
		return start_date;
	}
	public IntegerProperty prize_moneyProperty() {
		return prize_money;
	}
}
