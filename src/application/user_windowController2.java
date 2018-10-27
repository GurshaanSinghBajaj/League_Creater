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
import application.team_details;
import application.team_model;

public class user_windowController2 implements Initializable {
	
	String x;

	@FXML
	private Label user_label;
	@FXML
	private Label user_text;
	@FXML
    private TableView<team_details> Team_Table;
    @FXML
    private TableColumn<team_details, String>  Team_ID;
    @FXML
    private TableColumn<team_details, String>  Team_Name;
    @FXML
    private TableColumn<team_details, String> Team_coach;
    @FXML
    private TableColumn<team_details, String> Team_owner;
    @FXML
    private TextField enter_teamid;
    @FXML
    private TextField enter_teamname;
    @FXML
    private TextField enter_teamcoach;
    @FXML
    private TextField enter_teamowner;
    @FXML
    private Button add_button;
    @FXML
    private Button delete_button;
    @FXML
    private TextField league_id;
    @FXML
    private Button search_button;
    @FXML
    private Button team_mainmenu;
    
    @FXML
    private void search() throws SQLException, ClassNotFoundException {
        try {
            ObservableList<team_details> Team_List = team_model.team_table(x);
            Team_Table.setItems(Team_List);
        } catch (SQLException e){
            System.out.println("Error occurred while getting employees information from DB.\n" + e);
            throw e;
        }
    }
    
    @FXML
    private void searchLeague() throws SQLException, ClassNotFoundException {
        try {
        	if(league_id.getText()!=null) {
        		ObservableList<team_details> Team_List = team_model.search_team(league_id.getText(),x);
        		Team_Table.setItems(Team_List);
        	}
        	else search();
        } catch (SQLException e){
            System.out.println("Error occurred while getting employees information from DB.\n" + e);
            throw e;
        }
    }
    
    @FXML
    private void insertTeam (ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
    	try {
    		team_model.insertteam(enter_teamid.getText(),enter_teamname.getText(),enter_teamcoach.getText(),
				enter_teamowner.getText(),x,league_id.getText());
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
    private void DeleteTeam (ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
    	try {
    		team_model.deleteteam(enter_teamid.getText(),x);
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
		Stage stage = (Stage) team_mainmenu.getScene().getWindow();
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
	    Team_ID.setCellValueFactory(cellData -> cellData.getValue().team_idProperty());
        Team_Name.setCellValueFactory(cellData -> cellData.getValue().team_nameProperty());
        Team_coach.setCellValueFactory(cellData -> cellData.getValue().coach_nameProperty());
        Team_owner.setCellValueFactory(cellData -> cellData.getValue().team_ownerProperty());
	}

}

