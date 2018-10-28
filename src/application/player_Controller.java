package application;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import application.player_details;
import application.player_model;

public class player_Controller implements Initializable {
	
	String x;

	@FXML
	private Label user_label;
	@FXML
	private Label user_text;
	@FXML
    private TableView<player_details> Player_Table;
    @FXML
    private TableColumn<player_details, String>  Player_ID;
    @FXML
    private TableColumn<player_details, String>  Player_Name;
    @FXML
    private TableColumn<player_details, String> Date_Of_Birth;
    @FXML
    private TableColumn<player_details, String> City;
    @FXML
    private TableColumn<player_details, String> Role;
    @FXML
    private TextField enter_playerid;
    @FXML
    private TextField enter_playername;
    @FXML
    private TextField enter_dateofbirth;
    @FXML
    private TextField enter_city;
    @FXML
    private TextField enter_role;
    @FXML
    private Button add_button;
    @FXML
    private Button delete_button;
    @FXML
    private TextField team_id;
    @FXML
    private Button search_button;
    @FXML
    private Button player_mainmenu;
    
    @FXML
    private void search() throws SQLException, ClassNotFoundException {
        try {
            ObservableList<player_details> Player_List = player_model.player_table(x);
            Player_Table.setItems(Player_List);
        } catch (SQLException e){
            System.out.println("Error occurred while getting employees information from DB.\n" + e);
            throw e;
        }
    }
    
    @FXML
    private void searchTeam() throws SQLException, ClassNotFoundException {
        try {
        	String check = team_id.getText();
        	if(check.length() > 0) {
        		ObservableList<player_details> Player_List = player_model.search_player(team_id.getText(),x);
        		Player_Table.setItems(Player_List);
        	}
        	else search();
        } catch (SQLException e){
            System.out.println("Error occurred while getting employees information from DB.\n" + e);
            throw e;
        }
    }
    
    @FXML
    private void insertPlayer (ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
    	try {
    		player_model.insertplayer(enter_playerid.getText(),enter_playername.getText(),enter_dateofbirth.getText(),
				enter_city.getText(),enter_role.getText(),x,team_id.getText());
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
    private void DeletePlayer (ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
    	try {
    		player_model.deleteplayer(enter_playerid.getText(),x);
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
    private void backtomenu(ActionEvent event) {
    	Stage primaryStage = new Stage();
		FXMLLoader loader = new FXMLLoader();
		Pane root = null;
		try {
			root = loader.load(getClass().getResource("/application/Main_menu.fxml").openStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
		mainmenu_controller userController = (mainmenu_controller)loader.getController();
		System.out.println("Hello\n");
		userController.GetUser(x);
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.show();
		Stage stage = (Stage) player_mainmenu.getScene().getWindow();
	    stage.close();
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
	    Player_ID.setCellValueFactory(cellData -> cellData.getValue().player_idProperty());
        Player_Name.setCellValueFactory(cellData -> cellData.getValue().player_nameProperty());
        Date_Of_Birth.setCellValueFactory(cellData -> cellData.getValue().date_of_birthProperty());
        City.setCellValueFactory(cellData -> cellData.getValue().cityProperty());
        Role.setCellValueFactory(cellData -> cellData.getValue().roleProperty());
	}

}

