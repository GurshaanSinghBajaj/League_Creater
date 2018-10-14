package application;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import application.league_details;
import application.league_model;

public class user_windowController implements Initializable {
	
	String x;

	@FXML
	private Label user_label;
	@FXML
	private Label user_text;
	@FXML
    private TableView<league_details> League_Table;
    @FXML
    private TableColumn<league_details, String>  League_ID;
    @FXML
    private TableColumn<league_details, String>  League_Name;
    @FXML
    private TableColumn<league_details, String> Organizer;
    @FXML
    private TableColumn<league_details, String> Sponsor;
    @FXML
    private TableColumn<league_details, String> Start_Date;
    @FXML
    private TableColumn<league_details, Integer> Prize_Money;
    @FXML
    private TextField enter_id;
    @FXML
    private TextField enter_name;
    @FXML
    private TextField enter_organiser;
    @FXML
    private TextField enter_sponsor;
    @FXML
    private TextField enter_start;
    @FXML
    private TextField enter_prize;
    @FXML
    private Button add_button;
    @FXML
    private Button delete_button;
    @FXML
    private Button team_button;
    @FXML
    private Button players_button;
    @FXML
    private Button fixture_button;
    
    @FXML
    private void search() throws SQLException, ClassNotFoundException {
        try {
            ObservableList<league_details> League_List = league_model.league_table(x);
            League_Table.setItems(League_List);
        } catch (SQLException e){
            System.out.println("Error occurred while getting employees information from DB.\n" + e);
            throw e;
        }
    }
    
    @FXML
    private void insertLeague (ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
    	try {
    		league_model.insertleague(enter_id.getText(),enter_name.getText(),enter_organiser.getText(),
				enter_sponsor.getText(),enter_start.getText(),Integer.parseInt(enter_prize.getText()),x);
    	} catch (SQLException e){
            System.out.println("Error occurred while getting employees information from DB.\n" + e);
            throw e;
        }
    	try {
			search();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
    	
    }
    
    @FXML
    private void DeleteLeague (ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
    	try {
    		league_model.deleteleague(enter_id.getText(),x);
    	} catch (SQLException e){
            System.out.println("Error occurred while getting employees information from DB.\n" + e);
            throw e;
        }
    	try {
			search();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
    	
    }

	
	public void GetUser(String user) {
		user_label.setText("Hello " + user);
		x=user;
		try {
			search();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		League_ID.setCellValueFactory(cellData -> cellData.getValue().league_idProperty());
        League_Name.setCellValueFactory(cellData -> cellData.getValue().league_nameProperty());
        Organizer.setCellValueFactory(cellData -> cellData.getValue().organizerProperty());
        Sponsor.setCellValueFactory(cellData -> cellData.getValue().sponsorProperty());
        Start_Date.setCellValueFactory(cellData -> cellData.getValue().start_dateProperty());
        Prize_Money.setCellValueFactory(cellData -> cellData.getValue().prize_moneyProperty().asObject());
	}

}
